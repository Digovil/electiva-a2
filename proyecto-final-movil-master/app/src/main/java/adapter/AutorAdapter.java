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

import Model.Autor;

import com.example.proyectofinal.AutorActivity;

public class AutorAdapter extends ArrayAdapter<Autor> {
    AppCompatImageView b;
    AutorActivity service;
    public AutorAdapter(Context context, int layout, List<Autor> autorList){
        super(context,0, autorList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Autor autor = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_autor_items,parent,false);
        }

        TextView txtNombre = convertView.findViewById(R.id.txtTitulo);
        TextView txtAdscripcion = convertView.findViewById(R.id.txtIssn);
        TextView txtEmail = convertView.findViewById(R.id.txtAnio);

        txtNombre.setText(autor.getNombre());
        txtAdscripcion.setText(autor.getAdscripcion());
        txtEmail.setText(autor.getEmail());


        return convertView;
    }


}
