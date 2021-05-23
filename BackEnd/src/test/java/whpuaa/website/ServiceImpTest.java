package whpuaa.website;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import whpuaa.website.user.*;

import java.util.*;

@SpringBootTest
public class ServiceImpTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserService userService;
    @Test
    public void test1() throws UserNotExistException {
        List<Integer> select_id_from_user = jdbcTemplate.queryForList("select id from user", Integer.class);
        System.out.println(select_id_from_user);
    }
    @Test
    public void test2() throws UserNotExistException {
       //List<UserInfo> user =  userMapper.getUser(1);
        //ystem.out.println(user);
    }

    @Test
    public void test4() throws UserNotExistException {
        System.out.println("------");
        jdbcTemplate.execute("insert into user_detail values (3,'le','123',1)");
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from user_detail");

        for (Map<String, Object> map : maps) {
            Set<String> strings = map.keySet();
            for (String string : strings) {
                System.out.println(string);
                System.out.println(map.get(string));
            }
        }

    }

    @Test void test5() throws UserNotExistException {
        List<Map<String, String>>details = userMapper.getDetails(1);
        for (Map<String, String> detail : details) {
            for (Map.Entry<String, String> stringStringEntry : detail.entrySet()) {
                System.out.println(stringStringEntry.getKey());
                System.out.println(stringStringEntry.getValue());
            }
        }

    }
    @Test void test6() throws UserNotExistException {
        List<String> permissons = userMapper.getPermissions(1);
        System.out.println(permissons);

    }
    @Test void test7() throws UserNotExistException {
        long id=1;
        User user = userMapper.getUserById(id);
        List<Map<String, String>> map = userMapper.getDetails(id);
        Map<String,String> details=new HashMap<String,String>();
        for (Map<String, String> detail : map) {
            for (Map.Entry<String, String> entry : detail.entrySet()) {
                details.put(entry.getKey(),entry.getValue());
            }
        }
        List<String> permissions = userMapper.getPermissions(id);
        UserInfo userInfo = new UserInfo(user.getId(), user.getUsername(), user.getName(), user.getDescription(), permissions, details);
        System.out.println(userInfo);

    }
    @Test void test8() throws UserNotExistException {
        //getUserByUsername
        long root = userMapper.getIdByUsername("root");
        UserInfo user = userService.getUser(root);
        System.out.println(user);


    }
    @Test void test9() throws UserNotExistException {
        //findAll
        for(int i=5;i<20;i++){
           int c=(int)(Math.random()*100);
           String sql="insert into user values("+i+",'"+(char)c+"','dd','ld1','study')";
            jdbcTemplate.execute(sql);
        }
    }
    @Test void test10() throws UserNotExistException {
        List<Integer> list = userMapper.getUsersId(2, 5);
        System.out.println(list);

    }
    @Test void test11() throws InvalidOperationOnRootUserException {
        //delete
        System.out.println(userMapper.removeUser(19));
    }


}
