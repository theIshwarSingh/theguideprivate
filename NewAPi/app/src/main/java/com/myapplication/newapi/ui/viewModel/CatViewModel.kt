package com.myapplication.newapi.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapplication.newapi.model.BreedImagesDataClass
import com.myapplication.newapi.model.BreedsDataClass
import com.myapplication.newapi.network.CatRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class CatViewModel :ViewModel(){

    val breedsLiveData = MutableLiveData<List<BreedsDataClass>>()
    val imagesLiveData = MutableLiveData<List<BreedImagesDataClass>>()
    private val catRepository = CatRepository()

    fun getCatRepository(){
        viewModelScope.launch(Dispatchers.IO) {
            val result = catRepository.getBreeds()
            try {
                if (result.isSuccessful){
                    breedsLiveData.postValue(result.body())
                }
            }
            catch (e:Exception){
                Log.e("getCatRepository","${e.message.toString()}")
            }
        }
    }

    fun getImages(){
        viewModelScope.launch(Dispatchers.IO) {
            val result = catRepository.getbreedsImages()
            try {
                if (result.isSuccessful){
                    imagesLiveData.postValue(result.body())
                }
            }catch (e:Exception){
                Log.e("getImages","${e.message.toString()}")
            }
        }
    }
}