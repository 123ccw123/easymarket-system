package ccw;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.util.HashMap;
import java.util.Map;

public class TestJwt {

    //私钥对Jwt进行签名
    @Test
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
        Jwt jwt = JwtHelper.encode(JSON.toJSONString(map), new RsaSigner(rsaPrivateKey));

        String token = jwt.getEncoded();
        System.out.println(token);
    }

    //使用公钥解析JWT
    @Test
    public void parseJwt(){
        //定义令牌和公钥
        String token="eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhZGRyZXNzIjoi5peg6ZShIiwicm9sZSI6ImFkbWluLHVzZXIiLCJuYW1lIjoic21hbGwiLCJpZCI6IjExMTEifQ.MFJ2f42f6m5hmYUarQ1rxUgypH2K9Dn4GEsdHG9YLujujbAFJucpq9jMz6B0L2rOndp-peBTfjRLspUhi2tIrAjpap_KD0ZnWcD6ZiCu6BBVYTakmLuXmQ71DZyhJ--2s4qYGRzc1159K8NxbBQ5dZRW5qIerWwFS-R3f3rRF5xccBijOwgOs3foqZruAACNCunPaLIbq-ReZld7wel7gnA7RXKRo7ktTSFm4R6lMXifoH9LJqyE4hoLaxKJlqQ9nU3A0ZdZSbYxwVZCtI1IFYGdAfWe1f77hcydB612i6T6OuupUUD2xHep4OuQRZ5KgyT1XSKMi67ewEY_J7kESg";
        String publicKey="-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAg0iAIPRlFxll8jgdOH3IEgMRdVJpnaJ8PCcaFo26mfEUJ1DxyEhdQV4J6EERK0qIZ2Bw3kNvZRox62PogDg0tjXUb5LCGFDueZDwFOMm8kokfDwV7WFcaEFRWAdIhKdGcGs+sH77HYiRxy+69G/3N6DZhutqj1RzaldEhcd8YbeYu+UidAvBO+fgtAK3ZBleKLjSxA0VrmWy9s5j1iB3DkorgUqjajEzE8v1L1gwOQCqe7WzdCT+IHVUut7Hke8iAMoikIA2oOYJDm/qhSOD+L3x/D90N/mSiXbZqygfw9PDgMoO8cHdOSLbj5SlqPzgkFw081OvWg1Ngra3OulYwQIDAQAB-----END PUBLIC KEY-----";
        //校验jwt
        Jwt jwt = JwtHelper.decodeAndVerify(token, new RsaVerifier(publicKey));

        //读取原始内容
        String claims = jwt.getClaims();
        System.out.println(claims);
    }
}
