package tcc.ufpb.com.br.tcc;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class GerenciadorDeContextos extends AppCompatActivity {

    private ListView listView;
    private ContextoAdapter adapter;
    private ForcaApplication application;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciador_de_contextos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("Gerenciar Contextos");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),"Adicionar contexto",Toast.LENGTH_SHORT).show();
                }
            });
        }

        application = (ForcaApplication) getApplicationContext();
        adapter = new ContextoAdapter(this);
        listView = (ListView)findViewById(R.id.listViewContextos);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contexto retorno = adapter.getItem(position);
                adapter.notifyDataSetChanged();
                Toast.makeText(view.getContext(),retorno.getNome(), Toast.LENGTH_SHORT).show();

                // passar o contexto selecionado no bundle para a activity de nível
                Toast.makeText(view.getContext(),retorno.getNome(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if(id == android.R.id.home){
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
