package ru.magzyumov.material.ui.main

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import ru.magzyumov.material.R
import ru.magzyumov.material.common.Constants.RequestCode.Companion.REQUEST_TAKE_PHOTO
import ru.magzyumov.material.common.helpers.SnackBarHelper
import ru.magzyumov.material.common.helpers.StorageHelper
import ru.magzyumov.material.databinding.FragmentMainBinding
import ru.magzyumov.material.ui.base.BaseFragment
import ru.magzyumov.material.utils.ViewModelProviderFactory
import javax.inject.Inject


class FirstFragment: BaseFragment(R.layout.fragment_main), ImageAdapter.Interaction{
    override val binding by viewBinding(FragmentMainBinding::bind)

    private var imagesList: List<String> = arrayListOf()
    private lateinit var imagesAdapter: ImageAdapter
    private lateinit var firstViewModel: FirstViewModel

    @Inject
    lateinit var snackBarHelper: SnackBarHelper

    @Inject
    lateinit var storageHelper: StorageHelper

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

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
                ViewModelProvider(this, viewModelProviderFactory).get(FirstViewModel::class.java)
    }

    private fun observerLiveData() {
        firstViewModel.getImageLiveData().observe(viewLifecycleOwner, { listOfImages ->
            listOfImages?.let {
                imagesList = it
                imagesAdapter.swap(it)
            }
        })
        firstViewModel.updateImageList()
    }

    private fun initRecyclerView() {
        binding.recyclerViewAuto.apply {
            imagesAdapter = ImageAdapter(imagesList, this@FirstFragment)

            adapter = imagesAdapter
            val swipe = ItemTouchHelper(initSwipeToDelete())
            swipe.attachToRecyclerView(binding.recyclerViewAuto)
        }

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.recyclerViewAuto)
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(requireActivity().packageManager)?.also {
                firstViewModel.createImageFile()?.let {
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, it)
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            snackBarHelper.show(binding.root, "Picture added")
            firstViewModel.updateImageList()
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
                firstViewModel.deleteImage(imagesList[position])
            }
        }
    }
}