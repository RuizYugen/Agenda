package com.example.ruiz.agendav2;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.ruiz.agendav2.ObjetosYDaos.BaseDeDatos;
import com.example.ruiz.agendav2.ObjetosYDaos.DaoFicha;
import com.example.ruiz.agendav2.ObjetosYDaos.Ficha;
import com.example.ruiz.agendav2.ObjetosYDaos.FichaAdapter;

import java.util.ArrayList;
import java.util.List;

public class Principal extends AppCompatActivity {

    private ArrayList<Ficha> lista=new ArrayList<>();
    private RecyclerView recyclerView;
    private EditText txtBuscar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        recyclerView=findViewById(R.id.rcclrFicha);
        txtBuscar=findViewById(R.id.txtBuscar);
        ActualizarRecycler();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_principal,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.itmAgregar:
                Intent inte=new Intent(getApplicationContext(),Agregar.class);
                startActivity(inte);
                return true;
            case R.id.itmAcercaDe:
                Toast.makeText(getApplicationContext(),"Ruiz, Alex, Tony",Toast.LENGTH_LONG).show();
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }

    public void ActualizarRecycler(){
        DaoFicha dao=new DaoFicha(this);
        lista=dao.SeleccionarTodos();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        FichaAdapter adapter=new FichaAdapter(this,lista);

        recyclerView.setAdapter(adapter);
        adapter.setOnItemLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final View n=v;
                AlertDialog.Builder menu=new AlertDialog.Builder(v.getContext());
                Resources res=getResources();
                CharSequence[] opciones= {res.getString(R.string.ver),res.getString(R.string.modificar),res.getString(R.string.eliminar),res.getString(R.string.marcar)};
                menu.setItems(opciones, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int opcion) {
                        switch (opcion){
                            case 0:
                                //Toast.makeText(getApplicationContext(),lista.get(recyclerView.getChildAdapterPosition(n)).getTitulo(),Toast.LENGTH_LONG).show();
                                Intent intent=new Intent(getApplicationContext(),mostrar.class);
                                intent.putExtra("titulo",lista.get(recyclerView.getChildAdapterPosition(n)).getTitulo());
                                startActivity(intent);
                                break;
                            case 1:
                                Intent intent1=new Intent(getApplicationContext(),actualizar.class);
                                intent1.putExtra("titulo",lista.get(recyclerView.getChildAdapterPosition(n)).getTitulo());
                                startActivity(intent1);
                                break;
                            case 2:
                                DaoFicha daonuevo=new DaoFicha(getApplicationContext());
                                if(daonuevo.eliminar(lista.get(recyclerView.getChildAdapterPosition(n)))){
                                    Toast.makeText(getApplicationContext(),"Se elimino",Toast.LENGTH_LONG).show();
                                }
                                ActualizarRecycler();
                                break;
                            case 3:
                                DaoFicha daoAc=new DaoFicha(getApplicationContext());
                                Ficha fichaA=lista.get(recyclerView.getChildAdapterPosition(n));
                                fichaA.setEstado("false");
                                daoAc.actualizar(fichaA);
                                ActualizarRecycler();
                                break;
                        }
                    }
                });
                menu.create().show();
                return true;
            }
        });
    }
    public void  buscar(View v){
        actualizarRecyclerBuscar();
    }

    public void actualizarRecyclerBuscar(){
        DaoFicha dao=new DaoFicha(this);
        lista=dao.SeleccionarTodos(txtBuscar.getText().toString());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        FichaAdapter adapter=new FichaAdapter(this,lista);

        recyclerView.setAdapter(adapter);
        adapter.setOnItemLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final View n=v;
                AlertDialog.Builder menu=new AlertDialog.Builder(v.getContext());
                CharSequence[] opciones= {"Ver","Modificar","Eliminar","Marcar como terminado"};
                menu.setItems(opciones, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int opcion) {
                        switch (opcion){
                            case 0:
                                //Toast.makeText(getApplicationContext(),lista.get(recyclerView.getChildAdapterPosition(n)).getTitulo(),Toast.LENGTH_LONG).show();
                                Intent intent=new Intent(getApplicationContext(),mostrar.class);
                                intent.putExtra("titulo",lista.get(recyclerView.getChildAdapterPosition(n)).getTitulo());
                                startActivity(intent);
                                break;
                            case 1:
                                Toast.makeText(getApplicationContext(),"Modificar",Toast.LENGTH_LONG).show();
                                break;
                            case 2:
                                DaoFicha daonuevo=new DaoFicha(getApplicationContext());
                                if(daonuevo.eliminar(lista.get(recyclerView.getChildAdapterPosition(n)))){
                                    Toast.makeText(getApplicationContext(),"Se elimino",Toast.LENGTH_LONG).show();
                                }
                                ActualizarRecycler();
                                break;
                            case 3:
                                DaoFicha daoAc=new DaoFicha(getApplicationContext());
                                Ficha fichaA=lista.get(recyclerView.getChildAdapterPosition(n));
                                fichaA.setEstado("false");
                                daoAc.actualizar(fichaA);
                                ActualizarRecycler();
                                break;
                        }
                    }
                });
                menu.create().show();
                return true;
            }
        });
    }

}
