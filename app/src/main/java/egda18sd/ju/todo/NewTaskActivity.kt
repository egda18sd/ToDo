package egda18sd.ju.todo

import android.content.Intent
import android.app.Activity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class NewTaskActivity : AppCompatActivity() {
    lateinit var title : TextInputEditText
    lateinit var content : TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_task)

        title = findViewById(R.id.Titel)
        content = findViewById(R.id.content)

        val savebutton = findViewById <Button> (R.id.saveButton)



        savebutton.setOnClickListener{


            if(validateTitle(title.text.toString()) && validateContent(content.text.toString())) {
                val listID = toDoRepository.addToDo(title.text.toString(), content.text.toString());

                val ViewToDO = Intent(this, ViewTaskActivity::class.java).putExtra(ViewTaskActivity.EXTRA_TODO_ID, listID)
                startActivity(ViewToDO);
                finish();
            }
        }


    }

    private fun validateTitle(text : String) : Boolean{
        if(text.length < 3) {
            val titleError : String = getString(R.string.titelerror)
            title.setError(titleError);
            return false
        }
        else {
            title.setError(null)
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