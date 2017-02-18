package tcc.ufpb.com.br.tcc.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import tcc.ufpb.com.br.tcc.R;
import tcc.ufpb.com.br.tcc.gerenciador.GerenciadorDeContextos;


public class MainActivity extends AppCompatActivity {

    private ImageView botaoJogar, botaoConfigurar, botaoInfo, botaoTutorial;
    private Toast toast;
    private long lastBackPressTime = 0;
    private MediaPlayer mediaPlayer;
    private AlertDialog alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.bensoundbuddy);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        botaoJogar = (ImageView) findViewById(R.id.botaoJogar);
        botaoConfigurar = (ImageView) findViewById(R.id.botaoConfigurar);
        botaoInfo = (ImageView) findViewById(R.id.infomenu);
        botaoTutorial = (ImageView) findViewById(R.id.botaoTutorial);

        botaoTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TutorialActivity.class);
                mediaPlayer.stop();
                startActivity(intent);
                finish();
            }
        });

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

        botaoInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater li = getLayoutInflater();
                v = li.inflate(R.layout.info, null);

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

                builder.setTitle("Informações do aplicativo");
                builder.setView(v);

                alerta = builder.create();
                alerta.show();
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
                mediaPlayer.stop();
            }
            super.onBackPressed();
        }
    }
}
