package conversorieee.studio.com.conversorieee;

/**
 * Created by raphaelsantos on 10/15/16.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class EuroActivity extends AppCompatActivity {

    private TextView euroValue;
    private Button botaoRetornar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_euro);

        Intent intent = getIntent();
        double respostaEuro = intent.getDoubleExtra("passando_euro", 0);

        System.out.println(respostaEuro);

        euroValue = (TextView)findViewById(R.id.valoreuroid);
        euroValue.setText(String.format( "%.2f", respostaEuro ));


        botaoRetornar = (Button) findViewById(R.id.retornarid);




        botaoRetornar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EuroActivity.this, MainActivity.class));
            }
        });
    }
}
