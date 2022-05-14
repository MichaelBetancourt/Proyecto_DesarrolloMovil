package com.example.runburguer;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AgregarCliente extends AppCompatActivity {

    Button vl;
    EditText nombreusuario,apellidousuario, telefonousuario, corusuario;
    private StringRequest stringRequest;
    private RequestQueue requestQueue;
    TextView nusu;
    String nom,ape;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_cliente);

        vl = findViewById(R.id.btnVolver);

        nombreusuario = (EditText) findViewById(R.id.editnombre);
        apellidousuario = (EditText) findViewById(R.id.editapellido);
        corusuario = (EditText) findViewById(R.id.editcorreo);
        telefonousuario = (EditText) findViewById(R.id.edittelefono);




        vl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sj = new Intent(AgregarCliente.this, Login.class);
                startActivity(sj);
                Toast.makeText(getApplicationContext(),"PASA A MENU PRINCIPAL", Toast.LENGTH_SHORT).show();

            }
        });



    }
    public void agregar(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(AgregarCliente.this, R.style.Theme_Material3_Dark_Dialog_Alert);
        builder.setTitle("Importante");
        builder.setMessage("¿Quieres agregar los datos?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ejecutarDatos("http://" + URL.IP + "/" + URL.sitio + "/" + "insertar.php");
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"Cancel...",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .setCancelable(false)
                .show();
    }

    public String generaUser(){
        String nombre = ""+nombreusuario.getText().toString();
        String apellido =""+ apellidousuario.getText().toString();
        String usuario = (nombre.charAt(0) + apellido+ apellido.charAt(0)).toLowerCase();

        return usuario;
    }



    public String contraseña() {
        String contrasenia = "";
        int min = 1;
        int max = 250;

        Random random = new Random();
        int valor = random.nextInt((max + min) + min * 100000);
        contrasenia = "" + valor;
    return contrasenia;
    }




    public void ejecutarDatos(String URL) {
        stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "OPERACIÓN ÉXITOSA", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("nombre", nombreusuario.getText().toString());
                parametros.put("apellido", apellidousuario.getText().toString());
                parametros.put("correo", corusuario.getText().toString());
                parametros.put("telefono", telefonousuario.getText().toString());
                parametros.put("usuario", ""+ generaUser());
                parametros.put("contrasena", ""+ contraseña());
                parametros.put("id_perfil","2");
                return parametros;
            }
        };
        //Describe como envíar los datos a la base de datos
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}





