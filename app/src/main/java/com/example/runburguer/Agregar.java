package com.example.runburguer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Agregar extends AppCompatActivity {
    TextView nusu;
    String nom,ape;
    Button volver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        volver = findViewById(R.id.btnVolver);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent v = new Intent(Agregar.this, MenuAdmin.class);
                startActivity(v);
                Toast.makeText(getApplicationContext(),"PASA A MENU PRINCIPAL", Toast.LENGTH_SHORT).show();

            }
        });

    }
}