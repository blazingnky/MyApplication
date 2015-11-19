package blazingnky.intent;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class GoBackActivity extends AppCompatActivity {
    Button button;
    int num1, num2, method, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_back);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Intent intent = getIntent();

        num1 = intent.getIntExtra("Num1", 0);
        num2 = intent.getIntExtra("Num2", 0);
        method = intent.getIntExtra("Method", 0);

       // final  int hapValue = intent.getIntExtra("Num1", 0) + intent.getIntExtra("Num2", 0) + intent.getIntExtra("Method", 0);
        button  = (Button)findViewById(R.id.gobackButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent outIntent = new Intent(getApplicationContext(), IntentActivity.class);
                switch (method){
                    case 1: result = num1+num2;
                        break;
                    case 2: result = num1 - num2;
                        break;
                    case 3: result = num1 * num2;
                        break;
                    case 4: result = num1 / num2;
                        break;
                    default: break;
                }
                outIntent.putExtra("Result", result);
                setResult(RESULT_OK, outIntent);
                finish();
            }
        });
    }

}
