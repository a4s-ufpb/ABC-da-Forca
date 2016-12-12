package tcc.ufpb.com.br.tcc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Deyvison on 12/12/2016.
 */
public class PalavraAdapter extends BaseAdapter {

    private Context context;
    private List<Palavra> palavras;
    private ForcaApplication application;

    public PalavraAdapter(Context context, List<Palavra> palavras){
        this.context = context;
        this.application = (ForcaApplication) context.getApplicationContext();
        this.palavras = palavras;
    }

    @Override
    public int getCount() {
        return palavras.size();
    }

    @Override
    public Palavra getItem(int position) {
        return palavras.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;
        view = LayoutInflater.from(context).inflate(R.layout.layout_item_list, parent, false);

        Palavra palavra = palavras.get(position);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        TextView textView = (TextView) view.findViewById(R.id.textView);

        imageView.setImageResource(palavra.getPathImagem());
        textView.setText(palavra.getNome());

        return view;
    }

}
