package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.proyectofinal.R;

import java.util.List;

import Model.Articulo;

import com.example.proyectofinal.ArticuloActivity;

public class ArticuloAdapter extends ArrayAdapter<Articulo> {
    AppCompatImageView b;
    ArticuloActivity service;
    public ArticuloAdapter(Context context, int layout, List<Articulo> articuloList){
        super(context,0, articuloList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Articulo articulo = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_articulo_items,parent,false);
        }

        TextView txtTitulo2 = convertView.findViewById(R.id.txtTitulo2);
        TextView txtPaginainicio = convertView.findViewById(R.id.txtPaginainicio);
        TextView txtPaginafin = convertView.findViewById(R.id.txtPaginafin);
        TextView txtIdrevista = convertView.findViewById(R.id.txtIdrevista);

        txtTitulo2.setText(articulo.getTitulo());
        txtPaginainicio.setText(Integer.toString(articulo.getPaginainicio()));
        txtPaginafin.setText(Integer.toString(articulo.getPaginafin()));
        txtIdrevista.setText(Integer.toString(articulo.getIdrevista()));


        return convertView;
    }


}
