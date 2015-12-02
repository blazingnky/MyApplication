package blazingnky.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterActivity extends AppCompatActivity {
    public Integer[] posterID = { R.drawable.inside, R.drawable.jobs, R.drawable.monster, R.drawable.myungryang, R.drawable.newworld,
            R.drawable.watchers, R.drawable.assasinate, R.drawable.room7, R.drawable.avengers, R.drawable.inside, R.drawable.jobs, R.drawable.monster, R.drawable.myungryang, R.drawable.newworld,
            R.drawable.watchers, R.drawable.assasinate, R.drawable.room7, R.drawable.avengers};
    public String [] posterName = {"내부자들", "잡스", "괴물", "명량", "신세계", "감시자들", "암살", "7번방의선물", "어벤저스", "내부자들", "잡스", "괴물", "명량", "신세계", "감시자들", "암살", "7번방의선물", "어벤저스"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("그리드뷰 영화 포스터");

        final GridView gv = (GridView)findViewById(R.id.gridView1);
        MyGridAdapter gAdapter = new MyGridAdapter(this);
        gv.setAdapter(gAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_adapter, menu);
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

    private static class MoviewViewHolder{
        public ImageView poster;
        public TextView title;
    }

    public class MyGridAdapter extends BaseAdapter {
        private LayoutInflater layoutInflater;

        public MyGridAdapter(Context c){
            layoutInflater = LayoutInflater.from(c);
        }

        @Override
        public int getCount(){
            return posterID.length;
        }

        @Override
        public Object getItem(int position) {
            return  position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            MoviewViewHolder moviewViewHolder;
            View view = convertView;

            if(view ==null) {
                view = layoutInflater.inflate(R.layout.grid_item, parent, false);

                moviewViewHolder = new MoviewViewHolder();
                moviewViewHolder.poster = (ImageView) view.findViewById(R.id.imageView);
                moviewViewHolder.title = (TextView) view.findViewById(R.id.textView);
                view.setTag(moviewViewHolder);
            }
            else{
                moviewViewHolder = (MoviewViewHolder)view.getTag();
            }

            moviewViewHolder.poster.setImageResource(posterID[position]);
            moviewViewHolder.title.setText(posterName[position]);

            final int pos = position;
            moviewViewHolder.poster.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    View dialogView = View.inflate(AdapterActivity.this, R.layout.dialog, null);
                    AlertDialog.Builder dig = new AlertDialog.Builder(AdapterActivity.this);
                    ImageView ivPoster = (ImageView) dialogView.findViewById(R.id.ivPoster);
                    ivPoster.setImageResource(posterID[pos]);

                    dig.setIcon(R.drawable.movie);
                    dig.setTitle(posterName[pos]);
                    dig.setView(dialogView);
                    dig.setNegativeButton("닫기", null);
                    dig.show();
            }
            });

            return view;
        }
    }
}
