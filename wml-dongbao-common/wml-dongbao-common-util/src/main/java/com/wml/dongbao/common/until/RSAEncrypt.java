package com.wml.dongbao.common.until;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class RSAEncrypt {


    public static void main(String[] args) throws Exception {

        testAll();

    }

    public static void test() throws Exception {
        //生成公钥和私钥
        Map<String, String> keyMap = new HashMap<>();
        keyMap.put("publicKey", "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCfVDQ9nEtKulQrAQWAa3ZgemjhdGFnhI8Id6YYswfOnsOJb4Ho/9D3fKtELGVMjJqU44A42Y0s6sVl1YCRqPzYrQfpTa9SdjKN4c6wwphbK1+U1BvT0cvCIdJHguMkleTgOnfjnfyf5OERR0YwuP0/8K1BMoyczbr+ysEFKyE7qwIDAQAB");
        keyMap.put("privateKey", "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJ9UND2cS0q6VCsBBYBrdmB6aOF0YWeEjwh3phizB86ew4lvgej/0Pd8q0QsZUyMmpTjgDjZjSzqxWXVgJGo/NitB+lNr1J2Mo3hzrDCmFsrX5TUG9PRy8Ih0keC4ySV5OA6d+Od/J/k4RFHRjC4/T/wrUEyjJzNuv7KwQUrITurAgMBAAECgYAUn46fhvKL+rBYXKp8o+xPsqiOvkS5ZIQrIWmjetEA4rYGvSNAqJRA5Kc6jNCT5wM/UG9RbH+El+LzOn6I3CnWIXhT2Ihzm4GM0tLr1xHeTyk70xhO9xnaEaBJt2zcOkGxOA7icqI05pv1aAB4EDtXVizSf2aA0kRNHpIK3yRoUQJBAOJGbnl6Vn+mPiaUK1DzFItr7vBNTU0E+u3S8qnVBaJkdusb0d+ThLStwpAi8pMoukhyTNDO/sqDPzPkkY9TI7kCQQC0QmQEwSBrXMVqDraDfVbE50aEeG0BgfeYHgMatICxt0pyB/KszAszElKMlyeNsM3q+q9y0khQYRSigUU/55SDAkEA2AqhPjEVV2l1m8YKmY6NhhS2nQ9hh/ECWIH5mTe/2m4LsKzuNIaXxR7GTnJfoyvWuIcRWqjYUqj/dl98rxiVWQJALY8qIFl/jCMici/NyWYAbDvYAts9Ech7f6L2PeWpMUGWO0Pu/eWEXyAeZRI/GPy6zs0VUvv68vXWdUwe6IvraQJAKP7Vkx6uR/dIqOLv21KPLTeEqkSQ9SOkghwwFVgdNjhWaUZejxGG0PnqhItYhdec2znqIvL6Kj/Uu8/6Xzqb0Q==");
        String publicKey = keyMap.get("publicKey");
        String privateKey = keyMap.get("privateKey");
        System.out.println("公钥："+publicKey);
        System.out.println("私钥："+privateKey);
        // 原始字符串
        String message = "我是测试 原始内容";
        System.out.println("原始数据："+message);
        long startTime = System.currentTimeMillis();
        for (int i=0;i<10;i++){
            String messageEn = publicKeyEncrypt(message, publicKey);
            System.out.println("公钥加密后内容:" + messageEn);
            String messageDe = privateKeyDecrypt(messageEn, privateKey);
            System.out.println("私钥解密后内容:" + messageDe);
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);

    }

    /**
     * 公加私解
     * 私加公解
     * @throws Exception
     */
    public static void testAll() throws Exception {
        //生成公钥和私钥
        Map<String, String> keyMap = new HashMap<>();
        keyMap.put("publicKey", "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDEXr8P0dlvpZRhcKSkKgfzSRA3TpH8RfZc7b529PeiaRQGm7cXlath5w0Nj1gs6jZWSzltcf7SIdMEkpxTX4/xbXt8v/87L7DdicGOt4+VVh6NOrqB9HhNqeEtGRMv+DAHg6zij3uA+YCNA40Oretojjf4v51QSsvfQv6W4DWhTQIDAQAB");
        keyMap.put("privateKey", "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMRevw/R2W+llGFwpKQqB/NJEDdOkfxF9lztvnb096JpFAabtxeVq2HnDQ2PWCzqNlZLOW1x/tIh0wSSnFNfj/Fte3y//zsvsN2JwY63j5VWHo06uoH0eE2p4S0ZEy/4MAeDrOKPe4D5gI0DjQ6t62iON/i/nVBKy99C/pbgNaFNAgMBAAECgYBRAjUXtZ5ZrJkVyX5iKuS0vINwDX2z8Li9hWZ5dH1kBq04PKy/kgLtlH+SBHx/qu9XkhjSyaAx17pRvJm420dpvJy7T9GHlQ9bOVxaKNxsodxokERu69+YqHmfFrRG7UjfVup4gqU1SRdBaRWmMhY7QwTCQgV5IOJG/gDICa6LkQJBAOJoCZGz3d+oKsv1GRpmtUMrACyuK20jAC0G8wkNyn80UF8eyX9C2axSFX55Ha94YSkLGC5hzxlkHvT1COaRmVsCQQDeCaQSoypYfYDaSPXi8IpXHgY2QzB2ABP0SjqDlcMV+pK/G4e5i8ptmQZOlYUgkjAGEeXX8sq5iQHJ7hNZXYh3AkEArEsB5Thcw0RFdTrK5LV+gWPq2RWeBIqbKqjcMGqnTBAyjYBvVII6BhHdO4bN2WehgMtplnpmUOtJR55lLJlmewJAEcDDlZnmMN0YCFv9DQAej4ifBoeowEaRUd79frfiuUcnpJAW8gbzUIADuRTLaCdIH7QepH2NJ/iEZBjdAzAvUQJAW0jhWNcfM+2eMuUNvjRCLlKnoJN8yhY0Sl+oPP1ImEbFoWSh/kIa4dZep8FDSrChq8FTPaK0DgUWno893QMszA==");
        String publicKey = keyMap.get("publicKey");
        String privateKey = keyMap.get("privateKey");
        System.out.println("公钥："+publicKey);
        System.out.println("私钥："+privateKey);
        // 原始字符串
        String message = "我是测试原始内容";
        System.out.println("原始数据："+message);
        String messageEn = publicKeyEncrypt(message, publicKey);
        System.out.println("公钥加密后内容:" + messageEn);
        String messageDe = privateKeyDecrypt(messageEn, privateKey);
        System.out.println("私钥解密后内容:" + messageDe);

        System.out.println("=====================");

        //私钥加密，公钥解密
        String s = privateKeyEncrypt(message, privateKey);
        System.out.println("私钥加密后内容："+s);
        String s1 = publicKeyDecrypt(s, publicKey);
        System.out.println("公钥解密后内容："+s1);
    }

    /**
     * 随机生成密钥对
     *
     * @throws NoSuchAlgorithmException
     */
    public static Map<String, String> genKeyPair() throws NoSuchAlgorithmException {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(1024, new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();   // 得到私钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  // 得到公钥
        String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
        // 得到私钥字符串
        String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));
        // 将公钥和私钥保存到Map
        Map<String, String> map = new HashMap<>();
        map.put("publicKey", publicKeyString);
        map.put("privateKey", privateKeyString);
        return map;
    }

    /**
     * RSA公钥加密
     *
     * @param str       加密字符串
     * @param publicKey 公钥
     * @return 密文
     * @throws Exception 加密过程中的异常信息
     */
    public static String publicKeyEncrypt(String str, String publicKey) throws Exception {
        //base64编码的公钥
        byte[] decoded = Base64.decodeBase64(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").
                generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        String outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8")));
        return outStr;
    }

    /**
     * RSA私钥解密
     *
     * @param str        加密字符串
     * @param privateKey 私钥
     * @return 铭文
     * @throws Exception 解密过程中的异常信息
     */
    public static String privateKeyDecrypt(String str, String privateKey) throws Exception {
        //64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
        //base64编码的私钥
        byte[] decoded = Base64.decodeBase64(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA")
                .generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        String outStr = new String(cipher.doFinal(inputByte));
        return outStr;
    }


    /**
     * RSA私钥加密
     *
     * @param str
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static String privateKeyEncrypt(String str, String privateKey) throws Exception {
        //base64编码的公钥
        byte[] decoded = Base64.decodeBase64(privateKey);
        PrivateKey priKey = KeyFactory.getInstance("RSA").
                generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, priKey);
        String outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes()));
        return outStr;
    }

    /**
     * RSA公钥解密
     *
     * @param str
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static String publicKeyDecrypt(String str, String publicKey) throws Exception {
        //64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
        //base64编码的私钥
        byte[] decoded = Base64.decodeBase64(publicKey);
        PublicKey pubKey =  KeyFactory.getInstance("RSA")
                .generatePublic(new X509EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, pubKey);
        String outStr = new String(cipher.doFinal(inputByte));
        return outStr;
    }

}
