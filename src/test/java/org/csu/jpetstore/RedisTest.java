package org.csu.jpetstore;

import org.csu.jpetstore.common.security.TokenModel;
import org.csu.jpetstore.uitls.RedisKeyUtil;
import org.csu.jpetstore.uitls.RedisService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author: sun
 * @date: 2019/6/13
 */
public class RedisTest extends BaseTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource
    private ValueOperations<String, Object> valueOperations;

    @Autowired
    private HashOperations<String, String, Object> hashOperations;

    @Autowired
    private ListOperations<String, Object> listOperations;

    @Autowired
    private SetOperations<String, Object> setOperations;

    @Autowired
    private ZSetOperations<String, Object> zSetOperations;

    @Resource
    private RedisService redisService;

    @Test
    public void testObj() throws Exception {
        UserVo userVo = new UserVo();
        userVo.setAddress("上海");
        userVo.setName("测试dfas");
        userVo.setAge(123);
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        redisService.expireKey("name", 20, TimeUnit.SECONDS);
        String key = RedisKeyUtil.getKey(UserVo.Table, "name", userVo.getName());
        UserVo vo = (UserVo) operations.get(key);
        System.out.println(vo);
    }

    @Test
    public void testValueOption() throws Exception {
        UserVo userVo = new UserVo();
        userVo.setAddress("上海");
        userVo.setName("jantent");
        userVo.setAge(23);
        valueOperations.set("test", userVo);

        System.out.println(valueOperations.get("test"));
    }

    @Test
    public void testSetOperation() throws Exception {
        UserVo userVo = new UserVo();
        userVo.setAddress("北京");
        userVo.setName("jantent");
        userVo.setAge(23);
        UserVo auserVo = new UserVo();
        auserVo.setAddress("n柜昂周");
        auserVo.setName("antent");
        auserVo.setAge(23);
        setOperations.add("user:test", userVo, auserVo);
        Set<Object> result = setOperations.members("user:test");
        System.out.println(result);
    }

    @Test
    public void HashOperations() throws Exception {
        UserVo userVo = new UserVo();
        userVo.setAddress("北京");
        userVo.setName("jantent");
        userVo.setAge(23);
        hashOperations.put("hash:user", userVo.hashCode() + "", userVo);
        System.out.println(hashOperations.get("hash:user", userVo.hashCode() + ""));
    }

    @Test
    public void ListOperations() throws Exception {
        UserVo userVo = new UserVo();
        userVo.setAddress("北京");
        userVo.setName("jantent");
        userVo.setAge(23);
//        listOperations.leftPush("list:user",userVo);
//        System.out.println(listOperations.leftPop("list:user"));
        // pop之后 值会消失
        System.out.println(listOperations.leftPop("list:user"));
    }

    @Test
    public void test() {
        String userId = "123";
        String token = UUID.randomUUID().toString().replace("-", "");
        TokenModel model = new TokenModel(userId, token);
        // 存储到redis并设置过期时间
        redisTemplate.boundValueOps("user-token:" + userId).set(model, 7200, TimeUnit.SECONDS);
        System.out.println(valueOperations.get("user-token:" + userId));
        redisTemplate.boundValueOps("user-token:" + userId).set(123, 7200, TimeUnit.SECONDS);
        System.out.println(valueOperations.get("user-token:" + userId));
    }
}
