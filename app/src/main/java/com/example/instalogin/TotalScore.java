package com.example.instalogin;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class TotalScore {

    private String id;
    private String Score;

    public TotalScore() {

    }

    public TotalScore(String id, String Score){
        this.id = id;
        this.Score = Score;
    }

    public String getId() {
        return id;
    }
    public String getScore() {
        return Score;
    }
}
