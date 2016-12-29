package tcc.ufpb.com.br.tcc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


import tcc.ufpb.com.br.tcc.Contexto;
import tcc.ufpb.com.br.tcc.ForcaApplication;
import tcc.ufpb.com.br.tcc.Palavra;
import tcc.ufpb.com.br.tcc.R;

/**
 * Created by Deyvison on 23/12/2016.
 */
public class PalavraNivelFacilAdapter extends BaseAdapter {

    private Context context;
    private Contexto contexto;
    private ForcaApplication application;

    public PalavraNivelFacilAdapter(Context context, Contexto contextoEscolhido){
        this.context = context;
        this.application = (ForcaApplication) context.getApplicationContext();
        this.contexto = application.getContexto(contextoEscolhido);

    }

    @Override
    public int getCount() {
        return contexto.getPalavrasNivelFacil().size();
    }

    @Override
    public Palavra getItem(int position) {
        return contexto.getPalavrasNivelFacil().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;
        view = LayoutInflater.from(context).inflate(R.layout.layout_item_list, parent, false);

        Palavra palavra = contexto.getPalavrasNivelFacil().get(position);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        TextView textView = (TextView) view.findViewById(R.id.textView);


        if(palavra.getDefault()){
            // se for default, converte para int
            Picasso.with(view.getContext()).load(Integer.parseInt(palavra.getPathImagem())).into(imageView);
        }else{
            Picasso.with(view.getContext()).load(new File(palavra.getPathImagem())).into(imageView);
        }


        textView.setText(palavra.getNome());

        return view;
    }
}
