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

public class Reservas extends AppCompatActivity {
    Button vuelvo;
    TextView muestra;
    String nombre, apellido;
    EditText nombreres,fechares, horares, cantidad;
    private StringRequest stringRequest;
    private RequestQueue requestQueue;

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservas);
    muestra =(TextView) findViewById(R.id.nusu2);
    nombre = getIntent().getExtras().getString("nombre");
    apellido = getIntent().getExtras().getString("apellido");
    muestra.setText(""+nombre+" "+apellido);


        vuelvo = findViewById(R.id.btnVolver);
        vuelvo.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent volv = new Intent(Reservas.this, MenuPrincipal.class);
            startActivity(volv);
            Toast.makeText(getApplicationContext(),"PASA A MENU PRINCIPAL", Toast.LENGTH_SHORT).show();
        }
    });

    }

    public void agregar(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(Reservas.this, R.style.Theme_Material3_Dark_Dialog_Alert);
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
                parametros.put("nombre_persona_reserva", nombreres.getText().toString());
                parametros.put("fecha_reserva", fechares.getText().toString());
                parametros.put("hora_reserva", horares.getText().toString());
                parametros.put("cantidad_personas", cantidad.getText().toString());

                return parametros;
            }
        };
        //Describe como envíar los datos a la base de datos
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}