package com.example.leonardotalero.clases;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;

import java.io.IOException;
import java.text.Normalizer;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by leonardotalero on 6/22/15.
 */
public class RequestTask extends AsyncTask<String, String, String> {


    public String resultado="";

    @Override
    protected String doInBackground(String... mURL1) {


        try {
            Thread.sleep(100);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

///////////////////////////HTTP FUNCTION/////////////////////////////////////
        String response = "";
        String mURL = mURL1[0].replace(" ", "%20");
        Log.i("LocAnd Response HTTP", "Ejecutando get 0:" + mURL);
        HttpClient httpclient = new DefaultHttpClient();

        Log.i("LocAnd Response HTTP", "Ejecutando get 1");
        HttpGet httppost = new HttpGet(mURL);
        Log.i("LocAnd Response HTTP", "Ejecutando get 2");
        try {


            Log.i("LocAnd Response HTTP", "Ejecutando get");
            // Execute HTTP Post Request
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httppost, responseHandler);
            Log.i("LocAnd Response HTTP", response);
        } catch (ClientProtocolException e) {
            Log.i("LocAnd Response ERROR 1", e.getMessage());
            // TODO Auto-generated catch block
        } catch (IOException e) {

            Log.i("LocAnd Response ERROR 2", e.getMessage());
            // TODO Auto-generated catch block
        }
        resultado=response;
        return response;


        // TODO Auto-generated method stub

    }


    @Override
    protected void onPostExecute(String data) {
        super.onPostExecute(data);

        JSONArray ja = null;

        if (data.length() > 1){

            resultado=data;


            // ja = new JSONArray(data);
            /*StatusLine statusline = response.getStatusLine();

            if (statusline.getStatusCode() == HttpStatus.SC_OK) {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                try {
                    response.getEntity().writeTo(out);
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //String responsestring = out.toString();


            } else {
                try {
                    response.getEntity().getContent().close();
                    atributotextview.setText("Error");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            */
            System.out.println("ok despues de ejecutar");

        }

            /*
            try{


                if(ja.equals(null))
                {
                    Toast.makeText(getApplicationContext(), "Error recuperando la informacion del servidor, verifique su usuario y contrase�a de la empresa", 2000).show();
                }else{
                    Intent loginven = new Intent(MainActivity.this,MenuActivity.class);
                    startActivity(loginven);

                }


            } catch (Exception e) {

                //pass.setText("");
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Error recuperando la informacion del servidor, verifique usuario y contrase�a.", 1000).show();

            }*/


        /////////////////////


        //pvar.dismiss();


    }




}
