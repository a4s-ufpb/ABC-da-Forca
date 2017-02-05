/**
 * Created by Deyvison on 05/10/2016.
 */

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
import java.util.List;
import tcc.ufpb.com.br.tcc.application.ForcaApplication;
import tcc.ufpb.com.br.tcc.R;
import tcc.ufpb.com.br.tcc.entity.Contexto;

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
            // if default, convert to int
            Picasso.with(view.getContext()).load(Integer.parseInt(contexto.getPathImagem())).into(imageView);
        }else{
            Picasso.with(view.getContext()).load(new File(contexto.getPathImagem())).into(imageView);
        }

        textView.setText(contexto.getNome());
        return view;
    }
}
