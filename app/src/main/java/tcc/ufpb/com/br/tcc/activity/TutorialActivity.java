package tcc.ufpb.com.br.tcc.activity;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import me.relex.circleindicator.CircleIndicator;
import tcc.ufpb.com.br.tcc.R;
import tcc.ufpb.com.br.tcc.fragments.Fragment01;
import tcc.ufpb.com.br.tcc.fragments.Fragment02;
import tcc.ufpb.com.br.tcc.fragments.Fragment03;
import tcc.ufpb.com.br.tcc.fragments.Fragment04;
import tcc.ufpb.com.br.tcc.fragments.Fragment05;

public class TutorialActivity extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        CircleIndicator indicator = (CircleIndicator)findViewById(R.id.circ);
        viewPager.setOffscreenPageLimit(5);

        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        indicator.setViewPager(viewPager);


    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {

        String titulos[] = new String[]{"Frag01", "Frag02", "Frag03", "Frag04", "Frag05"};

        public ViewPagerAdapter(FragmentManager fm) {//iniciar o Objeto responsavel por gerenciar os fragments
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            if (position == 0) {
                fragment = new Fragment01();//inciar o frament na posição
            } else if (position == 1) {
                fragment = new Fragment02();
            } else if (position == 2){
                fragment = new Fragment03();
            }
             else if (position == 3){
                fragment = new Fragment04();
            }
             else if (position == 4){
                fragment = new Fragment05();
    }

            Bundle bundle = new Bundle();
            bundle.putInt("id", position);//controle de posição de cada fragment
            assert fragment != null;
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return titulos.length;
        }
    }


}

