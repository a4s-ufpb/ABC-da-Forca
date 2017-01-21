package tcc.ufpb.com.br.tcc;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
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
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class GerenciadorDeContextos extends AppCompatActivity {


    private String pathContextoASerCadastrado;
    private Bitmap imagemASerSalva;
    private boolean imagemCarregada=false;


    private File file;
    private Uri uri;
    private Intent CamIntent, GalIntent, CropIntent ;
    public  static final int RequestPermissionCode  = 1 ;

    private ListView listView;
    private ContextoAdapter adapter;
    private ForcaApplication application;
    private AlertDialog alerta;
    private Contexto contextoSelecionado;


    private EditText campoCadastrarNomeContexto;
    private Button btnCadastrarImagem;
    private ImageView campoImagemSelecionada;
    private Button btnCadastrar;
    private Button btnCancelarCadastro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciador_de_contextos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // solicitar permissão do usuario em tempo de execucao
        EnableRuntimePermission();

        // não abrir o teclado automaticamente
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("Gerenciar Contextos");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LayoutInflater li = getLayoutInflater();
                    view = li.inflate(R.layout.criar_novo_contexto, null);

                    campoCadastrarNomeContexto = (EditText) view.findViewById(R.id.campoCadastrarNomeContexto);
                    btnCadastrarImagem = (Button) view.findViewById(R.id.btnCadastrarImagem);
                    campoImagemSelecionada = (ImageView) view.findViewById(R.id.campoImagemSelecionada);
                    btnCadastrar = (Button) view.findViewById(R.id.btnCadastrar);
                    btnCancelarCadastro = (Button) view.findViewById(R.id.btnCancelarCadastro2);


                    btnCancelarCadastro.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alerta.cancel();
                        }
                    });

                    btnCadastrarImagem.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            selectImage();
                        }
                    });

                    btnCadastrar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            if(imagemCarregada && !campoCadastrarNomeContexto.getText().toString().isEmpty()){

                                try {
                                    saveImageToExternalStorage(imagemASerSalva);
                                    Toast.makeText(v.getContext(),pathContextoASerCadastrado,Toast.LENGTH_SHORT).show();

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                String nome = campoCadastrarNomeContexto.getText().toString();

                                Contexto c = new Contexto(nome,pathContextoASerCadastrado,false);
                                application.adicionarContexto(c);
                                adapter.notifyDataSetChanged();

                                alerta.cancel();
                                imagemCarregada = false;
                                //pathContextoASerCadastrado= null;
                                //imagemASerSalva=null;
                            }else{
                                Toast.makeText(v.getContext(),"Para continuar, Dê um nome ao contexto e selecione uma imagem da galeria",Toast.LENGTH_SHORT).show();
                            }


                        }
                    });

                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setCancelable(false);
                    builder.setTitle("Cadastrar Contexto");
                    builder.setView(view);

                    alerta = builder.create();
                    alerta.show();

                    Toast.makeText(view.getContext(),"Adicionar contexto",Toast.LENGTH_SHORT).show();


                }
            });
        }

        application = (ForcaApplication) getApplicationContext();
        adapter = new ContextoAdapter(this);
        listView = (ListView)findViewById(R.id.listViewContextos);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                contextoSelecionado = adapter.getItem(position);
                adapter.notifyDataSetChanged();
                Toast.makeText(view.getContext(),contextoSelecionado.getNome(), Toast.LENGTH_SHORT).show();

                LayoutInflater li = getLayoutInflater();
                View view1 = li.inflate(R.layout.editar_contexto, null);

                final EditText campoNomeContexto  = (EditText) view1.findViewById(R.id.campoNomeContexto);
                Button btnSelecionarImagem = (Button) view1.findViewById(R.id.btnSelecionarImagem);
                campoImagemSelecionada = (ImageView) view1.findViewById(R.id.campoImagem);
                Button btnPalavrasCadastradas = (Button) view1.findViewById(R.id.btnpalavrasCadastradas);
                Button btnRemover = (Button) view1.findViewById(R.id.btnRemover);
                Button btnCancelar = (Button) view1.findViewById(R.id.btnCancelar);

                Button btnAtualizar = (Button) view1.findViewById(R.id.btnAtualizar);

                campoNomeContexto.setText(contextoSelecionado.getNome());
                if(contextoSelecionado.getDefault()) {
                    campoNomeContexto.setEnabled(false);
                    btnSelecionarImagem.setEnabled(false);
                    btnRemover.setEnabled(false);
                    btnAtualizar.setEnabled(false);

                }


                imagemASerSalva = BitmapFactory.decodeFile(contextoSelecionado.getPathImagem());

                btnCancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alerta.cancel();
                    }
                });

                btnPalavrasCadastradas.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(v.getContext(),GerenciadorDePalavras.class);
                        i.putExtra("contexto",contextoSelecionado);
                        startActivity(i);
                        finish();
                    }
                });

                btnRemover.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!contextoSelecionado.getDefault()){
                            application.removerContexto(contextoSelecionado);
                            alerta.cancel();
                            adapter.notifyDataSetChanged();
                        }
                    }
                });

                btnSelecionarImagem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // aquiiiiiii
                        selectImage();
                    }
                });

                btnAtualizar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        try {
                            saveImageToExternalStorage(imagemASerSalva);
                            Toast.makeText(v.getContext(),pathContextoASerCadastrado,Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        String nome = campoNomeContexto.getText().toString();

                        String path = pathContextoASerCadastrado;


                        //Picasso.with(v.getContext()).load(new File(path)).into(campoImagemSelecionada);


                        application.alterarContexto(contextoSelecionado,nome, path);


                        adapter.notifyDataSetChanged();

                        alerta.cancel();
                    }
                });

                if(contextoSelecionado.getDefault()){
                    // se for default, converte para int
                    Picasso.with(view.getContext()).load(Integer.parseInt(contextoSelecionado.getPathImagem())).into(campoImagemSelecionada);
                }else{
                    Picasso.with(view.getContext()).load(new File(contextoSelecionado.getPathImagem())).into(campoImagemSelecionada);
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(view1.getContext());
                builder.setCancelable(false);
                builder.setTitle("Editar Contexto");
                builder.setView(view1);

                alerta = builder.create();
                alerta.show();

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if(id == android.R.id.home){
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    private void selectImage() {
        final CharSequence[] items = { "Tirar Foto", "Selecionar da Galeria",
                "Cancelar" };

        AlertDialog.Builder builder = new AlertDialog.Builder(GerenciadorDeContextos.this);
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
        if(finalBitmap == null){
            Toast.makeText(this,"Tá nuloo",Toast.LENGTH_SHORT).show();
        }

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
        this.pathContextoASerCadastrado = file1.getPath();

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

        if(Build.VERSION.SDK_INT >= 23){

            if (ActivityCompat.shouldShowRequestPermissionRationale(GerenciadorDeContextos.this,
                    Manifest.permission.CAMERA) && ActivityCompat.shouldShowRequestPermissionRationale(GerenciadorDeContextos.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)){

                Toast.makeText(GerenciadorDeContextos.this,"Todas as permissões concedidas", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(GerenciadorDeContextos.this,"Conceda as permissões", Toast.LENGTH_LONG).show();
                ActivityCompat.requestPermissions(GerenciadorDeContextos.this,new String[]{
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

                    Toast.makeText(GerenciadorDeContextos.this,"Permission Granted, Now your application can access CAMERA.", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(GerenciadorDeContextos.this,"Permission Canceled, Now your application cannot access CAMERA.", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }

}
