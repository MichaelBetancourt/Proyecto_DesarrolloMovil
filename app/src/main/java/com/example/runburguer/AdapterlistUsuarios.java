package com.example.runburguer;

import android.app.Activity;
import android.content.Intent;

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

public class AdapterlistUsuarios extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemname,itemname1,itemname2,itemname3,itemname4,itemname5,itemname6;
    private StringRequest stringRequest;
    private RequestQueue requestQueue;




    public AdapterlistUsuarios(Activity context, String[] itemname,String[] itemname1,String[] itemname2,String[] itemname3,String[] itemname4, String[] itemname5,String[] itemname6) {
        super(context, R.layout.item_list_view,itemname);
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
        View rowView=inflater.inflate(R.layout.item_list_view,null,true);
        TextView textnombre = (TextView) rowView.findViewById(R.id.nombre);
        TextView textapellido = (TextView) rowView.findViewById(R.id.apellido);
        TextView textusuario = (TextView) rowView.findViewById(R.id.usuario1);
        TextView txttelefono = (TextView) rowView.findViewById(R.id.telefono);
        TextView txtcorreo = (TextView) rowView.findViewById(R.id.correo);
        TextView txtniveldeacceso = (TextView) rowView.findViewById(R.id.niveldeacceso);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imgusuario);


        textnombre.setText(itemname1[posicion]);
        textapellido.setText(itemname2[posicion]);
        textusuario.setText(itemname3[posicion]);
        txttelefono.setText(itemname4[posicion]);
        txtcorreo.setText(itemname5[posicion]);
        txtniveldeacceso.setText(itemname6[posicion]);



        ImageButton imgeliminar = (ImageButton)rowView.findViewById(R.id.imgeliminar);
        imgeliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminardatos("http://"+URL.IP+"/"+URL.sitio+"/"+"eliminar.php?usuario="+textusuario.getText().toString()+"");
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
                            Class.forName("com.example.runburguer.ModificarUsuario"));


                    var.putExtra("iduser", ""+itemname[posicion]);
                    var.putExtra("nombyape", ""+textnombre.getText().toString());
                    var.putExtra("usuario", ""+textusuario.getText().toString());
                    var.putExtra("tel", ""+txttelefono.getText().toString());
                    var.putExtra("email", ""+txtcorreo.getText().toString());
                    var.putExtra("nivel",""+txtniveldeacceso.getText().toString());

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
                View rowView=inflater.inflate(R.layout.item_list_view,null,true);
                TextView textusuario = (TextView) rowView.findViewById(R.id.usuario1);

                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("usuario", textusuario.getText().toString());
                return parametros;

            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }






}
