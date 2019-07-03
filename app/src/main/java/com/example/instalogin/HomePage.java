package com.example.instalogin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity implements View.OnClickListener {

    ImageView input, output;
    Button rock, paper, scissors, btnScore;
    int[] images = new int[]{
            R.mipmap.rock,
            R.mipmap.paper,
            R.mipmap.scissors
    };
    int userinput = 0;
    int humanScore, compScore;

    DatabaseReference dbScore;
    List<TotalScore> scrs;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        input = findViewById(R.id.iv_input);
        output = findViewById(R.id.iv_output);
        rock = findViewById(R.id.btn_rock);
        paper = findViewById(R.id.btn_paper);
        scissors = findViewById(R.id.btn_scissors);
        btnScore = findViewById(R.id.btnScore);

        rock.setOnClickListener( this);
        paper.setOnClickListener(this);
        scissors.setOnClickListener(this);

        dbScore = FirebaseDatabase.getInstance().getReference("Scores");
        scrs = new ArrayList<>();

    }




    public  void onClick(View v){

        int id = v.getId();
        switch (id) {
            case R.id.btn_rock:
                userinput = 1;
                input.setBackgroundResource(R.mipmap.rock);
                setOutput();
                break;

            case R.id.btn_paper:
                userinput = 2;
                input.setBackgroundResource(R.mipmap.paper);
                setOutput();
                break;

            case R.id.btn_scissors:
                userinput = 3;
                input.setBackgroundResource(R.mipmap.scissors);
                setOutput();
                break;
        }
    }

    private void setOutput() {

        int imageId = (int) (Math.random() * images.length);
        output.setBackgroundResource(images[imageId]);
        checkresult(imageId);
    }

    private void checkresult(int imageId) {
        if (userinput == 1 && imageId == 0) {
            showresult(2);
        }
        else if (userinput == 1 && imageId == 1) {
            showresult(0);
        }
        else if (userinput == 1 && imageId == 2) {
            showresult(1);
        }
        else if (userinput == 2 && imageId == 0) {
            showresult(1);
        }
        else if (userinput == 2 && imageId == 1) {
            showresult(2);
        }
        else if (userinput == 2 && imageId == 2) {
            showresult(0);
        }
        else if (userinput == 3 && imageId == 0) {
            showresult(0);
        }
        else if (userinput == 3 && imageId == 1) {
            showresult(1);
        }
        else if (userinput == 3 && imageId == 2) {
            showresult(2);
        }
    }

    private void showresult(int result) {

        if (result == 0) {
            humanScore += 0;
            compScore += 2;
            String msg = "Score Human" + (humanScore) + "Computer" + (compScore);
            Toast.makeText(getApplicationContext(), msg , Toast.LENGTH_SHORT).show();
            //scoreCalc(0);
            String id = dbScore.push().getKey();
            TotalScore score2 = new TotalScore(id, msg);
            dbScore.child(id).setValue(score2);
        } else if (result == 1) {
            humanScore += 2;
            compScore += 0;
            String msg = "Score Human" + (humanScore) + "Computer" + (compScore);
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            //scoreCalc(2);
            String id = dbScore.push().getKey();
            TotalScore score2 = new TotalScore(id, msg);
            dbScore.child(id).setValue(score2);
        }
        else {
            humanScore += 1;
            compScore += 1;
            String msg = "Score Human" + (humanScore) + "Computer" + (compScore);
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            //scoreCalc(1);
            String id = dbScore.push().getKey();
            TotalScore score2 = new TotalScore(id, msg);
            dbScore.child(id).setValue(score2);
        }


    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

//    public void scoreCalc() {
//
//
//
//    }

    public void btnScoreClick(View view) {

        Intent intent = new Intent(HomePage.this,scorePage.class);
        //intent.putExtra("score", ;)
        startActivity(intent);
    }
}
