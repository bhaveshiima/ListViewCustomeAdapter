package bhavesh.vsl.listviewcustomeadapter

import android.graphics.BitmapFactory
import android.media.ThumbnailUtils
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.indiview.view.*
import java.io.File


class MyAdapter(var files: Array<File>,
                var mActivity: MainActivity) : BaseAdapter() {override fun getView(position: Int, p1: View?, p2: ViewGroup?): View {

        var inflater = LayoutInflater.from(mActivity)

        var v = inflater.inflate(R.layout.indiview,null)

        var file = files[position]

        // now set the real data on view
        //  v.imgview.setImageURI(Uri.fromFile(file))  // it will cause memory leakage

        // use the Thumbnail for image [ START ]
        var bmp = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(file.path),50,50 )
        v.imgview.setImageBitmap(bmp)
        // use the Thumbnail for image [ START ]

        v.name.text = file.name
        v.size.text = "${file.length()} bytes"
        v.del.setOnClickListener(){
            file.delete()  //it will delete image
            mActivity.readFiles()
        }



        return v  //return view object


    }



    override fun getItem(p0: Int): Any {
        return 0
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
      // How many items you need to present need to specify here
        return files.size
    }
}