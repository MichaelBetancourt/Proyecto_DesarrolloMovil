package com.example.runburguer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class AgregarPersona extends AppCompatActivity {

    Button vl;
    EditText nombreusuario,apellidousuario, usuarioUser, telefonousuario, corusuario, contrausuario;
    RadioButton radadmin, radminclast;
    String nombre1,apellido1;
    private StringRequest stringRequest;
    private RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_persona);
    vl = findViewById(R.id.btnVolver);

        nombreusuario = (EditText) findViewById(R.id.editnombrepro);
        apellidousuario = (EditText) findViewById(R.id.editdescrip);
        corusuario = (EditText) findViewById(R.id.editvalor);
        telefonousuario = (EditText) findViewById(R.id.edittelefono);
        usuarioUser = (EditText)findViewById(R.id.editusuario);
        contrausuario = (EditText) findViewById(R.id.editcontr);
        RadioGroup contenedor = (RadioGroup) findViewById(R.id.grupoperfil);
        radadmin= (RadioButton)findViewById(R.id.radiosede);
        radminclast= (RadioButton)findViewById(R.id.radioclient);
        nombre1 = getIntent().getExtras().getString("nombre");
        apellido1 = getIntent().getExtras().getString("apellido");






        vl.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent sj = new Intent(AgregarPersona.this, MenuAdmin.class);
            sj.putExtra("nombre", nombre1);
            sj.putExtra("apellido", apellido1);
            startActivity(sj);
            Toast.makeText(getApplicationContext(),"PASA A MENU PRINCIPAL", Toast.LENGTH_SHORT).show();

        }
    });



    }
    public void agregar(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(AgregarPersona.this, R.style.Theme_Material3_Dark_Dialog_Alert);
        builder.setTitle("Importante");
        builder.setMessage("¿Quieres agregar los datos?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ejecutarDatos("http://" + URL.IP + "/" + URL.sitio + "/" + "insertar.php"
                        );
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
                parametros.put("usuario", usuarioUser.getText().toString());
                parametros.put("contrasena", contrausuario.getText().toString());

                if(radadmin.isChecked()){

                    parametros.put("id_perfil","1");

                }else if (radminclast.isChecked()){

                    parametros.put

                            ("id_perfil","2");

                }
                return parametros;
            }
        };
        //Describe como envíar los datos a la base de datos
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}