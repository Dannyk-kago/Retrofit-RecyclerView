package com.example.retrofitdemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitdemo.network.ApiClient
import com.example.retrofitdemo.network.Character
import kotlinx.coroutines.launch

class MainViewModel(private val repository:Repository = Repository(ApiClient.apiService)): ViewModel() {
    private var _charactersLiveData = MutableLiveData<ScreenState<List<Character>?>>()
    val characterLiveData: LiveData<ScreenState<List<Character>?>>
    get() = _charactersLiveData

    init {
        fetchCharacter()
    }

    private fun fetchCharacter(){
        _charactersLiveData.postValue(ScreenState.Loading(null))
        viewModelScope.launch {
            try {
                val client = repository.getCharacters("1")
                _charactersLiveData.postValue(ScreenState.Success(client.result))
            }catch (e:Exception){
                _charactersLiveData.postValue(ScreenState.Error(e.message.toString(),null))
            }
        }

    }

}