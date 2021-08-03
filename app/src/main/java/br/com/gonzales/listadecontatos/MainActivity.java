package br.com.gonzales.listadecontatos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private EditText cep;
    private EditText estado;
    private EditText cidade;
    private EditText bairro;
    private EditText rua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterAndEdit.class);
                startActivity(intent);
            }
        });

//        cep = findViewById(R.id.cep_edit_text);
//        estado = findViewById(R.id.estado_edit_text);
//        cidade = findViewById(R.id.cidade_edit_text);
//        bairro = findViewById(R.id.bairro_edit_text);
//        rua = findViewById(R.id.rua_edit_text);
//
//        //Evento do clique do usuário em "OK" assim que o CPF for digitado
//        cep.setOnEditorActionListener(
//                new EditText.OnEditorActionListener() {
//                    @Override
//                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
//                                actionId == EditorInfo.IME_ACTION_DONE ||
//                                event != null &&
//                                        event.getAction() == KeyEvent.ACTION_DOWN &&
//                                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
//                            if (event == null || !event.isShiftPressed()) {
//                                // the user is done typing.
//                                ExecutorService executor = Executors.newSingleThreadExecutor();
//                                Handler handler = new Handler(Looper.getMainLooper());
//                                executor.execute(new Runnable() {
//                                    @Override
//                                    public void run() {
//
//                                        try {
//                                            buscar(cep.getText().toString());
//                                        } catch (ViaCEPException e) {
//                                            e.printStackTrace();
//                                        } catch (JSONException e) {
//                                            e.printStackTrace();
//                                        }
//
//                                        handler.post(new Runnable() {
//                                            @Override
//                                            public void run() {
//                                                popularCampos();
//                                            }
//                                        });
//                                    }
//                                });
//
//                                return true; // consume.
//                            }
//                        }
//                        return false; // pass on to other listeners.
//                    }
//                }
//        );
    }






//    public void buscarCEP(){
//        // evento para buscar um cep
//        ConnectivityManager connMgr = (ConnectivityManager)
//                getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
//        if (networkInfo != null && networkInfo.isConnected()) {
//            // limpa
//            this.estado.setText("");
//            this.cidade.setText("");
//            this.bairro.setText("");
//            this.rua.setText("");
//
//            // cep
//            String cep = this.cep.getText().toString();
//
//            // verifica se o CEP é válido
//            Pattern pattern = Pattern.compile("^[0-9]{5}-[0-9]{3}$");
//            Matcher matcher = pattern.matcher(cep);
//
//            if (matcher.find()) {
//                new DownloadCEPTask().execute(cep);
//            } else {
//                //JOptionPane.showMessageDialog(null, "Favor informar um CEP válido!", "Aviso!", JOptionPane.WARNING_MESSAGE);
//                new AlertDialog.Builder(this)
//                        .setTitle("Aviso!")
//                        .setMessage("Favor informar um CEP válido!")
//                        .setPositiveButton(R.string.msgOk, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                // nada
//                            }
//                        })
//                        .setIcon(android.R.drawable.ic_dialog_alert)
//                        .show();
//            }
//        } else {
//            new AlertDialog.Builder(this)
//                    .setTitle("Sem Internet!")
//                    .setMessage("Não tem nenhuma conexão de rede disponível!")
//                    .setPositiveButton(R.string.msgOk, new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            // nada
//                        }
//                    })
//                    .setIcon(android.R.drawable.ic_dialog_alert)
//                    .show();
//        }

//    }

}