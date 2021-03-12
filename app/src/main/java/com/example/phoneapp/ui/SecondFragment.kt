package com.example.phoneapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phoneapp.databinding.FragmentSecondBinding
import com.example.phoneapp.model.PhoneViewModel


class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    private val viewModel : PhoneViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPhone().observe(viewLifecycleOwner, Observer {
            it?.let {
                Log.d("SEGUNDO", "$it")
            }
        })
    }
}