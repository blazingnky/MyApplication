package kr.ac.hansung.dialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class dialogActivity extends AppCompatActivity {
    Button clickBtn;
    View dialogView, toastView;
    EditText mEditTextName, mEditTextEmail, dEditTextName, dEditTextEmail;
    TextView toastText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
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

        mEditTextName = (EditText)findViewById(R.id.meditText_name);
        mEditTextEmail = (EditText)findViewById(R.id.meditText_email);

        clickBtn = (Button)findViewById(R.id.clickBtn);
        clickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogView = (View)View.inflate(dialogActivity.this, R.layout.dialog, null);
                AlertDialog.Builder dig = new AlertDialog.Builder(dialogActivity.this);

                dEditTextName = (EditText)dialogView.findViewById(R.id.deditText_name);
                dEditTextEmail = (EditText)dialogView.findViewById(R.id.deditText_email);

                dig.setTitle("사용자 정보 입력");
                dig.setIcon(R.drawable.ic_account_box_black_24dp);

                dig.setView(dialogView);

                dEditTextName.setText(mEditTextName.getText().toString());
                dEditTextEmail.setText(mEditTextEmail.getText().toString());

                dig.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        mEditTextName.setText(dEditTextName.getText().toString());
                        mEditTextEmail.setText(dEditTextEmail.getText().toString());
                    }
                });
                dig.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast toast = new Toast(dialogActivity.this);
                        toastView = (View)View.inflate(dialogActivity.this, R.layout.toast, null);
                        toastText = (TextView)toastView.findViewById(R.id.toastTextView);
                        toastText.setText("취소했습니다.");

                        Display display = ((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
                        int xOffset = (int)(Math.random()*display.getWidth());
                        int yOffset = (int)(Math.random()*display.getHeight());

                        toast.setGravity(Gravity.TOP | Gravity.LEFT, xOffset, yOffset);
                        toast.setView(toastView);
                        toast.show();
                    }
                });
                dig.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dialog, menu);
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
