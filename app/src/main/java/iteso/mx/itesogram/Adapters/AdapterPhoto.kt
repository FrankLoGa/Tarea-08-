package iteso.mx.itesogram.Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.parse.ParseFile
import com.parse.ParseObject
import iteso.mx.itesogram.Adapters.AdapterPhoto.PhotoViewHolder
import iteso.mx.itesogram.R

class AdapterPhoto  (private val users : List<ParseObject>): RecyclerView.Adapter<AdapterPhoto.PhotoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterPhoto.PhotoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return PhotoViewHolder(view)
        
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(users[position])
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class PhotoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var username: TextView = view.findViewById(R.id.user_name_tv)
        private var likes: TextView = view.findViewById(R.id.like_btn_tv)
        private var photoM: ImageView = view.findViewById(R.id.photo_iv)
        private var rm = Glide.with(view);

        fun bind(photo: ParseObject) {
            Log.d(
                "AdapterUser",
                photo.getString("username") + " - " + photo.getString("commentsNumber")
            )
            username.text = photo.getString("username")
            likes.text = photo.getInt("commentsNumber").toString() + " likes!"
            var imagen: ParseFile = photo.getParseFile("photo")!!
            rm.load(imagen.url).into(photoM)
            //photo.text = user.get("photo"). oString()
        }
    }

    override fun getItemCount(): Int = users.size
}