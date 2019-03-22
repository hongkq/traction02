package com.example.traction02.Login;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.traction02.MainActivity;

import com.example.traction02.R;

import com.example.traction02.util.HttpRequest;
import com.example.traction02.util.Shared;
import com.example.traction02.util.UrlBean;


import org.json.JSONException;
import org.json.JSONObject;



public class LoginActivity extends AppCompatActivity {
    private ImageView imageView01;
    private ImageView imageView02;
    private EditText editText01;
    private EditText editText02;
    private Button button01;
    private Button button02;
    private UrlBean urlBean;
    private String urlHttp;
    private String urlPort ;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_login);
       com.example.traction02.util.Util.Util.clear (LoginActivity.this);
        InterView();
        Onlick();
       context=LoginActivity.this;



    }

    private  void  InterView(){
    imageView01=(ImageView)findViewById (R.id.back);
    imageView02=(ImageView)findViewById (R.id.shezhi);
    editText01=(EditText)findViewById (R.id.zhanghao);
    editText02=(EditText)findViewById (R.id.pwdlogin);
    button01=(Button)findViewById (R.id.login);
    button02=(Button)findViewById (R.id.zhuce);

    }
    private  void  Onlick(){
     imageView01.setOnClickListener (new View.OnClickListener ( ) {
         @Override
         public void onClick(View v) {
             exitAppDialog();
         }
     });
     imageView02.setOnClickListener (new View.OnClickListener ( ) {
         @Override
         public void onClick(View v) {
             final Dialog urlSettingDialog = new Dialog(LoginActivity.this);
             urlSettingDialog.show();
             urlSettingDialog.setTitle("setting");
             urlSettingDialog.getWindow().setContentView(R.layout.login_setting);
             final EditText edit_urlHttp = (EditText) urlSettingDialog.getWindow().findViewById(R.id.edit_setting_url);
             final EditText edit_urlPort = (EditText) urlSettingDialog.getWindow().findViewById(R.id.edit_setting_port);

//             edit_urlHttp.setText( urlBean.getUrl() );
//             edit_urlPort.setText( urlBean.getPort());
             Button save = (Button) urlSettingDialog.getWindow().findViewById(R.id.save);
             Button cancel = (Button) urlSettingDialog.getWindow().findViewById(R.id.cancel);
             save.setOnClickListener (new View.OnClickListener ( ) {
                 @Override
                 public void onClick(View v) {
                      urlHttp=edit_urlHttp.getEditableText ().toString ().trim ();
                      urlPort=edit_urlPort.getEditableText ().toString ().trim ();
                     if ( urlHttp == null || urlHttp.equals("")   ) {
                         Toast.makeText(LoginActivity.this,R.string.error_ip, Toast.LENGTH_LONG).show();
                     } else {

//                         edit_urlHttp.setText( urlBean.getUrl() );
//                         edit_urlPort.setText( urlBean.getPort());
                        com.example.traction02.util.Util.Util.saveSetting(urlHttp,urlPort,LoginActivity.this);
                         urlBean =  com.example.traction02.util.Util.Util.loadSetting( LoginActivity.this );
                         Toast.makeText (context,"服务器地址设置成功，服务器地址:"+"http://"+urlHttp+":"+urlPort+"/",Toast.LENGTH_LONG).show ();
                        urlSettingDialog.dismiss();

                     }

                 }
             });
           cancel.setOnClickListener (new View.OnClickListener ( ) {
               @Override
               public void onClick(View v) {
                   urlSettingDialog.dismiss();
               }
           });
           urlSettingDialog.show ();
         }
     });
     button01.setOnClickListener (new View.OnClickListener ( ) {
         @Override
         public void onClick(View v) {
             login();
         }
     });
    }
    public void exitAppDialog() {
        new AlertDialog.Builder(this)
                // .setIcon(android.R.drawable.ic_menu_info_detailsp)
                .setTitle("提示").setMessage("你确定要退出吗").setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                finish();
            }
        }).show();

    }
    private void login(){
        if (urlHttp==null||urlPort==null){
            Toast.makeText (LoginActivity.this,"没有指定服务器地址，请先设置",Toast.LENGTH_LONG).show ();

        }else {

            final String username = editText01.getEditableText ( ).toString ( ).trim ( );
            final String userpwd = editText02.getEditableText ( ).toString ( ).trim ( );

            HttpRequest.post ("user_login", "{\"UserName\":\"" + username + "\",\"UserPwd\":\"" + userpwd + "\"}",
                    new Response.Listener <JSONObject> ( ) {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            String role = null;
                            try {
                                role = jsonObject.getString ("UserRole");
                            } catch (JSONException e) {
                                e.printStackTrace ( );
                            }
                            Shared.edit (LoginActivity.this).putString ("role", role).putString ("username", username).apply ( );
                            startActivity (new Intent (LoginActivity.this, MainActivity.class));
                            finish ( );
                        }
                    },
                    new Response.ErrorListener ( ) {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            Toast.makeText (getApplication ( ), "登录失败，用户名或密码或服务器设置错误", Toast.LENGTH_LONG).show ( );

                             // finish ( );
                        }
                    }


            );
        }
    }
}
