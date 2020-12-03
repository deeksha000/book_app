package com.example.bookhub

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class DashboardRecyclerAdapter(val context : Context, val itemList : ArrayList<Book>):RecyclerView.Adapter<DashboardRecyclerAdapter.DashboardViewHolder>() {
    class DashboardViewHolder(view: View): RecyclerView.ViewHolder(view){
        val txtBookName: TextView = view.findViewById(R.id.txtBookName)
        val txtAuthorName : TextView = view.findViewById(R.id.txtBookAuthor)
        val txtBookRating : TextView = view.findViewById(R.id.txtBookRating)
        val txtBookPrice: TextView = view.findViewById(R.id.txtBookPrice)
        val imgBookImage : ImageView = view.findViewById(R.id.imgBookImage)
        val llContent : LinearLayout= view.findViewById(R.id.llContent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_dashboard_single_row,parent,false)
        return DashboardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        val book = itemList[position]
        holder.txtBookName.text = book.name
        holder.txtAuthorName.text = book.author
        holder.txtBookPrice.text = book.price
        holder.txtBookRating.text = book.rating
     //   holder.imgBookImage.setImageResource(book.image)
       // Picasso.get().load(book.image).into(holder.imgBookImage)
        Picasso.get().load(book.image).error(R.drawable.default_book).into(holder.imgBookImage)
        holder.llContent.setOnClickListener {
           // Toast.makeText(context, "Clicked on ${holder.txtBookName.text}",Toast.LENGTH_LONG).show()

        val intent = Intent(context,DescriptionActivity :: class.java)
            intent.putExtra("book_id",book.book_id)
            context.startActivity(intent)
        }

    }
}