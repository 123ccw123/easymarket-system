package ccw;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    @Test
    public void testCreateJwt(){
        JwtBuilder builder = Jwts.builder()
                .setId("888") //JWT的唯一标识
                .setSubject("easymarket商城") //JWT的主题信息，内容是JSON格式
                .setIssuedAt(new Date()) //签发日期
                //.setExpiration(new Date(20000))  //签证超时时间
                .signWith(SignatureAlgorithm.HS256,"ujiuye"); //自定义加密形式进行加密

        Map map = new HashMap<>();
        map.put("name","张三");
        map.put("address","西安");
        builder.addClaims(map);
        System.out.println(builder.compact());
    }

    @Test
    public void testParseJwt(){
        String claimsJwt="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODgiLCJzdWIiOiJlYXN5bWFya2V05ZWG5Z-OIiwiaWF0IjoxNjI3ODEwODk2LCJhZGRyZXNzIjoi6KW_5a6JIiwibmFtZSI6IuW8oOS4iSJ9.3RaNGxmWhsR477GEjXqybuyomEML43eZFVklNziWkDs";
        Claims body = Jwts.parser().setSigningKey("ujiuye").parseClaimsJws(claimsJwt).getBody();
        System.out.println(body);
    }
}
