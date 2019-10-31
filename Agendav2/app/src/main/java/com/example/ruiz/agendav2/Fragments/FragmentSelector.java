package com.example.ruiz.agendav2.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ruiz.agendav2.ObjetosYDaos.Archivo;
import com.example.ruiz.agendav2.ObjetosYDaos.ArchivoAdapter;
import com.example.ruiz.agendav2.R;
import com.example.ruiz.agendav2.mostrar;

import java.util.ArrayList;

public class FragmentSelector extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<Archivo>lista=new ArrayList<>();
    private Activity activity;
    private ArchivoAdapter adapter;

    public void setLista(ArrayList<Archivo> lista) {
        this.lista = lista;
    }

    public void setAdapter(ArchivoAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
            this.activity=(Activity)context;
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista=inflater.inflate(R.layout.fragment_selector,container,false);
        recyclerView=(RecyclerView)vista.findViewById(R.id.rcclrArchivoFragment);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int no=recyclerView.getChildAdapterPosition(v);
                Archivo ar=lista.get(no);

                ((mostrar)activity).mostrarDetalle(ar);
                return true;
            }
        });
        return vista;
    }
}
