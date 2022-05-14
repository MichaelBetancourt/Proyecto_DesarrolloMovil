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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ModificarProducto extends AppCompatActivity {
    EditText nombreproducto, descripcionproducto, valorproducto, idestablecimiento;
    TextView idproducto;
    String[] elementos, elementos2, elementos3, elementos4,elementoid;
    String nombreprod,descripprod,valorprod,idesta;
    private StringRequest stringRequest;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
        idproducto = (TextView) findViewById(R.id.idproducto);
        nombreproducto = (EditText) findViewById(R.id.nombreproducto);
        descripcionproducto = (EditText) findViewById(R.id.descripcionproducto);
        valorproducto = (EditText) findViewById(R.id.valorproducto);
        idestablecimiento = (EditText) findViewById(R.id.idestablecimiento);


        String iduser = getIntent().getStringExtra("idprod");
        idproducto.setText("" + iduser);

        String nombyape = getIntent().getStringExtra("nombreprd");
        nombreproducto.setText("" + nombyape);

        String usuario = getIntent().getStringExtra("descriprod");
        descripcionproducto.setText("" + usuario);

        String tel = getIntent().getStringExtra("valorprod");
        valorproducto.setText("" + tel);

        String email = getIntent().getStringExtra("establecimientoprod");
        idestablecimiento.setText("" + email);
    }

    public void navAtras(View view) {
        Intent i = new Intent(this, GestionProductos.class);
        startActivity(i);

    }

    public void modif(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(ModificarProducto.this, R.style.Theme_Material3_Dark_Dialog_Alert);
        builder.setTitle("Importante");
        builder.setMessage("¿Quieres modificar los datos?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ejecutarDatos("http://" + URL.IP + "/" + URL.sitio + "/" + "modificarproducto.php"
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
                parametros.put("id_producto", idproducto.getText().toString());
                parametros.put("nombre_producto", nombreproducto.getText().toString());
                parametros.put("descripcion_producto", descripcionproducto.getText().toString());
                parametros.put("valor_producto", valorproducto.getText().toString());
                parametros.put("establecimiento_id_establecimiento", idestablecimiento.getText().toString());
                return parametros;
            }
        };
        //Describe como envíar los datos a la base de datos
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}