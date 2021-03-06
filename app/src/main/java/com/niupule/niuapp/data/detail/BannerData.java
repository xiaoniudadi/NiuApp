package com.niupule.niuapp.data.detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Coder: niupuyue (牛谱乐)
 * Date: 2018/9/11
 * Time: 21:36
 * Desc:
 * Version:
 */
public class BannerData {
    public List<BannerDetailData> getData() {
        return data;
    }

    public void setData(List<BannerDetailData> data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Expose
    @SerializedName("data")
    private List<BannerDetailData> data;

    @Expose
    @SerializedName("errorCode")
    private int errorCode;
    @Expose
    @SerializedName("errorMsg")
    private String errorMsg;
}
