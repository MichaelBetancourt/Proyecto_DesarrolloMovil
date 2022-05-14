package com.example.runburguer;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
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

public class Adapterlistventas extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemname,itemname1,itemname2,itemname3,itemname4,itemname5,itemname6;
    private StringRequest stringRequest;
    private RequestQueue requestQueue;




    public Adapterlistventas(Activity context, String[] itemname,String[] itemname1,String[] itemname2,String[] itemname3,String[] itemname4, String[] itemname5,String[] itemname6) {
        super(context, R.layout.item_list_view_ventas,itemname);
        this.context=context;
        this.itemname=itemname;
        this.itemname1=itemname1;
        this.itemname2=itemname2;
        this.itemname3=itemname3;
        this.itemname4=itemname4;
        this.itemname5=itemname5;
        this.itemname6=itemname6;


    }

    public View getView(int posicion, View view, ViewGroup parent){
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.item_list_view_ventas,null,true);
        TextView fechaventa = (TextView) rowView.findViewById(R.id.fechaventa);
        TextView cantidadvendida = (TextView) rowView.findViewById(R.id.cantidadvendida);
        TextView mediodepago = (TextView) rowView.findViewById(R.id.mediodepago);
        TextView producto = (TextView) rowView.findViewById(R.id.productos);
        TextView usuario = (TextView) rowView.findViewById(R.id.usuarioa);
        TextView establecimiento = (TextView) rowView.findViewById(R.id.establecimiento);



        fechaventa.setText(itemname1[posicion]);
        cantidadvendida.setText(itemname2[posicion]);
        mediodepago.setText(itemname3[posicion]);
        producto.setText(itemname4[posicion]);
        usuario.setText(itemname5[posicion]);
        establecimiento.setText(itemname6[posicion]);




        ImageButton imgeliminar = (ImageButton)rowView.findViewById(R.id.imgeliminar);
        imgeliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminardatos("http://"+URL.IP+"/"+URL.sitio+"/"+"eliminarventa.php?fecha_venta="+fechaventa.getText().toString()+"");
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
                            Class.forName("com.example.runburguer.ModificarVenta"));


                    var.putExtra("id_venta", ""+itemname[posicion]);
                    var.putExtra("fecha_venta", ""+fechaventa.getText().toString());
                    var.putExtra("cantidad_vendida", ""+cantidadvendida.getText().toString());
                    var.putExtra("medio_de_pago", ""+mediodepago.getText().toString());
                    var.putExtra("productos_id_producto", ""+producto.getText().toString());
                    var.putExtra("usuarios_id_usuario", ""+usuario.getText().toString());
                    var.putExtra("establecimiento_id_establecimiento", ""+establecimiento.getText().toString());


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
                View rowView=inflater.inflate(R.layout.item_list_view_ventas,null,true);
                TextView fechaventa = (TextView) rowView.findViewById(R.id.fechaventa);

                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("fecha_venta", fechaventa.getText().toString());
                return parametros;

            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

}