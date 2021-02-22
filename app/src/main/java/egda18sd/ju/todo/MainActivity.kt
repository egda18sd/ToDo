package egda18sd.ju.todo

import android.content.Intent
import android.os.Bundle
import android.widget.*
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity





class MainActivity : AppCompatActivity() {

    lateinit var myadapter : ArrayAdapter<ToDo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


        val newTask = findViewById<Button> (R.id.CreateTask);

         val listView = findViewById<ListView>(R.id.List_Item_Main)

        myadapter = ArrayAdapter <ToDo> (
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                toDoRepository.getAllToDos()
        )


        listView.adapter = myadapter

       listView.setOnItemClickListener { _,view, position, id ->
            var listID = listView.adapter.getItem(position) as ToDo
           val ViewToDO = Intent(this, ViewTaskActivity::class.java).putExtra(ViewTaskActivity.EXTRA_TODO_ID, listID.id)

           startActivity(ViewToDO);

       }


            newTask.setOnClickListener{
                val intent = Intent(this , NewTaskActivity::class.java)
                startActivity(intent)
                
            }


    }

    override fun onStart() {
        super.onStart()
        myadapter.notifyDataSetChanged()
    }

}