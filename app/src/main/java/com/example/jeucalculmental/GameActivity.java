package com.example.jeucalculmental;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {
    private int score = 0;
    private int lives = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        updateToolbar();
        generateNewQuestion();
    }

    private void updateToolbar() {
        TextView scoreView = findViewById(R.id.score);
        TextView livesView = findViewById(R.id.lives);
        scoreView.setText("Score: " + score);
        livesView.setText("Lives: " + lives);
    }

    private void generateNewQuestion() {
        TextView questionView = findViewById(R.id.question);
        int a = (int) (Math.random() * 10);
        int b = (int) (Math.random() * 10);
        questionView.setText(a + " + " + b + " = ?");
        questionView.setTag(a + b);
    }

    public void submitAnswer(View view) {
        TextView questionView = findViewById(R.id.question);
        EditText answerView = findViewById(R.id.answer);
        int correctAnswer = (int) questionView.getTag();
        int userAnswer = Integer.parseInt(answerView.getText().toString());

        if (userAnswer == correctAnswer) {
            score++;
        } else {
            lives--;
        }

        if (lives <= 0) {
            showSaveScoreDialog();
        } else {
            updateToolbar();
            generateNewQuestion();
            answerView.setText("");
        }
    }

    private void showSaveScoreDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Game Over");
        builder.setMessage("Your score: " + score + "\nEnter your name:");

        final EditText input = new EditText(this);
        builder.setView(input);

        builder.setPositiveButton("Save", (dialog, which) -> {
            String playerName = input.getText().toString();
            saveScore(playerName, score);
            Intent intent = new Intent(GameActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> {
            dialog.cancel();
            Intent intent = new Intent(GameActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        builder.show();
    }

    private void saveScore(String playerName, int score) {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        ContentValues values = new ContentValues();
        values.put("name", playerName);
        values.put("score", score);
        dbHelper.getWritableDatabase().insert("highscores", null, values);
    }
}
