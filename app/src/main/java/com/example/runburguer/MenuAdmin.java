package com.example.runburguer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MenuAdmin extends AppCompatActivity {
        ImageButton imb1, imb2, imb3, imb4, imb5;
        Intent in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);

        imb1 = findViewById(R.id.imageButton1);
        imb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre = getIntent().getExtras().getString("nombre");
                String apellido = getIntent().getExtras().getString("apellido");
                Intent j = new Intent(MenuAdmin.this, AgregarMenu.class);
                j.putExtra("nombre", nombre);
                j.putExtra("apellido", apellido);
                startActivity(j);
                Toast.makeText(getApplicationContext(),"PASA A AGREGAR CARTA", Toast.LENGTH_SHORT).show();
            }
        });

        imb2 = findViewById(R.id.imageButton2);
        imb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                String nombre = getIntent().getExtras().getString("nombre");
                String apellido = getIntent().getExtras().getString("apellido");
                Intent j = new Intent(MenuAdmin.this, GestionProductos.class);
                j.putExtra("nombre", nombre);
                j.putExtra("apellido", apellido);
                startActivity(j);
                Toast.makeText(getApplicationContext(),"PASA A GESTIÓN DE PRODUCTOS", Toast.LENGTH_SHORT).show();
            }
        });

        imb3 = findViewById(R.id.imageButton3);
        imb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v3) {
                String nombre = getIntent().getExtras().getString("nombre");
                String apellido = getIntent().getExtras().getString("apellido");
                Intent j = new Intent(MenuAdmin.this, GestionUsuarios.class);
                j.putExtra("nombre", nombre);
                j.putExtra("apellido", apellido);
                startActivity(j);
                Toast.makeText(getApplicationContext(),"PASA A GESTIÓN DE USUARIOS", Toast.LENGTH_SHORT).show();
            }
        });

        imb4 = findViewById(R.id.imageButton4);
        imb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v4) {
                String nombre = getIntent().getExtras().getString("nombre");
                String apellido = getIntent().getExtras().getString("apellido");
                Intent j = new Intent(MenuAdmin.this, VerComentarios.class);
                j.putExtra("nombre", nombre);
                j.putExtra("apellido", apellido);
                startActivity(j);
                Toast.makeText(getApplicationContext(),"PASA A SECCIÓN DE COMENTARIOS", Toast.LENGTH_SHORT).show();
            }
        });

        imb5 = findViewById(R.id.imageButton5);
        imb5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = getIntent().getExtras().getString("nombre");
                String apellido = getIntent().getExtras().getString("apellido");
                Intent j = new Intent(MenuAdmin.this, GestionVentas.class);
                j.putExtra("nombre", nombre);
                j.putExtra("apellido", apellido);
                startActivity(j);
                Toast.makeText(getApplicationContext(),"PASA A SECCIÓN DE VENTAS", Toast.LENGTH_SHORT).show();
            }
        });

    }


    }

