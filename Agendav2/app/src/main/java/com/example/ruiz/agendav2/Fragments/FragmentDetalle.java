package com.example.ruiz.agendav2.Fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.ruiz.agendav2.ObjetosYDaos.Archivo;
import com.example.ruiz.agendav2.R;

public class FragmentDetalle extends Fragment{
    private Archivo archivo;
    //WIDGETS
    private ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista=inflater.inflate(R.layout.fragment_detalle,container,false);
        imageView=(ImageView)vista.findViewById(R.id.imgvwImagen);
        Uri uri=Uri.parse(archivo.getRuta());
        imageView.setImageURI(uri);
        return vista;
    }

    public void setArchivo(Archivo archivo) {
        this.archivo = archivo;
    }
}
