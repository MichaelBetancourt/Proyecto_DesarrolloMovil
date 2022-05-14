package com.example.runburguer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {
    TextView nusu;
    String nom,ape;
    EditText editusuario, editpass;
    String perfil1 ="1";
    String perfil2 ="2";
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editusuario=(EditText)findViewById(R.id.Correo);
        editpass=(EditText)findViewById(R.id.Contraseña);
    }



    public void registrarse (View view){

        Intent j = new Intent(Login.this, AgregarCliente.class);
        startActivity(j);

    }

    public void iniciarsesion (View view){
        loginUsuario("http://"+URL.IP+"/"+URL.sitio+"/validarsesion.php?"+"usuario="+editusuario.getText().toString()+ "&contrasena="+editpass.getText().toString());

    }

    public void loginUsuario(String URL){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, response -> {
            JSONObject jsonObject;
            for (int i = 0; i < response.length(); i++) {
                try {
                    jsonObject = (JSONObject) response.getJSONObject(i);
                    if(editusuario.getText().toString().equals(jsonObject.getString("usuario")) &&
                            editpass.getText().toString().equals(jsonObject.getString("contrasena"))){

                    }
                    if (perfil1.equals(jsonObject.getString("id_perfil"))) {
                        Intent j = new Intent(Login.this, MenuPrincipal.class);
                        j.putExtra("id", jsonObject.getString("id_usuario"));
                        j.putExtra("nombre", jsonObject.getString("nombre"));
                        j.putExtra("apellido", jsonObject.getString("apellido"));
                        j.putExtra("correo", jsonObject.getString("correo"));
                        j.putExtra("telefono", jsonObject.getString("telefono"));
                        startActivity(j);
                    }
                    else if(perfil2.equals(jsonObject.getString("id_perfil"))){
                        Intent j = new Intent(Login.this, MenuAdmin.class);
                        j.putExtra("id", jsonObject.getString("id_usuario"));
                        j.putExtra("nombre", jsonObject.getString("nombre"));
                        j.putExtra("apellido", jsonObject.getString("apellido"));
                        j.putExtra("correo", jsonObject.getString("correo"));
                        j.putExtra("telefono", jsonObject.getString("telefono"));
                        startActivity(j);
                    }

                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        }, error -> Toast.makeText(getApplicationContext(), "DATOS INCORRECTOS, INTENTE DE NUEVO",
                Toast.LENGTH_SHORT).show()
        );
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

}
