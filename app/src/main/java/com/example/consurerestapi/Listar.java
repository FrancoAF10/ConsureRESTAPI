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
import com.example.consurerestapi.adaptadores.PeliculaAdapter;
import com.example.consurerestapi.entidades.Pelicula;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

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
        //valor de retorno= [{},{},{}]
        JsonArrayRequest jsonRequest=new JsonArrayRequest(
                Request.Method.GET,
                URL_Local,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        procesarDatos(response);
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

    /**
     * Renderizar los datos obtenidos REST API en ListView utilizando Adaptador personalizado
     * */
    private void procesarDatos(JSONArray response){
        try{
            Pelicula pelicula;
            ArrayList<Pelicula>listaPeliculas= new ArrayList<>();

            for(int i=0;i<response.length();i++){
                JSONObject jsonObject=response.getJSONObject(i);

                pelicula=new Pelicula();
                pelicula.setId(jsonObject.getInt("id"));
                pelicula.setTitulo(jsonObject.getString("titulo"));
                pelicula.setDuracion(jsonObject.getInt("duracion"));
                pelicula.setClasificacion(jsonObject.getString("clasificacion"));
                pelicula.setAlanzamiento(jsonObject.getString("alanzamiento"));

                listaPeliculas.add(pelicula);
            }
            PeliculaAdapter adapter = new PeliculaAdapter(this,listaPeliculas);
            lstPeliculas.setAdapter(adapter);
        }catch (Exception e){
            Log.e("ERROR JSONARRAY",e.toString());
        }
    }
    private void loadUI(){
        lstPeliculas=findViewById(R.id.lstPeliculas);
    }
}