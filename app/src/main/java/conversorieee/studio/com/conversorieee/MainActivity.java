package conversorieee.studio.com.conversorieee;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    private EditText valorReal;
    private ImageView botaoDolar;
    private ImageView botaoEuro;
    private ImageView botaoLibra;
    private ImageView botaoYene;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoDolar = (ImageView) findViewById(R.id.dolarid);
        botaoEuro = (ImageView) findViewById(R.id.euroid);
        valorReal = (EditText) findViewById(R.id.realid);


        botaoDolar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String textoValorReal = valorReal.getText().toString();
                double valorReal = Double.parseDouble(textoValorReal);

                double dolarFinal = valorReal / (3.18);
                System.out.print(dolarFinal);

                Intent intent = new Intent(MainActivity.this, DolarActivity.class);
//                Bundle passing = new Bundle();
//                passing.putDouble("dolarEnd", dolarFinal);
                intent.putExtra("passando", dolarFinal);

                startActivity(intent);

            }
        });

        botaoEuro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String textoValorReal = valorReal.getText().toString();
                double valorReal = Double.parseDouble(textoValorReal);

                double euroFinal = valorReal / (3.68);
                System.out.print(euroFinal);

                Intent intent1 = new Intent(MainActivity.this, EuroActivity.class);
//                Bundle passing = new Bundle();
//                passing.putDouble("dolarEnd", dolarFinal);
                intent1.putExtra("passando_euro", euroFinal);

                startActivity(intent1);

            }
        });

    }

}

