package ru.magzyumov.material.ui.main

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.app.Application
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
import android.content.Intent.FLAG_GRANT_WRITE_URI_PERMISSION
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log

import android.view.*
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.google.android.material.snackbar.Snackbar
import ru.magzyumov.material.App
import ru.magzyumov.material.BuildConfig
import ru.magzyumov.material.R
import ru.magzyumov.material.common.Constants.RequestCode.Companion.REQUEST_IMAGE_CAPTURE
import ru.magzyumov.material.common.Constants.RequestCode.Companion.REQUEST_IMAGE_STORE
import ru.magzyumov.material.common.Constants.RequestCode.Companion.REQUEST_TAKE_PHOTO
import ru.magzyumov.material.data.ImageList
import ru.magzyumov.material.databinding.FragmentFirstBinding
import ru.magzyumov.material.di.services.BaseServices
import ru.magzyumov.material.ui.base.BaseFragment
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


class FirstFragment: BaseFragment(R.layout.fragment_first), ImageAdapter.Interaction{
    override val binding by viewBinding(FragmentFirstBinding::bind)

    private var imagesList: List<String> = arrayListOf()
    private lateinit var imagesAdapter: ImageAdapter
    private var currentPhotoPath: String = ""

    @Inject
    lateinit var baseService: BaseServices

    @Inject
    lateinit var application: Application

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentInteraction.changePageTitle("First fragment")

        binding.fab.setOnClickListener {
            dispatchTakePictureIntent()
        }

        setupData()
        initRecyclerView()
    }

    private fun setupData(){
        val list: MutableList<String> = ArrayList()
        val storageDir: File? = application.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        storageDir?.let {fileList ->
            fileList.listFiles().forEach {file ->
                val photoURI: String = Uri.fromFile(file).toString()
                list.add(photoURI)
            }
            imagesList = list
        }
    }

    private fun initRecyclerView() {
        binding.recyclerViewMain.apply {
            imagesAdapter = ImageAdapter(imagesList, this@FirstFragment)
            imagesAdapter.swap(imagesList)
            layoutManager = GridLayoutManager(
                this@FirstFragment.requireContext(),
                3,
                GridLayoutManager.VERTICAL,
                false
            )
            adapter = imagesAdapter
        }
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.recyclerViewMain)
    }

    private fun createImageFile(): File {

        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = application.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        ).apply {
            currentPhotoPath = absolutePath
        }
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(requireActivity().packageManager)?.also {
                // Create the File where the photo should go
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    Log.e("!!!", ex.localizedMessage)
                    null
                }
                // Continue only if the File was successfully created
                photoFile?.also {photo ->
                    val photoURI: Uri = FileProvider.getUriForFile(
                            requireContext(),
                            BuildConfig.APPLICATION_ID + ".fileprovider",
                            photo
                    )

                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
                }
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            data?.let {
                baseService.snackBarHelper.show(binding.root, "Picture added")
            }
        }
    }

    override fun onLikeSelected(position: Int, item: String) {

    }

    override fun onShareSelected(item: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, item)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }
}