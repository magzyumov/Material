package ru.magzyumov.material.ui.main


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import ru.magzyumov.material.repository.ImageRepository
import javax.inject.Inject

class FirstViewModel @Inject constructor(
        private val repository: ImageRepository
): ViewModel() {

    private val imageList: MutableLiveData<List<String>> = MutableLiveData()

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

}