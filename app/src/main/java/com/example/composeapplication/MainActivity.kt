package com.example.composeapplication

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.composeapplication.databinding.ActivityMainBinding
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private var characterAdapter: CharacterAdapter? = null
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        characterAdapter = CharacterAdapter {
            Toast.makeText(this@MainActivity, it.name, Toast.LENGTH_SHORT).show()
        }
        binding.characterRecyclerview.adapter = characterAdapter

        val potterApi = Retrofit.Builder()
            .baseUrl((application as PotterApp).getBaseUrl())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .client(OkHttpProvider.getOkHttpClient())
            .build()
            .create(PotterApi::class.java)

        potterApi.getCharacters().enqueue(object : Callback<List<CharacterModel>> {
            override fun onFailure(call: Call<List<CharacterModel>>, t: Throwable) {
                showErrorState()
            }

            override fun onResponse(
                call: Call<List<CharacterModel>>,
                response: Response<List<CharacterModel>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    val characterList = response.body()!!
                    if (characterList.isEmpty()) {
                        showEmptyDataState()
                    } else {
                        showCharacterList(characterList)
                    }
                } else {
                    showErrorState()
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun showEmptyDataState() {
        with(binding) {
            characterRecyclerview.visibility = View.GONE
            progressBar.visibility = View.GONE
            textview.visibility = View.VISIBLE
            textview.text = getString(R.string.there_seems_to_be_no_data)
        }
    }

    private fun showCharacterList(characterList: List<CharacterModel>) {
        with(binding) {
            characterRecyclerview.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
            textview.visibility = View.GONE
            characterAdapter?.updateDataSet(characterList)
        }
    }

    private fun showErrorState() {
        with(binding) {
            characterRecyclerview.visibility = View.GONE
            progressBar.visibility = View.GONE
            textview.visibility = View.VISIBLE
            textview.text = getString(R.string.something_went_wrong)
        }

    }

}
