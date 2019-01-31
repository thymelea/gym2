package com.example.gym2.nettyDemo.server;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * @Author: zhangbo
 * @Date: 2019/1/30/030 17:49
 * @Version 1.0
 */
public class FromStringHex  {
    public static String fromHexString(String hexSStrign) throws UnsupportedEncodingException {
        String result="";
        hexSStrign = hexSStrign.toUpperCase();
        String hexDigital = "0123456789ABCDEF";
        char [] hexs = hexSStrign.toCharArray();
        byte[] bytes = new byte[hexSStrign.length()/2];
        int n;
        for(int i = 0;i < bytes.length; i++){
            n = hexDigital.indexOf(hexs[2 * i])*16 + hexDigital.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte)(n & 0xff);
        }
        result = new String(bytes, StandardCharsets.UTF_8);
        return  result;
    }
}
