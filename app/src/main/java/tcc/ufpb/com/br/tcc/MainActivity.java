package tcc.ufpb.com.br.tcc;

import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    ImageView botaoJogar, botaoConfigurar;

    private Toast toast;
    private long lastBackPressTime = 0;
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.bensoundbuddy);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        botaoJogar = (ImageView) findViewById(R.id.botaoJogar);
        botaoConfigurar = (ImageView) findViewById(R.id.botaoConfigurar);

        botaoJogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),ContextoActivity.class);
                mediaPlayer.stop();
                startActivity(i);
                finish();
            }
        });

        botaoConfigurar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),GerenciadorDeContextos.class);
                mediaPlayer.stop();
                startActivity(i);
                finish();
            }
        });


    }

    @Override
    public void onBackPressed() {
        if (this.lastBackPressTime < System.currentTimeMillis() - 4000) {
            toast = Toast.makeText(this, "Pressione o Botão Voltar novamente para fechar o Aplicativo.", Toast.LENGTH_LONG);
            toast.show();
            this.lastBackPressTime = System.currentTimeMillis();
        } else {
            if (toast != null) {
                toast.cancel();
            }
            super.onBackPressed();
        }
    }
}
