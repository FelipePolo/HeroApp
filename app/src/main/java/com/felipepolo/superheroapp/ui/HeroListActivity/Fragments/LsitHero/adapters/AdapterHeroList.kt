package com.felipepolo.superheroapp.ui.HeroListActivity.Fragments.LsitHero.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.felipepolo.superheroapp.R
import com.felipepolo.superheroapp.data.userRemoteDataSource.HeroApiRetrofitServices.dataStructure.Hero
import com.felipepolo.superheroapp.databinding.TemplateHeroItemListBinding
import com.squareup.picasso.Picasso

class AdapterHeroList(
    var heros: ArrayList<Hero>,
    var onItemHeroClickListener: (hero: Hero) -> Unit,
    var onBtnVsClickListener: (hero: Hero) -> Unit
) : RecyclerView.Adapter<AdapterHeroList.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.template_hero_item_list, parent, false)
        val ViewHolder = ViewHolder(itemView,onItemHeroClickListener,onBtnVsClickListener)
        return ViewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) { holder.bind(heros[position]) }
    override fun getItemCount(): Int  = heros.count()

    class ViewHolder(
        itemView: View,
        private val onItemHeroClickListener: (hero: Hero) -> Unit,
        private val onBtnVsClickListener: (hero: Hero) -> Unit) : RecyclerView.ViewHolder(itemView){

        private val binding = TemplateHeroItemListBinding.bind(itemView)

        fun bind(hero: Hero){
            // block of stats(attack, defense, healt, speed)
            binding.tvhp.text =  hero.powerstats.durability
            binding.tvattack.text = hero.powerstats.power
            binding.tvdefense.text = hero.powerstats.strength
            binding.tvspeed.text = hero.powerstats.speed

            // name and image
            binding.tvHeroName.text = hero.name
            Picasso.get()
                    .load(hero.image.url)
                    .into(binding.ivHero)

            // clickListeners
            binding.heroItem.setOnClickListener{ onItemHeroClickListener(hero)}
            binding.bVs.setOnClickListener{onBtnVsClickListener(hero)}
        }
    }
}