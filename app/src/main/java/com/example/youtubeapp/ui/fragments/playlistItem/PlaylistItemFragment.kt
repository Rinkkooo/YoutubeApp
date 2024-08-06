package com.example.youtubeapp.ui.fragments.playlistItem

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtubeapp.R
import com.example.youtubeapp.databinding.FragmentPlaylistItemBinding
import com.example.youtubeapp.ui.fragments.base.BaseFragment
import com.example.youtubeapp.utils.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlaylistItemFragment :
    BaseFragment<FragmentPlaylistItemBinding>(FragmentPlaylistItemBinding::inflate) {

    private val viewModel by viewModel<PlaylistItemViewModel>()
    private val videoAdapter: PlaylistItemAdapter by lazy {
        PlaylistItemAdapter { video ->
            navigateToPlaylistFragment(video.id)
        }
    }

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        viewModel.getPlaylists()
    }

    override fun setupObserver() {
        viewModel.playlists.resourceHandler(
            onSuccess = { data ->
                videoAdapter.submitList(data.items)
            },
            state = { state ->
                binding.progressBar.isVisible = state is Resource.Loading
            }
        )
    }

    private fun setupRecyclerView() = with(binding.rvVideo) {
        layoutManager = LinearLayoutManager(context)
        adapter = videoAdapter
    }

    private fun navigateToPlaylistFragment(videoId: String) {
        val bundle = Bundle().apply {
            putString("VIDEO_ID", videoId)
        }
        findNavController().navigate(R.id.action_playlistFragment_to_playlistItemFragment, bundle)
    }
}