package com.example.runburguer;
import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.ImageButton;
import android.widget.ImageView;

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

public class Adapterlistproductos extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemid,itemname,itemname1,itemname2,itemname3;
    private StringRequest stringRequest;
    private RequestQueue requestQueue;


    public Adapterlistproductos(Activity context,String[] itemid, String[] itemname,String[] itemname1,String[] itemname2,String[] itemname3) {
        super(context, R.layout.item_list_view,itemname);
        this.context=context;
        this.itemid=itemid;
        this.itemname=itemname;
        this.itemname1=itemname1;
        this.itemname2=itemname2;
        this.itemname3=itemname3;

    }
    public View getView(int posicion, View view, ViewGroup parent){
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.item_list_view_productos,null,true);
        TextView nombreproducto = (TextView) rowView.findViewById(R.id.nombreproducto);
        TextView descripcionproducto = (TextView) rowView.findViewById(R.id.descripcionproducto);
        TextView valorproducto = (TextView) rowView.findViewById(R.id.valorproducto);
        TextView establecimiento = (TextView) rowView.findViewById(R.id.establecimiento);



        nombreproducto.setText(itemname[posicion]);
        descripcionproducto.setText(itemname1[posicion]);
        valorproducto.setText(itemname2[posicion]);
        establecimiento.setText(itemname3[posicion]);



        ImageButton imgeliminar = (ImageButton)rowView.findViewById(R.id.imgeliminar);
        imgeliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminardatos("http://"+URL.IP+"/"+URL.sitio+"/"+"eliminarproducto.php?nombre_producto="+nombreproducto.getText().toString()+"");
                context.recreate();
            }
        });

        ImageButton imgmodificar = (ImageButton)rowView.findViewById(R.id.imgmodificar);
        imgmodificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent var;
                try {
                    var = new Intent(context.getApplicationContext(),
                            Class.forName("com.example.runburguer.ModificarProducto"));


                    var.putExtra("idprod", ""+itemid[posicion]);
                    var.putExtra("nombreprd", ""+nombreproducto.getText().toString());
                    var.putExtra("descriprod", ""+descripcionproducto.getText().toString());
                    var.putExtra("valorprod", ""+valorproducto.getText().toString());
                    var.putExtra("establecimientoprod", ""+establecimiento.getText().toString());


                    Toast toast1 = Toast.makeText(context.getApplicationContext(),"¡LOS DATOS FUERON ENVIADOS!", Toast.LENGTH_SHORT);
                    toast1.show();

                    context.startActivity(var);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        return rowView;
    }



    public void eliminardatos(String URL) {

        stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context.getApplicationContext(), "EL PRODUCTO FUE ELIMINADO", Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context.getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {LayoutInflater inflater=context.getLayoutInflater();
                View rowView=inflater.inflate(R.layout.item_list_view_productos,null,true);
                TextView nombreproduc = (TextView) rowView.findViewById(R.id.nombreproducto);

                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("nombre_producto", nombreproduc.getText().toString());
                return parametros;

            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }



}