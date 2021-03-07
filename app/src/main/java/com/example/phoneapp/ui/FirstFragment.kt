package com.example.phoneapp.ui

import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phoneapp.R
import com.example.phoneapp.databinding.FragmentFirstBinding
import com.example.phoneapp.model.PhoneViewModel


class FirstFragment : Fragment() {
        private lateinit var binding: FragmentFirstBinding
        private val viewModel: PhoneViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater,container,false )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        val adapter = PhoneAdapter()
        binding.rvPhones.adapter = adapter
        binding.rvPhones.layoutManager = LinearLayoutManager(context)
        binding.rvPhones.addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))


        viewModel.allPhones.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.update(it)

            }
        })

       /* viewModel.allPhones.observe(viewLifecycleOwner, Observer {
            it?.let {

            }
        })*/

           /*  .setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }*/
    }

}