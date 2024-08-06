package com.example.youtubeapp.ui.fragments.playlist

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtubeapp.databinding.FragmentPlaylistBinding
import com.example.youtubeapp.ui.fragments.base.BaseFragment
import com.example.youtubeapp.utils.Resource

class PlaylistFragment : BaseFragment<FragmentPlaylistBinding>(FragmentPlaylistBinding::inflate) {

    private val viewModel by viewModels<PlaylistViewModel>()
    private val playlistAdapter: PlaylistAdapter by lazy { PlaylistAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObserver()

        val videoId = arguments?.getString("videoId") ?: return
        viewModel.getPlaylists(videoId)

        binding.imgBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun setupObserver() {
        viewModel.playlists.resourceHandler(
            onSuccess = { data ->
                playlistAdapter.submitList(data.items)
                if (data.items.isNotEmpty()) {
                    val item = data.items[0]
                    binding.tvTitle.text = item.snippet.title
                    binding.tvDescription.text = item.snippet.description
                    binding.tvVideoCount.text = "${data.items.size} video series"
                }
            },
            state = { state ->
                binding.progressBar.isVisible = state is Resource.Loading
            }
        )
    }


    private fun setupRecyclerView() = with(binding.rvVideo) {
        layoutManager = LinearLayoutManager(context)
        adapter = playlistAdapter
    }
}