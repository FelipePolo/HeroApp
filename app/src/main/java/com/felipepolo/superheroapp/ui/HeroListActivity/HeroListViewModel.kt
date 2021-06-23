package com.felipepolo.superheroapp.ui.HeroListActivity

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felipepolo.superheroapp.data.userRemoteDataSource.HeroApiRetrofitServices.dataStructure.Hero
import com.felipepolo.superheroapp.data.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class HeroListViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val contextScope = viewModelScope.coroutineContext + Dispatchers.Main

    private val _newHeroAddedEvent: MutableLiveData<Hero> = MutableLiveData()
    val newHeroAddedEvent: LiveData<Hero> = _newHeroAddedEvent

    private val _errorEvent: MutableLiveData<String> = MutableLiveData()
    val errorEvent : LiveData<String> = _errorEvent

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> = _loading

    private val _heroList: MutableLiveData<ArrayList<Hero>> = MutableLiveData(ArrayList())
    val heroList: LiveData<ArrayList<Hero>> = _heroList

    private val _hero1: MutableLiveData<Hero> = MutableLiveData()
    val hero1: LiveData<Hero> = _hero1

    private val _hero2: MutableLiveData<Hero> = MutableLiveData()
    val hero2: LiveData<Hero> = _hero2

    private val _herosReadyToFight: MutableLiveData<Boolean> = MutableLiveData(false)
    val herosReadyToFight: LiveData<Boolean> = _herosReadyToFight

    private val _heroWinner: MutableLiveData<Hero?> = MutableLiveData()
    val heroWinner: LiveData<Hero?> = _heroWinner

    private var figthFinished = true
    private var newHero1 = true
    private var newHero2 = true

    private var savedCurrentSimpleList = ArrayList<Hero>()

    fun getSimpleListHeros() {
        if (heroList.value?.isEmpty() == true) {
            getHeros()
        }
    }

    fun getHeros() {
        CoroutineScope(contextScope).launch {
            try {
                _loading.value = true
                val hero = userRepository.getHeroById((heroList.value!!.size + 1).toString())
                _newHeroAddedEvent.value = hero
                heroList.value?.add(hero)
                _loading.value = false
            }catch (e: Exception){
                _loading.value = false
                _errorEvent.value = e.message
            }
        }
    }

    fun getListHerosByName(name: String) {
        CoroutineScope(contextScope).launch {
            try {
                savedCurrentSimpleList = _heroList.value?: ArrayList()
                _heroList.value = ArrayList()
                _loading.value = true
                val heroList = userRepository.getHerosByName(name)
                if (heroList.isNotEmpty()){
                    _heroList.value = heroList
                }else{
                    _loading.value = false
                    _errorEvent.value = "character with given name not found"
                }
            }catch (e: Exception){
                _loading.value = false
                _errorEvent.value = e.message
            }

        }
    }


    fun onBtnVsClickListener(hero: Hero) {
        if (figthFinished) {
            assignSpaceToHero(hero)
            if (!newHero1 && !newHero2) {
                _herosReadyToFight.value = true
                figthFinished = false
            }
        }else{
            _errorEvent.value = "the heroes are waiting to compete in the test of your choice"
        }
    }

    private fun assignSpaceToHero(hero: Hero) {
        if (newHero1) {
            _hero1.value = hero
            newHero1 = false
        } else if(newHero2 && hero != _hero1.value) {
            _hero2.value = hero
            newHero2 = false
        }else{
            _errorEvent.value = "you can't select the same hero"
        }
    }

    fun onBtnBattleClickListener(mode: String) {
        _herosReadyToFight.value = false
        if (mode == "speed") {
            _heroWinner.value =
                Winner(hero1.value!!.powerstats.speed, hero2.value!!.powerstats.speed)
        } else {
            _heroWinner.value =
                Winner(hero1.value!!.powerstats.power, hero2.value!!.powerstats.power)
        }
        newHero1 = true
        newHero2 = true
        figthFinished = true
    }

    private fun Winner(statHero1: String?, statHero2: String?): Hero? {
        if (statHero1 != "null" && statHero2 != "null") {
            if (statHero1!!.toInt() > statHero2!!.toInt()) {
                return hero1.value
            } else if (statHero1.toInt() < statHero2.toInt()) {
                return hero2.value
            }
        }
        return null
    }

    fun returnCurrentList() {
        _heroList.value = savedCurrentSimpleList
    }
}