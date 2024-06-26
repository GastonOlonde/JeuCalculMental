package com.example.jeucalculmental;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Random;

public class AboutActivity extends AppCompatActivity {

    private static final int NUM_IMAGES = 50; // Nombre d'ImageView à générer
    private static final int DELAY_MS = 350; // Délai entre chaque génération en millisecondes

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout); // Récupérer le ConstraintLayout
        Random random = new Random();

        // Handler pour introduire un délai
        Handler handler = new Handler(Looper.getMainLooper());

        // Générer NUM_IMAGES ImageView avec un délai entre chaque génération
        for (int i = 0; i < NUM_IMAGES; i++) {
            final int index = i;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ImageView imageView = new ImageView(AboutActivity.this);
                    imageView.setImageResource(getRandomImageResource()); // Méthode pour obtenir une ressource d'image aléatoire
                    imageView.setId(View.generateViewId()); // Générer un ID unique pour chaque ImageView

                    ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                            ConstraintLayout.LayoutParams.WRAP_CONTENT,
                            ConstraintLayout.LayoutParams.WRAP_CONTENT
                    );

                    // Positionner les ImageView de manière aléatoire
                    layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID; // Adapter si nécessaire
                    layoutParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID; // Adapter si nécessaire
                    layoutParams.leftMargin = random.nextInt(2700);
                    // layoutParams.setMargins(random.nextInt(300), random.nextInt(600), 0, 0);

                    // rand entre 10 et 40
                    int val = random.nextInt(40) + 35;
                    layoutParams.width = val;
                    layoutParams.height = val;
                    imageView.setLayoutParams(layoutParams);
                    constraintLayout.addView(imageView); // Ajouter l'ImageView au ConstraintLayout

                    // Charger l'animation à partir du fichier XML
                    Animation fallAnimation = AnimationUtils.loadAnimation(AboutActivity.this, R.anim.fall_down);

                    // Lancer l'animation sur l'image
                    imageView.startAnimation(fallAnimation);
                }
            }, i * DELAY_MS); // Multiplier l'index par le délai pour échelonner les générations
        }
    }

    // Méthode pour obtenir une ressource d'image aléatoire
    private int getRandomImageResource() {
        // Retourner une ressource d'image aléatoire de votre choix
        // Par exemple, vous pouvez stocker les IDs d'images dans un tableau et retourner un élément aléatoire.
        int[] imageResources = {R.drawable.ic_plus, R.drawable.ic_minus, R.drawable.ic_equal};
        Random random = new Random();
        return imageResources[random.nextInt(imageResources.length)];
    }
}
