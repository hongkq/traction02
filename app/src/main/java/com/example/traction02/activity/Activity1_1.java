package com.example.traction02.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.traction02.Login.LoginActivity;
import com.example.traction02.MainActivity;
import com.example.traction02.R;
import com.example.traction02.SqlLitter.SQLiteDbHelpes;
import com.example.traction02.util.HttpRequest;
import com.example.traction02.util.Shared;


import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.sql.SQLData;
import java.text.SimpleDateFormat;

public class Activity1_1 extends AppCompatActivity {
private ImageView imageView;
private TextView textView01;
private Spinner spinner;
private EditText editText;
private Button button01;
private  Button button02;
private SQLiteDbHelpes dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_activity1_1);
        InterView();
        OnClick();
        getCar ( 1);

    }
    private void InterView(){
     imageView=(ImageView) findViewById (R.id.imgtitle);
     textView01=(TextView)findViewById (R.id.text01);
     spinner=(Spinner) findViewById (R.id.spinner);
     editText=(EditText)findViewById (R.id.ye);
     button01=(Button)findViewById (R.id.zhaicun);
     button02=(Button)findViewById (R.id.chongzhi);

    }
    private void OnClick(){
        imageView.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent (Activity1_1.this, MainActivity.class);
                startActivity (intent);
            }
        });

        button01.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {

               int carcthem= Integer.parseInt((String) spinner.getSelectedItem());
                getCar (carcthem);
            }

        });
      button02.setOnClickListener (new View.OnClickListener ( ) {
          @Override
          public void onClick(View v) {
              int carthem= Integer.parseInt((String) spinner.getSelectedItem());
              int Money=Integer.parseInt (editText.getEditableText ().toString ().trim ());
              setCar(carthem,Money);
          }
      });

    }

    private  void setCar(final int carthem, final int Money){
    final String username = Shared.getString (Activity1_1.this,"username"," ");
   HttpRequest.post ("SetCarAccountRecharge", "{\"CarId\":\"" + carthem + "\"," + "\"Money\": \"" + Money + "\"," + "\"UserName\":\"" + username + "\"}",
           new Response.Listener <JSONObject> ( ) {
               @Override
               public void onResponse(JSONObject jsonObject) {
                try {
                   String success=jsonObject.getString ("ERRMSG");


                }catch (JSONException e){

                }
                   SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
                   //获取当前时间
                   Date time = new Date(System.currentTimeMillis());
                   dbHelper=new  SQLiteDbHelpes(Activity1_1.this,"MyDatabase.db",null,1);
                   SQLiteDatabase db=dbHelper.getWritableDatabase ();
                   ContentValues values=new ContentValues ();
                   values.put ("carid",carthem);
                   values.put ("money",Money);
                   values.put ("username",username);
                   values.put ("userTime", String.valueOf (time));
                   db.insert ("ETCchonzhi",null,values);

                Toast.makeText (getApplication (),"充值成功,请点击查询更新当前账户余额",Toast.LENGTH_LONG).show ();
               }
           }, new Response.ErrorListener ( ) {
               @Override
               public void onErrorResponse(VolleyError volleyError) {
                   Toast.makeText (getApplication (),"充值失败，当前账户金额"+textView01.getText (),Toast.LENGTH_LONG).show ();

               }
           }


   );
    }
    private void getCar(int carcthem) {
       final String username = Shared.getString (Activity1_1.this,"username"," ");
        HttpRequest.post ("GetCarAccountBalance", "{\"CarId\":\"" + carcthem + "\",\"UserName\": \"" + username + "\"}",
                new Response.Listener <JSONObject> ( ) {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        String Balance = null;
                        try{
                            Balance=jsonObject.getString ("Balance");

                        }catch (JSONException e){
                            e.printStackTrace ();
                        }
                        textView01.setText (Balance);
                        Toast.makeText (getApplication (),"查询成功，当前账户金额"+textView01.getText (),Toast.LENGTH_LONG).show ();

                    }
                },
                new Response.ErrorListener ( ) {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        textView01.setText ("xxx");
                        Toast.makeText ( getApplication (),"网络错误，无法获取当前余额",Toast.LENGTH_LONG).show ();

                    }
                }
        );

    }

}
