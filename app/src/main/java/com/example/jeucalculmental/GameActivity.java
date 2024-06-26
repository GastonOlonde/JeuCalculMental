package com.example.jeucalculmental;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {
    private int score = 0;
    private int lives = 3;
    private ProgressBar progressBar;
    private static final int PROGRESS_BAR_INCREMENT = 10;
    private static final int MAX_PROGRESS = 100;
    private static final int MIN_PROGRESS = 0;
    private TextView answer;
    private TextView textViewCalcul;
    private static final int Palier1 = 20;
    private static final int Palier2 = 40;
    private static final int Palier3 = 60;
    private static final int Palier4 = 80;
    private static final int Palier5 = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game); // Move this line to the top

        // Initialize the buttons after setting the content view
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        Button btn4 = findViewById(R.id.btn4);
        Button btn5 = findViewById(R.id.btn5);
        Button btn6 = findViewById(R.id.btn6);
        Button btn7 = findViewById(R.id.btn7);
        Button btn8 = findViewById(R.id.btn8);
        Button btn9 = findViewById(R.id.btn9);
        Button btn0 = findViewById(R.id.btn0);
        Button btnClear = findViewById(R.id.btnC);
        btn0.setOnClickListener(view -> appuieBoutonChiffre("0"));
        btn1.setOnClickListener(view -> appuieBoutonChiffre("1"));
        btn2.setOnClickListener(view -> appuieBoutonChiffre("2"));
        btn3.setOnClickListener(view -> appuieBoutonChiffre("3"));
        btn4.setOnClickListener(view -> appuieBoutonChiffre("4"));
        btn5.setOnClickListener(view -> appuieBoutonChiffre("5"));
        btn6.setOnClickListener(view -> appuieBoutonChiffre("6"));
        btn7.setOnClickListener(view -> appuieBoutonChiffre("7"));
        btn8.setOnClickListener(view -> appuieBoutonChiffre("8"));
        btn9.setOnClickListener(view -> appuieBoutonChiffre("9"));
        btnClear.setOnClickListener(view -> effacerChiffre());

        answer = findViewById(R.id.answer);
        progressBar = findViewById(R.id.progressBar);

        updateToolbar();
        generateNewQuestion();
    }

    private void effacerChiffre(){
        String currentAnswer = answer.getText().toString();
        if (currentAnswer.length() > 0) {
            answer.setText(currentAnswer.substring(0, currentAnswer.length() - 1));
        }
    }

    private void appuieBoutonChiffre(String valeur){
        ajouterCharactere(valeur);
    }

    private void ajouterCharactere(String chiffreAAjouter){
        answer.append(chiffreAAjouter); // Use append instead of setText
    }

    private void updateToolbar() {
        TextView scoreView = findViewById(R.id.score);
        TextView livesView = findViewById(R.id.lives);
        scoreView.setText("Score: " + score);
        livesView.setText("Lives: " + lives);
        updateLivesDisplay();
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
        int correctAnswer = (int) questionView.getTag();
        int userAnswer = Integer.parseInt(answer.getText().toString());

        if (userAnswer == correctAnswer) {
            score++;
            progressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);
        } else {
            lives--;
        }

        if (lives <= 0) {
            showSaveScoreDialog();
        } else {
            updateToolbar();
            generateNewQuestion();
            answer.setText("");
        }
    }

    private void updateLivesDisplay() {
        ImageView coeur1 = findViewById(R.id.coeur1);
        ImageView coeur2 = findViewById(R.id.coeur2);
        ImageView coeur3 = findViewById(R.id.coeur3);

        coeur1.setImageResource(R.drawable.coeurs);
        coeur2.setImageResource(R.drawable.coeurs);
        coeur3.setImageResource(R.drawable.coeurs);

        if (lives < 3) {
            coeur1.setImageResource(R.drawable.coeursgris);
        }
        if (lives < 2) {
            coeur2.setImageResource(R.drawable.coeursgris);
        }
        if (lives < 1) {
            coeur3.setImageResource(R.drawable.coeursgris);
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
