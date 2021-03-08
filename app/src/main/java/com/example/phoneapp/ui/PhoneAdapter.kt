package com.example.phoneapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.phoneapp.databinding.ActivityMainBinding
import com.example.phoneapp.databinding.RvPhoneImagelistBinding
import com.example.phoneapp.local.PhoneListEntity

class PhoneAdapter : RecyclerView.Adapter<PhoneAdapter.PhoneVH>() {

    private var listPhones = listOf<PhoneListEntity>()
    private val selectedPhone = MutableLiveData<PhoneListEntity>()



    fun update(list: List<PhoneListEntity>){
        listPhones = list
        notifyDataSetChanged()

    }

    fun selectedPhone():LiveData<PhoneListEntity> = selectedPhone

    inner class PhoneVH (private val binding: RvPhoneImagelistBinding ):
            RecyclerView.ViewHolder(binding.root),View.OnClickListener{
                fun bind(phone: PhoneListEntity){
                    Glide.with(binding.imageView3).load(phone.image).into(binding.imageView3)
                    itemView.setOnClickListener(this)
                    binding.tVName.text = phone.name
                    binding.tVPrice.text = phone.price.toInt().toString()

                }

        override fun onClick(v: View?) {
            selectedPhone.value = listPhones[adapterPosition]

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneVH {
    return PhoneVH(RvPhoneImagelistBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PhoneVH, position: Int) {
        val task = listPhones[position]
        holder.bind(task)

    }

    override fun getItemCount(): Int = listPhones.size
}