package egda18sd.ju.todo


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog


class ViewTaskActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TODO_ID = "TODO_ID"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_task)

        val edit = findViewById <Button> (R.id.edit)
        val deleat = findViewById <Button> (R.id.delet)


        val todoID = intent.getIntExtra(EXTRA_TODO_ID,-1)
        val todoid = toDoRepository.getToDoById(todoID)

        val titeltext = findViewById<TextView>(R.id.Titel);
        val contenttext = findViewById<TextView>(R.id.content);

        titeltext.text = todoid.toString();
        contenttext.text = todoid?.content.toString();



        edit.setOnClickListener{

            val EditToDo = Intent(this, EditActivity::class.java)
            EditToDo.putExtra(EXTRA_TODO_ID, todoID);
            startActivity(EditToDo);
            finish()
        }

        deleat.setOnClickListener{

            AlertDialog.Builder(this)
                    .setTitle(getString(R.string.delettitel))
                    .setMessage(getString(R.string.delettext))
                    .setPositiveButton(
                            getString(R.string.deletawser_YES)
                    ){dialog, which ->
                        toDoRepository.deleteToDoById(todoID)
                        val home = Intent(this,MainActivity::class.java)
                        startActivity(home)
                        finish()

                    }
                    .setNegativeButton(
                            getString(R.string.Deletanwser_NO)
                    ){dialog, which ->

                    }.show()


        }

    }


    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

    }
}