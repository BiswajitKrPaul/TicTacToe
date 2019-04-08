package com.biswajit.freelance.musicapp.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int crossIsActivePlayer = 1;
    boolean currentGameState = true;
    int counterPositions[] = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int winningPosition[][] = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    Button btn_playagain;
    boolean flag=false;
    int tappedCounter = 0;

    public void counterOnClick(View view) {
        ImageView counterImage = (ImageView) view;
        if (currentGameState) {
            tappedCounter++;
            counterPositions[Integer.parseInt(view.getTag().toString())] = crossIsActivePlayer;
            if (crossIsActivePlayer == 1) {
                counterImage.setImageResource(R.drawable.cross);
                counterImage.setEnabled(false);
                crossIsActivePlayer = 0;
            } else {
                counterImage.setImageResource(R.drawable.zero);
                crossIsActivePlayer = 1;
                counterImage.setEnabled(false);
            }

            for (int[] winningpos : winningPosition) {
                if (counterPositions[winningpos[0]] == counterPositions[winningpos[1]] && counterPositions[winningpos[1]] == counterPositions[winningpos[2]] && counterPositions[winningpos[0]] != 2) {
                    Toast.makeText(getApplication(), crossIsActivePlayer == 1 ? "Zero Won" : "Cross Won", Toast.LENGTH_SHORT).show();
                    currentGameState = false;
                    btn_playagain = findViewById(R.id.btn_playAgain);
                    btn_playagain.setVisibility(View.VISIBLE);
                    flag=true;

                }
            }
            if (tappedCounter == 9 && flag==false) {
                btn_playagain = findViewById(R.id.btn_playAgain);
                btn_playagain.setVisibility(View.VISIBLE);
                Toast.makeText(getApplication(), "Game Draw: Click on Play Again Button", Toast.LENGTH_SHORT).show();
            }
        }


    }

    public void playAgain(View view) {

        crossIsActivePlayer = 1;
        currentGameState = true;
        btn_playagain = findViewById(R.id.btn_playAgain);
        btn_playagain.setVisibility(View.INVISIBLE);
        GridLayout gridLayout = findViewById(R.id.dsad);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView img = (ImageView) gridLayout.getChildAt(i);
            img.setImageDrawable(null);
            img.setEnabled(true);
        }

        for (int i = 0; i < counterPositions.length; i++) {
            counterPositions[i] = 2;
        }
        tappedCounter = 0;
        flag=false;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


}
