package tcc.ufpb.com.br.tcc;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;
import android.view.View;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.content.Intent;

import java.util.Arrays;
import java.util.Locale;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class JogoActivity extends AppCompatActivity implements OnInitListener  {

    //TTS object
    private TextToSpeech myTTS;
    //status check code
    private int MY_DATA_CHECK_CODE = 0;

    Palavra palavra;
    char[] palavraChar;
    char[] resposta;

    TextView campoPalavra;
    ImageView campoImagem;

    GerenciadorDePalavras gerenciadorDePalavras;

    ImageView botaoHome;

    private AlertDialog alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);

        //check for TTS data
        Intent checkTTSIntent = new Intent();
        checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkTTSIntent, MY_DATA_CHECK_CODE);

        campoPalavra = (TextView) findViewById(R.id.campoPalavra);
        campoImagem = (ImageView) findViewById(R.id.campoImagem);
        botaoHome = (ImageView) findViewById(R.id.botaoHome);
        gerenciadorDePalavras = new GerenciadorDePalavras();

        palavra = gerenciadorDePalavras.getPalavraAleatoria();
        palavraChar = palavra.getPalavra().toCharArray();
        resposta = new char[palavraChar.length];

        Picasso
                .with(this).load(palavra.getPathImagem())
                .resize(110,110)
                .into(campoImagem);


        iniciarCampoPalavra();

        botaoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        campoImagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakWords(palavra.getPalavra());
            }
        });

        final Button btnA = (Button) findViewById(R.id.botaoA);
        if (btnA != null) {
            btnA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String words = "A";
                    speakWords(words);
                    btnA.setEnabled(false);
                    verificarLetra('A');
                    //   btnA.setBackgroundColor(Color.RED);

                }
            });
        }

        final Button btnB = (Button) findViewById(R.id.botaoB);
        if (btnB != null) {
            btnB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String words = "B";
                    speakWords(words);
                    btnB.setEnabled(false);
                    verificarLetra('B');
                    // btnB.setBackgroundColor(Color.RED);

                }
            });
        }

        final Button btnC = (Button) findViewById(R.id.botaoC);
        if (btnC != null) {
            btnC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String words = "C";
                    speakWords(words);
                    btnC.setEnabled(false);
                    verificarLetra('C');
                    // btnC.setBackgroundColor(Color.RED);
                }
            });
        }

        final Button btnD = (Button) findViewById(R.id.botaoD);
        if (btnD != null) {
            btnD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String words = "D";
                    speakWords(words);
                    btnD.setEnabled(false);
                    verificarLetra('D');
                    // btnC.setBackgroundColor(Color.RED);
                }
            });
        }

        final Button btnE = (Button) findViewById(R.id.botaoE);
        if (btnE != null) {
            btnE.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String words = "E";
                    speakWords(words);
                    btnE.setEnabled(false);
                    verificarLetra('E');
                    // btnC.setBackgroundColor(Color.RED);
                }
            });
        }

        final Button btnF = (Button) findViewById(R.id.botaoF);
        if (btnF != null) {
            btnF.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String words = "F";
                    speakWords(words);
                    btnF.setEnabled(false);
                    verificarLetra('F');
                    // btnC.setBackgroundColor(Color.RED);
                }
            });
        }

        final Button btnG = (Button) findViewById(R.id.botaoG);
        if (btnG != null) {
            btnG.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String words = "G";
                    speakWords(words);
                    btnG.setEnabled(false);
                    verificarLetra('G');
                    // btnC.setBackgroundColor(Color.RED);
                }
            });
        }

        final Button btnH = (Button) findViewById(R.id.botaoH);
        if (btnH != null) {
            btnH.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String words = "H";
                    speakWords(words);
                    btnH.setEnabled(false);
                    verificarLetra('H');
                    // btnC.setBackgroundColor(Color.RED);
                }
            });
        }

        final Button btnI = (Button) findViewById(R.id.botaoI);
        if (btnI != null) {
            btnI.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String words = "I";
                    speakWords(words);
                    btnI.setEnabled(false);
                    verificarLetra('I');
                    // btnC.setBackgroundColor(Color.RED);
                }
            });
        }

        final Button btnJ = (Button) findViewById(R.id.botaoJ);
        if (btnJ != null) {
            btnJ.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String words = "J";
                    speakWords(words);
                    btnJ.setEnabled(false);
                    verificarLetra('J');
                    // btnC.setBackgroundColor(Color.RED);
                }
            });
        }

        final Button btnK = (Button) findViewById(R.id.botaoK);
        if (btnK != null) {
            btnK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String words = "K";
                    speakWords(words);
                    btnK.setEnabled(false);
                    verificarLetra('K');
                    // btnC.setBackgroundColor(Color.RED);
                }
            });
        }

        final Button btnL = (Button) findViewById(R.id.botaoL);
        if (btnL != null) {
            btnL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String words = "L";
                    speakWords(words);
                    btnL.setEnabled(false);
                    verificarLetra('L');
                    // btnC.setBackgroundColor(Color.RED);
                }
            });
        }

        final Button btnM = (Button) findViewById(R.id.botaoM);
        if (btnM != null) {
            btnM.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String words = "M";
                    speakWords(words);
                    btnM.setEnabled(false);
                    verificarLetra('M');
                    // btnC.setBackgroundColor(Color.RED);
                }
            });
        }

        final Button btnN = (Button) findViewById(R.id.botaoN);
        if (btnN != null) {
            btnN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String words = "N";
                    speakWords(words);
                    btnN.setEnabled(false);
                    verificarLetra('N');
                    // btnC.setBackgroundColor(Color.RED);
                }
            });
        }

        final Button btnO = (Button) findViewById(R.id.botaoO);
        if (btnO != null) {
            btnO.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String words = "O";
                    speakWords(words);
                    btnO.setEnabled(false);
                    verificarLetra('O');
                    // btnC.setBackgroundColor(Color.RED);
                }
            });
        }

        final Button btnP = (Button) findViewById(R.id.botaoP);
        if (btnP != null) {
            btnP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String words = "P";
                    speakWords(words);
                    btnP.setEnabled(false);
                    verificarLetra('P');
                    // btnC.setBackgroundColor(Color.RED);
                }
            });
        }

        final Button btnQ = (Button) findViewById(R.id.botaoQ);
        if (btnQ != null) {
            btnQ.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String words = "Q";
                    speakWords(words);
                    btnQ.setEnabled(false);
                    verificarLetra('Q');
                    // btnC.setBackgroundColor(Color.RED);
                }
            });
        }

        final Button btnR = (Button) findViewById(R.id.botaoR);
        if (btnR != null) {
            btnR.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String words = "R";
                    speakWords(words);
                    btnR.setEnabled(false);
                    verificarLetra('R');
                    // btnC.setBackgroundColor(Color.RED);
                }
            });
        }

        final Button btnS = (Button) findViewById(R.id.botaoS);
        if (btnS != null) {
            btnS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String words = "S";
                    speakWords(words);
                    btnS.setEnabled(false);
                    verificarLetra('S');
                    // btnC.setBackgroundColor(Color.RED);
                }
            });
        }

        final Button btnT = (Button) findViewById(R.id.botaoT);
        if (btnT != null) {
            btnT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String words = "T";
                    speakWords(words);
                    btnT.setEnabled(false);
                    verificarLetra('T');
                    // btnC.setBackgroundColor(Color.RED);
                }
            });
        }

        final Button btnU = (Button) findViewById(R.id.botaoU);
        if (btnU != null) {
            btnU.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String words = "U";
                    speakWords(words);
                    btnU.setEnabled(false);
                    verificarLetra('U');
                    // btnC.setBackgroundColor(Color.RED);
                }
            });
        }

        final Button btnV = (Button) findViewById(R.id.botaoV);
        if (btnV != null) {
            btnV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String words = "V";
                    speakWords(words);
                    btnV.setEnabled(false);
                    verificarLetra('V');
                    // btnC.setBackgroundColor(Color.RED);
                }
            });
        }

        final Button btnW = (Button) findViewById(R.id.botaoW);
        if (btnW != null) {
            btnW.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String words = "W";
                    speakWords(words);
                    btnW.setEnabled(false);
                    verificarLetra('W');
                    // btnC.setBackgroundColor(Color.RED);
                }
            });
        }

        final Button btnX = (Button) findViewById(R.id.botaoX);
        if (btnX != null) {
            btnX.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String words = "X";
                    speakWords(words);
                    btnX.setEnabled(false);
                    verificarLetra('X');
                    // btnC.setBackgroundColor(Color.RED);
                }
            });
        }

        final Button btnY = (Button) findViewById(R.id.botaoY);
        if (btnY != null) {
            btnY.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String words = "Y";
                    speakWords(words);
                    btnY.setEnabled(false);
                    verificarLetra('Y');
                    // btnC.setBackgroundColor(Color.RED);
                }
            });
        }

        final Button btnZ = (Button) findViewById(R.id.botaoZ);
        if (btnZ != null) {
            btnZ.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String words = "Z";
                    speakWords(words);
                    btnZ.setEnabled(false);
                    verificarLetra('Z');
                    // btnC.setBackgroundColor(Color.RED);
                }
            });
        }



    }
    //speak the user text
    public void speakWords(String speech) {
        //speak straight away
        myTTS.setLanguage(new Locale("pt","BR"));
        myTTS.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
    }

    //act on result of TTS data check
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MY_DATA_CHECK_CODE) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                //the user has the necessary data - create the TTS
                myTTS = new TextToSpeech(this, this);
            }
            else {
                //no data - install it now
                Intent installTTSIntent = new Intent();
                installTTSIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installTTSIntent);
            }
        }
    }

    //setup TTS
    public void onInit(int initStatus) {

        //check for successful instantiation
        if (initStatus == TextToSpeech.SUCCESS) {
            if(myTTS.isLanguageAvailable(Locale.US)==TextToSpeech.LANG_AVAILABLE){

                myTTS.setLanguage(Locale.US);
            }

        }
        else if (initStatus == TextToSpeech.ERROR) {
            Toast.makeText(this, "Sorry! Text To Speech failed...", Toast.LENGTH_LONG).show();
        }
    }

    public void verificarLetra(char letra){
        for(int i=0; i < palavraChar.length ; i++){
            if(palavraChar[i] == letra){
                resposta[i] = letra;
            }
        }
        atualizarCampoPalavra();
    }

    public void atualizarCampoPalavra(){
        if (campoPalavra != null) {
            String texto = "";
            for(int i=0 ; i < resposta.length ; i++){
                if(resposta[i] =='_'){
                    texto +=resposta[i]+"_ ";
                }else{
                    texto +=resposta[i]+" ";
                }
            }
            campoPalavra.setText(texto);
            verificaPalavra();
        }
    }

    public void iniciarCampoPalavra(){
        String texto = "";
        for(int i=0 ; i < resposta.length ; i++){
            resposta[i] = '_';
            texto+="__ ";
        }
        campoPalavra.setText(texto);
    }

    public void verificaPalavra(){
        if(Arrays.equals(palavraChar,resposta)){
            // Ganhou
            try {
                Thread.sleep( 1000 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            speakWords(palavra.getPalavra());

            alertDialogAcertou();
        }
    }

    public void alertDialogAcertou() {
        //LayoutInflater é utilizado para inflar nosso layout em uma view.
        //-pegamos nossa instancia da classe
        LayoutInflater li = getLayoutInflater();

        //inflamos o layout alerta.xml na view
        View view = li.inflate(R.layout.alerta, null);
        //definimos para o botão do layout um clickListener
        view.findViewById(R.id.btProximaPalavra).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent intent = getIntent();
                finish();
                startActivity(intent);


//                //exibe um Toast informativo.
//                Toast.makeText(JogoActivity.this, "alerta.dismiss()", Toast.LENGTH_SHORT).show();
//                //desfaz o alerta.
//                alerta.dismiss();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Palavra correta!");
        builder.setView(view);
        alerta = builder.create();
        alerta.show();
    }
}
