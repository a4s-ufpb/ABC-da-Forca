package tcc.ufpb.com.br.tcc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

/**
 * Created by Deyvison on 12/12/2016.
 */
public class PalavraAdapter extends BaseAdapter {

    private Context context;
    private Contexto contexto;
    private ForcaApplication application;

    public PalavraAdapter(Context context, Contexto contextoEscolhido){
        this.context = context;
        this.application = (ForcaApplication) context.getApplicationContext();
        this.contexto = application.getContexto(contextoEscolhido);
    }

    @Override
    public int getCount() {
        return contexto.getPalavras().size();
    }

    @Override
    public Palavra getItem(int position) {
        return contexto.getPalavras().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;
        view = LayoutInflater.from(context).inflate(R.layout.layout_item_list, parent, false);

        Palavra palavra = contexto.getPalavras().get(position);

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
