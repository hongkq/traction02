package com.example.traction02.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.traction02.MainActivity;
import com.example.traction02.R;
import com.example.traction02.SqlLitter.SQLiteDbHelpes;

public class ETCzhangdang extends AppCompatActivity {
    private ImageView imageView;
    private Spinner spinner;
    private Button button;
    private ListView listView;
    private SQLiteDbHelpes dbHelpes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_etczhangdang);
        initView();
        onlick();
    }
    private  void initView(){
        imageView=(ImageView)findViewById (R.id.back);
        spinner=(Spinner)findViewById (R.id.spinner2);
        button=(Button)findViewById (R.id.button1);
        listView=(ListView)findViewById (R.id.listView1);

    }
    private void  onlick(){
        imageView.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent (ETCzhangdang.this, MainActivity.class);
                startActivity (intent);
            }
        });
        button.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                String ETchem=spinner.toString ().trim ();
                if (ETchem.equals ("时间升序")){
                    SQLiteDatabase db=dbHelpes.getWritableDatabase ();
                    Cursor cursor=db.query ("ETCchonzhi",null,null,null,null,null,null);
                    if (cursor.moveToFirst ()){
                        do {
                            String carid = cursor.getString (cursor.getColumnIndex ("carid"));
                            String money = cursor.getString (cursor.getColumnIndex ("money"));
                            String username = cursor.getString (cursor.getColumnIndex ("username"));
                            String userTime = cursor.getString (cursor.getColumnIndex ("userTime"));
                            String []data={username,carid,money,userTime};
                            ArrayAdapter <String> adapter=new ArrayAdapter <> (ETCzhangdang.this,android.R.layout.simple_list_item_1,data);
                            listView.setAdapter (adapter);

                        }while (cursor.moveToNext ());
                    }
                    cursor.close ();
                }else{
                    SQLiteDatabase db=dbHelpes.getWritableDatabase ();
                    Cursor cursor=db.query ("ETCchonzhi",null,null,null,null,null,null);
                    if (cursor.moveToFirst ()){
                        do {
                            String carid = cursor.getString (cursor.getColumnIndex ("carid"));
                            String money = cursor.getString (cursor.getColumnIndex ("money"));
                            String username = cursor.getString (cursor.getColumnIndex ("username"));
                            String userTime = cursor.getString (cursor.getColumnIndex ("userTime"));
                            String []data={username,carid,money,userTime};
                            ArrayAdapter <String> adapter=new ArrayAdapter <> (ETCzhangdang.this,android.R.layout.simple_list_item_1,data);
                            listView.setAdapter (adapter);

                        }while (cursor.moveToNext ());
                    }
                    cursor.close ();

                }
            }
        });
    }
}
