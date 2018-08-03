package bhavesh.vsl.listviewcustomeadapter

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var status = ContextCompat.checkSelfPermission(this ,Manifest.permission.WRITE_EXTERNAL_STORAGE)

        if(status == PackageManager.PERMISSION_GRANTED){
            // Call ReadFiles function
            readFiles()
        }else{
             //REquest to user to grant permission
            ActivityCompat.requestPermissions(this  ,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 0)

        }

    }

    // to check user grant or not permission  [ START ]
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
            readFiles()
        }else{
            Toast.makeText(this,
                    "U can't process for App",
                    Toast.LENGTH_SHORT).show()
        }

    }
    // to check user grant or not permission  [ END ]


    // Readfile [ START ]
    fun readFiles(){
        //specify the path
        var path = "/storage/emulated/0/WhatsApp/Media/WhatsApp Images/"

        //convert string obj into file object
        var file = File(path)

        // now check path
        if(!file.exists()){
            var path = "/storage/sdcard0/WhatsApp/Media/WhatsApp Images/"
            var file = File(path)
        }

       /*
        Arrary Adpter to present Data [ START ]
        // declare the array & store data into files arrary
        var files:Array<String> = file.list()

        // set adapter
        var myadapter = ArrayAdapter<String>(this ,
                android.R.layout.simple_list_item_single_choice,files)

        // set adpter to list view
        lview.adapter = myadapter
        Arrary Adpter to present Data [ END ]
        */

        //set arrary adapter in file arrary
        var files:Array<File> = file.listFiles()

        //set custome adapter for list view
        // passing two paramenter
        //1 is files arrary
        //2 activity
        lview.adapter = MyAdapter(files,this)





    }
    // Readfile [ END ]


}
