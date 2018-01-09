package com.mogul.xxm.sts.ui;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mogul.xxm.sts.R;
import com.mogul.xxm.sts.ui.fragment.HomeFragment;

/**
 * Time:1/9/18 21:34
 * Created by Curtain.
 */

public class MainGenerator {

    public static final int [] mTabRes = new int[]{R.mipmap.ic_home_nor,R.mipmap.ic_record_nor,R.mipmap.ic_my_nor};
    public static final int [] mTabResPressed = new int[]{R.mipmap.ic_home_sel,R.mipmap.ic_record_sel,R.mipmap.ic_my_sel};
    public static final String []mTabTitle = new String[]{"首页","记录","我的"};

    public static Fragment[] getFragments(){
        Fragment fragments[] = new Fragment[3];
        fragments[0] = new HomeFragment();
        fragments[1] = new HomeFragment();
        fragments[2] = new HomeFragment();
        return fragments;
    }

    public static View getTabView(Context context, int position){
        View view = LayoutInflater.from(context).inflate(R.layout.item_main_tablayout,null);
        ImageView tabIcon = (ImageView) view.findViewById(R.id.tab_image);
        tabIcon.setImageResource(MainGenerator.mTabRes[position]);
        TextView tabText = (TextView) view.findViewById(R.id.tab_title);
        tabText.setText(mTabTitle[position]);
        return view;
    }
}
