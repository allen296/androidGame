package com.mygdx.game;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
    @Override
    protected void onStart(){
        super.onStart();
        Intent miIntent=new Intent(this, Servicio.class);
        this.startService(miIntent);
    }
        @Override
        public void onBackPressed() {
            AlertDialog.Builder db = new AlertDialog.Builder(this);

            db.setTitle("Salir");
            db.setMessage("Entonces... te vas? ");
            db.setPositiveButton("Ta lue crack", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                    launcher.super.onBackPressed();
                }
            });
            db.setNegativeButton("Nope", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                }
            });
            db.show();

    }
}
