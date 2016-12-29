package tcc.ufpb.com.br.tcc.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import tcc.ufpb.com.br.tcc.Contexto;
import tcc.ufpb.com.br.tcc.GerenciadorDeContextos;
import tcc.ufpb.com.br.tcc.GerenciadorDePalavras;
import tcc.ufpb.com.br.tcc.Palavra;
import tcc.ufpb.com.br.tcc.R;
import tcc.ufpb.com.br.tcc.adapter.PalavraNivelFacilAdapter;


public class TabFragment1 extends Fragment {

    private ListView listView;
    private PalavraNivelFacilAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_tab_fragment1, container, false);

        adapter = new PalavraNivelFacilAdapter(getContext(), GerenciadorDePalavras.contextoEscolhido);
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
