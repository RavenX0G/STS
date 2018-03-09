package com.mogul.xxm.sts.BmobBean;

import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * Created by Raven on 2018/1/26.
 */

public class SiteBean extends BmobObject{

    private String siteName;
    private String siteAddress;
    private List<BusBean> busList;

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSiteAddress() {
        return siteAddress;
    }

    public void setSiteAddress(String siteAddress) {
        this.siteAddress = siteAddress;
    }

    public List<BusBean> getBusList() {
        return busList;
    }

    public void setBusList(List<BusBean> busList) {
        this.busList = busList;
    }
}
