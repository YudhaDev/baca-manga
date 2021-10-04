package com.xndrive.baca_manga.view.adapters

import android.app.Activity
import android.content.res.Resources
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.xndrive.baca_manga.R

class BannerViewpagerAdapter(val activity: Activity, val list: List<String>): RecyclerView.Adapter<BannerViewpagerAdapter.ViewHolder>() {
    class ViewHolder(val itemview: View):RecyclerView.ViewHolder(itemview) {
        val _item_banner_imageview: ImageView = itemview.findViewById(R.id.item_banner_imageview)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BannerViewpagerAdapter.ViewHolder {
        val view = activity.layoutInflater.inflate(R.layout.item_banner, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: BannerViewpagerAdapter.ViewHolder, position: Int) {
        holder._item_banner_imageview.setImageDrawable(activity.getDrawable(R.drawable.ic_account))
    }

    override fun getItemCount(): Int {
        return list.size
    }
}