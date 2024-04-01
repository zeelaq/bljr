package com.example.submission1.detail.follow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission1.UserAdapter
import com.example.submission1.data.responsemodel.ResponseUserGithub
import com.example.submission1.databinding.FragmentFollowsBinding
import com.example.submission1.detail.DetailViewModel
import com.example.submission1.utils.Result

class FollowsFragment : Fragment() {

    private var binding:FragmentFollowsBinding? = null //layout fragment follows
    private val adapter by lazy {
        UserAdapter {
        }
    }

    private val viewModel by activityViewModels<DetailViewModel>()
    private var type = 1
    private var userId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowsBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.rvFollows?.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            setHasFixedSize(true)
            adapter = this@FollowsFragment.adapter
        }

        when(type) {
            FOLLOWERS -> {
                viewModel.resultFollowersUser.observe(viewLifecycleOwner,
                    this::manageResultFollows)
            }

            FOLLOWING -> {
                viewModel.resultFollowingUser.observe(viewLifecycleOwner,
                    this::manageResultFollows)
            }
        }
    }

    private fun manageResultFollows(state: Result) {
        when (state) {
            is Result.Success<*> -> {
                adapter.setData(state.data as
                        MutableList<ResponseUserGithub.Item>)
            }
            is Result.Error -> {
                Toast.makeText(
                    requireActivity(),
                    state.exception.message.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
            is Result.Loading -> {
                binding?.LoadingBar?.isVisible = state.isLoading
            }
        }
    }

    companion object {
        const val FOLLOWING = 46
        const val FOLLOWERS = 57

        fun newInstance(Followtype: Int) = FollowsFragment()
            .apply {
                this.type = Followtype
                this.userId = userId
            }
    }
}