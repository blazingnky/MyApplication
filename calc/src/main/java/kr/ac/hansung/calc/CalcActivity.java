package kr.ac.hansung.calc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CalcActivity extends AppCompatActivity {

    EditText firstNum;
    EditText secondNum;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        firstNum = (EditText)findViewById(R.id.inputText1);
        secondNum = (EditText)findViewById(R.id.inputText2);
        result = (TextView)findViewById(R.id.clacResult);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calc, menu);
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

    public void onClick(View view){
        switch (view.getId()){
            case R.id.additionBtn:
                result.setText("계산 결과 : "+ addition());
                break;
            case R.id.abstractionBtn:
                result.setText("계산 결과 : "+ abstraction());
                break;
            case R.id.multiplicationBtn:
                result.setText("계산 결과 : "+ multiplication());
                break;
            case R.id.divisionBtn:
                result.setText("계산 결과 : "+ division());
                break;
            case R.id.remainderBtn:
                result.setText("계산 결과 : "+ remainder());
                break;
        }
    }

    public String addition(){
        double num1 = Double.parseDouble(firstNum.getText().toString());
        double num2 = Double.parseDouble(secondNum.getText().toString());
        double sum =  num1+num2;
        return String.valueOf(sum);
    }

    public String abstraction(){
        double num1 = Double.parseDouble(firstNum.getText().toString());
        double num2 = Double.parseDouble(secondNum.getText().toString());
        double sum =  num1-num2;
        return String.valueOf(sum);
    }

    public String multiplication(){
        double num1 = Double.parseDouble(firstNum.getText().toString());
        double num2 = Double.parseDouble(secondNum.getText().toString());
        double sum =  num1*num2;
        return String.valueOf(sum);
    }

    public String division(){
        double num1 = Double.parseDouble(firstNum.getText().toString());
        double num2 = Double.parseDouble(secondNum.getText().toString());
        if(num2==0){
            Toast.makeText(CalcActivity.this, "0으로 나눌 수 없습니다.", Toast.LENGTH_LONG).show();
            return "다시 입력하세요";
        }
        double sum =  num1/num2;
        return String.valueOf(sum);
    }

    public String remainder(){
        double num1 = Double.parseDouble(firstNum.getText().toString());
        double num2 = Double.parseDouble(secondNum.getText().toString());
        if(num2==0){
            Toast.makeText(CalcActivity.this, "0으로 나눌 수 없습니다.", Toast.LENGTH_LONG).show();
            return "다시 입력하세요";
        }
        double sum =  num1%num2;
        return String.valueOf(sum);
    }
}
