package com.example.ruiz.agendav2;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ruiz.agendav2.Fragments.FragmentAudio;
import com.example.ruiz.agendav2.Fragments.FragmentDetalle;
import com.example.ruiz.agendav2.Fragments.FragmentSelector;
import com.example.ruiz.agendav2.Fragments.FragmentVideo;
import com.example.ruiz.agendav2.ObjetosYDaos.Archivo;
import com.example.ruiz.agendav2.ObjetosYDaos.ArchivoAdapter;
import com.example.ruiz.agendav2.ObjetosYDaos.DaoArchivo;
import com.example.ruiz.agendav2.ObjetosYDaos.DaoFicha;
import com.example.ruiz.agendav2.ObjetosYDaos.Ficha;
import com.example.ruiz.agendav2.ObjetosYDaos.FichaAdapter;

import java.util.ArrayList;

public class mostrar extends AppCompatActivity {
    private Ficha ficha;
    private ArrayList<Archivo> archivoArrayList;
    private TextView titulo;
    private TextView descripcion;
    private RadioButton nota;
    private RadioButton tarea;
    private TextView recordatorio;
    private String titulo1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);

        Bundle bundle=getIntent().getExtras();
        titulo1=bundle.getString("titulo");
        //---------------------------------------------
        titulo=findViewById(R.id.txtTituloM);
        descripcion=findViewById(R.id.txtDescripcionM);
        nota=findViewById(R.id.rdNotaM);
        nota.setEnabled(false);
        tarea=findViewById(R.id.rdTareaM);
        tarea.setEnabled(false);
        recordatorio=findViewById(R.id.lblRecordatorioM);
        llenarCampos();
    }

    public void llenarCampos(){
        DaoArchivo daoArchivo=new DaoArchivo(getApplicationContext());
        DaoFicha daoFicha=new DaoFicha(getApplicationContext());
        Toast.makeText(this,titulo1,Toast.LENGTH_SHORT).show();
        ficha=daoFicha.seleccionarFicha(titulo1);
        archivoArrayList=daoArchivo.seleccionarArchivos(ficha);
        titulo.setText(ficha.getTitulo());
        descripcion.setText(ficha.getDescripcion());
        if(ficha.getTipo().equals("nota")){
            nota.setChecked(true);
        }else if(ficha.getTipo().equals("tarea")){
            tarea.setChecked(true);
            recordatorio.setText(ficha.getFechaRecordatorio());
        }
//        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(linearLayoutManager);
        ArchivoAdapter adapter=new ArchivoAdapter(this,archivoArrayList);
        //recyclerView.setAdapter(adapter);
        if(findViewById(R.id.contenedor)!=null&&(getSupportFragmentManager().findFragmentById(R.id.contenedor)==null)){
            FragmentSelector primerFragment=new FragmentSelector();
            primerFragment.setLista(archivoArrayList);
            primerFragment.setAdapter(adapter);
            getSupportFragmentManager().beginTransaction().add(R.id.contenedor,primerFragment).commit();
        }
    }

    public void mostrarDetalle(Archivo archivo){
        if(archivo.getTipo().equals("imagen")){
            FragmentDetalle detalle=new FragmentDetalle();
            detalle.setArchivo(archivo);
            FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.contenedor,detalle);
            transaction.addToBackStack(null);
            transaction.commit();
        }else if(archivo.getTipo().equals("video")){
            FragmentVideo detalle1=new FragmentVideo();
            detalle1.setArchivo(archivo);
            FragmentTransaction transaction1=getSupportFragmentManager().beginTransaction();
            transaction1.replace(R.id.contenedor,detalle1);
            transaction1.addToBackStack(null);
            transaction1.commit();
        }else if(archivo.getTipo().equals("audio")){
            FragmentAudio detalle2=new FragmentAudio();
            detalle2.setArchivo(archivo);
            FragmentTransaction transaction2=getSupportFragmentManager().beginTransaction();
            transaction2.replace(R.id.contenedor,detalle2);
            transaction2.addToBackStack(null);
            transaction2.commit();
        }
    }
}
