package com.example.composeapplication.data.util

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.composeapplication.data.local.BeerDatabase
import com.example.composeapplication.data.local.model.BeerEntity
import com.example.composeapplication.data.mappers.toBeerEntity
import com.example.composeapplication.data.remote.BeerApi
import retrofit2.HttpException
import java.io.IOException

/**
 *  Central control unit of paging.
 *  Controls paging logic that puts loaded items from remote api to local database and
 *  then forwards just the page we want to load in ui.20:34
 */
@OptIn(ExperimentalPagingApi::class)
class BeerRemoteMediator(
    private val beerDb: BeerDatabase,
    private val beerApi: BeerApi,
) : RemoteMediator<Int, BeerEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, BeerEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        1
                    } else {
                        (lastItem.id / state.config.pageSize) + 1
                    }

                }
            }

            val beers = beerApi.getBeers(
                page = loadKey,
                pageCount = state.config.pageSize
            )

            beerDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    beerDb.dao.clearAll()
                }
                val beerEntity = beers.map { it.toBeerEntity() }

                beerDb.dao.upsertAll(beerEntity)
            }

            MediatorResult.Success(
                endOfPaginationReached = beers.isEmpty()
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}