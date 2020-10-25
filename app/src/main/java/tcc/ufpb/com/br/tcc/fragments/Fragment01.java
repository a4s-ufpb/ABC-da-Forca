package tcc.ufpb.com.br.tcc.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tcc.ufpb.com.br.tcc.R;

/**
 * Created by Rivanildo on 11/09/16.
 */
public class Fragment01 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_1,container,false);
    }
}
