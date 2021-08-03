package br.com.gonzales.listadecontatos;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.Date;

public class User {

    private String nameTxt;
    private long phoneTxt;
    private Date dateOfBirthTxt;
    private String cepTxt;
    private String estadoTxt;
    private String cidadeTxt;
    private String bairroTxt;
    private String ruaTxt;
    private int numeroTxt;

    public User(){
        this.nameTxt = null;
        this.phoneTxt = 0;
        this.dateOfBirthTxt = null;
        this.cepTxt = null;
        this.estadoTxt = null;
        this.cidadeTxt = null;
        this.bairroTxt = null;
        this.ruaTxt = null;
        this.numeroTxt = 0;
    }

    /**
     * Busca um CEP no ViaCEP
     *
     * @param cep
     * @throws ViaCEPException caso ocorra algum erro
     */
    public final void buscar(String cep) throws ViaCEPException, JSONException {
        this.cepTxt = cep;

        // define a url
        String url = "https://viacep.com.br/ws/" + cep + "/json/";

        // define os dados
        JSONObject obj = new JSONObject(this.get(url));

        if (!obj.has("erro")) {
            this.cepTxt = obj.getString("cep");
            this.estadoTxt = obj.getString("uf");
            this.cidadeTxt = obj.getString("localidade");
            this.bairroTxt = obj.getString("bairro");
            this.ruaTxt = obj.getString("logradouro");
        } else {
            throw new ViaCEPException("Não foi possível encontrar o CEP", cep);
        }
    }


        /**
     * Procedimento para obtem dados via GET
     *
     * @param urlToRead endereço
     * @return conteúdo remoto
     * @throws ViaCEPException caso ocorra algum erro
     */
    public final String get(String urlToRead) throws ViaCEPException {
        StringBuilder result = new StringBuilder();

        try {
            URL url = new URL(urlToRead);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

        } catch (MalformedURLException | ProtocolException ex) {
            throw new ViaCEPException(ex.getMessage());
        } catch (IOException ex) {
            throw new ViaCEPException(ex.getMessage());
        }

        return result.toString();
    }


    //Tela de Cadastro
//    private void popularCampos(){
//        estado.setText(estadoTxt);
//        cidade.setText(cidadeTxt);
//        bairro.setText(bairroTxt);
//        rua.setText(ruaTxt);
//    }
}
