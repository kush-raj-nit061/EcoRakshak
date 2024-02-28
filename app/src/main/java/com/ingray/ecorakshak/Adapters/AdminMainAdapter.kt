package com.ingray.ecorakshak.Adapters


import android.app.AlertDialog
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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.ingray.ecorakshak.CustomDialog
import com.ingray.ecorakshak.DataClass.SentData
import com.ingray.ecorakshak.R
import java.util.Locale


class AdminMainAdapter(options: FirebaseRecyclerOptions<SentData?>) :
    FirebaseRecyclerAdapter<SentData?, AdminMainAdapter.userAdapterHolder?>(options) {
        private lateinit var str:String
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
        holder.action.text=model.status
        if(model.status == "Done"){
            str = "Waiting"
        }else{
            str="Done"
        }
        holder.query.setOnClickListener{
            val customDialog = CustomDialog(holder.itemView.context, model,1)
            customDialog.showDialog()
        }
        holder.action.setOnClickListener{
            val builder: AlertDialog.Builder = AlertDialog.Builder(holder.action.context)
            builder

                .setTitle("Do You surely want to change the status to $str?")
                .setPositiveButton("Yes") { dialog, which ->
                    var map = HashMap<String,Any>()
                    map.put("status",str)
                    FirebaseDatabase.getInstance().reference.child("Items").child(model.key).updateChildren(map)
                    FirebaseDatabase.getInstance().reference.child("Users").child(FirebaseAuth.getInstance().currentUser?.uid.toString()).child("Items").child(model.key).updateChildren(map)
                    Toast.makeText(holder.action.context,"Updated", Toast.LENGTH_LONG).show()
                }
                .setNegativeButton("No") { dialog, which ->
                    // Do something else.
                }

            val dialog: AlertDialog = builder.create()
            dialog.show()

        }


    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): userAdapterHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.admin_page_item,parent,false)
        return userAdapterHolder(view)
    }

    inner class userAdapterHolder(innerView:View):RecyclerView.ViewHolder(innerView) {
        val sNo:TextView
        val query:TextView
        val status:ImageView
        val action:TextView


        init {

            sNo = innerView.findViewById(R.id.sNo)
            query = innerView.findViewById(R.id.query)
            status = itemView.findViewById(R.id.status)
            action = itemView.findViewById(R.id.action
            )
        }
    }
}