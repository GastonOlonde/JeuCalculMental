package com.example.jeucalculmental;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class HighScoreActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        loadHighScore();
    }

    private void loadHighScore() {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {"name", "score"};
        Cursor cursor = db.query("highscores", projection, null, null, null, null, "score DESC", "1");

        if (cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            int score = cursor.getInt(cursor.getColumnIndexOrThrow("score"));
            TextView highScoreView = findViewById(R.id.high_score);
            highScoreView.setText("High Score: " + score +"  ( " + name + " )");
        }
        cursor.close();
    }
}
