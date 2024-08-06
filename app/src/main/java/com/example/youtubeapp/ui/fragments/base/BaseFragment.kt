package com.example.youtubeapp.ui.fragments.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.viewbinding.ViewBinding
import com.example.youtubeapp.utils.Resource

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB : ViewBinding>(
    private val inflate: Inflate<VB>
) : Fragment() {
    private var _binding: VB? = null
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
    }

    protected open fun setupObserver() {}

    protected fun <T> LiveData<Resource<T>>.resourceHandler(
        onSuccess: (data: T) -> Unit,
        state: (Resource<T>) -> Unit
    ) {
        this.observe(viewLifecycleOwner) { resource ->
            state(resource)
            when(resource){
                is Resource.Error -> (resource.message) ?: "Error"
                is Resource.Loading -> {}
                is Resource.Success -> resource.data?.let { onSuccess(it) }
            }
        }
    }
}