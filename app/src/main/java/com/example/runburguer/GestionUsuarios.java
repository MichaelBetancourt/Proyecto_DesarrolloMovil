package com.example.runburguer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GestionUsuarios extends AppCompatActivity {

    private RequestQueue requestQueue;
    ListView lista;
    String id,nombre1,apellido1;
    String nombre;
    String apellido;
    String telefono;
    String correo;
    String usuario;
    String clave;
    String perfil;
    String[] elementos,elementos1,elementos2,elementos3,elementos4,elementos5,elementos6;
    EditText svSearch;
    TextView usuario1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_usuarios);

        requestQueue = Volley.newRequestQueue(this);
        lista=(ListView) findViewById(R.id.rvLista);
        ejecutarURL();

        svSearch = (EditText) findViewById(R.id.svSearch);

        usuario1 = (TextView)findViewById(R.id.usuario1);
       nombre1 = getIntent().getExtras().getString("nombre");
        apellido1 = getIntent().getExtras().getString("apellido");



        LayoutInflater inflater=getLayoutInflater();
        View rowView=inflater.inflate(R.layout.item_list_view,null,true);
        ImageButton imgmodificar = (ImageButton)rowView.findViewById(R.id.imgmodificar);

    }

    public void ejecutarURL(){

        listaUsuario("http://"+URL.IP+"/"+URL.sitio+"/"+"listar.php");
    }

    public void buscar(View view) {
        buscarDatos("http://"+URL.IP+"/"+URL.sitio+"/"+"buscar.php?usuario="+svSearch.getText().toString()+"");
        if(svSearch.getText().toString().equals("")){
            recreate();
        }

    }

    public void mostrar() {
        AdapterlistUsuarios adapter=new AdapterlistUsuarios(this, elementos,elementos1,elementos2,elementos3,elementos4,elementos5,elementos6);
        ListView lista = (ListView) findViewById(R.id.rvLista);
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                //Al hacer click en cualquiera de los elementos de la lista
            }
        });

    }

    public void listaUsuario(String URL){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, response -> {
            JSONObject jsonObject;
            elementos = new String[response.length()];
            elementos1 = new String[response.length()];
            elementos2 = new String[response.length()];
            elementos3 = new String[response.length()];
            elementos4 = new String[response.length()];
            elementos5 = new String[response.length()];
            elementos6 = new String[response.length()];
            for (int i = 0; i < response.length(); i++) {
                try {
                    jsonObject = response.getJSONObject(i);
                    id = jsonObject.getString("id_usuario");
                    nombre = jsonObject.getString("nombre");
                    apellido = jsonObject.getString("apellido");
                    correo = jsonObject.getString("correo");
                    telefono = jsonObject.getString("telefono");
                    usuario = jsonObject.getString("usuario");
                    clave = jsonObject.getString("contrasena");
                    perfil = jsonObject.getString("nombre_perfil");

                    elementos[i] =""+id;
                    elementos1[i] =""+nombre;
                    elementos2[i] =""+apellido;
                    elementos3[i] =""+usuario;
                    elementos4[i] =""+telefono;
                    elementos5[i] =""+correo;
                    elementos6[i] =""+perfil;




                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),
                            e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
            mostrar();
        }, error -> Toast.makeText(getApplicationContext(), "ERROR DE CONEXION",
                Toast.LENGTH_SHORT).show()
        );
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }


    public void buscarDatos(String URL) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject;
                elementos = new String[response.length()];
                elementos1 = new String[response.length()];
                elementos2 = new String[response.length()];
                elementos3 = new String[response.length()];
                elementos4 = new String[response.length()];
                elementos5 = new String[response.length()];
                elementos6 = new String[response.length()];
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        id = jsonObject.getString("id_usuario");
                        nombre = jsonObject.getString("nombre");
                        apellido = jsonObject.getString("apellido");
                        telefono = jsonObject.getString("telefono");
                        correo = jsonObject.getString("correo");
                        usuario = jsonObject.getString("usuario");
                        clave = jsonObject.getString("contrasena");
                        perfil = jsonObject.getString("nombre_perfil");

                        elementos[i] =""+id;
                        elementos1[i] =""+nombre;
                        elementos2[i] =""+apellido;
                        elementos3[i] =""+usuario;
                        elementos4[i] =""+telefono;
                        elementos5[i] =""+correo;
                        elementos6[i] =""+perfil;

                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    mostrar();
                }

                Toast.makeText(getApplicationContext(), "se encontro el dato", Toast.LENGTH_SHORT).show();

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "No se encontró el dato", Toast.LENGTH_SHORT).show();
            }

        }

        );

        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);


    }

    public void navagregar (View view){

        Intent in;
        in = new Intent(GestionUsuarios.this, AgregarPersona.class);
        in.putExtra("nombre", nombre1);
        in.putExtra("apellido", apellido1);
        startActivity(in);
        Toast.makeText(getApplicationContext(),"PASA A AGREGAR USUARIO", Toast.LENGTH_SHORT).show();

    }
}