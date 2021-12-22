package com.gql.springcloud.utils;



import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;

@Slf4j
public class MD5 {

    private static final String[] hexDigits = { "0", "1", "2", "3", "4", "5","6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    public static String encode(String rawPass, String salt){
        String result = new String();
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            result = byteArrayToHexString(md.digest(mergeSalt(rawPass, salt).getBytes("utf-8")));
        } catch (Exception e){
            log.info(e.getMessage());
        }
        return result;
    }

    private static String byteArrayToHexString(byte[] b){
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < b.length; i++){
            sb.append(byteToHexString(b[i]));
        }
        return sb.toString();
    }

    private static String byteToHexString(byte b){
        int n = b;
        if(n < 0){
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    private static String mergeSalt(String rawPass, String salt){
        if(rawPass.isEmpty()){
            rawPass = "";
        }
        if(salt == null || "".equals(salt)){
            return rawPass;
        } else {
            return rawPass + salt.toString();
        }
    }
}
