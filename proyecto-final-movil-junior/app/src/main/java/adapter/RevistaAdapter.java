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
import com.example.proyectofinal.RevistaActivity;

import java.util.List;

import Model.Revista;

public class RevistaAdapter extends ArrayAdapter<Revista> {
    AppCompatImageView b;
    RevistaActivity service;
    public RevistaAdapter(Context context, int layout, List<Revista> revistaList){
        super(context,0, revistaList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Revista revista = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_revista_items,parent,false);
        }

        TextView txtNumero = convertView.findViewById(R.id.txtNumero);
        TextView txtTitulo = convertView.findViewById(R.id.txtTitulo);
        TextView txtAnio = convertView.findViewById(R.id.txtAnio);
        TextView txtIssn = convertView.findViewById(R.id.txtIssn);

        txtNumero.setText(Integer.toString(revista.getNumero()));
        txtTitulo.setText(revista.getTitulo());
        txtAnio.setText(revista.getAnio());
        txtIssn.setText(revista.getIssn());


        return convertView;
    }


}
