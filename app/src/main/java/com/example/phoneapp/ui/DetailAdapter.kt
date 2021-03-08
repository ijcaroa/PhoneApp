package com.example.phoneapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.phoneapp.databinding.RvDetailBinding
import com.example.phoneapp.local.PhoneDetailEntity

class DetailAdapter : RecyclerView.Adapter<DetailAdapter.PhoneVH>() {
    var detailImage = listOf<PhoneDetailEntity>()
    val selectedPhone = MutableLiveData<PhoneDetailEntity>()

    fun update(detail: List<PhoneDetailEntity>){
        detailImage = detail
        notifyDataSetChanged()

    }
    fun selectedPhone(): LiveData<PhoneDetailEntity> = selectedPhone

    inner class PhoneVH(private val binding: RvDetailBinding) :
            RecyclerView.ViewHolder(binding.root), View.OnClickListener{
        fun bind (detail: PhoneDetailEntity){
            Glide.with(binding.root).load(detail.image).into(binding.imageViewPhone)
           binding.textViewName.text = detail.name
            binding.textViewDescrip.text = detail.description

            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            selectedPhone.value = detailImage[adapterPosition]
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneVH {
        return PhoneVH(RvDetailBinding.inflate(LayoutInflater.from(parent.context)))
    }
    override fun onBindViewHolder(holder: PhoneVH, position: Int) {
        holder.bind(detailImage[position])
    }
    override fun getItemCount() = detailImage.size
}