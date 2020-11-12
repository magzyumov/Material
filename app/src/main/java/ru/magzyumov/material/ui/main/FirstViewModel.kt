package ru.magzyumov.material.ui.main


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.magzyumov.material.repository.ImageRepository
import javax.inject.Inject

class FirstViewModel @Inject constructor(
        private val repository: ImageRepository
): ViewModel() {

    private val imageList: MutableLiveData<List<String>> = MutableLiveData()

    fun updateImageList() {
        imageList.postValue(repository.getFileList())
    }

    fun getImageLiveData(): LiveData<List<String>> {
        updateImageList()
        return imageList
    }

}