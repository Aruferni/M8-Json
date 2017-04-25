package com.example.albertfernie.m8_json;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.example.albertfernie.m8_json.R.id.etLogin;

public class MainActivity extends AppCompatActivity {

    private EditText login, password, sha,  md5, json;
    private Button btCifrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        traerReferencias();
    }

    public void cifrar(View view) throws JSONException {
        String user = login.getText().toString();
        String pass = password.getText().toString();
        String cifradoSha = SHA1_Hash(user);
        String cifradoMd5 = MD5_Hash(pass);
        JSONObject cifradoJson = stringsTojson();
        sha.setText(cifradoSha);
        md5.setText(cifradoMd5);
        json.setText((CharSequence) cifradoJson);
    }

    private void  jsonToStrings(String json) throws JSONException {
        JSONObject jsonObj = new JSONObject(json);
        String user = jsonObj.getString("UserName");
        String pass = jsonObj.getString("Password");
    }

    private JSONObject stringsTojson() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("UserName", login.getText().toString());
        json.put("Password", password.getText().toString());
        return json;
    }

    public  String SHA1_Hash(String s) {
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        m.update(s.getBytes(), 0, s.length());
        String hash = new BigInteger(1, m.digest()).toString(16);
        return hash;
    }

    public  String MD5_Hash(String s) {
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        m.update(s.getBytes(),0,s.length());
        String hash = new BigInteger(1, m.digest()).toString(16);
        return hash;
    }

    private void traerReferencias() {
        login = (EditText) findViewById(etLogin);
        password = (EditText) findViewById(R.id.etPassword);
        sha = (EditText) findViewById(R.id.etSha);
        md5 = (EditText) findViewById(R.id.etMd5);
        json = (EditText) findViewById(R.id.etJson);
        btCifrar = (Button) findViewById(R.id.btAccept);
    }


}
