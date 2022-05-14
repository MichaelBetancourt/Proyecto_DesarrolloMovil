package com.example.runburguer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class GestionProductos extends AppCompatActivity {
    Button btnVolver;
    Intent in;
    Button Modificar;
    private RequestQueue requestQueue;
    String idproducto,nombreproducto,valorproducto,descripcionproducto,idestablecimiento;
    String[] elementoid,elementos,elementos1,elementos2,elementos3;
    EditText svSearch;
    ListView lista;
    String nombre1,apellido1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_productos);
        requestQueue = Volley.newRequestQueue(this);
        lista=(ListView) findViewById(R.id.rvLista);
        ejecutarURL();
        svSearch = (EditText) findViewById(R.id.svSearch1);
        nombre1 = getIntent().getExtras().getString("nombre");
        apellido1 = getIntent().getExtras().getString("apellido");




        LayoutInflater ifla = getLayoutInflater();
        View rowView=ifla.inflate(R.layout.item_list_view, null,true);
        ImageButton imgmodifi = (ImageButton)rowView.findViewById(R.id.imgmodificar);
    }

        public void ejecutarURL(){

            listaUsuario("http://"+URL.IP+"/"+URL.sitio+"/"+"listarproducto.php");
        }

    public void buscar(View view) {
        buscarproductos("http://"+URL.IP+"/"+URL.sitio+"/"+"buscarproducto.php?nombre_producto="+svSearch.getText().toString()+"");
        if(svSearch.getText().toString().equals("")){
            recreate();
        }

    }

        public void mostrar() {
            Adapterlistproductos adapter=new Adapterlistproductos(this,elementoid,elementos,elementos1,elementos2,elementos3);
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
                elementoid = new String[response.length()];
                elementos = new String[response.length()];
                elementos1 = new String[response.length()];
                elementos2 = new String[response.length()];
                elementos3 = new String[response.length()];

                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        idproducto = jsonObject.getString("id_producto");
                        nombreproducto = jsonObject.getString("nombre_producto");
                        descripcionproducto = jsonObject.getString("descripcion_producto");
                        valorproducto = jsonObject.getString("valor_producto");
                        idestablecimiento = jsonObject.getString("establecimiento_id_establecimiento");


                        elementoid[i] =""+idproducto;
                        elementos[i] =""+nombreproducto;
                        elementos1[i] =""+descripcionproducto;
                        elementos2[i] =""+valorproducto;
                        elementos3[i] =""+idestablecimiento;


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


        public void buscarproductos(String URL) {
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    JSONObject jsonObject;
                    elementoid = new String[response.length()];
                    elementos = new String[response.length()];
                    elementos1 = new String[response.length()];
                    elementos2 = new String[response.length()];
                    elementos3 = new String[response.length()];

                    for (int i = 0; i < response.length(); i++) {
                        try {
                            jsonObject = response.getJSONObject(i);
                            idproducto = jsonObject.getString("id_producto");
                            nombreproducto = jsonObject.getString("nombre_producto");
                            descripcionproducto = jsonObject.getString("descripcion_producto");
                            valorproducto = jsonObject.getString("valor_producto");
                            idestablecimiento = jsonObject.getString("establecimiento_id_establecimiento");


                            elementoid[i] =""+idproducto;
                            elementos[i] =""+nombreproducto;
                            elementos1[i] =""+descripcionproducto;
                            elementos2[i] =""+valorproducto;
                            elementos3[i] =""+idestablecimiento;

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
            in = new Intent(GestionProductos.this, Agregarproducto.class);
            in.putExtra("nombre", nombre1);
            in.putExtra("apellido", apellido1);
            startActivity(in);
            Toast.makeText(getApplicationContext(),"PASA A AGREGAR PRODUCTO", Toast.LENGTH_SHORT).show();

        }

    }



