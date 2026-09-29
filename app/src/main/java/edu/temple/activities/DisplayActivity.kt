package edu.temple.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

const val MESSAGE_KEY = "msg"
const val RESULT_KEY = "reply_message"
class DisplayActivity : AppCompatActivity() {

    // TODO Step 1: Launch TextSizeActivity when button clicked to allow selection of text size value

    val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            it.data?.apply {
                lyricsDisplayTextView.textSize = getIntExtra(SIZE_KEY, 22).toFloat()
            }
            /*
            it.data?.getIntExtra(SIZE_KEY, 22)?.run {       // to avoid if not null feature, only runs if there is a value
                lyricsDisplayTextView.textSize = this.toFloat()
            }
            */
        }
    }

    // TODO Step 3: Use returned value for lyricsDisplayTextView text size

    private lateinit var lyricsDisplayTextView: TextView
    private lateinit var textSizeSelectorButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        //Log.d("Fired", "onCreate()")

        lyricsDisplayTextView = findViewById(R.id.lyricsDisplayTextView)
        textSizeSelectorButton = findViewById<Button>(R.id.textSizeSelectorButton).apply{
            setOnClickListener {
                launcher.launch(
                    Intent(this@DisplayActivity, TextSizeActivity::class.java)
                )
            }
        }

        /*
        findViewById<Button>(R.id.textSizeSelectorButton).setOnClickListener {
            val launchIntent = Intent(this@DisplayActivity, TextSizeActivity::class.java)
            launchIntent.putExtra(MESSAGE_KEY, "Hello! this is a message to TextSizeActivity")
            launcher.launch(launchIntent)
        }
*/
    }
}