package com.felipepolo.superheroapp.ui.HeroDetailActivity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import com.felipepolo.superheroapp.data.userRemoteDataSource.HeroApiRetrofitServices.dataStructure.Hero
import com.felipepolo.superheroapp.databinding.ActivityHeroDetailBinding
import com.squareup.picasso.Picasso

class HeroDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityHeroDetailBinding
    lateinit var hero: Hero

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeroDetailBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        setSupportActionBar(binding.DetailToolbar)

        hero = intent.getSerializableExtra("hero") as Hero

        setupImage()
        setupToolbar()
        setupPowerStast()
        setupBiography()
        setupAppearance()
        setupWork()
        setupConections()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }


    private fun setupToolbar() {
        supportActionBar?.title = hero.name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    @SuppressLint("SetTextI18n")
    private fun setupPowerStast(){
        binding.tvFullName.text = "Full Name : ${hero.biography.fullName}"
        binding.tvHeroName.text = "Name: ${hero.name}"
        binding.tvIntelligence.text = "Intelligence: ${hero.powerstats.intelligence}"
        binding.tvStrength.text = "Streght: ${hero.powerstats.strength}"
        binding.tvspeed.text = "Speed: ${hero.powerstats.speed}"
        binding.tvdurability.text = "Durability: ${hero.powerstats.durability}"
        binding.tvpower.text = "Power: ${hero.powerstats.power}"
        binding.tvcombat.text = "Combat: ${hero.powerstats.combat}"
    }

    @SuppressLint("SetTextI18n")
    private fun setupBiography(){
        binding.tvAlterEgos.text = "Alter Egos: ${hero.biography.alterEgos}"
        binding.tvFirstAppearance.text = "First Appearance: ${hero.biography.Firstapperance}"
        binding.tvPublisher.text = "Publisher: ${hero.biography.publisher}"
        binding.tvAlignment.text = "Alignment ${hero.biography.alignment}"
    }

    @SuppressLint("SetTextI18n")
    private fun setupAppearance(){
        binding.tvgender.text = "Gender: ${hero.appearance.gender}, Race: ${hero.appearance.race}"
        binding.tvEyeColor.text = "Eye Color: ${hero.appearance.eyeColor}"
        binding.tvHairColor.text = "Hair Color ${hero.appearance.hairColor}"
    }

    @SuppressLint("SetTextI18n")
    private fun setupWork(){
        binding.tvOcupation.text = "Occupation: ${hero.work.occupation}"
    }
    @SuppressLint("SetTextI18n")
    private fun setupConections(){
        binding.tvAffiliation.text = "Group Affiliation: ${hero.connections.affiliation}"
        binding.tvRelatices.text = "Relatives: ${hero.connections.relatives}"
    }

    private fun setupImage(){
        Picasso.get().load(hero.image.url).into(binding.ivHero)
    }
}