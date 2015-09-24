package kr.ac.hansung.button;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class ButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_button, menu);
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
            case R.id.button1:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.nate.com")));
                break;
            case R.id.button2:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("tel:/911")));
                break;

            case R.id.button3:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("content://media/Internal/images/media")));
                break;

            case R.id.button4: finish();
                break;
        }
    }
}
