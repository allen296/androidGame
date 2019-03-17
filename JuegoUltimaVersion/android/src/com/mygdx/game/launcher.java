package com.mygdx.game;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class launcher extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
    }

    public void salirOnClick(View view) {
        System.exit(0);
    }

    public void iniciarOnClick(View view) {
        Intent lanzar= new Intent(this,AndroidLauncher.class);
        this.startActivity(lanzar);
    }
}
