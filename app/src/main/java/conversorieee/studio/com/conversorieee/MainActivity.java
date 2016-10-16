package conversorieee.studio.com.conversorieee;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    private EditText valorReal;
    private ImageView botaoDolar;
    private ImageView botaoEuro;
    private ImageView botaoLibra;
    private ImageView botaoIene;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoDolar = (ImageView) findViewById(R.id.dolarid);
        botaoEuro = (ImageView) findViewById(R.id.euroid);
        botaoLibra = (ImageView) findViewById(R.id.libraid);
        botaoIene = (ImageView) findViewById(R.id.ieneid);
        valorReal = (EditText) findViewById(R.id.realid);


        botaoDolar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String textoValorReal = valorReal.getText().toString();

                int controlvariable = valorReal.getText().toString().length();

                if(controlvariable == 0) {
                    Toast.makeText(getApplicationContext(), "Para realizar a conversão deve ser inserido um valor válido", Toast.LENGTH_SHORT).show();
                }
                else {
                    double valorReal = Double.parseDouble(textoValorReal);

                    double dolarFinal = valorReal / (3.35);


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
                    Toast.makeText(getApplicationContext(), "Para realizar a conversão deve ser inserido um valor válido", Toast.LENGTH_SHORT).show();
                }
                else {
                double valorReal = Double.parseDouble(textoValorReal);

                double euroFinal = valorReal / (3.68);
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
                    Toast.makeText(getApplicationContext(), "Para realizar a conversão deve ser inserido um valor válido", Toast.LENGTH_SHORT).show();
                }
                else {
                double valorReal = Double.parseDouble(textoValorReal);

                double libraFinal = valorReal / (4.22);
                System.out.print(libraFinal);

                Intent intent2 = new Intent(MainActivity.this, LibraActivity.class);
//                Bundle passing = new Bundle();
//                passing.putDouble("libraEnd", libraFinal);
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
                    Toast.makeText(getApplicationContext(), "Para realizar a conversão deve ser inserido um valor válido", Toast.LENGTH_SHORT).show();
                }
                else {
                double valorReal = Double.parseDouble(textoValorReal);

                double ieneFinal = valorReal * (32.57);
                System.out.print(ieneFinal);

                Intent intent3 = new Intent(MainActivity.this, IeneActivity.class);
//                Bundle passing = new Bundle();
//                passing.putDouble("ieneEnd", ieneFinal);
                intent3.putExtra("passando_iene", ieneFinal);

                startActivity(intent3);}

            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client2 = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client2.connect();
        AppIndex.AppIndexApi.start(client2, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client2, getIndexApiAction());
        client2.disconnect();
    }
}

