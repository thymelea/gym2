package com.example.gym2.nettyDemo;

import java.io.Serializable;

/**
 * @Author: zhangbo
 * @Date: 2019/1/29/029 16:10
 * @Version 1.0
 */
public class SubscribeResp implements Serializable {
    private static final long nSerialVerUID = 2L;

    private int nSubReqID;

    private int nRespCode;

    private String strDesc;

    public final int getnSubReqID() {
        return nSubReqID;
    }

    public final void setnSubReqID(int nSubReqID) {
        this.nSubReqID = nSubReqID;
    }

    public final int getnRespCode() {
        return nRespCode;
    }

    public final void setRespCode(int nRespCode) {
        this.nRespCode = nRespCode;
    }

    public final String getstrDesc() {
        return strDesc;
    }

    public final void setDesc(String strDesc) {
        this.strDesc = strDesc;
    }

    @Override
    public String toString() {
        return "SubscribeResp [nSubReqID=" + nSubReqID + ", nRespCode=" + nRespCode
                + ", strDesc=" + strDesc + "]";
    }

}