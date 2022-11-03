package com.proj.recapbeta

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CourseAdapter(private val courselist: ArrayList<Course>): RecyclerView.Adapter<CourseAdapter.CourseViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.courseitem,
        parent,false)
        return CourseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {

        val currentitem: Course = courselist[position]
        holder.code.text = currentitem.code
        holder.description.text= currentitem.description
    }

    override fun getItemCount(): Int {
        return courselist.size
    }

    class CourseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val code: TextView = itemView.findViewById(R.id.code)
        val description: TextView = itemView.findViewById(R.id.desc)

    }
}