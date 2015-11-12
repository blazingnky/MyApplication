package kr.ac.hansung.simplediary;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class SimpleDiaryActivity extends AppCompatActivity {

    DatePicker datePicker;
    EditText editTextDiary;
    Button btnWrite;
    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_diary);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        datePicker = (DatePicker)findViewById(R.id.datePicker);
        editTextDiary = (EditText)findViewById(R.id.editText);
        btnWrite = (Button)findViewById(R.id.btnWrite);

        Calendar calendar = Calendar.getInstance();
        int cYear = calendar.get(Calendar.YEAR);
        int cMonth = calendar.get(Calendar.MONTH);
        int cDay = calendar.get(Calendar.DAY_OF_MONTH);


        fileName = Integer.toString(cYear)+ "_"+Integer.toString(cMonth+1)+"_"+Integer.toString(cDay)+".txt";
        String str = readDiary(fileName);
        if(str!=null){
            editTextDiary.setText(str);
            btnWrite.setText("수정하기");
            btnWrite.setEnabled(true);
        }

        datePicker.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                fileName = Integer.toString(year)+ "_"+Integer.toString(monthOfYear+1)+"_"+Integer.toString(dayOfMonth)+".txt";
                String str = readDiary(fileName);
                if(str!=null){
                    editTextDiary.setText(str);
                    btnWrite.setText("수정하기");
                    btnWrite.setEnabled(true);
                }
                editTextDiary.setText(str);
                btnWrite.setEnabled(true);
            }
        });

    btnWrite.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try{
                FileOutputStream outFs = openFileOutput(fileName, Context.MODE_WORLD_WRITEABLE);
                String str = editTextDiary.getText().toString();
                outFs.write(str.getBytes());
                outFs.close();
                Toast.makeText(getApplicationContext(), fileName+"이 저장됨", Toast.LENGTH_LONG).show();
            }catch (IOException e){

            }
        }
    });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    String readDiary(String fileName){
        String diaryStr = null;
        FileInputStream inFs;
        try{
            inFs = openFileInput(fileName);
            byte[] txt = new byte[500];
            inFs.read(txt);
            inFs.close();
            diaryStr = (new String(txt)).trim();
            btnWrite.setText("수정하기");

        }catch(IOException e){
            editTextDiary.setHint("일기 없음");
            btnWrite.setText("새로 저장");
       }
        return diaryStr;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_simple_diary, menu);
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
}
