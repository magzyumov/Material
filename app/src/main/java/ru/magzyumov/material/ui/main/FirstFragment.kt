package ru.magzyumov.material.ui.main

import android.app.Activity.RESULT_OK
import android.app.Application
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import ru.magzyumov.material.BuildConfig
import ru.magzyumov.material.R
import ru.magzyumov.material.common.Constants.RequestCode.Companion.REQUEST_TAKE_PHOTO
import ru.magzyumov.material.common.helpers.SnackBarHelper
import ru.magzyumov.material.common.helpers.StorageHelper
import ru.magzyumov.material.databinding.FragmentFirstBinding
import ru.magzyumov.material.ui.base.BaseFragment
import ru.magzyumov.material.utils.ViewModelProviderFactory
import java.io.File
import java.io.IOException
import javax.inject.Inject


class FirstFragment: BaseFragment(R.layout.fragment_first), ImageAdapter.Interaction{
    override val binding by viewBinding(FragmentFirstBinding::bind)

    private var imagesList: List<String> = arrayListOf()
    private lateinit var imagesAdapter: ImageAdapter
    private lateinit var firstViewModel: FirstViewModel

    @Inject
    lateinit var snackBarHelper: SnackBarHelper

    @Inject
    lateinit var storageHelper: StorageHelper

    @Inject
    lateinit var viewmodelProviderFactory: ViewModelProviderFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentInteraction.changePageTitle("First fragment")

        binding.fab.setOnClickListener {
            dispatchTakePictureIntent()
        }

        setupViewModel()
        initRecyclerView()
        observerLiveData()
    }

    private fun setupViewModel() {
        firstViewModel =
                ViewModelProvider(this, viewmodelProviderFactory).get(FirstViewModel::class.java)
    }

    private fun observerLiveData() {
        firstViewModel.getImageLiveData().observe(viewLifecycleOwner, { listOfImages ->
            listOfImages?.let {
                imagesList = it
                imagesAdapter.swap(it)
            }
        })
    }

    private fun initRecyclerView() {
        binding.recyclerViewMain.apply {
            imagesAdapter = ImageAdapter(imagesList, this@FirstFragment)
            layoutManager = GridLayoutManager(
                this@FirstFragment.requireContext(),
                3,
                GridLayoutManager.VERTICAL,
                false
            )
            adapter = imagesAdapter
            val swipe = ItemTouchHelper(initSwipeToDelete())
            swipe.attachToRecyclerView(binding.recyclerViewMain)
        }
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.recyclerViewMain)
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(requireActivity().packageManager)?.also {
                // Create the File where the photo should go
                val photoFile: File? = try {
                    storageHelper.createImageFile()
                } catch (ex: IOException) {
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
                snackBarHelper.show(binding.root, "Picture added")
                firstViewModel.updateImageList()
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

    private fun initSwipeToDelete(): ItemTouchHelper.SimpleCallback {
        //Swipe recycler view items on RIGHT
        return object : ItemTouchHelper.SimpleCallback(
                0, ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                //noteViewModel.delete(allNotes.get(position))
            }
        }
    }
}