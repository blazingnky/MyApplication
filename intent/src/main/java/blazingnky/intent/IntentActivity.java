package blazingnky.intent;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class IntentActivity extends AppCompatActivity {
    EditText firstNum, secondNum;
    Button calButton;
    RadioGroup radioGroup;
    RadioButton add, sub, mul, div;
    int method;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);
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

        firstNum = (EditText)findViewById(R.id.firstEditText);
        secondNum = (EditText)findViewById(R.id.secondEditText);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        add = (RadioButton)findViewById(R.id.addRadioButton);
        sub = (RadioButton)findViewById(R.id.subRadioButton);
        mul = (RadioButton)findViewById(R.id.mulRadioButton);
        div = (RadioButton)findViewById(R.id.divRadioButton);

        calButton = (Button)findViewById(R.id.calButton);

        calButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GoBackActivity.class);

                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.addRadioButton:
                        method = 1;
                        break;
                    case R.id.subRadioButton:
                        method = 2;
                        break;
                    case R.id.mulRadioButton:
                        method = 3;
                        break;
                    case R.id.divRadioButton:
                        method = 4;
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "계산 방식을 선택하세요", Toast.LENGTH_LONG).show();
                        break;
                }
                intent.putExtra("Num1", Integer.parseInt(firstNum.getText().toString()));
                intent.putExtra("Num2", Integer.parseInt(secondNum.getText().toString()));
                intent.putExtra("Method", method);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_intent, menu);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            int result = data.getIntExtra("Result", 0);
            Toast.makeText(getApplicationContext(), "결과 : "+ result, Toast.LENGTH_LONG).show();
        }
    }
}
