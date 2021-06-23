package com.felipepolo.superheroapp.ui.HeroListActivity.Fragments.vsHeroes

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.felipepolo.superheroapp.R
import com.felipepolo.superheroapp.databinding.FragmentHerosVsBinding
import com.felipepolo.superheroapp.ui.HeroListActivity.HeroListViewModel
import com.felipepolo.superheroapp.ui.HeroListActivity.MainHeroListActivity
import com.squareup.picasso.Picasso

class FragmentHerosVs : Fragment() {


    private lateinit var binding: FragmentHerosVsBinding

    lateinit var model: HeroListViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        model = (context as MainHeroListActivity).viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHerosVsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupHero1()
        setupHero2()
        setupBtns()
        setupWinner()
    }

    private fun setupWinner() {

        model.heroWinner.observe(viewLifecycleOwner, Observer {
            val dialog = AlertDialog.Builder(requireContext()).create()
            var message = "NONE !, perhaps these heroes were so strong that their abilities cannot be calculated!"
            var title = "And the winner is..."
            if (it != null) {
                title = "${it.name} is the WINNER"
                message = "${it.name} has proven to be a superior hero"
            }
            dialog.setTitle(title)
            dialog.setMessage(message)
            dialog.show()
        })
    }


    private fun setupBtns() {
        model.herosReadyToFight.observe(viewLifecycleOwner, Observer {
            setBtnsListeners(it)
            setHeroview(it)
        })
    }

    private fun setHeroview(active: Boolean) {
        if(!active) {
            binding.ivfHero.setImageResource(R.drawable.unknowhero)
            binding.ivfHero2.setImageResource(R.drawable.unknowhero)
            binding.tvfHeroPower.text = "Power: ?"
            binding.tvfHeroPower2.text = "Power: ?"
            binding.tvfHeroSpeed.text = "Speed: ?"
            binding.tvfHeroSpeed2.text = "Speed: ?"
        }
    }

    fun setBtnsListeners(active: Boolean){
        if(active){
            binding.bFight.setOnClickListener { model.onBtnBattleClickListener("power") }
            binding.bRun.setOnClickListener { model.onBtnBattleClickListener("speed") }
        }else{
            binding.bFight.setOnClickListener {  }
            binding.bRun.setOnClickListener {  }
        }
    }

    private fun setupHero1() {
        model.hero1.observe(viewLifecycleOwner, Observer {
            Picasso.get().load(it.image.url).into(binding.ivfHero)
            binding.tvfHeroPower.text = "Power: ${it.powerstats.power}"
            binding.tvfHeroSpeed.text = "Speed: ${it.powerstats.speed}"
        })
    }

    private fun setupHero2() {
        model.hero2.observe(viewLifecycleOwner, Observer {
            Picasso.get().load(it.image.url).into(binding.ivfHero2)
            binding.tvfHeroPower2.text = "Power: ${it.powerstats.power}"
            binding.tvfHeroSpeed2.text = "Speed: ${it.powerstats.speed}"
        })
    }

}