package kr.ac.hansung.image;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class ImageActivity extends AppCompatActivity {
    Switch aSwitch;
    TextView selectText;
    TextView favoriteText;
    RadioGroup radioGroup;
    RadioButton jellybean, kitkat, lollipop;
    Button finishButton;
    Button restartButton;
    ImageView androidImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        selectText = (TextView)findViewById(R.id.selectMessage);
        favoriteText = (TextView)findViewById(R.id.favoriteMessage);
        radioGroup = (RadioGroup)findViewById(R.id.androidSelection);
        jellybean = (RadioButton)findViewById(R.id.jellybeanButton);
        kitkat = (RadioButton)findViewById(R.id.kitkatButton);
        lollipop = (RadioButton)findViewById(R.id.lollipopButton);
        finishButton = (Button)findViewById(R.id.finishButton);
        restartButton = (Button)findViewById(R.id.restartButton);
        androidImage = (ImageView)findViewById(R.id.androidImage);

        aSwitch = (Switch)findViewById(R.id.switch1);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(aSwitch.isChecked()){
                    favoriteText.setVisibility(View.VISIBLE);
                    radioGroup.setVisibility(View.VISIBLE);
                    finishButton.setVisibility(View.VISIBLE);
                    restartButton.setVisibility(View.VISIBLE);
                    androidImage.setVisibility(View.VISIBLE);
                }
                else {
                    favoriteText.setVisibility(View.INVISIBLE);
                    radioGroup.setVisibility(View.INVISIBLE);
                    finishButton.setVisibility(View.INVISIBLE);
                    restartButton.setVisibility(View.INVISIBLE);
                    androidImage.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_image, menu);
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
            case R.id.jellybeanButton:
                androidImage.setImageResource(R.drawable.jellybean);
                Toast.makeText(this, "젤리빈이 선택되었습니다~", Toast.LENGTH_LONG).show();
                break;
            case R.id.kitkatButton:
                androidImage.setImageResource(R.drawable.kitkat);
                Toast.makeText(this, "킷켓이 선택되었습니다~", Toast.LENGTH_LONG).show();
                break;
            case R.id.lollipopButton:
                androidImage.setImageResource(R.drawable.lollipop);
                Toast.makeText(this, "롤리팝이 선택되었습니다~", Toast.LENGTH_LONG).show();
                break;
            case R.id.finishButton:
                Toast.makeText(this, "종료합니다~", Toast.LENGTH_LONG).show();
                finish();
                break;
            case R.id.restartButton:
                favoriteText.setVisibility(View.INVISIBLE);
                radioGroup.setVisibility(View.INVISIBLE);
                finishButton.setVisibility(View.INVISIBLE);
                restartButton.setVisibility(View.INVISIBLE);
                androidImage.setVisibility(View.INVISIBLE);
                androidImage.setImageResource(0);
                aSwitch.setChecked(false);
                Toast.makeText(this, "처음으로 돌아갑니다~", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
