package com.example.consurerestapi.adaptadores;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.consurerestapi.R;
import com.example.consurerestapi.entidades.Pelicula;

import java.util.List;


//1. Herencia indicando entidad a iterar
//2. Constructor
public class PeliculaAdapter extends ArrayAdapter<Pelicula> {
    //3. Atributos
    private Context context;
    private List<Pelicula> listaPeliculas;
    //4. Actualizar Constructor

    public PeliculaAdapter(@NonNull Context context, List<Pelicula> listaPeliculas) {
        super(context, R.layout.list_item_pelicula, listaPeliculas);
        this.context=context;
        this.listaPeliculas=listaPeliculas;
    }

    private void showModal(String message){
        AlertDialog.Builder dialogo= new AlertDialog.Builder(this.context);
        dialogo.setTitle("Confirmación Proceso");
        dialogo.setMessage(message);
        dialogo.setCancelable(false);

        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context,"Proceso Terminado correctamente",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialogo.setNegativeButton("Cancelar",null);
        dialogo.create().show();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
           convertView= LayoutInflater.from(context).inflate(R.layout.list_item_pelicula,parent,false);
        }

        //Capturar el objeto Pelicula
        Pelicula pelicula=listaPeliculas.get(position);
        //Referencia
        TextView tvItemTitulo=convertView.findViewById(R.id.tvItemTitulo);
        TextView tvItemDescripcion=convertView.findViewById(R.id.tvItemDescripcion);
        Button btnProceso= convertView.findViewById(R.id.btnProceso);

        //Asignando datos
        tvItemTitulo.setText(pelicula.getTitulo());
        tvItemDescripcion.setText("duración: "+String.valueOf(pelicula.getDuracion())+ " - "+ pelicula.getClasificacion());
        btnProceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showModal("ID PROCESO:"+String.valueOf(pelicula.getId()));
            }
        });

        return convertView;
    }
}
