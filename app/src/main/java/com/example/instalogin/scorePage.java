package com.example.instalogin;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class scorePage extends AppCompatActivity {

    List<TotalScore> scrs;
    DatabaseReference dbScore;

    ListView listViewScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_page);

        dbScore = FirebaseDatabase.getInstance().getReference("Scores");


        listViewScore = findViewById(R.id.listViewScore);

        scrs = new ArrayList<>();


    }

    @Override
    protected void onStart() {
        super.onStart();

        dbScore.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                scrs.clear();

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {

                    TotalScore scr = postSnapshot.getValue(TotalScore.class);
                    scrs.add(scr);
                }

                ScoreList scoreAdapter = new ScoreList(scorePage.this, scrs);
                listViewScore.setAdapter(scoreAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
