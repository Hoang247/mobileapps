package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0, 4, 8}, {2,4,6}};
    int currPlayer = 0; //0 means red, 1 means yellow, 2 means nothing
    boolean gameActive = true;


    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        int tag = Integer.parseInt(view.getTag().toString());
        if (gameActive) {
            if (gameState[tag] == 2) {
                if (currPlayer == 0) {
                    counter.setTranslationY(-1500);
                    counter.setImageResource(R.drawable.red);
                    counter.animate().translationYBy(1500).setDuration(300);
                    gameState[tag] = 0;

                } else {
                    counter.setTranslationY(-1500);
                    counter.setImageResource(R.drawable.yellow);
                    counter.animate().translationYBy(1500).setDuration(300);
                    gameState[tag] = 1;
                }
                for (int[] iArray : winningPositions) {
                    if (gameState[iArray[0]] == gameState[iArray[1]]
                            && gameState[iArray[1]] == gameState[iArray[2]]
                            && gameState[iArray[0]] == currPlayer)
                        {
                            gameActive = false;
                            if (currPlayer ==1) {
                                Toast.makeText(this, "Yellow player has won", Toast.LENGTH_LONG).show();
                            }
                            else{
                                Toast.makeText( this, "Red player has won", Toast.LENGTH_LONG).show();
                            }
                            break;
                        }
                }
                currPlayer ^= 1;

            }
        }
    }

    public void resetBoard(View view){
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for (int i = 0; i<gridLayout.getChildCount(); i++){
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);

        }
        for(int i = 0; i<9; i++){
            gameState[i] = 2;
        }
        currPlayer = 0;
        gameActive = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}