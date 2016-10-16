package conversorieee.studio.com.conversorieee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class IeneActivity extends AppCompatActivity {

    private TextView ieneValue;
    private Button botaoRetornar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iene);

        Intent intent = getIntent();
        double respostaIene = intent.getDoubleExtra("passando_iene", 0);

        System.out.println(respostaIene);

        ieneValue = (TextView)findViewById(R.id.valorieneid);
        ieneValue.setText(String.format( "%.2f", respostaIene ));


        botaoRetornar = (Button) findViewById(R.id.retornarid);




        botaoRetornar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IeneActivity.this, MainActivity.class));
            }
        });


    }
}
