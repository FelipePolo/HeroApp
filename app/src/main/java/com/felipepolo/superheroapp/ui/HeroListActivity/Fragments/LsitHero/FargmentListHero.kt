package com.felipepolo.superheroapp.ui.HeroListActivity.Fragments.LsitHero

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.felipepolo.superheroapp.R
import com.felipepolo.superheroapp.data.userRemoteDataSource.HeroApiRetrofitServices.dataStructure.Hero
import com.felipepolo.superheroapp.databinding.FragmentFargmentListHeroBinding
import com.felipepolo.superheroapp.ui.HeroDetailActivity.HeroDetailActivity
import com.felipepolo.superheroapp.ui.HeroListActivity.Fragments.LsitHero.adapters.AdapterHeroList
import com.felipepolo.superheroapp.ui.HeroListActivity.HeroListViewModel
import com.felipepolo.superheroapp.ui.HeroListActivity.MainHeroListActivity
import javax.inject.Inject

class FargmentListHero : Fragment() {

    lateinit var model: HeroListViewModel

    lateinit var binding: FragmentFargmentListHeroBinding
    lateinit var adapter: AdapterHeroList


    override fun onAttach(context: Context) {
        super.onAttach(context)
        model = (context as MainHeroListActivity).viewModel
        model.getSimpleListHeros()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFargmentListHeroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupLoading()
        setupNestedScrollView()

        ifNeedHeros()
        showErrors()
    }

    private fun showErrors() {
        model.errorEvent.observe(viewLifecycleOwner, Observer {
            if(it.isNotEmpty()) {
                val dialog = AlertDialog.Builder(requireContext()).create()
                dialog.setTitle("UPS...")
                dialog.setMessage(it)
                dialog.show()
            }
        })
    }

    private fun setupNestedScrollView() {
        binding.scrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY == v[0].measuredHeight - v.measuredHeight) {
                model.getHeros()
            }
        })
    }


    private fun ifNeedHeros() {
        model.newHeroAddedEvent.observe(viewLifecycleOwner, Observer {
            if (adapter.heros.size < 5) {
                model.getHeros()
            }
        })
    }

    private fun setupRecyclerView() {
        adapter = AdapterHeroList(
            ArrayList(),
            {hero -> handleItemListClickListener(hero)},
            model::onBtnVsClickListener,
        )
        binding.rvHeroesList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvHeroesList.adapter = adapter

        model.newHeroAddedEvent.observe(viewLifecycleOwner, Observer {
            adapter.notifyItemInserted(model.heroList.value!!.size)
        })

        model.heroList.observe(viewLifecycleOwner, Observer {
            adapter.heros = it
            adapter.notifyDataSetChanged()
        })
    }

    private fun setupLoading() {
        model.loading.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.LaLoading.visibility = View.VISIBLE
                binding.LaLoading.playAnimation()
            } else {
                binding.LaLoading.cancelAnimation()
                binding.LaLoading.visibility = View.GONE
            }
        })
    }

    private fun handleItemListClickListener(hero: Hero){
        val intent = Intent(requireContext(),HeroDetailActivity::class.java)
        intent.putExtra("hero",hero)
        startActivity(intent)
    }


}