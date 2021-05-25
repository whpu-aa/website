package whpuaa.website;

import javafx.beans.binding.ObjectExpression;
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
    UserService userService;

    @Test
    public void insertDetail() throws UserNotExistException {
        System.out.println("------");
        jdbcTemplate.execute("insert into user_detail values (5,'ll','1234',1)");
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from user_detail");
        for (Map<String, Object> map : maps) {
            Set<String> strings = map.keySet();
            for (String string : strings) {
                System.out.println(string);
                System.out.println(map.get(string));
            }
        }
    }

    @Test
    public void insertUsers() throws UserNotExistException, UsernameConflictException {


        for(int i=0;i<20;i++){
            Object[]objects={"l"+i,"l"+i};
            jdbcTemplate.update("insert into user(username,password) values(?,?)",objects);
        }

    }

    @Test
    public void queryUsers() throws UserNotExistException, UsernameConflictException {
        List<UserInfo> users = userService.getUsers(1, 50);
        for(UserInfo user:users)
            System.out.println(user);
    }

    @Test
    public void CheckRepository(){
        System.out.println(userService);
    }









}
