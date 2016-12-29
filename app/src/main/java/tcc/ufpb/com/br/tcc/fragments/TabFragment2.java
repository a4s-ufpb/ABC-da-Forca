package tcc.ufpb.com.br.tcc.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import tcc.ufpb.com.br.tcc.GerenciadorDePalavras;
import tcc.ufpb.com.br.tcc.Palavra;
import tcc.ufpb.com.br.tcc.R;
import tcc.ufpb.com.br.tcc.adapter.PalavraNivelMedioAdapter;


public class TabFragment2 extends Fragment {

    private ListView listView;
    private PalavraNivelMedioAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_tab_fragment2, container, false);

        adapter = new PalavraNivelMedioAdapter(getContext(), GerenciadorDePalavras.contextoEscolhido);
        listView = (ListView)view.findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Palavra retorno = adapter.getItem(position);
                adapter.notifyDataSetChanged();
                Toast.makeText(view.getContext(),retorno.getNome(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}