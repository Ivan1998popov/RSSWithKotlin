package com.monitoringplus.rsswithkotlin.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.monitoringplus.rsswithkotlin.R
import com.monitoringplus.rsswithkotlin.`interface`.ItemClickListener
import com.monitoringplus.rsswithkotlin.model.RSSObject
import kotlinx.android.synthetic.main.row.view.*

class FeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener,
    View.OnLongClickListener {

    var txtTitle: TextView = itemView.txtTitle
    var txtPutDate: TextView = itemView.txtPubdate
    var txtContent: TextView = itemView.txtContent

    private var itemClickListener: ItemClickListener? = null

    init {
        itemView.setOnClickListener(this)
        itemView.setOnLongClickListener(this)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun onClick(v: View) {
        itemClickListener!!.onClick(v,adapterPosition,false)
    }


    override fun onLongClick(v: View): Boolean {
        itemClickListener!!.onClick(v,adapterPosition,true)
        return true
    }

}
class FeedAdapter (private val rssObject: RSSObject, private val mContext: Context):
    RecyclerView.Adapter<FeedViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(mContext)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val itemView = inflater.inflate(R.layout.row, parent, false)
        return FeedViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return rssObject.items.size
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {

        holder.txtTitle.text = rssObject.items[position].title
        holder.txtPutDate.text = rssObject.items[position].pubDate
        holder.txtContent.text = rssObject.items[position].content

        holder.setItemClickListener(object : ItemClickListener {
            override fun onClick(view: View, position: Int, isLongClick: Boolean) {
                if(!isLongClick) {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(rssObject.items[position].link))
                    mContext.startActivity(browserIntent)
                }
            }
        })

    }

}