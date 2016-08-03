package tcc.ufpb.com.br.tcc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {

    ImageButton botaoJogar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoJogar = (ImageButton) findViewById(R.id.botaoJogar);

        botaoJogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),JogoActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
