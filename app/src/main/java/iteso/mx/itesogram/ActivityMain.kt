package iteso.mx.itesogram

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseObject
import com.parse.ParseQuery
import iteso.mx.itesogram.Adapters.AdapterPhoto
import kotlinx.android.synthetic.main.mian_recyler_view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.internals.AnkoInternals.getContext
import java.io.File
import java.util.*

class ActivityMain: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mian_recyler_view)


 //       val recyclerView = findViewById<RecyclerView>(R.id.RecyclerViewMain)
   //     val query: ParseQuery<ParseObject> = ParseQuery.getQuery("feed")
     //   query.findInBackground { objects, e ->

//            if (e == null) {
  //              recyclerView.adapter = AdapterPhoto(objects)
    //            recyclerView.layoutManager = LinearLayoutManager(this)
      //      }

        doAsync{
            val query = ParseQuery.getQuery<ParseObject>("Feed")
            query.findInBackground(object : FindCallback<ParseObject> {
                var recyclerView = findViewById<RecyclerView>(R.id.RecyclerViewMain)
                var posts: List<ParseObject> = arrayListOf<ParseObject>()

                override fun done(postList: List<ParseObject>, e: ParseException?) {
                    if (e == null) {
                        posts = postList
                        runOnUiThread {
                            recyclerView.adapter = AdapterPhoto(posts)
                            recyclerView.adapter?.notifyDataSetChanged()
                        }
                        Log.d("score", "Retrieved " + posts.size + " scores")

                    } else {
                        Log.d("score", "Error: " + e.getStackTrace())
                    }
                }
            })

        }
        //}
    }
}