package com.example.composeapplication

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.composeapplication.databinding.RowCharacterViewholderBinding

class CharacterAdapter(
    val onCharacterClick: (CharacterModel) -> Unit
) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private val characterList = mutableListOf<CharacterModel>()

    fun updateDataSet(characterList: List<CharacterModel>) {
        this.characterList.clear()
        this.characterList.addAll(characterList)
        notifyDataSetChanged()
    }

    inner class CharacterViewHolder(private val itemBinding: RowCharacterViewholderBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(character: CharacterModel) {
            val context = itemBinding.root.context
            with(itemBinding) {
                characterNameTextview.text = String.format(
                    context.getString(R.string.name_placeholder),
                    character.name
                )
                characterRoleTextview.text = String.format(
                    context.getString(R.string.role_placeholder),
                    character.house?.capitalize() ?: "Unknown"
                )
                orderOfThePhoenixTextview.text = String.format(
                    context.getString(R.string.orderOfThePhoenix_placeholder),
                    character.wizard
                )

                characterInfoContainer.setOnClickListener {
                    onCharacterClick(character)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = RowCharacterViewholderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CharacterViewHolder(view)
    }


    @SuppressLint("DefaultLocale")
    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characterList[position]
        holder.bind(character)
    }

    override fun getItemCount() = characterList.size
}