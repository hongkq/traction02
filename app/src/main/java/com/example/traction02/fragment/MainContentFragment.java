package com.example.traction02.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;


import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.traction02.R;
import com.example.traction02.activity.Activity1_1;
import com.example.traction02.activity.ETCzhangdang;
import com.example.traction02.util.HttpRequest;
import com.example.traction02.util.Shared;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 主页内容展示窗口
 */
public class MainContentFragment extends Fragment {

    private View view;
    private GridView gridView;
    private String role;
    private String[] name = {"image","title"};
    private int[] content = {R.id.image_page,R.id.title_page};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_layout, container, false);
        role = Shared.get(getActivity()).getString("role", "R02");
//        role = Shared.getString(appContext,"role", "R02");
        initView();
        initEvent();
        return view;
    }

    private void initView() {
        gridView = view.findViewById(R.id.main_layout);
        SimpleAdapter simpleAdapter =new SimpleAdapter (getActivity(),getList(),R.layout.item_page,name,content);
        gridView.setAdapter(simpleAdapter);
    }
    public List<Map<String, Object>> getList(){
        List<Map<String, Object>> list = new ArrayList<> ();
        Map<String, Object> map;
        if (role.equals("R01")){//判断用户权限
            String[] titles = new String[]{
                    "第1题：实现个人车辆ETC账户管理功能",
                    "红路灯管理",
                    "充值历史记录",
                    "车辆违章浏览功能",
                    "环境指标",
                    "传感器实时数据",
                    "阈值设置功能",
                    "实现公司交通单双号管制功能",
                    "车管局车辆账户管理",
                    "公交查询",
                    "第11题：编码实现红绿灯管理模块",
                    "第12题：编码实现车辆违章查看功能",
                    "第13题：编码实现路况查询模块",
                    "第14题：编码实现生活助手功能",
                    "第15题：编码数据分析功能",
                    "第16题：编码个人中心功能1",
                    "第17题：编码实现生活指数功能",
                    "第18题：编码实现我的消息功能",
                    "第19题：编码数据分析功能",
                    "第20题：编码个人中心功能2",
                    "第21题：编码实现红绿灯管理模块",
                    "第22题：编码实现车辆ETC账户管理功能1",
                    "第23题：编码实现车辆ETC账户告警功能2",
                    "第24题：编码实现生活助手功能",
                    "编码实现路况查询模块",
                    "第26题：编码数据分析功能",
                    "第27题：编码实现生活助手功能",
                    "第28题：编码实现公交查询模块功能2",
                    "第29题：编码实现实时环境指标显示模块",
                    "编码实现车辆违章视频浏览播放功能",
                    "第31题：编码实现意见反馈功能",
                    "第32题：编码实现城市地铁查看功能",
                    "第33题：编码实现高速路况查询功能",
                    "第34题：编码实现高速ETC功能",
                    "第35题：编码实现旅行助手功能",
                    "第36题：编码实现天气信息功能",
                    "第37题：编码实现二维码支付功能",
                    "第38题：编码定制班车功能",
                    "新闻客户端",
                    "智能停车场模块中的IC卡充值功能",
                    "第41题：编码实现智能停车场模块的车辆收费查询功能",
                    "第42题：编码停车场信息管理功能",
                    "第43题：实现小车充值功能",
                    "第44题：编码实现用户登录注册功能",
                    "第45题：编码实现我的座驾功能",
                    "第46题：编码实现我的交通功能",
                    "定制班车",
                    "天气信息",
                    "传感器实时数据",
                    "高速ETC",
                    "地铁查询",
                    "停车场"
            };
            int[] icons = {
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
            };
            for (int i = 0; i< icons.length;i++){
                map = new HashMap<> ();
                map.put("image",icons);
                map.put("title",titles);
                list.add(map);
            }
        }else{  //表示R02
            String[] titles = new String[]{
                    "第1题：实现个人车辆ETC账户管理功能",
                    "红路灯管理",
                    "充值历史记录",
                    "车辆违章浏览功能",
                    "环境指标",
                    "传感器实时数据",
                    "阈值设置功能",
                    "实现公司交通单双号管制功能",
                    "车管局车辆账户管理",
                    "公交查询",
                    "第11题：编码实现红绿灯管理模块",
                    "第12题：编码实现车辆违章查看功能",
                    "第13题：编码实现路况查询模块",
                    "第14题：编码实现生活助手功能",
                    "第15题：编码数据分析功能",
                    "第16题：编码个人中心功能1",
                    "第17题：编码实现生活指数功能",
                    "第18题：编码实现我的消息功能",
                    "第19题：编码数据分析功能",
                    "第20题：编码个人中心功能2",
                    "第21题：编码实现红绿灯管理模块",
                    "第22题：编码实现车辆ETC账户管理功能1",
                    "第23题：编码实现车辆ETC账户告警功能2",
                    "第24题：编码实现生活助手功能",
                    "编码实现路况查询模块",
                    "第26题：编码数据分析功能",
                    "第27题：编码实现生活助手功能",
                    "第28题：编码实现公交查询模块功能2",
                    "第29题：编码实现实时环境指标显示模块",
                    "编码实现车辆违章视频浏览播放功能",
                    "第31题：编码实现意见反馈功能",
                    "第32题：编码实现城市地铁查看功能",
                    "第33题：编码实现高速路况查询功能",
                    "第34题：编码实现高速ETC功能",
                    "第35题：编码实现旅行助手功能",
                    "第36题：编码实现天气信息功能",
                    "第37题：编码实现二维码支付功能",
                    "第38题：编码定制班车功能",
                    "新闻客户端",
                    "智能停车场模块中的IC卡充值功能",
                    "第41题：编码实现智能停车场模块的车辆收费查询功能",
                    "第42题：编码停车场信息管理功能",
                    "第43题：实现小车充值功能",
                    "第44题：编码实现用户登录注册功能",
                    "第45题：编码实现我的座驾功能",
                    "第46题：编码实现我的交通功能",
                    "定制班车",
                    "天气信息",
                    "传感器实时数据",
                    "高速ETC",
                    "地铁查询",
                    "停车场"
            };
            int[] icons = {
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,
                    R.drawable.icon_1,


            };
            for (int i = 0; i < icons.length; i++) {
                map = new HashMap<> ();
                map.put("image", icons[i]);
                map.put("title", titles[i]);
                list.add(map);
            }
        }
        return list;
    }
    private void initEvent() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (role.equals("R01")){    //普通用户
                    switch (position){
                        case 0:
                          Intent intent=new Intent (getActivity (), Activity1_1.class);
                          startActivity (intent);

                            break;
                        case 1:
                            break;
                        case 2:
                            Intent intent1=new Intent (getActivity (), ETCzhangdang.class);
                            startActivity (intent1);
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                        case 5:

                            break;
                        case 6:
                            break;
                        case 7:
                            break;
                        case 8:
                            break;
                        case 9:
                            break;
                        case 10:
                            break;
                        case 11:
                            break;
                        case 12:
                            break;
                        case 13:
                            break;
                        case 14:
                            break;
                        case 15:
                            break;
                        case 16:
                            break;
                        case 17:
                            break;
                        case 18:
                            break;
                        case 19:
                            break;
                        case 20:
                            break;
                        case 21:
                            break;
                        case 22:
                            break;
                        case 23:
                            break;
                        case 24:
                            break;
                        case 25:
                            break;
                        case 26:
                            break;
                        case 27:
                            break;
                        case 28:
                            break;
                        case 29:
                            break;
                        case 30:
                            break;
                        case 31:
                            break;
                        case 32:
                            break;
                        case 33:
                            break;
                        case 34:
                            break;
                        case 35:
                            break;
                        case 36:
                            break;
                        case 37:
                            break;
                        case 38:
                            break;
                        case 39:
                            break;
                        case 40:
                            break;
                        case 41:
                            break;
                        case 42:
                            break;
                        case 43:
                            break;
                        case 44:
                            break;
                        case 45:
                            break;



                    }
                }else {                    //一般管理员
                    switch (position){
                        case 0:
                            Intent intent=new Intent (getActivity (), Activity1_1.class);
                            startActivity (intent);
                            break;
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                        case 5:

                            break;
                        case 6:
                            break;
                        case 7:
                            break;
                        case 8:
                            break;
                        case 9:
                            break;
                        case 10:
                            break;
                        case 11:
                            break;
                        case 12:
                            break;
                        case 13:
                            break;
                        case 14:
                            break;
                        case 15:
                            break;
                        case 16:
                            break;
                        case 17:
                            break;
                        case 18:
                            break;
                        case 19:
                            break;
                        case 20:
                            break;
                        case 21:
                            break;
                        case 22:
                            break;
                        case 23:
                            break;
                        case 24:
                            break;
                        case 25:
                            break;
                        case 26:
                            break;
                        case 27:
                            break;
                        case 28:
                            break;
                        case 29:
                            break;
                        case 30:
                            break;
                        case 31:
                            break;
                        case 32:
                            break;
                        case 33:
                            break;
                        case 34:
                            break;
                        case 35:
                            break;
                        case 36:
                            break;
                        case 37:
                            break;
                        case 38:
                            break;
                        case 39:
                            break;
                        case 40:
                            break;
                        case 41:
                            break;
                        case 42:
                            break;
                        case 43:
                            break;
                        case 44:
                            break;
                        case 45:
                            break;

                    }
                }
            }
        });
    }

}
