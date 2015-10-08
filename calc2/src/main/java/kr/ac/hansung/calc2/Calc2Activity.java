package kr.ac.hansung.calc2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Calc2Activity extends AppCompatActivity {

    EditText input1;
    EditText input2;
    TextView result;
    Button[] numButtons = new Button[10];
    Integer[] numBtnIDs = {R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9};

    String num1;
    String num2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc2);
        input1 = (EditText)findViewById(R.id.editText);
        input2 = (EditText)findViewById(R.id.editText2);
        result = (TextView)findViewById(R.id.resultText);
        setTitle("테이블레이아웃 계산기");

        for(int i=0; i<numBtnIDs.length; i++){
            numButtons[i] = (Button)findViewById(numBtnIDs[i]);
        }

        for(int i=0; i<numBtnIDs.length; i++){
            final int index;
            index = i;

            numButtons[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(input1.isFocused()==true){
                        num1 = input1.getText().toString()+numButtons[index].getText().toString();
                        input1.setText(num1);
                    }
                    else if(input2.isFocused()==true){
                        num2 = input2.getText().toString()+numButtons[index].getText().toString();
                        input2.setText(num2);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "먼저 EditText를 선택하세요.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calc2, menu);
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
            case R.id.button_add:
                result.setText("계산 결과 : "+ addition());
                break;
            case R.id.button_sub:
                result.setText("계산 결과 : "+ abstraction());
                break;
            case R.id.button_mul:
                result.setText("계산 결과 : "+ multiplication());
                break;
            case R.id.button_div:
                result.setText("계산 결과 : "+ division());
                break;
            case R.id.button_rem:
                result.setText("계산 결과 : "+ remainder());
        }
    }

    public String addition(){
        double num1 = Double.parseDouble(input1.getText().toString());
        double num2 = Double.parseDouble(input2.getText().toString());
        double sum =  num1+num2;
        return String.valueOf(sum);
    }

    public String abstraction(){
        double num1 = Double.parseDouble(input1.getText().toString());
        double num2 = Double.parseDouble(input2.getText().toString());
        double sum =  num1-num2;
        return String.valueOf(sum);
    }

    public String multiplication(){
        double num1 = Double.parseDouble(input1.getText().toString());
        double num2 = Double.parseDouble(input2.getText().toString());
        double sum =  num1*num2;
        return String.valueOf(sum);
    }

    public String division(){
        double num1 = Double.parseDouble(input1.getText().toString());
        double num2 = Double.parseDouble(input2.getText().toString());
        if(num2==0){
            Toast.makeText(getApplicationContext(), "0으로 나눌 수 없습니다.", Toast.LENGTH_LONG).show();
            return "다시 입력하세요";
        }
        double sum =  num1/num2;
        return String.valueOf(sum);
    }

    public String remainder(){
        double num1 = Double.parseDouble(input1.getText().toString());
        double num2 = Double.parseDouble(input2.getText().toString());
        if(num2==0){
            Toast.makeText(getApplicationContext(), "0으로 나눌 수 없습니다.", Toast.LENGTH_LONG).show();
            return "다시 입력하세요";
        }
        double sum =  num1%num2;
        return String.valueOf(sum);
    }
}
