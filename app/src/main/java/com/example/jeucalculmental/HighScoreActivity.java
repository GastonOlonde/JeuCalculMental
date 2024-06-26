package com.example.jeucalculmental;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class HighScoreActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        loadHighScores();
    }

    private void loadHighScores() {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {"name", "score"};
        Cursor cursor = db.query("highscores", projection, null, null, null, null, "score DESC", "3");

        List<Score> scores = new ArrayList<>();
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            int score = cursor.getInt(cursor.getColumnIndexOrThrow("score"));
            scores.add(new Score(name, score));
        }
        cursor.close();

        ScoreAdapter adapter = new ScoreAdapter(this, scores);
        ListView scoreListView = findViewById(R.id.score_list);
        scoreListView.setAdapter(adapter);
    }
}
