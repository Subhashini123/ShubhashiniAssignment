package com.example.shubhashiniassignment.views

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shubhashiniassignment.AlbumApplication
import com.example.shubhashiniassignment.R
import com.example.shubhashiniassignment.databinding.FragmentAlbumListBinding
import com.example.shubhashiniassignment.model.AlbumModel
import com.example.shubhashiniassignment.viewmodel.AlbumListViewModel
import javax.inject.Inject

class AlbumListFragment : Fragment(), AlbumAdapter.RecordSelectListener {
    private lateinit var binding: FragmentAlbumListBinding
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    var recordsLastId = 1

    private val viewModel by lazy {
        viewModelFactory.create(
            AlbumListViewModel::class.java
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as AlbumApplication).appComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentAlbumListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.checkIfDbIsEmpty()
        viewModel.setProgressVisibility(true)
        observeData()
    }

    private fun observeData() {
        viewModel.noDataStatusLiveData.observe(viewLifecycleOwner) {
            if (it) {
                viewModel.fetchPhotoAlbum()
            } else {
                viewModel.catchCarsPageWise()
            }
        }

        viewModel.progressLiveData.observe(viewLifecycleOwner) {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }

        viewModel.albumLiveData.observe(viewLifecycleOwner) {
            setupRecyclerView(it)
            viewModel.setProgressVisibility(false)
        }
    }

    private fun setupRecyclerView(data: List<AlbumModel.AlbumModelItem>) {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = AlbumAdapter(data, this@AlbumListFragment)
        }
    }

    override fun onRecordSelect(albumModel: AlbumModel.AlbumModelItem) {
        findNavController().navigate(
            R.id.detailedFragment,
            bundleOf("ID" to albumModel.id, "TITLE" to albumModel.title, "URL" to albumModel.url)
        )
    }
}