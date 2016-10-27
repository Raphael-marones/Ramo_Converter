package conversorieee.studio.com.conversorieee;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText valorReal;
    private ImageView botaoDolar;
    private ImageView botaoEuro;
    private ImageView botaoLibra;
    private ImageView botaoIene;


    public Double realPound;
    public Double realEuro;
    public Double realYen;
    public Double dollar_real;
    public Double dollar_euro;
    public Double dollar_iene;
    public Double dollar_libra;
    String moving_results;
    SharedPreferences preferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoDolar = (ImageView) findViewById(R.id.dolarid);
        botaoEuro = (ImageView) findViewById(R.id.euroid);
        botaoLibra = (ImageView) findViewById(R.id.libraid);
        botaoIene = (ImageView) findViewById(R.id.ieneid);
        valorReal = (EditText) findViewById(R.id.realid);

        final Toast errorMessage = Toast.makeText(getApplicationContext(), "Ops!!!" +
                " Faltou inserir um valor válido",Toast.LENGTH_SHORT);
        errorMessage.setGravity(Gravity.CENTER, 0, 0);

        final Toast internetOff = Toast.makeText(getApplicationContext(), "Ops!!!" + "SEM INTERNET: Valores podem não estar atualizados!!!",Toast.LENGTH_SHORT);
        errorMessage.setGravity(Gravity.CENTER, 0, 0);


        isOnline();

        if (isOnline()==true){
            new GetWebpageTask().execute("https://openexchangerates.org/api/latest.json?app_id=bce86f739d7b4cd9a60b82da71c9c742");

            preferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("Rates", moving_results);
            editor.apply();

        }
        else if (isOnline()==false){

            preferences = PreferenceManager.getDefaultSharedPreferences(this);
            String updated_rates = preferences.getString("Rates", "");

            if (updated_rates.length()>0){


                try{
                    JSONObject obj = new JSONObject(updated_rates);
                    JSONObject rates = obj.getJSONObject("rates");
                    String reais = rates.getString("BRL");
                    String euros = rates.getString("EUR");
                    String ienes = rates.getString("JPY");
                    String libras = rates.getString("GBP");

                    dollar_real = 1/ Double.parseDouble(reais);
                    dollar_euro = Double.parseDouble(euros);
                    dollar_iene = Double.parseDouble(ienes);
                    dollar_libra = Double.parseDouble(libras);

                    realEuro = dollar_euro * dollar_real;
                    realPound = dollar_libra * dollar_real;
                    realYen = dollar_iene * dollar_real;}

                catch (JSONException e1) {
                    e1.printStackTrace();
                }

            }
            else {

                internetOff.show();
                realEuro = 0.29;
                realPound = 0.26;
                realYen = 32.97;
                dollar_real = 0.32;

            }

            //AQUI DEVERIAMOS PUXAR VALORES DO SHARED PREFERENCES

        }


        botaoDolar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String textoValorReal = valorReal.getText().toString();

                int controlvariable = valorReal.getText().toString().length();

                if(controlvariable == 0) {

                    errorMessage.show();


                }
                else {
                    double valorReal = Double.parseDouble(textoValorReal);

                    double dolarFinal = valorReal * dollar_real;


                    Intent intent = new Intent(MainActivity.this, DolarActivity.class);

                    intent.putExtra("passando", dolarFinal);

                    startActivity(intent);
                }

            }
        });

        botaoEuro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String textoValorReal = valorReal.getText().toString();
                int controlvariable = valorReal.getText().toString().length();

                if(controlvariable == 0) {
                    errorMessage.show();

                }
                else {
                double valorReal = Double.parseDouble(textoValorReal);

                double euroFinal = valorReal * realEuro;
                System.out.print(euroFinal);

                Intent intent1 = new Intent(MainActivity.this, EuroActivity.class);
//                Bundle passing = new Bundle();
//                passing.putDouble("dolarEnd", dolarFinal);
                intent1.putExtra("passando_euro", euroFinal);

                startActivity(intent1);}

            }
        });

        botaoLibra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String textoValorReal = valorReal.getText().toString();
                int controlvariable = valorReal.getText().toString().length();

                if(controlvariable == 0) {
                    errorMessage.show();

                }
                else {
                double valorReal = Double.parseDouble(textoValorReal);

                double libraFinal = valorReal * realPound;
                System.out.print(libraFinal);

                Intent intent2 = new Intent(MainActivity.this, LibraActivity.class);
                intent2.putExtra("passando_libra", libraFinal);

                startActivity(intent2);}

            }
        });

        botaoIene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String textoValorReal = valorReal.getText().toString();
                int controlvariable = valorReal.getText().toString().length();

                if(controlvariable == 0) {
                    errorMessage.show();

                }
                else {
                double valorReal = Double.parseDouble(textoValorReal);

                double ieneFinal = valorReal * realYen;
                System.out.print(ieneFinal);

                Intent intent3 = new Intent(MainActivity.this, IeneActivity.class);
                intent3.putExtra("passando_iene", ieneFinal);

                startActivity(intent3);}

            }
        });

    }

    private String getWebsite(String address){

        StringBuffer buffering = new StringBuffer();

        BufferedReader reader = null;

        try{
            URL url = new URL(address);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            reader = new BufferedReader(new InputStreamReader(in));
            String line ="";

            while ((line = reader.readLine()) != null){
                buffering.append(line);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return buffering.toString();
    }

    public boolean isOnline() {

        Runtime runtime = Runtime.getRuntime();
        try {

            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);

        } catch (IOException e)          { e.printStackTrace(); }
        catch (InterruptedException e) { e.printStackTrace(); }

        return false;
    }

    public class GetWebpageTask extends AsyncTask<String,Void,String>{


//        @Override
//        protected void onProgressUpdate(){
//            super.onProgressUpdate();
//        }

        @Override
        protected void onPostExecute(String result){

            super.onPostExecute(result);



            try{

                JSONObject obj = new JSONObject(result);
                JSONObject rates = obj.getJSONObject("rates");
                String reais = rates.getString("BRL");
                String euros = rates.getString("EUR");
                String ienes = rates.getString("JPY");
                String libras = rates.getString("GBP");

                dollar_real = 1/ Double.parseDouble(reais);
                dollar_euro = Double.parseDouble(euros);
                dollar_iene = Double.parseDouble(ienes);
                dollar_libra = Double.parseDouble(libras);

                realEuro = dollar_euro * dollar_real;
                realPound = dollar_libra * dollar_real;
                realYen = dollar_iene * dollar_real;}

            catch (JSONException e1) {
                e1.printStackTrace();
            }

            moving_results = result;

        }

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... url) {

            return getWebsite(url[0]);


        }

    }



}

