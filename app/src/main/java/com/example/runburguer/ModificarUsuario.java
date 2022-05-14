package com.example.runburguer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class ModificarUsuario extends AppCompatActivity {
    EditText nombreusuario, usuarioUser, telefonousuario, correousuario, nivelacceso;
    TextView idusuario;
    String[] elementos, elementos2, elementos3, elementos4, elementos5, elementos6;
    String doc;
    String nombre;
    String apellido;
    String telefono;
    String correo;
    String usuario;
    String clave;
    String perfil;
    String iconousu;
    private StringRequest stringRequest;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_usuario);
        idusuario = (TextView) findViewById(R.id.idusuario);
        nombreusuario = (EditText) findViewById(R.id.nombreusuario);
        usuarioUser = (EditText) findViewById(R.id.usuarioUser);
        telefonousuario = (EditText) findViewById(R.id.telefonousuario);
        correousuario = (EditText) findViewById(R.id.correousuario);
        nivelacceso = (EditText) findViewById(R.id.nivelacceso);

        String iduser = getIntent().getStringExtra("iduser");
        idusuario.setText("" + iduser);

        String nombyape = getIntent().getStringExtra("nombyape");
        nombreusuario.setText("" + nombyape);

        String usuario = getIntent().getStringExtra("usuario");
        usuarioUser.setText("" + usuario);

        String tel = getIntent().getStringExtra("tel");
        telefonousuario.setText("" + tel);

        String email = getIntent().getStringExtra("email");
        correousuario.setText("" + email);

        String nivel = getIntent().getStringExtra("nivel");
        nivelacceso.setText("" + nivel);

    }

    public void navAtras(View view) {
        Intent i = new Intent(this, GestionUsuarios.class);
        String nombre = getIntent().getExtras().getString("nombre");
        String apellido = getIntent().getExtras().getString("apellido");
        i.putExtra("nombre", nombre);
        i.putExtra("apellido", apellido);
        startActivity(i);

    }

    public void modif(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(ModificarUsuario.this, R.style.Theme_Material3_Dark_Dialog_Alert);
        builder.setTitle("Importante");
        builder.setMessage("¿Quieres modificar los datos?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ejecutarDatos("http://" + URL.IP + "/" + URL.sitio + "/" + "modificarusuadmin.php"
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
                parametros.put("id_usuario", idusuario.getText().toString());
                parametros.put("nombre", nombreusuario.getText().toString());
                parametros.put("telefono", telefonousuario.getText().toString());
                parametros.put("correo", correousuario.getText().toString());
                parametros.put("usuario", usuarioUser.getText().toString());
                parametros.put("id_perfil", nivelacceso.getText().toString());
                return parametros;
            }
        };
        //Describe como envíar los datos a la base de datos
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}