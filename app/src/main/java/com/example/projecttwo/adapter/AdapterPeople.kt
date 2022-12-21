package com.example.projecttwo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projecttwo.databinding.PeopleElementBinding
import com.example.projecttwo.service.InfoPerson

class AdapterPeople(private val context:Context, private val peopleList: List<InfoPerson>) :
    RecyclerView.Adapter<AdapterPeople.ViewHolder>() {

    class ViewHolder(view:PeopleElementBinding):RecyclerView.ViewHolder(view.root){
        val tvName = view.tvName
        val tvHeight = view.tvHeight
        val tvBirthYear = view.tvBirthYear
        val tvGender = view.tvGender
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PeopleElementBinding.inflate(LayoutInflater.from(context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = peopleList[position]!!.name
        holder.tvHeight.text = peopleList[position]!!.height
        holder.tvBirthYear.text = peopleList[position]!!.birth_year
        holder.tvGender.text = peopleList[position]!!.gender

    }

    override fun getItemCount(): Int = peopleList.size
}
