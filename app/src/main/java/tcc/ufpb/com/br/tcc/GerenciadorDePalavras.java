package tcc.ufpb.com.br.tcc;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class GerenciadorDePalavras extends AppCompatActivity {

    private ListView listView;
    private PalavraAdapter palavraAdapter;
    private ForcaApplication application;
    private AlertDialog alerta;
    private Contexto contextoEscolhido;
    private Palavra palavraSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciador_de_palavras);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Bundle b = getIntent().getExtras();
        contextoEscolhido = (Contexto) b.get("contexto");

        Toast.makeText(this,"Contexto escolhido: "+contextoEscolhido.getNome(),Toast.LENGTH_SHORT).show();

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("Palavras cadastradas em: "+contextoEscolhido.getNome());
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        application = (ForcaApplication) getApplicationContext();
        palavraAdapter = new PalavraAdapter(this, contextoEscolhido.getPalavras());
        listView = (ListView)findViewById(R.id.listViewPalavras);
        listView.setAdapter(palavraAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                palavraSelecionada = palavraAdapter.getItem(position);
                palavraAdapter.notifyDataSetChanged();
                Toast.makeText(view.getContext(),palavraSelecionada.getNome(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if(id == android.R.id.home){
            Intent i = new Intent(this,GerenciadorDeContextos.class);
            startActivity(i);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
