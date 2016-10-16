package conversorieee.studio.com.conversorieee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DolarActivity extends AppCompatActivity {

    private TextView dolarValue;
    private Button  botaoRetornar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dolar);

        Intent intent = getIntent();
        double respostaDolar = intent.getDoubleExtra("passando", 0);

        System.out.println(respostaDolar);

        dolarValue = (TextView)findViewById(R.id.valordolarid);
        dolarValue.setText(String.format( "%.2f", respostaDolar ));


        botaoRetornar = (Button) findViewById(R.id.retornarid);




        botaoRetornar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DolarActivity.this, MainActivity.class));
            }
        });
    }
}
