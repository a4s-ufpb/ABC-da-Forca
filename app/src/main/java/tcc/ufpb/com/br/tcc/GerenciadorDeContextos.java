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
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class GerenciadorDeContextos extends AppCompatActivity {

    private ListView listView;
    private ContextoAdapter adapter;
    private ForcaApplication application;
    private AlertDialog alerta;
    private Contexto contextoSelecionado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciador_de_contextos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // não abrir o teclado automaticamente
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

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
                contextoSelecionado = adapter.getItem(position);
                adapter.notifyDataSetChanged();
                Toast.makeText(view.getContext(),contextoSelecionado.getNome(), Toast.LENGTH_SHORT).show();

                LayoutInflater li = getLayoutInflater();
                View view1 = li.inflate(R.layout.editar_contexto, null);

                EditText campoNomeContexto  = (EditText) view1.findViewById(R.id.campoNomeContexto);
                Button btnSelecionarImagem = (Button) view1.findViewById(R.id.btnSelecionarImagem);
                ImageView campoImagem = (ImageView) view1.findViewById(R.id.campoImagem);
                Button btnPalavrasCadastradas = (Button) view1.findViewById(R.id.btnpalavrasCadastradas);
                Button btnRemover = (Button) view1.findViewById(R.id.btnRemover);
                Button btnCancelar = (Button) view1.findViewById(R.id.btnCancelar);

                campoNomeContexto.setText(contextoSelecionado.getNome());

                if(contextoSelecionado.getDefault()){
                    // se for default, converte para int
                    Picasso.with(view.getContext()).load(Integer.parseInt(contextoSelecionado.getPathImagem())).into(campoImagem);
                }else{
                    Picasso.with(view.getContext()).load(contextoSelecionado.getPathImagem()).into(campoImagem);
                }


                btnCancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alerta.cancel();
                    }
                });

                btnPalavrasCadastradas.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(v.getContext(),GerenciadorDePalavras.class);
                        i.putExtra("contexto",contextoSelecionado);
                        startActivity(i);
                        finish();
                    }
                });

                AlertDialog.Builder builder = new AlertDialog.Builder(view1.getContext());
                builder.setCancelable(false);
                builder.setTitle("Editar Contexto");
                builder.setView(view1);

                alerta = builder.create();
                alerta.show();

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
