package com.felipepolo.superheroapp.ui.HeroListActivity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.widget.SearchView
import com.felipepolo.superheroapp.R
import com.felipepolo.superheroapp.TodoApplication
import com.felipepolo.superheroapp.databinding.ActivityMainBinding
import javax.inject.Inject

class MainHeroListActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: HeroListViewModel

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as TodoApplication).appComponent
            .MainHeroListComponent().create(this)
            .inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        setSupportActionBar(binding.toolBar)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.template_toolbar,menu)

        val searchHero = menu?.findItem(R.id.searchHero)?.actionView as SearchView
        searchHero.queryHint = "Search a Hero"

        searchHero.setOnCloseListener {
            if(searchHero.query.toString().isEmpty()){
                viewModel.returnCurrentList()
            }
            searchHero.clearFocus()
            false
        }
        searchHero.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.getListHerosByName(query?:"")
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean { return true }
        })

        return super.onCreateOptionsMenu(menu)
    }

}