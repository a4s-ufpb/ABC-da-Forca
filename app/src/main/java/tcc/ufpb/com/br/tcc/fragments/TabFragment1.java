package tcc.ufpb.com.br.tcc.fragments;

import android.Manifest;
import android.app.Activity;
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
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import tcc.ufpb.com.br.tcc.Contexto;
import tcc.ufpb.com.br.tcc.ForcaApplication;
import tcc.ufpb.com.br.tcc.GerenciadorDeContextos;
import tcc.ufpb.com.br.tcc.GerenciadorDePalavras;
import tcc.ufpb.com.br.tcc.Palavra;
import tcc.ufpb.com.br.tcc.R;
import tcc.ufpb.com.br.tcc.adapter.PalavraNivelFacilAdapter;


public class TabFragment1 extends Fragment {
    public static Bitmap imagemAserSalva;
    public static ImageView fumador;
    public static String pathImagemASerCadastrada;

    private String pathContextoASerCadastrado;
    private String pathPalavraASerCadastrada;
    private boolean imagemCarregada=false;

    public  static final int RequestPermissionCode  = 1 ;
    private File file;
    private Uri uri;
    private Intent CamIntent, GalIntent, CropIntent ;
    public static Palavra retorno;
    private ForcaApplication application;
    public static ImageView campoImagemSelecionada;
    private AlertDialog alerta;
    private ListView listView;
    private PalavraNivelFacilAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_tab_fragment1, container, false);
        application = (ForcaApplication) getActivity().getApplicationContext();
        adapter = new PalavraNivelFacilAdapter(getContext(), GerenciadorDePalavras.contextoEscolhido);
        listView = (ListView)view.findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                retorno = adapter.getItem(position);
                adapter.notifyDataSetChanged();

                GerenciadorDePalavras.palavraEscolhida = retorno;
                Toast.makeText(view.getContext(),retorno.getNome(), Toast.LENGTH_SHORT).show();

                LayoutInflater li = getActivity().getLayoutInflater();
                View view1 = li.inflate(R.layout.editar_palavra, null);

                GerenciadorDePalavras.viewPalavra = view1;

                final EditText campoNomePalavra  = (EditText) view1.findViewById(R.id.campoNomePalavra);
                Button btnSelecionarImagem = (Button) view1.findViewById(R.id.btnSelecionarImagem);
                campoImagemSelecionada = (ImageView) view1.findViewById(R.id.campoImagemPalavra);


                Button btnAtualizar = (Button) view1.findViewById(R.id.btnAtualizarPalavra);
                Button btnRemover = (Button) view1.findViewById(R.id.btnRemover);

                Button btnCancelar = (Button) view1.findViewById(R.id.btnCancelar);

                campoNomePalavra.setText(retorno.getNome());

                if(retorno.getDefault()) {
                    campoNomePalavra.setEnabled(false);
                    btnSelecionarImagem.setEnabled(false);
                    btnRemover.setEnabled(false);
                    btnAtualizar.setEnabled(false);

                }

                imagemAserSalva = BitmapFactory.decodeFile(retorno.getPathImagem());

                if(retorno.getDefault()){
                    // se for default, converte para int
                    Picasso.with(view.getContext()).load(Integer.parseInt(retorno.getPathImagem())).into(campoImagemSelecionada);
                }else{
                    Picasso.with(view.getContext()).load(new File(retorno.getPathImagem())).into(campoImagemSelecionada);
                }

                btnCancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alerta.dismiss();



                    }
                });


                btnRemover.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder builder1 = new AlertDialog.Builder(v.getContext());
                        builder1.setMessage("Deseja remover o contexto?");
                        builder1.setCancelable(true);

                        builder1.setNegativeButton(
                                "Cancelar",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();

                                    }
                                });

                        builder1.setPositiveButton(
                                "Sim",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        application.removerPalavra(GerenciadorDeContextos.contextoSelecionado,retorno);
                                        alerta.cancel();
                                        adapter.notifyDataSetChanged();
                                    }
                                });

                        AlertDialog alert11 = builder1.create();
                        alert11.show();


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
                            saveImageToExternalStorage(imagemAserSalva);
                            Toast.makeText(v.getContext(),pathImagemASerCadastrada,Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        String nome = campoNomePalavra.getText().toString();
                        Log.e("lol",nome);

                        String path = pathImagemASerCadastrada;
                        Log.e("lol",path+"");

                        //Picasso.with(v.getContext()).load(new File(path)).into(campoImagemSelecionada);


                        application.alterarPalavra(GerenciadorDePalavras.contextoEscolhido,retorno,nome, pathImagemASerCadastrada,retorno.getNivel());


                        adapter.notifyDataSetChanged();

                        alerta.cancel();
                        Log.d("lol","chupa caju");


                    }
                });

                AlertDialog.Builder builder = new AlertDialog.Builder(view1.getContext());
                builder.setCancelable(false);
                builder.setTitle("Editar Palavra");
                builder.setView(view1);

                alerta = builder.create();
                alerta.show();
            }
        });

        return view;

    }

    public void selectImage() {
        final CharSequence[] items = { "Tirar Foto", "Selecionar da Galeria",
                "Cancelar" };

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
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

        getActivity().startActivityForResult(CamIntent, 0);

    }

    public void GetImageFromGallery(){

        GalIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        getActivity().startActivityForResult(Intent.createChooser(GalIntent, "Select Image From Gallery"), 2);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);



        Toast.makeText(getContext(),"entrou aqui? acho dificil",Toast.LENGTH_SHORT).show();


    }



    public void saveImageToExternalStorage(Bitmap finalBitmap) throws IOException {
        if(finalBitmap == null){
            Toast.makeText(getContext(),"Tá nuloo",Toast.LENGTH_SHORT).show();
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
        TextView lol = (TextView)getActivity().findViewById(R.id.text);
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
        this.pathImagemASerCadastrada = file1.getPath();

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
        MediaScannerConnection.scanFile(getContext(), new String[] { file1.toString() },                null,
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

            getActivity().startActivityForResult(CropIntent, 1);

        } catch (ActivityNotFoundException e) {

        }
    }
    //Image Crop Code End Here



}
