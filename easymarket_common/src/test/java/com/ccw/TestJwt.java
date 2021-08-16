package com.ccw;

import org.bouncycastle.asn1.pkcs.RSAPrivateKey;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;

public class TestJwt {

    //私钥对Jwt进行签名
    public void createJwt(){
       //定义秘钥的路径
        String key_path="dongyimai.jks";
        //秘钥库的密码
        String keyStore_password="dongyimai";
        //秘钥的密码
        String key_password="dongyimai";
        //秘钥的别名
        String alias="dongyimai";

        //加载秘钥
        ClassPathResource classPathResource = new ClassPathResource(key_path);
        //创建秘钥工厂
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(classPathResource, keyStore_password.toCharArray());
        //读取密钥对
        KeyPair keyPair = keyStoreKeyFactory.getKeyPair(alias, key_password.toCharArray());
        //获取私钥
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();

        //生成令牌的载荷
        Map map = new HashMap();
        map.put("id","1111");
        map.put("name","small");
        map.put("address","无锡");
        map.put("role","admin,user");

        //生成令牌
    }

    //使用公钥解析JWT
}
