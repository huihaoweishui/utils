package com.xc.utils.algorithm;

import org.apache.commons.codec.digest.DigestUtils;
import sun.misc.BASE64Decoder;

import java.io.IOException;

/**
 * @auther 薛晨
 * @date 2019/8/14
 * @time 14:47
 * @description
 */
public class MyAlgorithmUtil {

    /**
     * MD5方法
     *
     * @param text 明文
     * @param key 密钥
     * @return 密文
     * @throws Exception
     */
    public static String md5(String text, String key) throws Exception {
        //加密后的字符串
        String encodeStr= DigestUtils.md5Hex(text + key);
        return encodeStr;
    }

    /**
     * MD5验证方法
     *
     * @param text 明文
     * @param key 密钥
     * @param md5 密文
     * @return true/false
     * @throws Exception
     */
    public static boolean verify(String text, String key, String md5) throws Exception {
        //根据传入的密钥进行验证
        String md5Text = md5(text, key);
        if(md5Text.equalsIgnoreCase(md5)) {
            return true;
        }
        return false;
    }

    /**
     *
     * @param base64EncodedPassword 64位编码的字符串
     * @param charset 编码类型 如"UTF-8"
     * @return
     * @throws IOException
     */
    public static String decodedBase64(String base64EncodedPassword ,String charset ) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        String decodedPassword = new String(decoder.decodeBuffer(base64EncodedPassword), charset);
        return  decodedPassword;
    }
}
