package tcc.ufpb.com.br.tcc;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;
import android.view.View;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.content.Intent;
import android.os.Vibrator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class JogoActivity extends AppCompatActivity implements OnInitListener  {

    //Objeto TTS
    private TextToSpeech myTTS;
    //Codigo de checagem
    private int MY_DATA_CHECK_CODE = 0;

    private MediaPlayer acerto;
    private MediaPlayer erro;

    private char[] palavraChar;
    private char[] resposta;

    private Contexto contextoEscolhido;
    private Niveis nivelEscolhido;
    private Palavra palavraSorteada;

    private TextView campoPalavra;
    private ImageView campoImagem;
    private RelativeLayout layout;

    private GerenciadorDeContextos gerenciadorDeContextos; // aplicar o singleton depois
    private ImageView botaoHome;

    private Vibrator vibrate;

    private AlertDialog alerta;

    private int qtErros = 0;

    private ArrayList<Button> botoes;

    //back key
    private Toast toast;
    private long lastBackPressTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);

        //check for TTS data
        Intent checkTTSIntent = new Intent();
        checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkTTSIntent, MY_DATA_CHECK_CODE);


        acerto = MediaPlayer.create(this, R.raw.correct);
        erro = MediaPlayer.create(this, R.raw.erro);


        //Instanciando os objetos contidos na view
        layout = (RelativeLayout) findViewById(R.id.jogolayout);
        campoPalavra = (TextView) findViewById(R.id.campoPalavra);
        campoImagem = (ImageView) findViewById(R.id.campoImagem);
        botaoHome = (ImageView) findViewById(R.id.botaoHome);
        vibrate = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);


        // recuperar o contexto e nivel escolhido pelo jogador
        this.contextoEscolhido = (Contexto) getIntent().getExtras().getSerializable("contexto");
        this.nivelEscolhido = (Niveis) getIntent().getExtras().getSerializable("nivel");
        //Toast.makeText(this, "Nivel recebido no jogo: "+nivelEscolhido, Toast.LENGTH_LONG).show();


        this.botoes= new ArrayList<>();

        final Button btnA = (Button) findViewById(R.id.botaoA);
        botoes.add(btnA);
        final Button btnB = (Button) findViewById(R.id.botaoB);
        botoes.add(btnB);
        final Button btnC = (Button) findViewById(R.id.botaoC);
        botoes.add(btnC);
        final Button btnD = (Button) findViewById(R.id.botaoD);
        botoes.add(btnD);
        final Button btnE = (Button) findViewById(R.id.botaoE);
        botoes.add(btnE);
        final Button btnF = (Button) findViewById(R.id.botaoF);
        botoes.add(btnF);
        final Button btnG = (Button) findViewById(R.id.botaoG);
        botoes.add(btnG);
        final Button btnH = (Button) findViewById(R.id.botaoH);
        botoes.add(btnH);
        final Button btnI = (Button) findViewById(R.id.botaoI);
        botoes.add(btnI);
        final Button btnJ = (Button) findViewById(R.id.botaoJ);
        botoes.add(btnJ);
        final Button btnK = (Button) findViewById(R.id.botaoK);
        botoes.add(btnK);
        final Button btnL = (Button) findViewById(R.id.botaoL);
        botoes.add(btnL);
        final Button btnM = (Button) findViewById(R.id.botaoM);
        botoes.add(btnM);
        final Button btnN = (Button) findViewById(R.id.botaoN);
        botoes.add(btnN);
        final Button btnO = (Button) findViewById(R.id.botaoO);
        botoes.add(btnO);
        final Button btnP = (Button) findViewById(R.id.botaoP);
        botoes.add(btnP);
        final Button btnQ = (Button) findViewById(R.id.botaoQ);
        botoes.add(btnQ);
        final Button btnR = (Button) findViewById(R.id.botaoR);
        botoes.add(btnR);
        final Button btnS = (Button) findViewById(R.id.botaoS);
        botoes.add(btnS);
        final Button btnT = (Button) findViewById(R.id.botaoT);
        botoes.add(btnT);
        final Button btnU = (Button) findViewById(R.id.botaoU);
        botoes.add(btnU);
        final Button btnV = (Button) findViewById(R.id.botaoV);
        botoes.add(btnV);
        final Button btnW = (Button) findViewById(R.id.botaoW);
        botoes.add(btnW);
        final Button btnX = (Button) findViewById(R.id.botaoX);
        botoes.add(btnX);
        final Button btnY = (Button) findViewById(R.id.botaoY);
        botoes.add(btnY);
        final Button btnZ = (Button) findViewById(R.id.botaoZ);
        botoes.add(btnZ);
        final Button btnAcomAcentoAgudo = (Button) findViewById(R.id.botaoAComAcentoAgudo);
        botoes.add(btnAcomAcentoAgudo);
        final Button btnEcomAcentoAgudo = (Button) findViewById(R.id.botaoEComAcentoAgudo);
        botoes.add(btnEcomAcentoAgudo);
        final Button btnIcomAcentoAgudo = (Button) findViewById(R.id.botaoIComAcentoAgudo);
        botoes.add(btnIcomAcentoAgudo);
        final Button btnOcomAcentoAgudo = (Button) findViewById(R.id.botaoOComAcentoAgudo);
        botoes.add(btnOcomAcentoAgudo);
        final Button btnUcomAcentoAgudo = (Button) findViewById(R.id.botaoUComAcentoAgudo);
        botoes.add(btnUcomAcentoAgudo);
        final Button btnAcomAcentoCircunflexo = (Button) findViewById(R.id.botaoAComCircunflexo);
        botoes.add(btnAcomAcentoCircunflexo);
        final Button btnEcomAcentoCircunflexo = (Button) findViewById(R.id.botaoEComCircunflexo);
        botoes.add(btnEcomAcentoCircunflexo);
        final Button btnOcomAcentoCircunflexo = (Button) findViewById(R.id.botaoOComcircunflexo);
        botoes.add(btnOcomAcentoCircunflexo);
        final Button btnAcomTil = (Button) findViewById(R.id.botaoAComTil);
        botoes.add(btnAcomTil);
        final Button btnOcomTil = (Button) findViewById(R.id.botaoOComTil);
        botoes.add(btnOcomTil);
        final Button btnCedilha = (Button) findViewById(R.id.botaoCedilha);
        botoes.add(btnCedilha);


        carregarPalavra(this);


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
                speakWords(palavraSorteada.getPalavra());
            }
        });



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


        if (btnQ != null) {
            btnQ.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String words = "Quê";
                    speakWords(words);
                    btnQ.setEnabled(false);
                    verificarLetra('Q');
                    // btnC.setBackgroundColor(Color.RED);
                }
            });
        }


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

        if (btnAcomAcentoAgudo != null) {
            btnAcomAcentoAgudo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String words = "A, com acento agudo";
                    speakWords(words);
                    btnAcomAcentoAgudo.setEnabled(false);
                    verificarLetra('Á');

                }
            });
        }

        if (btnEcomAcentoAgudo != null) {
            btnEcomAcentoAgudo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String words = "E, com acento agudo";
                    speakWords(words);
                    btnEcomAcentoAgudo.setEnabled(false);
                    verificarLetra('É');

                }
            });
        }

        if (btnIcomAcentoAgudo != null) {
            btnIcomAcentoAgudo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String words = "I, com acento agudo";
                    speakWords(words);
                    btnIcomAcentoAgudo.setEnabled(false);
                    verificarLetra('Í');

                }
            });
        }

        if (btnOcomAcentoAgudo != null) {
            btnOcomAcentoAgudo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String words = "O, com acento agudo";
                    speakWords(words);
                    btnOcomAcentoAgudo.setEnabled(false);
                    verificarLetra('Ó');

                }
            });
        }

        if (btnUcomAcentoAgudo != null) {
            btnUcomAcentoAgudo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String words = "U, com acento agudo";
                    speakWords(words);
                    btnUcomAcentoAgudo.setEnabled(false);
                    verificarLetra('Ú');

                }
            });
        }

        if (btnAcomAcentoCircunflexo != null) {
            btnAcomAcentoCircunflexo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String words = "A, com acento circunflexo";
                    speakWords(words);
                    btnAcomAcentoCircunflexo.setEnabled(false);
                    verificarLetra('Â');

                }
            });
        }

        if (btnEcomAcentoCircunflexo != null) {
            btnEcomAcentoCircunflexo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String words = "E, com acento circunflexo";
                    speakWords(words);
                    btnEcomAcentoCircunflexo.setEnabled(false);
                    verificarLetra('Ê');

                }
            });
        }

        if (btnOcomAcentoCircunflexo != null) {
            btnOcomAcentoCircunflexo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String words = "O, com acento circunflexo";
                    speakWords(words);
                    btnOcomAcentoCircunflexo.setEnabled(false);
                    verificarLetra('Ô');

                }
            });
        }

        if (btnAcomTil != null) {
            btnAcomTil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String words = "A, com til";
                    speakWords(words);
                    btnAcomTil.setEnabled(false);
                    verificarLetra('Ã');

                }
            });
        }

        if (btnOcomTil != null) {
            btnOcomTil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String words = "O, com til";
                    speakWords(words);
                    btnOcomTil.setEnabled(false);
                    verificarLetra('Õ');

                }
            });
        }

        if (btnCedilha != null) {
            btnCedilha.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String words = "C Cedilha";
                    speakWords(words);
                    btnCedilha.setEnabled(false);
                    verificarLetra('Ç');

                }
            });
        }



    }
    //speak the user text
    public void speakWords(String speech) {
        //speak straight away
        myTTS.setLanguage(new Locale("pt","BR"));
        //myTTS.setSpeechRate((float) 0.9); // velocidade da voz
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
            Toast.makeText(this, "Desculpe! Houve algum problema interno...", Toast.LENGTH_LONG).show();
    }
    }

    public void verificarLetra(char letra){
        boolean existeLetra = false;
        for(int i=0; i < palavraChar.length ; i++){
            if(palavraChar[i] == letra){
                resposta[i] = letra;
                existeLetra = true;
            }
        }
        if(!existeLetra){

            erro.start();
            this.qtErros++;
            vibrate.vibrate(500);

            if(qtErros == 1)
                layout.setBackground(getResources().getDrawable(R.drawable.background1erro));
            else if(qtErros == 2)
                layout.setBackground(getResources().getDrawable(R.drawable.background2erros));
            else if(qtErros == 3)
                layout.setBackground(getResources().getDrawable(R.drawable.background3erros));
            else if(qtErros == 4)
                layout.setBackground(getResources().getDrawable(R.drawable.background4erros));
            else if(qtErros == 5){
                layout.setBackground(getResources().getDrawable(R.drawable.background5erros));
            }else if(qtErros == 6){
                layout.setBackground(getResources().getDrawable(R.drawable.background6erros));
                // game over, inflar o alert dialog do game over
                gameOver();

            }



        }else{

            acerto.start();
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
            speakWords(palavraSorteada.getPalavra());

            alertDialogAcertou();
        }
    }

    public void alertDialogAcertou() {
        //LayoutInflater é utilizado para inflar nosso layout em uma view.
        //-pegamos nossa instancia da classe
        LayoutInflater li = getLayoutInflater();

        //inflamos o layout palavra_correta.xmlrreta.xml na view
        final View view = li.inflate(R.layout.palavra_correta, null);
        //definimos para o botão do layout um clickListener
        view.findViewById(R.id.botaoNext).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                //Intent intent = getIntent();
                //finish();
                //startActivity(intent);

                carregarPalavra(view.getContext());
                alerta.cancel();
//                //exibe um Toast informativo.
//                Toast.makeText(JogoActivity.this, "palavra_correta.dismiss()", Toast.LENGTH_SHORT).show();
//                //desfaz o palavra_correta.
//                palavra_correta.dismiss();
            }
        });

        ;

        view.findViewById(R.id.botaoHome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(view.getContext(), MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("");
        builder.setView(view);
        alerta = builder.create();
        alerta.show();
    }

    public void gameOver(){
        LayoutInflater li = getLayoutInflater();
        final View view = li.inflate(R.layout.game_over, null);


        view.findViewById(R.id.botaoHome).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent i = new Intent(view.getContext(), MainActivity.class);
                startActivity(i);
                finish();


//                //exibe um Toast informativo.
//                Toast.makeText(JogoActivity.this, "palavra_correta.dismiss()", Toast.LENGTH_SHORT).show();
//                //desfaz o palavra_correta.
//                palavra_correta.dismiss();
            }
        });

        view.findViewById(R.id.botaoRestart).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent intent = getIntent();
                finish();
                startActivity(intent);


//                //exibe um Toast informativo.
//                Toast.makeText(JogoActivity.this, "palavra_correta.dismiss()", Toast.LENGTH_SHORT).show();
//                //desfaz o palavra_correta.
//                palavra_correta.dismiss();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("");
        builder.setView(view);
        alerta = builder.create();
        alerta.show();

    }

    public void habilitarLetras(){
        for(Button b : this.botoes){
            b.setEnabled(true);
        }

    }

    public void carregarPalavra(Context context){
        palavraSorteada = contextoEscolhido.getPalavraAleatoria(this.nivelEscolhido); // mudar o nivel para qual o usuario escolher
        palavraChar = palavraSorteada.getPalavra().toCharArray();
        resposta = new char[palavraChar.length];

        Picasso
                .with(context).load(palavraSorteada.getPathImagem())
                //.resize(110,110)
                .into(campoImagem);

        layout.setBackground(getResources().getDrawable(R.drawable.backgrond0erros));
        this.qtErros = 0;
        habilitarLetras();
        iniciarCampoPalavra();

    }

    //back key
    @Override
    public void onBackPressed() {
        if (this.lastBackPressTime < System.currentTimeMillis() - 4000) {
            toast = Toast.makeText(this, "Pressione o Botão Voltar novamente para fechar o Aplicativo.", Toast.LENGTH_SHORT);
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
