package br.com.gonzales.listadecontatos.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import br.com.gonzales.listadecontatos.exceptions.ViaCEPException;

public class User {

    private String nameTxt;
    private String phoneTxt;
    private String dateOfBirthTxt;
    private String cepTxt;
    private String estadoTxt;
    private String cidadeTxt;
    private String bairroTxt;
    private String ruaTxt;
    private String numeroTxt;

    public User(){
        this.nameTxt = null;
        this.phoneTxt = null;
        this.dateOfBirthTxt = null;
        this.cepTxt = null;
        this.estadoTxt = null;
        this.cidadeTxt = null;
        this.bairroTxt = null;
        this.ruaTxt = null;
        this.numeroTxt = null;
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

    public String getNameTxt() {
        return nameTxt;
    }

    public void setNameTxt(String nameTxt) {
        this.nameTxt = nameTxt;
    }

    public String getPhoneTxt() {
        return phoneTxt;
    }

    public void setPhoneTxt(String phoneTxt) {
        this.phoneTxt = phoneTxt;
    }

    public String getDateOfBirthTxt() {
        return dateOfBirthTxt;
    }

    public void setDateOfBirthTxt(String dateOfBirthTxt) {
        this.dateOfBirthTxt = dateOfBirthTxt;
    }

    public String getCepTxt() {
        return cepTxt;
    }

    public void setCepTxt(String cepTxt) {
        this.cepTxt = cepTxt;
    }

    public String getEstadoTxt() {
        return estadoTxt;
    }

    public void setEstadoTxt(String estadoTxt) {
        this.estadoTxt = estadoTxt;
    }

    public String getCidadeTxt() {
        return cidadeTxt;
    }

    public void setCidadeTxt(String cidadeTxt) {
        this.cidadeTxt = cidadeTxt;
    }

    public String getBairroTxt() {
        return bairroTxt;
    }

    public void setBairroTxt(String bairroTxt) {
        this.bairroTxt = bairroTxt;
    }

    public String getRuaTxt() {
        return ruaTxt;
    }

    public void setRuaTxt(String ruaTxt) {
        this.ruaTxt = ruaTxt;
    }

    public String getNumeroTxt() {
        return numeroTxt;
    }

    public void setNumeroTxt(String numeroTxt) {
        this.numeroTxt = numeroTxt;
    }

    @Override
    public String toString() {
        return "User{" +
                "nameTxt='" + nameTxt + '\'' +
                ", phoneTxt=" + phoneTxt +
                ", dateOfBirthTxt=" + dateOfBirthTxt +
                ", cepTxt='" + cepTxt + '\'' +
                ", estadoTxt='" + estadoTxt + '\'' +
                ", cidadeTxt='" + cidadeTxt + '\'' +
                ", bairroTxt='" + bairroTxt + '\'' +
                ", ruaTxt='" + ruaTxt + '\'' +
                ", numeroTxt=" + numeroTxt +
                '}';
    }
}
