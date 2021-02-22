package egda18sd.ju.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.textfield.TextInputEditText
import org.w3c.dom.Text

class EditActivity : AppCompatActivity() {


    lateinit var titel : TextInputEditText
    lateinit var content : TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val todoID = intent.getIntExtra(ViewTaskActivity.EXTRA_TODO_ID,-1)
        val todoid = toDoRepository.getToDoById(todoID)

        titel = findViewById<TextInputEditText>(R.id.Titel)
        content = findViewById<TextInputEditText>(R.id.content)

        titel.setText(todoid?.title.toString())
        content.setText(todoid?.content.toString())




        val save = findViewById<Button>(R.id.save)
        save.setOnClickListener {

            if (validateTitle(titel.text.toString()) && validateContent(content.text.toString())) {


                toDoRepository.updateToDoById(todoID, titel.text.toString(), content.text.toString());

                val EditToDo = Intent(this, ViewTaskActivity::class.java).putExtra(ViewTaskActivity.EXTRA_TODO_ID, todoID)
                startActivity(EditToDo);

                finish()
            }
        }
        }
    private fun validateTitle(text : String) : Boolean{
        if(text.length < 3) {
            val titleError : String = getString(R.string.titelerror)
            titel.setError(titleError)
            return false
        }
        else {
            titel.setError(null)
            return true
        }
    }

    private fun validateContent(text : String) : Boolean{
        if(text.isEmpty()) {
            val contentError : String = getString(R.string.contenteror)
            content.setError(contentError)
            return false
        }
        else {
            content.setError(null)
            return true
        }
    }


}
