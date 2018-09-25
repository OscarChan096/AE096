package com.studio.oscar.agendaescolar.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.studio.oscar.agendaescolar.R;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle saved){
        super.onCreate(saved);
        setContentView(R.layout.about);
    }

    public void aboutdev(View v){
        Intent i = new Intent(About.this, about_dev.class);
        startActivity(i);
    }

}
