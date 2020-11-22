package ru.magzyumov.material.ui.main


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import ru.magzyumov.material.data.entity.FavouritesEntity
import ru.magzyumov.material.repository.ImageRepository
import javax.inject.Inject

class FirstViewModel @Inject constructor(
        private val repository: ImageRepository
): ViewModel() {

    private val imageList: MutableLiveData<List<String>> = MutableLiveData()
    private val favoriteImageList: MutableLiveData<List<String>> = MutableLiveData()

    fun updateImageList() {
        viewModelScope.launch (Dispatchers.IO){
            imageList.postValue(repository.getFileList())
        }
    }

    fun deleteImage(image: String) {
        viewModelScope.launch (Dispatchers.IO){
            when(repository.deleteImageFile(image)){
                true -> updateImageList()
            }
        }
    }

    fun createImageFile() = runBlocking {
        return@runBlocking repository.createImageFile()?.let {
            repository.getUriFromFile(it)
        }
    }

    fun getImageLiveData(): LiveData<List<String>> {
        return imageList
    }

    fun getFavoriteImageLiveData(): LiveData<List<String>> {
        return favoriteImageList
    }

    fun insertFavorite(favorite: String){
        Log.e("!!!!", "insertFavorite")
        viewModelScope.launch (Dispatchers.IO){
            when(repository.insertFavourite(FavouritesEntity(favorite))){
                true -> updateFavoriteImageList()
            }
        }
    }

    fun deleteFavorite(path: String){
        viewModelScope.launch (Dispatchers.IO){
            when(repository.deleteFavorite(path)){
                true -> updateFavoriteImageList()
            }
        }
    }

    fun updateFavoriteImageList() {
        Log.e("!!!!", "updateFavoriteImageList")
        val result: MutableList<String> = arrayListOf()
        viewModelScope.launch (Dispatchers.IO){
            repository.getFavouriteImages().forEach{
                result.add(it.path)
            }
            favoriteImageList.postValue(result)
        }
    }
}