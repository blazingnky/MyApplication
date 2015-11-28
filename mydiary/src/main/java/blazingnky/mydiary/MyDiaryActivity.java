package blazingnky.mydiary;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

public class MyDiaryActivity extends AppCompatActivity {
    TextView dayTextview;  //날짜출력 TextView
    String fileName;  //파일 이름 저장 변수
    EditText diaryEditText;  //일기내용 입력 및 출력 EditText
    Button saveBtn;  //저장버튼
    View dialogDateView;  //coustom dialog view 날짜설정위한 뷰
    DatePicker datePicker;  //날짜선택 위젯
    int cYear, cMonth, cDay;  //날짜 년, 월, 일
    private static final String DIRECTORY_PATH = Environment.getExternalStorageDirectory() + "/mydiary/";
    File directory; //디렉터리
    float fontSize; //dayTextview의 글씨 크기를 설정

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_diary);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 초기화
        dayTextview = (TextView) findViewById(R.id.dayTextView);
        diaryEditText = (EditText) findViewById(R.id.diaryEditText);
        saveBtn = (Button) findViewById(R.id.saveBtn);
        Calendar calendar = Calendar.getInstance();
        cYear = calendar.get(Calendar.YEAR);
        cMonth = calendar.get(Calendar.MONTH);
        cDay = calendar.get(Calendar.DAY_OF_MONTH);
        fontSize = 20;

        //디렉터리 생성
        directory =  makeDirectory(DIRECTORY_PATH);

        //오늘 날짜 표시
        dayTextview.setText(Integer.toString(cYear) + "년 " + Integer.toString(cMonth + 1) + "월 " + Integer.toString(cDay) + "일");

        //파일 이름 년_월_일.txt
        fileName = Integer.toString(cYear) + "_" + Integer.toString(cMonth + 1) + "_" + Integer.toString(cDay) + ".txt";

        //어플 시작시 오늘 날짜에 해당하는 일기장이 있으면 불러온다
        final String str = readDiary(DIRECTORY_PATH+fileName); // "디렉터리 경로/파일 이름" 완전한 경로를 readDiary에 전달한다.

        //일기장에 내용이 있으면 내용을 출력한다.
        if (str != null) {
            diaryEditText.setText(str);
            saveBtn.setText("수정하기");
            saveBtn.setEnabled(true);
        }

        saveBtn.setOnClickListener(new View.OnClickListener() {    // 저장버튼 클릭 시
            @Override
            public void onClick(View v) {
                if(directory.isDirectory()) { //해당 디렉터리가 존재할 때
                    File file = new File(DIRECTORY_PATH + fileName); //파일생성
                    FileOutputStream fileOutputStream;

                    //해당 날짜 일기장이 없을 경우
                    if (file != null && !file.exists()) {
                        try {
                            file.createNewFile();//파일을 디렉터리에 생성
                            fileOutputStream = new FileOutputStream(file);
                            try {
                                String str = diaryEditText.getText().toString();
                                fileOutputStream.write(str.getBytes());//파일에 쓴다.
                                fileOutputStream.close();
                                saveBtn.setText("수정하기");
                                Toast.makeText(getApplicationContext(), fileName + "이 저장됨", Toast.LENGTH_LONG).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }//해당 파일이 존재하는 상태서 수정하기 버튼을 누르면 해당 일기장에 수정된 내용을 덮어 쓴다..
                    else if (file.exists() && saveBtn.getText().equals("수정하기")) {
                        try {
                            file.createNewFile();
                            fileOutputStream = new FileOutputStream(file);
                            try {
                                String str = diaryEditText.getText().toString();
                                fileOutputStream.write(str.getBytes());
                                fileOutputStream.close();
                                Toast.makeText(getApplicationContext(), fileName + "이 수정됨", Toast.LENGTH_LONG).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_diary, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

       switch (id){
           //크게 버튼을 누를 때마다 글씨크기 5씩 증가, 40이상 증가 불가
           case R.id.bigsize : //크게
               if(fontSize != 40){
                   fontSize+=5;
                   diaryEditText.setTextSize(fontSize);
               }
               else
                   Toast.makeText(getApplicationContext(), "최대 크기입니다.", Toast.LENGTH_LONG).show();
               break;
           //기본 글씨 크기
           case R.id.normalsize : //보통
               fontSize = 20;
               diaryEditText.setTextSize(fontSize);
               break;
           case R.id.smallsize : //작게
               if(fontSize!=10){
                   fontSize-=5;
                   diaryEditText.setTextSize(fontSize);
               }
               else
                   Toast.makeText(getApplicationContext(), "최소 크기입니다.", Toast.LENGTH_LONG).show();
               break;
           case R.id.reread : //다시 불러오기
               String str = readDiary(DIRECTORY_PATH + fileName);
               String date = dayTextview.getText().toString();
               if(str != null) {
                   diaryEditText.setText(str);
                   Toast.makeText(getApplicationContext(), date + "의 일기를 다시 불러왔습니다.", Toast.LENGTH_LONG).show();
               }else
                   Toast.makeText(getApplicationContext(), date +"의 일기가 없습니다.", Toast.LENGTH_LONG).show();
               break;
           case R.id.deletediary : //삭제하기
               View dialogView = View.inflate(MyDiaryActivity.this, R.layout.delete_dialog, null);
               AlertDialog.Builder dig = new AlertDialog.Builder(MyDiaryActivity.this);
               TextView deleteText = (TextView)dialogView.findViewById(R.id.deleteTextView);
               deleteText.setText(dayTextview.getText().toString() + " 일기를 삭제하시겠습니까?");
               dig.setTitle("일기 삭제");
               dig.setView(dialogView);
               dig.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       deleteDiary(DIRECTORY_PATH+fileName); //삭제함수
                   }
               });
               dig.setNegativeButton("닫기", null);
               dig.show();
               break;
           default: break;
       }

        return super.onOptionsItemSelected(item);
    }

    //Directory 생성 함수
    private File makeDirectory(String directoryPath) {
        File dir = new File(directoryPath);
        if (!dir.exists()) {  //Directory가 존재하지 않을 시
            dir.mkdirs();    //Directory 생성 : 경로의 상위 디렉터리도 생성
        }
        return dir;
    }

    //파일 읽기 함수
    public String readDiary(String filePath) {
        String diaryStr = null;
        FileInputStream inFs;
            try {   //일기장이 존재하면 해당 날짜 파일에서 읽어 문자열로 리턴
                inFs = new FileInputStream(filePath);
                byte[] txt = new byte[500];
                inFs.read(txt);
                inFs.close();
                diaryStr = (new String(txt)).trim();
            } catch (IOException e) {   //일기장이 존재하지 않을 때
                diaryEditText.setHint("일기 없음");
                saveBtn.setText("저장");
            }
        return diaryStr;
    }

    //파일을 지우는 함수
    public void deleteDiary(String filePath){
        diaryEditText.setText(null);
        File deleteFile = new File(filePath);
        deleteFile.delete(); //파일 삭제
        diaryEditText.setHint("일기 없음");
        saveBtn.setText("저장");
        String date = dayTextview.getText().toString();
        Toast.makeText(getApplicationContext(), date +"의 일기가 삭제되었습니다.", Toast.LENGTH_LONG).show();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dayTextView:
                dialogDateView = View.inflate(MyDiaryActivity.this, R.layout.dialog_date_picker, null);
                AlertDialog.Builder dialog = new AlertDialog.Builder(MyDiaryActivity.this);

                dialog.setTitle("날짜 선택");
                dialog.setView(dialogDateView);

                datePicker = (DatePicker) dialogDateView.findViewById(R.id.datePicker);
                datePicker.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        dayTextview.setText(Integer.toString(year) + "년 " + Integer.toString(monthOfYear + 1) + "월 " + Integer.toString(dayOfMonth) + "일");

                        //날짜가 바뀌면 해당하는 날짜의 일기 파일이 있는지 확인하기 위해 바뀐 날짜로 fileName을 변경한다.
                        fileName = Integer.toString(year) + "_" + Integer.toString(monthOfYear + 1) + "_" + Integer.toString(dayOfMonth) + ".txt";

                        //해당 날짜 이름을 가진 파일을 읽는다.
                        String str = readDiary(DIRECTORY_PATH + fileName);
                        if (str != null) {
                            diaryEditText.setText(str);
                            saveBtn.setText("수정하기");
                        }
                    }
                });

                dialog.setPositiveButton("확인", null);;

                dialog.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dayTextview.setText(Integer.toString(cYear) + "년 " + Integer.toString(cMonth + 1) + "월 " + Integer.toString(cDay) + "일");
                        Toast.makeText(getApplicationContext(), "취소", Toast.LENGTH_LONG).show();
                    }
                });
                dialog.show();
                break;
            default: break;
        }
    }
}
