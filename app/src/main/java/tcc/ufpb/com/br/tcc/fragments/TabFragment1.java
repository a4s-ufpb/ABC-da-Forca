package tcc.ufpb.com.br.tcc.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import tcc.ufpb.com.br.tcc.Contexto;
import tcc.ufpb.com.br.tcc.ForcaApplication;
import tcc.ufpb.com.br.tcc.GerenciadorDeContextos;
import tcc.ufpb.com.br.tcc.GerenciadorDePalavras;
import tcc.ufpb.com.br.tcc.Palavra;
import tcc.ufpb.com.br.tcc.R;
import tcc.ufpb.com.br.tcc.adapter.PalavraNivelFacilAdapter;


public class TabFragment1 extends Fragment {
    public static Bitmap imagemAserSalva;
    public static String pathImagemASerCadastrada;

    public static Palavra retorno;
    private ForcaApplication application;
    public static ImageView campoImagemSelecionada;
    private AlertDialog alerta;


    private ListView listView;
    private PalavraNivelFacilAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_tab_fragment1, container, false);
        application = (ForcaApplication) getActivity().getApplicationContext();
        adapter = new PalavraNivelFacilAdapter(getContext(), GerenciadorDePalavras.contextoEscolhido);
        listView = (ListView)view.findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                retorno = adapter.getItem(position);
                adapter.notifyDataSetChanged();

                GerenciadorDePalavras.palavraEscolhida = retorno;
                Toast.makeText(view.getContext(),retorno.getNome(), Toast.LENGTH_SHORT).show();

                LayoutInflater li = getActivity().getLayoutInflater();
                View view1 = li.inflate(R.layout.editar_palavra, null);

                GerenciadorDePalavras.viewPalavra = view1;

                final TextView campoNomePalavra  = (TextView) view1.findViewById(R.id.campoNomePalavra);
                campoImagemSelecionada = (ImageView) view1.findViewById(R.id.campoImagemPalavra);


                Button btnRemover = (Button) view1.findViewById(R.id.btnRemover);

                Button btnCancelar = (Button) view1.findViewById(R.id.btnCancelar);

                campoNomePalavra.setText(retorno.getNome());

                if(retorno.getDefault()) {
                   // campoNomePalavra.setEnabled(false);

                    btnRemover.setEnabled(false);
                }

                imagemAserSalva = BitmapFactory.decodeFile(retorno.getPathImagem());

                if(retorno.getDefault()){
                    // se for default, converte para int
                    Picasso.with(view.getContext()).load(Integer.parseInt(retorno.getPathImagem())).into(campoImagemSelecionada);
                }else{
                    Picasso.with(view.getContext()).load(new File(retorno.getPathImagem())).into(campoImagemSelecionada);
                }

                btnCancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alerta.dismiss();



                    }
                });


                btnRemover.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder builder1 = new AlertDialog.Builder(v.getContext());
                        builder1.setMessage("Deseja remover a palavra?");
                        builder1.setCancelable(true);

                        builder1.setNegativeButton(
                                "Cancelar",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();

                                    }
                                });

                        builder1.setPositiveButton(
                                "Sim",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        application.removerPalavra(GerenciadorDeContextos.contextoSelecionado,retorno);
                                        alerta.cancel();
                                        adapter.notifyDataSetChanged();
                                    }
                                });

                        AlertDialog alert11 = builder1.create();
                        alert11.show();


                    }
                });

                AlertDialog.Builder builder = new AlertDialog.Builder(view1.getContext());
                builder.setCancelable(false);
                builder.setTitle("Editar Palavra");
                builder.setView(view1);

                alerta = builder.create();
                alerta.show();
            }
        });

        return view;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

    }









}
