package com.example.runburguer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class ModificarVenta extends AppCompatActivity {
    EditText fechaventa, cantidadvendida, mediodepago, idproductos, idusuarios,idestablecimiento;
    TextView idventa;
    String[] elementos, elementos2, elementos3, elementos4,elementoid;
    String nombreprod,descripprod,valorprod,idesta;
    private StringRequest stringRequest;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_venta);
        idventa = (TextView) findViewById(R.id.idventa);
        fechaventa = (EditText) findViewById(R.id.fechaventa);
        cantidadvendida = (EditText) findViewById(R.id.cantidadvendida1);
        mediodepago = (EditText) findViewById(R.id.mediodepago1);
        idproductos = (EditText) findViewById(R.id.idproductos);
        idusuarios = (EditText) findViewById(R.id.idusuarios);
        idestablecimiento = (EditText) findViewById(R.id.idestablecimiento);


        String idven = getIntent().getStringExtra("id_venta");
        idventa.setText("" + idven);

        String fechven = getIntent().getStringExtra("fecha_venta");
        fechaventa.setText("" + fechven);

        String cantven = getIntent().getStringExtra("cantidad_vendida");
        cantidadvendida.setText("" + cantven);

        String medpag = getIntent().getStringExtra("medio_de_pago");
        mediodepago.setText("" + medpag);

        String email = getIntent().getStringExtra("productos_id_producto");
        idestablecimiento.setText("" + email);

        String email1 = getIntent().getStringExtra("usuarios_id_usuario");
        idestablecimiento.setText("" + email1);

        String email2 = getIntent().getStringExtra("establecimiento_id_establecimiento");
        idestablecimiento.setText("" + email2);

    }

    public void navAtras(View view) {
        Intent i = new Intent(this, GestionProductos.class);
        startActivity(i);

    }

    public void modif(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(ModificarVenta.this, R.style.Theme_Material3_Dark_Dialog_Alert);
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
                parametros.put("id_venta", idventa.getText().toString());
                parametros.put("fecha_venta", fechaventa.getText().toString());
                parametros.put("cantidad_vedida", cantidadvendida.getText().toString());
                parametros.put("medio_de_pago", mediodepago.getText().toString());
                parametros.put("productos_id_producto", idproductos.getText().toString());
                parametros.put("usuarios_id_usuario", idusuarios.getText().toString());
                parametros.put("establecimiento_id_establecimiento", idestablecimiento.getText().toString());
                return parametros;
            }
        };
        //Describe como envíar los datos a la base de datos
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}

