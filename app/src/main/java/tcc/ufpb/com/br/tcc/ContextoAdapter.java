package tcc.ufpb.com.br.tcc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Deyvison on 05/10/2016.
 */
public class ContextoAdapter extends BaseAdapter {

    private Context context;
    private List<Contexto> contextos;
    private ForcaApplication application;

    public ContextoAdapter(Context context){
        this.context = context;
        this.application = (ForcaApplication) context.getApplicationContext();
        this.contextos = application.getContextos();
    }


    @Override
    public int getCount() {
        return contextos.size();
    }

    @Override
    public Contexto getItem(int position) {
        return contextos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;
        view = LayoutInflater.from(context).inflate(R.layout.layout_item_list, parent, false);

        Contexto contexto = contextos.get(position);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        TextView textView = (TextView) view.findViewById(R.id.textView);

        if(contexto.getDefault()){
            // se for default, converte para int
            Picasso.with(view.getContext()).load(Integer.parseInt(contexto.getPathImagem())).into(imageView);
        }else{
            Picasso.with(view.getContext()).load(contexto.getPathImagem()).into(imageView);
        }

        textView.setText(contexto.getNome());

        return view;
    }
}
