package com.example.leonardotalero.activities;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.leonardotalero.clases.PaisPrima;
import com.example.leonardotalero.primariesgosur.R;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends ActionBarActivity {
    List<PaisPrima> listapaisesprima=new ArrayList<PaisPrima>();
    ProgressDialog pvar;
    List<String> listapaises;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pvar = ProgressDialog.show(MainActivity.this, "Verificando Acceso", "Espere por favor...", true);


        String paisesarray[]={"Portugal","Espana","Italia","Francia","Alemania","Grecia","Irlanda"};

        listapaises= Arrays.asList(paisesarray);

        String nombrepaisreferencia=PaisPrima.paisreferencia.nombre;
        System.out.println("Nombre pais de Referencia - [" + nombrepaisreferencia + "]");
        Collections.sort(listapaises);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        List<String> arrayurls=new ArrayList<String>();
        String url1="";
        int k=1;

        String urls[]=new String[listapaises.size()+1];
        for(String l:listapaises){
            
            PaisPrima paisprima=new PaisPrima(l);
            String urlpais=paisprima.nombreURL();
           urls[k]="http://www.eleconomista.es/prima-riesgo/"+ urlpais;
            //arrayurls.add(url);
            String response = "";
            String mURL = urls[k].replace(" ", "%20");
            Log.i("LocAnd Response HTTP", "Ejecutando get 0:" + mURL);
            HttpClient httpclient = new DefaultHttpClient();

           // Log.i("LocAnd Response HTTP", "Ejecutando get 1");
            HttpGet httppost = new HttpGet(mURL);
           // Log.i("LocAnd Response HTTP", "Ejecutando get 2");
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

           String resultado=response;
            String responsestring = resultado.toString();
            Pattern patern = Pattern.compile("\\| (\\d{3},\\d{2})");
            Matcher matcher = patern.matcher(responsestring);
            String prima="";
            while (matcher.find()) {
                prima = matcher.group(1);
            }
            if(!prima.equals("")){
                paisprima.prima=Float.parseFloat(prima.replace(",","."));
               // listapaisesprima.add(paisprima);
            }

            listapaisesprima.add(paisprima);
            k++;

        }
        //new RequestTaskactivity().execute(urls[1], urls[2], urls[3], urls[4], urls[5]);
        PaisPrima[] array=new PaisPrima[listapaisesprima.size()];
        ArrayAdapter<PaisPrima> adapter = new ArrayAdapter<PaisPrima>(this,
                android.R.layout.simple_list_item_1, listapaisesprima.toArray(array));
        //ArrayAdapter<PaisPrima> paisprimaarrayadapter=new ArrayAdapter<PaisPrima>
        //        (this,R.layout.activity_main,listapaisesprima.toArray(array));
        ListView listiview=(ListView) findViewById(R.id.listView);
        listiview.setAdapter(adapter);

        pvar.dismiss();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    public class RequestTaskactivity extends AsyncTask<String, String, String> {


        public String resultado="";

        @Override
        protected String doInBackground(String... mURLS) {
            try {
                Thread.sleep(100);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            int count = mURLS.length;
            //long totalSize = 0;
            for (int i = 0; i < count; i++) {






///////////////////////////HTTP FUNCTION/////////////////////////////////////
            String response = "";
            String mURL = mURLS[i].replace(" ", "%20");
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


        }
            return "ok";
            // TODO Auto-generated method stub

        }


        @Override
        protected void onPostExecute(String data) {
            super.onPostExecute(data);

            JSONArray ja = null;

            if (data.length() > 1){



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


            pvar.dismiss();


        }




    }

}
