package com.example.pradhuman.othello;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout mainLayout;
    MyButton[][] grid;
    LinearLayout[] rowLayout;
    private static boolean gameOver;
    private static boolean blackTurn;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameOver = false;
        blackTurn = true;
        mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
        createGrid();
        updateBoard();
    }

    public void createGrid() {
        rowLayout = new LinearLayout[8];
        grid = new MyButton[8][8];
        for (int i = 0; i < 8; i++) {
            rowLayout[i] = new LinearLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1f);
            rowLayout[i].setOrientation(LinearLayout.HORIZONTAL);
            rowLayout[i].setLayoutParams(params);
            mainLayout.addView(rowLayout[i]);
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                grid[i][j] = new MyButton(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1f);
                grid[i][j].setLayoutParams(params);
                grid[i][j].setX(i);
                grid[i][j].setY(j);
                grid[i][j].setBackgroundResource(R.drawable.rect);
                grid[i][j].setOnClickListener(this);
                if ((i == 3 && j == 3) || (i == 4 && j == 4)) {
                    grid[i][j].setClicked(true);
                    grid[i][j].setBlack(false);
                    grid[i][j].setBackgroundResource(R.drawable.whiterect);
                }
                if ((i == 4 && j == 3) || (i == 3 && j == 4)) {
                    grid[i][j].setClicked(true);
                    grid[i][j].setBackgroundResource(R.drawable.blackrect);
                    grid[i][j].setBlack(true);
                }
                rowLayout[i].addView(grid[i][j]);
            }
        }
    }

    public void updateBoard() {
        count = 0;
        int valid = 0, i, j;
        boolean flag = false;
        for (int olc = 0; olc < 8; olc++) {
            for (int ilc = 0; ilc < 8; ilc++) {
                if (grid[olc][ilc].getClicked())
                    continue;
                if (blackTurn) {
                    i = olc + 1;
                    j = ilc + 1;
                    flag = false;
                    valid = 0;
                    // Diagonal Right-Down
                    while (i < 8 && j < 8 && grid[i][j].getClicked()) {
                        if (grid[i][j].getBlack() && valid == 0) {
                            break;
                        }
                        if (!grid[i][j].getBlack())
                            valid = 1;
                        if (valid == 1 && grid[i][j].getBlack()) {
                            flag = true;
                            break;
                        }
                        i++;
                        j++;
                    }
                    i = olc - 1;
                    j = ilc - 1;
                    valid = 0;
                    // Up Left
                    while (i >= 0 && j >= 0 && !flag && grid[i][j].getClicked()) {

                        if (grid[i][j].getBlack() && valid == 0) {
                            break;
                        }
                        if (!grid[i][j].getBlack())
                            valid = 1;
                        if (valid == 1 && grid[i][j].getBlack()) {
                            flag = true;
                            break;
                        }
                        i--;
                        j--;
                    }
                    i = olc - 1;
                    j = ilc + 1;
                    valid = 0;
                    //UP-Right
                    while (i >= 0 && j < 8 && !flag && grid[i][j].getClicked()) {

                        if (grid[i][j].getBlack() && valid == 0) {
                            break;
                        }
                        if (!grid[i][j].getBlack())
                            valid = 1;
                        if (valid == 1 && grid[i][j].getBlack()) {
                            flag = true;
                            break;
                        }
                        i--;
                        j++;
                    }
                    i = olc + 1;
                    j = ilc - 1;
                    valid = 0;
                    //Down- LEFT
                    while (i < 8 && j >= 0 && !flag && grid[i][j].getClicked()) {

                        if (grid[i][j].getBlack() && valid == 0) {
                            break;
                        }
                        if (!grid[i][j].getBlack())
                            valid = 1;
                        if (valid == 1 && grid[i][j].getBlack()) {
                            flag = true;
                            break;
                        }
                        i++;
                        j--;
                    }
                    i = olc - 1;
                    j = ilc;
                    valid = 0;
                    //UP
                    while (i >= 0 && !flag && grid[i][j].getClicked()) {
                        if (grid[i][j].getBlack() && valid == 0) {
                            break;
                        }
                        if (!grid[i][j].getBlack())
                            valid = 1;
                        if (valid == 1 && grid[i][j].getBlack()) {
                            flag = true;
                            break;
                        }
                        i--;
                    }
                    i = olc + 1;
                    j = ilc;
                    valid = 0;
                    //Down
                    while (i < 8 && !flag && grid[i][j].getClicked()) {

                        if (grid[i][j].getBlack() && valid == 0) {
                            break;
                        }
                        if (!grid[i][j].getBlack())
                            valid = 1;
                        if (valid == 1 && grid[i][j].getBlack()) {
                            flag = true;
                            break;
                        }
                        i++;
                    }
                    i = olc;
                    j = ilc - 1;
                    valid = 0;
                    //Left
                    while (j >= 0 && !flag && grid[i][j].getClicked()) {

                        if (grid[i][j].getBlack() && valid == 0) {
                            break;
                        }
                        if (!grid[i][j].getBlack())
                            valid = 1;
                        if (valid == 1 && grid[i][j].getBlack()) {
                            flag = true;
                            break;
                        }
                        j--;
                    }
                    i = olc;
                    j = ilc + 1;
                    valid = 0;
                    //Right
                    while (j < 8 && !flag && grid[i][j].getClicked()) {

                        if (grid[i][j].getBlack() && valid == 0) {
                            break;
                        }
                        if (!grid[i][j].getBlack())
                            valid = 1;
                        if (valid == 1 && grid[i][j].getBlack()) {
                            flag = true;
                            break;
                        }
                        j++;
                    }
                }//******************************************************************************
                else {
                    i = olc + 1;
                    j = ilc + 1;
                    flag = false;
                    // Diagonal Right-Down
                    while (i < 8 && j < 8 && grid[i][j].getClicked()) {
                        if (!grid[i][j].getBlack() && valid == 0) {
                            break;
                        }
                        if (grid[i][j].getBlack())
                            valid = 1;
                        if (valid == 1 && !grid[i][j].getBlack()) {
                            Log.d("White-Turn", " Diagonal Right Down" + " " + olc + " " + ilc + "");
                            flag = true;
                            break;
                        }
                        i++;
                        j++;
                    }
                    i = olc - 1;
                    j = ilc - 1;
                    valid = 0;
                    // Up Left
                    while (i >= 0 && j >= 0 && !flag && grid[i][j].getClicked()) {

                        if (!grid[i][j].getBlack() && valid == 0) {
                            break;
                        }
                        if (grid[i][j].getBlack())
                            valid = 1;
                        if (valid == 1 && !grid[i][j].getBlack()) {
                            Log.d("White-Turn", " Up Left" + " " + olc + " " + ilc + "");
                            flag = true;
                            break;
                        }
                        i--;
                        j--;
                    }
                    i = olc - 1;
                    j = ilc + 1;
                    valid = 0;
                    //UP-Right
                    while (i >= 0 && j < 8 && !flag && grid[i][j].getClicked()) {

                        if (!grid[i][j].getBlack() && valid == 0) {
                            break;
                        }
                        if (grid[i][j].getBlack())
                            valid = 1;
                        if (valid == 1 && !grid[i][j].getBlack()) {
                            Log.d("White-Turn", " Diagonal Up Right" + " " + olc + " " + ilc + "");
                            flag = true;
                            break;
                        }
                        i--;
                        j++;
                    }
                    i = olc + 1;
                    j = ilc - 1;
                    valid = 0;
                    //Down- LEFT
                    while (i < 8 && j >= 0 && !flag && grid[i][j].getClicked()) {

                        if (!grid[i][j].getBlack() && valid == 0) {
                            break;
                        }
                        if (grid[i][j].getBlack())
                            valid = 1;
                        if (valid == 1 && !grid[i][j].getBlack()) {
                            Log.d("White-Turn", " Diagonal Left Down" + " " + olc + " " + ilc + "");
                            flag = true;
                            break;
                        }
                        i++;
                        j--;
                    }
                    i = olc - 1;
                    j = ilc;
                    valid = 0;
                    //UP
                    while (i >= 0 && !flag && grid[i][j].getClicked()) {
                        if (!grid[i][j].getBlack() && valid == 0) {
                            break;
                        }
                        if (grid[i][j].getBlack())
                            valid = 1;
                        if (valid == 1 && !grid[i][j].getBlack()) {
                            flag = true;
                            Log.d("White-Turn", " Up" + " " + olc + " " + ilc + "");
                            break;
                        }
                        i--;
                    }
                    i = olc + 1;
                    j = ilc;
                    valid = 0;
                    //Down
                    while (i < 8 && !flag && grid[i][j].getClicked()) {

                        if (!grid[i][j].getBlack() && valid == 0) {
                            break;
                        }
                        if (grid[i][j].getBlack())
                            valid = 1;
                        if (valid == 1 && !grid[i][j].getBlack()) {
                            Log.d("White-Turn", " Down" + " " + olc + " " + ilc + "");
                            flag = true;
                            break;
                        }
                        i++;
                    }
                    i = olc;
                    j = ilc - 1;
                    valid = 0;
                    //Left
                    while (j >= 0 && !flag && grid[i][j].getClicked()) {

                        if (!grid[i][j].getBlack() && valid == 0) {
                            break;
                        }
                        if (grid[i][j].getBlack())
                            valid = 1;
                        if (valid == 1 && !grid[i][j].getBlack()) {
                            Log.d("White-Turn", " Left" + " " + olc + " " + ilc + "");
                            flag = true;
                            break;
                        }
                        j--;
                    }
                    i = olc;
                    j = ilc + 1;
                    valid = 0;
                    //Right
                    while (j < 8 && !flag && grid[i][j].getClicked()) {

                        if (!grid[i][j].getBlack() && valid == 0) {
                            break;
                        }
                        if (grid[i][j].getBlack())
                            valid = 1;
                        if (valid == 1 && !grid[i][j].getBlack()) {
                            Log.d("White-Turn", " Right" + " " + olc + " " + ilc + "");
                            flag = true;
                            break;
                        }
                        j++;
                    }
                }
                if (flag) {
                    grid[olc][ilc].setCanClicked(true);
                    grid[olc][ilc].setBackgroundResource(R.color.grey);
                    count++;
                } else {
                    grid[olc][ilc].setCanClicked(false);
                    grid[olc][ilc].setBackgroundResource(R.drawable.rect);
                }
            }
        }
    }

    @Override
    public void onClick(View view) {
        MyButton button = (MyButton) view;
        if (button.getClicked() || gameOver || !button.isCanClicked())
            return;

        if (blackTurn) {
            button.setBlack(true);
            button.setClicked(true);
            button.setBackgroundResource(R.drawable.blackrect);
        } else {
            button.setBackgroundResource(R.drawable.whiterect);
            button.setBlack(false);
            button.setClicked(true);
        }
        //Flip
        if(blackTurn){
            checkBlack(button.getAtX(),button.getAtY());
        }
        else
            checkWhite(button.getAtX(),button.getAtY());
        button.setClicked(true);
        blackTurn = !blackTurn;
        updateBoard();
        if (count == 0) {
            blackTurn = !blackTurn;
            updateBoard();
            if (count == 0)
                gameOver = true;
            Toast.makeText(this, "PASS!!", Toast.LENGTH_SHORT).show();
        }

    }
    public void checkBlack(int row,int col){

        int i,j,count;
        //UP
        i = row-1;
        j = col;
        count = 0;
        while(i>=0&&grid[i][j].getClicked()&&!grid[i][j].getBlack()) {
            count++;
            i--;
        }
        if(i>=0&&i<8&&j>=0&&j<8&&!grid[i][j].getClicked())
        {
            count = 0;
        }
        while(count>0)
        {
            grid[row-count][col].setBackgroundResource(R.drawable.blackrect);
            grid[row-count][col].setBlack(true);
            count--;
        }
        //Down
        i = row+1;
        j = col;
        count = 0;
        while(i<8&&grid[i][j].getClicked()&&!grid[i][j].getBlack()) {
            count++;
            i++;
        }
        if(i>=0&&i<8&&j>=0&&j<8&&!grid[i][j].getClicked())
        {
            count = 0;
        }
        while(count>0)
        {
            grid[row+count][col].setBackgroundResource(R.drawable.blackrect);
            grid[row+count][col].setBlack(true);
            count--;
        }
        //Left
        i = row;
        j = col-1;
        count = 0;
        while(j>=0&&grid[i][j].getClicked()&&!grid[i][j].getBlack())
        {
            j--;
            count++;
        }
        if(i>=0&&i<8&&j>=0&&j<8&&!grid[i][j].getClicked())
        {
            count = 0;
        }
        while(count>0)
        {
            grid[row][col-count].setBackgroundResource(R.drawable.blackrect);
            grid[row][col-count].setBlack(true);
            count--;
        }
        //Right
        i = row;
        j = col+1;
        count = 0;
        while(j<8&&grid[i][j].getClicked()&&!grid[i][j].getBlack())
        {
            j++;
            count++;
        }
        if(i>=0&&i<8&&j>=0&&j<8&&!grid[i][j].getClicked())
        {
            count = 0;
        }
        while(count>0)
        {
            grid[row][col+count].setBackgroundResource(R.drawable.blackrect);
            grid[row][col+count].setBlack(true);
            count--;
        }
        //Up_Right
        i = row-1;
        j = col+1;
        count = 0;
        while(j<8&&i>=0&&grid[i][j].getClicked()&&!grid[i][j].getBlack())
        {
            j++;
            i--;
            count++;
        }
        if(i>=0&&i<8&&j>=0&&j<8&&!grid[i][j].getClicked())
        {
            count = 0;
        }
        while(count>0)
        {
            grid[row-count][col+count].setBackgroundResource(R.drawable.blackrect);
            grid[row-count][col+count].setBlack(true);
            count--;
        }
        //up_left
        i = row-1;
        j = col-1;
        count = 0;
        while(j>=0&&i>=0&&grid[i][j].getClicked()&&!grid[i][j].getBlack())
        {
            j--;
            i--;
            count++;
        }
        if(i>=0&&i<8&&j>=0&&j<8&&!grid[i][j].getClicked())
        {
            count = 0;
        }
        while(count>0)
        {
            grid[row-count][col-count].setBackgroundResource(R.drawable.blackrect);
            grid[row-count][col-count].setBlack(true);
            count--;
        }
        //down_left
        i = row+1;
        j = col-1;
        count = 0;
        while(j>=0&&i<8&&grid[i][j].getClicked()&&!grid[i][j].getBlack())
        {
            j--;
            i++;
            count++;
        }
        if(i>=0&&i<8&&j>=0&&j<8&&!grid[i][j].getClicked())
        {
            count = 0;
        }
        while(count>0)
        {
            grid[row+count][col-count].setBackgroundResource(R.drawable.blackrect);
            grid[row+count][col-count].setBlack(true);
            count--;
        }
        //down_right
        i = row+1;
        j = col+1;
        count = 0;
        while(j<8&&i<8&&grid[i][j].getClicked()&&!grid[i][j].getBlack())
        {
            j++;
            i++;
            count++;
        }
        if(i>=0&&i<8&&j>=0&&j<8&&!grid[i][j].getClicked())
        {
            count = 0;
        }
        while(count>0)
        {
            grid[row+count][col+count].setBackgroundResource(R.drawable.blackrect);
            grid[row+count][col+count].setBlack(true);
            count--;
        }
    }
    /*******************************************************************/
    public void checkWhite(int row, int col){
        int i,j,count;
        //UP
        i = row-1;
        j = col;
        count = 0;
        while(i>=0&&grid[i][j].getClicked()&&grid[i][j].getBlack()) {
            count++;
            i--;
        }
        if(i>=0&&i<8&&j>=0&&j<8&&!grid[i][j].getClicked())
        {
            count = 0;
        }
        while(count>0)
        {
            grid[row-count][col].setBackgroundResource(R.drawable.whiterect);
            grid[row-count][col].setBlack(false);
            count--;
        }
        //Down
        i = row+1;
        j = col;
        count = 0;
        while(i<8&&grid[i][j].getClicked()&&grid[i][j].getBlack()) {
            count++;
            i++;
        }
        if(i>=0&&i<8&&j>=0&&j<8&&!grid[i][j].getClicked())
        {
            count = 0;
        }
        while(count>0)
        {
            grid[row+count][col].setBackgroundResource(R.drawable.whiterect);
            grid[row+count][col].setBlack(false);
            count--;
        }
        //Left
        i = row;
        j = col-1;
        count = 0;
        while(j>=0&&grid[i][j].getClicked()&&grid[i][j].getBlack())
        {
            j--;
            count++;
        }
        if(i>=0&&i<8&&j>=0&&j<8&&!grid[i][j].getClicked())
        {
            count = 0;
        }
        while(count>0)
        {
            grid[row][col-count].setBackgroundResource(R.drawable.whiterect);
            grid[row][col-count].setBlack(false);
            count--;
        }
        //Right
        i = row;
        j = col+1;
        count = 0;
        while(j<8&&grid[i][j].getClicked()&&grid[i][j].getBlack())
        {
            j++;
            count++;
        }
        if(i>=0&&i<8&&j>=0&&j<8&&!grid[i][j].getClicked())
        {
            count = 0;
        }
        while(count>0)
        {
            grid[row][col+count].setBackgroundResource(R.drawable.whiterect);
            grid[row][col+count].setBlack(false);
            count--;
        }
        //Up_Right
        i = row-1;
        j = col+1;
        count = 0;
        while(j<8&&i>=0&&grid[i][j].getClicked()&&grid[i][j].getBlack())
        {
            j++;
            i--;
            count++;
        }
        if(i>=0&&i<8&&j>=0&&j<8&&!grid[i][j].getClicked())
        {
            count = 0;
        }
        while(count>0)
        {
            grid[row-count][col+count].setBackgroundResource(R.drawable.whiterect);
            grid[row-count][col+count].setBlack(false);
            count--;
        }
        //up_left
        i = row-1;
        j = col-1;
        count = 0;
        while(j>=0&&i>=0&&grid[i][j].getClicked()&&grid[i][j].getBlack())
        {
            j--;
            i--;
            count++;
        }
        if(i>=0&&i<8&&j>=0&&j<8&&!grid[i][j].getClicked())
        {
            count = 0;
        }
        while(count>0)
        {
            grid[row-count][col-count].setBackgroundResource(R.drawable.whiterect);
            grid[row-count][col-count].setBlack(false);
            count--;
        }
        //down_left
        i = row+1;
        j = col-1;
        count = 0;
        while(j>=0&&i<8&&grid[i][j].getClicked()&&grid[i][j].getBlack())
        {
            j--;
            i++;
            count++;
        }
        if(i>=0&&i<8&&j>=0&&j<8&&!grid[i][j].getClicked())
        {
            count = 0;
        }
        while(count>0)
        {
            grid[row+count][col-count].setBackgroundResource(R.drawable.whiterect);
            grid[row+count][col-count].setBlack(false);
            count--;
        }
        //down_right
        i = row+1;
        j = col+1;
        count = 0;
        while(j<8&&i<8&&grid[i][j].getClicked()&&grid[i][j].getBlack())
        {
            j++;
            i++;
            count++;
        }
        if(i>=0&&i<8&&j>=0&&j<8&&!grid[i][j].getClicked())
        {
            count = 0;
        }
        while(count>0)
        {
            grid[row+count][col+count].setBackgroundResource(R.drawable.whiterect);
            grid[row+count][col+count].setBlack(false);
            count--;
        }
    }
}
