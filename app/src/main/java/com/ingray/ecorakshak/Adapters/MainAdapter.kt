package com.ingray.ecorakshak.Adapters


import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.ingray.ecorakshak.CustomDialog
import com.ingray.ecorakshak.DataClass.SentData
import com.ingray.ecorakshak.R
import java.util.Locale


class MainAdapter(options: FirebaseRecyclerOptions<SentData?>) :
    FirebaseRecyclerAdapter<SentData?, MainAdapter.userAdapterHolder?>(options) {
    override fun onBindViewHolder(
        holder: userAdapterHolder,
        position: Int,
        model: SentData
    ) {

        holder.sNo.text = (position+1).toString()
        holder.query.text = model.key
        if(model.status.lowercase(Locale.ROOT) =="waiting"){
            val tintColor = ContextCompat.getColor(holder.status.context, R.color.red)
            holder.status.setColorFilter(tintColor, PorterDuff.Mode.SRC_ATOP)
        }
        holder.query.setOnClickListener{
            val customDialog = CustomDialog(holder.itemView.context, model,0)
            customDialog.showDialog()
        }

    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): userAdapterHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.user_page_item,parent,false)
        return userAdapterHolder(view)
    }

    inner class userAdapterHolder(innerView:View):RecyclerView.ViewHolder(innerView) {
        val sNo:TextView
        val query:TextView
        val status:ImageView


        init {

            sNo = innerView.findViewById(R.id.sNo)
            query = innerView.findViewById(R.id.query)
            status = itemView.findViewById(R.id.status)

        }
    }
}