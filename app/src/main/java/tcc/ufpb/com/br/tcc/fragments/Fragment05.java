package tcc.ufpb.com.br.tcc.fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import tcc.ufpb.com.br.tcc.R;
import tcc.ufpb.com.br.tcc.activity.MainActivity;


public class Fragment05 extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_5,container,false);
        ImageView btn = (ImageView)view.findViewById(R.id.imageView2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplication(), MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();

            }
        });

        ImageView btncomecar = (ImageView)view.findViewById(R.id.btncomecar);
        btncomecar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplication(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();

            }
        });


        return view;
    }
}