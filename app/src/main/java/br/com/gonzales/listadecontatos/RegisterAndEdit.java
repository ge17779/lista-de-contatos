package br.com.gonzales.listadecontatos;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import br.com.gonzales.listadecontatos.exceptions.ViaCEPException;
import br.com.gonzales.listadecontatos.models.User;

public class RegisterAndEdit extends AppCompatActivity {

    private EditText nome;
    private EditText telefone;
    private EditText data_nascimento;
    private EditText cep;
    private EditText estado;
    private EditText cidade;
    private EditText bairro;
    private EditText rua;
    private EditText numero;
    private User user;
    private String localArquivoFoto;
    private final int REQUEST_CODE = 123;
    ImageView photoImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.register_edit_user);

        ImageView icArrow = findViewById(R.id.ic_arrow);
        icArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        nome = findViewById(R.id.name_edit);
        telefone = findViewById(R.id.phone_edit);
        data_nascimento = findViewById(R.id.date_birth_edit);
        cep = findViewById(R.id.cep_edit);
        estado = findViewById(R.id.uf_edit);
        cidade = findViewById(R.id.city_edit);
        bairro = findViewById(R.id.district_edit);
        rua = findViewById(R.id.street_edit);
        numero = findViewById(R.id.number_edit);

        photoImageView = findViewById(R.id.photo_user);

        user = new User();

        //Evento do clique do usuário em "Enter" assim que o CEP for digitado
        cep.setOnEditorActionListener(
                new EditText.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                                actionId == EditorInfo.IME_ACTION_DONE ||
                                event != null &&
                                        event.getAction() == KeyEvent.ACTION_DOWN &&
                                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                            if (event == null || !event.isShiftPressed()) {
                                // the user is done typing.
                                ExecutorService executor = Executors.newSingleThreadExecutor();
                                Handler handler = new Handler(Looper.getMainLooper());
                                executor.execute(new Runnable() {
                                    @Override
                                    public void run() {

                                        try {
                                            user.buscar(cep.getText().toString());
                                        } catch (ViaCEPException e) {
                                            e.printStackTrace();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                        handler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                popularCampos();
                                            }
                                        });
                                    }
                                });

                                return true; // consume.
                            }
                        }
                        return false; // pass on to other listeners.
                    }
                }
        );

        Button btnSalvar = findViewById(R.id.save_button);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBaseController crud = new DataBaseController(getBaseContext());
                String nomeTxt = nome.getText().toString();
                String telefoneTxt = telefone.getText().toString();
                String dataNascimentoTxt = data_nascimento.getText().toString();
                String cepTxt = cep.getText().toString();
                String estadoTxt = estado.getText().toString();
                String cidadeTxt = cidade.getText().toString();
                String bairroTxt = bairro.getText().toString();
                String ruaTxt = rua.getText().toString();
                String numeroTxt = numero.getText().toString();
                String resultado;

                resultado = crud.insereDado(nomeTxt, telefoneTxt, dataNascimentoTxt, cepTxt, estadoTxt, cidadeTxt,
                            bairroTxt, ruaTxt, numeroTxt);

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
                onBackPressed();
            }
        });

        FloatingActionButton btnPhoto = findViewById(R.id.btn_photo);
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                localArquivoFoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() +  ".jpg";
                Uri localFoto = Uri.fromFile(new File(localArquivoFoto));
//                camera.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                carregaImagem(this.localArquivoFoto);
            } else {
                this.localArquivoFoto = null;
            }
        }
    }

    private void carregaImagem(String localArquivoFoto) {
        Bitmap imagemFoto = BitmapFactory.decodeFile(localArquivoFoto);
        Bitmap imagemFotoReduzida = Bitmap.createScaledBitmap(imagemFoto, imagemFoto.getWidth(), 300, true);
        photoImageView.setImageBitmap(imagemFotoReduzida);
        photoImageView.setTag(localArquivoFoto);
    }

    private void popularCampos(){
        estado.setText(user.getEstadoTxt());
        cidade.setText(user.getCidadeTxt());
        bairro.setText(user.getBairroTxt());
        rua.setText(user.getRuaTxt());
    }
}
