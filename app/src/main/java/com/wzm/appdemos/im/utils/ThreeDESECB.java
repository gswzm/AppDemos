package com.wzm.appdemos.im.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

/**
 * Created by Administrator on 2017/5/8 0008.
 */
public class ThreeDESECB {
    /**
     * 3des加密
     * @param src
     * @param key
     * @return
     */
    public static byte[] encryptThreeDESECB(byte[] src, String key) throws Exception {
            DESedeKeySpec dks = new DESedeKeySpec(key.getBytes("UTF-8"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
            SecretKey securekey = keyFactory.generateSecret(dks);

            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, securekey);
            byte[] b = cipher.doFinal(src);
            return b;
    }

    /**
     * 3DES解密
     * @param src
     * @param key
     * @return
     */
    public static byte[] decryptThreeDESECB(byte[] src, String key) throws Exception {

            //--解密的key
            DESedeKeySpec dks = new DESedeKeySpec(key.getBytes("UTF-8"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
            SecretKey securekey = keyFactory.generateSecret(dks);

            //--Chipher对象解密
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, securekey);
            byte[] b = cipher.doFinal(src);

            return b;
    }
}
