package com.example.consurerestapi;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class Registrar extends AppCompatActivity {
    private final String URL="https://apirest-cine-production-cc0f.up.railway.app/api/peliculas/4";
    RequestQueue requestQueue;


    Button RegistarPelicula, EliminaPelicula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registrar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        loadUI();
        RegistarPelicula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMovie();
            }
        });
        EliminaPelicula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteMovie();
            }
        });
    }
    private void deleteMovie(){
        requestQueue= Volley.newRequestQueue(this);
        //Paso 2:
        JsonObjectRequest jsonRequest= new JsonObjectRequest(
                Request.Method.DELETE,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Condición",response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error WS",error.toString());
                    }
                }
        );

        //Paso 3:
        requestQueue.add(jsonRequest);
    }
    private void saveMovie(){
        //Paso 1:
        requestQueue= Volley.newRequestQueue(this);

        //Paso 1.1:
        JSONObject datos= new JSONObject();
        //Paso 1.2: La asignación debe ir en try catch
        try{
            datos.put("titulo","Terminator 3");
            datos.put("duracion",105);
            datos.put("clasificacion","+14");
            datos.put("alanzamiento","2009");

        }catch (Exception e){
            Log.e("Error en el JSON",e.toString());
        }


        //Paso 2:
        JsonObjectRequest jsonRequest= new JsonObjectRequest(
                Request.Method.POST,
                URL,
                datos,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Condición",response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error WS",error.toString());
                    }
                }
        );

        //Paso 3:
        requestQueue.add(jsonRequest);
    }
    private void loadUI(){
        RegistarPelicula=findViewById(R.id.btnRegistrarPelicula);
        EliminaPelicula=findViewById(R.id.btnEliminarPelicula);
    }
}