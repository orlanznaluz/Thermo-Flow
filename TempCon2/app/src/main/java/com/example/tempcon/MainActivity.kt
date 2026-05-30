package com.example.tempcon

import android.content.Intent // Make sure Intent is imported
import android.os.Bundle
import android.widget.Button   // Make sure Button is imported
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main) // This should be your activity_main.xml

        // 1. Find the Button
        // Ensure you have a Button with android:id="@+id/letsConvertButton" in R.layout.activity_main
        val letsConvertButton = findViewById<Button>(R.id.letsConvertButton)

        // 2. Set an OnClickListener
        letsConvertButton.setOnClickListener {
            // 3. Create an Intent to start MainActivity2
            val intent = Intent(this, MainActivity2::class.java)
            //                        ^ Current Activity   ^ Target Activity

            // 4. Start MainActivity2
            startActivity(intent)
        }

        // This is your existing code for window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}