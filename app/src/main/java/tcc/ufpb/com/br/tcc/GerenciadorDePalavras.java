package tcc.ufpb.com.br.tcc;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class GerenciadorDePalavras extends AppCompatActivity {

    private String pathPalavraASerCadastrada;
    private Bitmap imagemASerSalva;
    private boolean imagemCarregada=false;

    private RadioGroup radioButtons;

    private File file;
    private Uri uri;
    private Intent CamIntent, GalIntent, CropIntent ;
    public  static final int RequestPermissionCode  = 1 ;

    private EditText campoCadastrarNomeImagem;
    private Button btnCadastrarImagem;
    private ImageView campoImagemSelecionada;
    private Button btnCadastrar;
    private Button btnCancelarCadastro;

    private ListView listView;
    private PalavraAdapter palavraAdapter;
    private ForcaApplication application;
    private AlertDialog alerta;
    public static Contexto contextoEscolhido;
    private Palavra palavraSelecionada;
    private ViewPager viewPager;
    private PagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciador_de_palavras);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // solicitar permissão do usuario em tempo de execucao
        EnableRuntimePermission();

        // não abrir o teclado automaticamente
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        Bundle b = getIntent().getExtras();
        contextoEscolhido = (Contexto) b.get("contexto");

        Toast.makeText(this,"Contexto escolhido: "+contextoEscolhido.getNome(),Toast.LENGTH_SHORT).show();

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("Palavras cadastradas em: "+contextoEscolhido.getNome());
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),"entrou?",Toast.LENGTH_SHORT).show();
                    LayoutInflater li = getLayoutInflater();
                    view = li.inflate(R.layout.cadastrar_nova_palavra, null);

                    campoCadastrarNomeImagem = (EditText) view.findViewById(R.id.campoNomeContexto);
                    btnCadastrarImagem = (Button) view.findViewById(R.id.btnSelecionarImagem);
                    campoImagemSelecionada = (ImageView) view.findViewById(R.id.campoImagem);
                    btnCadastrar = (Button) view.findViewById(R.id.btnCadastrar2);
                    btnCancelarCadastro = (Button) view.findViewById(R.id.btnCancelar);
                    radioButtons = (RadioGroup) view.findViewById(R.id.radioButtonNiveis);

                    btnCadastrar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Niveis nivel = null;

                            switch (radioButtons.getCheckedRadioButtonId()){
                                case R.id.radioButton:
                                    Toast.makeText(v.getContext(),"Fácil",Toast.LENGTH_SHORT).show();
                                    nivel = Niveis.FACIL;
                                    break;
                                case R.id.radioButton2:
                                    Toast.makeText(v.getContext(),"Médio",Toast.LENGTH_SHORT).show();
                                    nivel = Niveis.MEDIO;
                                    break;
                                case R.id.radioButton3:
                                    Toast.makeText(v.getContext(),"Dificil",Toast.LENGTH_SHORT).show();
                                    nivel = Niveis.DIFICIL;
                                    break;

                            }
                            if(imagemCarregada){
                                try {
                                    saveImageToExternalStorage(imagemASerSalva);
                                    Toast.makeText(v.getContext(),pathPalavraASerCadastrada,Toast.LENGTH_SHORT).show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                String nome = campoCadastrarNomeImagem.getText().toString();
                                //contextoEscolhido.adicionarPalavra(new Palavra(nome,pathPalavraASerCadastrada, nivel, false));
                                application.adiconarPalavraAoContexto(contextoEscolhido,new Palavra(nome,pathPalavraASerCadastrada, nivel, false));
                                //palavraAdapter.notifyDataSetChanged();
                                adapter.notifyDataSetChanged();

                                alerta.cancel();
                                imagemCarregada = false;
                                pathPalavraASerCadastrada= null;
                                imagemASerSalva=null;

                            }else{
                                Toast.makeText(v.getContext(),"Para continuar, Dê um nome a palavra e selecione uma imagem da galeria",Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                    btnCadastrarImagem.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            selectImage();
                        }
                    });

                    btnCancelarCadastro.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alerta.cancel();
                        }
                    });



                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setCancelable(false);
                    builder.setTitle("Cadastrar Palavra");
                    builder.setView(view);

                    alerta = builder.create();
                    alerta.show();

                    Toast.makeText(view.getContext(),"Adicionar Palavra",Toast.LENGTH_SHORT).show();
                }
            });
        }


        application = (ForcaApplication) getApplicationContext();
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        TabLayout.Tab t1 = tabLayout.newTab().setText("Fácil");

        TabLayout.Tab t2 = tabLayout.newTab().setText("Médio");
        TabLayout.Tab t3 = tabLayout.newTab().setText("Difícil");

        tabLayout.addTab(t1);
        tabLayout.addTab(t2);
        tabLayout.addTab(t3);

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) findViewById(R.id.pager);
        adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if(id == android.R.id.home){
            Intent i = new Intent(this,GerenciadorDeContextos.class);
            startActivity(i);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }



    private void selectImage() {
        final CharSequence[] items = { "Tirar Foto", "Selecionar da Galeria",
                "Cancelar" };

        AlertDialog.Builder builder = new AlertDialog.Builder(GerenciadorDePalavras.this);
        builder.setTitle("Adicionar foto!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("Tirar Foto")) {
                    ClickImageFromCamera();

                } else if (items[item].equals("Selecionar da Galeria")) {
                    GetImageFromGallery();

                } else if (items[item].equals("Cancelar")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    public void ClickImageFromCamera() {

        CamIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

        file = new File(Environment.getExternalStorageDirectory(),
                "file" + String.valueOf(System.currentTimeMillis()) + ".jpg");
        uri = Uri.fromFile(file);

        CamIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, uri);

        CamIntent.putExtra("return-data", true);

        startActivityForResult(CamIntent, 0);

    }

    public void GetImageFromGallery(){

        GalIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(Intent.createChooser(GalIntent, "Select Image From Gallery"), 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0 && resultCode == RESULT_OK) { // tirar foto com a camera
            ImageCropFunction();
        }
        else if (requestCode == 2) { // carregar da galeria
            if (data != null) {
                uri = data.getData();
                ImageCropFunction();
            }
        }
        else if (requestCode == 1) { // cortar
            if (data != null) {
                try {
                    Bundle bundle = data.getExtras();
                    if(bundle != null) {
                        Bitmap bitmap = bundle.getParcelable("data");
                        imagemASerSalva = bitmap;

                        campoImagemSelecionada.setImageBitmap(bitmap);
                        imagemCarregada = true;
                        //saveImageToExternalStorage(bitmap);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }



    public void saveImageToExternalStorage(Bitmap finalBitmap) throws IOException {
        //String root =Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
        File root = android.os.Environment.getExternalStorageDirectory();
        File myDir = new File(root + "/ABCdaForca");
        myDir.mkdirs();
        Random generator = new Random();
        int n = 1000000000;
        int x = 1000000000;
        int y = 1000000000;
        n = generator.nextInt(n);
        x = generator.nextInt(x);
        y = generator.nextInt(y);
        String fname = "Image-" + n + x + y + ".jpg";
        File file1 = new File(myDir, fname);

        // teste
        TextView lol = (TextView)findViewById(R.id.text);
        //lol.setText(myDir+fname);
        if (lol != null) {
            lol.setText(file1.getPath());
        }

//        ImageView lol2 = (ImageView) findViewById(R.id.imageview2);
//        Picasso
//                .with(this)
//                .load(new File(file1.getPath()))
//                .resize(200,200)
//                .into(lol2);
        //teste
        this.pathPalavraASerCadastrada = file1.getPath();

        if (file1.exists())
            file1.delete();
        //file1.createNewFile();
        try {
            FileOutputStream out = new FileOutputStream(file1);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);

            out.flush();
            out.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // Tell the media scanner about the new file so that it is
        // immediately available to the user.
        MediaScannerConnection.scanFile(this, new String[] { file1.toString() },                null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String path, Uri uri) {
                        Log.i("ExternalStorage", "Scanned " + path + ":");
                        Log.i("ExternalStorage", "-> uri=" + uri);
                    }
                });
    }

    public void ImageCropFunction() {

        // Image Crop Code
        try {
            CropIntent = new Intent("com.android.camera.action.CROP");
            CropIntent.setDataAndType(uri, "image/*");

            CropIntent.putExtra("crop", "true");
            //CropIntent.putExtra("outputX", 180);
            //CropIntent.putExtra("outputY", 180);
            CropIntent.putExtra("aspectX", 0);
            CropIntent.putExtra("aspectY", 0);
            CropIntent.putExtra("scaleUpIfNeeded", true);
            CropIntent.putExtra("return-data", true);

            startActivityForResult(CropIntent, 1);

        } catch (ActivityNotFoundException e) {

        }
    }
    //Image Crop Code End Here

    public void EnableRuntimePermission(){

        if(Build.VERSION.SDK_INT > 23){

            if (ActivityCompat.shouldShowRequestPermissionRationale(GerenciadorDePalavras.this,
                    Manifest.permission.CAMERA) && ActivityCompat.shouldShowRequestPermissionRationale(GerenciadorDePalavras.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)){

                Toast.makeText(GerenciadorDePalavras.this,"Todas as permissões concedidas", Toast.LENGTH_LONG).show();

            } else {
                ActivityCompat.requestPermissions(GerenciadorDePalavras.this,new String[]{
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE}, RequestPermissionCode);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {

        switch (RC) {

            case RequestPermissionCode:

                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(GerenciadorDePalavras.this,"Permission Granted, Now your application can access CAMERA.", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(GerenciadorDePalavras.this,"Permission Canceled, Now your application cannot access CAMERA.", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }

}
