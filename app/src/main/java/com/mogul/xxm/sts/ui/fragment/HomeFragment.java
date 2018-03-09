package com.mogul.xxm.sts.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mogul.xxm.sts.BmobBean.BusBean;
import com.mogul.xxm.sts.BmobBean.SiteBean;
import com.mogul.xxm.sts.R;
import com.mogul.xxm.sts.base.BaseFragment;
import com.mogul.xxm.sts.widget.MToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Time:1/9/18 21:34
 * Created by Curtain.
 */

public class HomeFragment extends BaseFragment {

    @BindView(R.id.add_data)
    Button addData;
    @BindView(R.id.get_data)
    Button getData;
    Unbinder unbinder;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void updateViews() {

    }

    @OnClick({R.id.add_data,R.id.get_data})
    protected void OnViewClicked(View view){
        switch (view.getId()){
            case R.id.add_data:
                addBmobData();
                break;
            case R.id.get_data:
                getBmobData();
                break;
        }
    }

    private void addBmobData(){

        SiteBean siteBean = new SiteBean();
        siteBean.setSiteName("大钟楼");
        siteBean.setSiteAddress("芜湖路与徽州大道交口");
        List<BusBean> busList = new ArrayList<>();
        busList.add(new BusBean("busbean","133",1));
        busList.add(new BusBean("busbean","146",1));
        siteBean.setBusList(busList);
        siteBean.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if(e==null){
                    MToast.makeTextShort(mContext,"添加数据成功，返回objectId为："+objectId).show();
                }else{
                    MToast.makeTextShort(mContext,"创建数据失败：" + e.getMessage()).show();
                }
            }
        });
    }

    private void getBmobData(){

    }
}
