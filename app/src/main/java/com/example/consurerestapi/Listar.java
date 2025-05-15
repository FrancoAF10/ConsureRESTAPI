package com.example.consurerestapi;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

public class Listar extends AppCompatActivity {

    //Constante que almacene el Endpoint
    private final String URL="https://apirest-cine-production-cc0f.up.railway.app/api/peliculas/";
    private final String URL_Local="http://192.168.101.20:3000/api/peliculas";//tiene que estar en ejecución

    //Los datos mostrados en el listview son externos
    RequestQueue requestQueue;

    ListView lstPeliculas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
           // v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        loadUI();

        getMovies();
    }
    private void getMovies(){
        //Paso 1: Canal de comunicación
        requestQueue= Volley.newRequestQueue(this);
        //Paso 2: Preparación de la solicitud
        //Definir objeto: cadena, Json, array de json
        JsonArrayRequest jsonRequest=new JsonArrayRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Peliculas",response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error WS", error.toString());
                    }
                }
        );

        //Paso 3: Envio de la solicitud por el canal de comunicación
        requestQueue.add(jsonRequest);
    }
    private void loadUI(){
        lstPeliculas=findViewById(R.id.lstPeliculas);
    }
}