package com.example.runburguer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
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

public class GestionVentas extends AppCompatActivity {
    private RequestQueue requestQueue;
    String idventa,fechaventa,cantidadvendida,mediodepago,idproducto,idusuario,idestablecimiento;
    String[] elementoid,elementos,elementos1,elementos2,elementos3,elementos4,elementos5;
    EditText svSearch;
    ListView lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_ventas);

        requestQueue = Volley.newRequestQueue(this);
        lista=(ListView) findViewById(R.id.rvLista1);
        ejecutarURL();
        svSearch = (EditText) findViewById(R.id.svSearch);

        }

        public void ejecutarURL(){

            listaVenta("http://"+URL.IP+"/"+URL.sitio+"/"+"listarventas.php");
        }

        public void buscar(View view) {
            buscarproductos("http://"+URL.IP+"/"+URL.sitio+"/"+"buscarventa.php?fecha_venta="+svSearch.getText().toString()+"");
            if(svSearch.getText().toString().equals("")){
                recreate();
            }

        }

        public void mostrar() {
            Adapterlistventas adapter=new Adapterlistventas(this,elementoid,elementos,elementos1,elementos2,elementos3,elementos4,elementos5);
            ListView lista = (ListView) findViewById(R.id.rvLista1);
            lista.setAdapter(adapter);
            lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
                {
                    //Al hacer click en cualquiera de los elementos de la lista
                }
            });

        }

        public void listaVenta(String URL){
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, response -> {
                JSONObject jsonObject;
                elementoid = new String[response.length()];
                elementos = new String[response.length()];
                elementos1 = new String[response.length()];
                elementos2 = new String[response.length()];
                elementos3 = new String[response.length()];
                elementos4 = new String[response.length()];
                elementos5 = new String[response.length()];

                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        idventa = jsonObject.getString("id_venta");
                        fechaventa = jsonObject.getString("fecha_venta");
                        cantidadvendida = jsonObject.getString("cantidad_vendida");
                        mediodepago = jsonObject.getString("medio_de_pago");
                        idproducto = jsonObject.getString("nombre_producto");
                        idusuario = jsonObject.getString("nombre");
                        idestablecimiento = jsonObject.getString("nombre_establecimiento");


                        elementoid[i] =""+idventa;
                        elementos[i] =""+fechaventa;
                        elementos1[i] =""+cantidadvendida;
                        elementos2[i] =""+mediodepago;
                        elementos3[i] =""+idproducto;
                        elementos4[i] =""+idestablecimiento;
                        elementos5[i] =""+idestablecimiento;

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
                    elementos4 = new String[response.length()];
                    elementos5 = new String[response.length()];

                    for (int i = 0; i < response.length(); i++) {
                        try {
                            jsonObject = response.getJSONObject(i);
                            idventa = jsonObject.getString("id_venta");
                            fechaventa = jsonObject.getString("fecha_venta");
                            cantidadvendida = jsonObject.getString("cantidad_vendida");
                            mediodepago = jsonObject.getString("medio_de_pago");
                            idproducto = jsonObject.getString("nombre_producto");
                            idusuario = jsonObject.getString("nombre");
                            idestablecimiento = jsonObject.getString("nombre_establecimiento");


                            elementoid[i] =""+idventa;
                            elementos[i] =""+fechaventa;
                            elementos1[i] =""+cantidadvendida;
                            elementos2[i] =""+mediodepago;
                            elementos3[i] =""+idproducto;
                            elementos4[i] =""+idestablecimiento;
                            elementos5[i] =""+idestablecimiento;

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
            in = new Intent(GestionVentas.this, AgregarVenta.class);
            startActivity(in);
            Toast.makeText(getApplicationContext(),"PASA A AGREGAR PRODUCTO", Toast.LENGTH_SHORT).show();

        }

    }