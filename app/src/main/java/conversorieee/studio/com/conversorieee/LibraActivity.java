package conversorieee.studio.com.conversorieee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LibraActivity extends AppCompatActivity {

    private TextView libraValue;
    private Button botaoRetornar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libra);

        Intent intent = getIntent();
        double respostaLibra = intent.getDoubleExtra("passando_libra", 0);

        System.out.println(respostaLibra);

        libraValue = (TextView)findViewById(R.id.valorlibraid);
        libraValue.setText(String.format( "%.2f", respostaLibra ));


        botaoRetornar = (Button) findViewById(R.id.retornarid);




        botaoRetornar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LibraActivity.this, MainActivity.class));
            }
        });


    }
}
