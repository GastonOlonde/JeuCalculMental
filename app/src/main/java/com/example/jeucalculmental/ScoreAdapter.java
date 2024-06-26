package com.example.jeucalculmental;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class ScoreAdapter extends ArrayAdapter<Score> {
    public ScoreAdapter(Context context, List<Score> scores) {
        super(context, 0, scores);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Score score = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.score_item, parent, false);
        }

        TextView nameTextView = convertView.findViewById(R.id.name);
        TextView scoreTextView = convertView.findViewById(R.id.score);
        TextView rankTextView = convertView.findViewById(R.id.position);


        // rank position , string premier, second, third depuis le fichier strings.xml
        if (position == 0) {
            rankTextView.setText(R.string.premier);
        } else if (position == 1) {
            rankTextView.setText(R.string.deuxieme);
        } else if (position == 2) {
            rankTextView.setText(R.string.troisieme);
        } else {
            rankTextView.setText("");
        }
        nameTextView.setText(score.getName());
        scoreTextView.setText(String.valueOf(score.getScore()));

        return convertView;
    }
}
