package whpuaa.website.user;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UserMapper {
    Integer getUserIdByUsername(String username);

    /*
     *将UserInfo拆成User,再通过User来找其他的属性，原因：jdbc没有返回Map<k,v>集合的方法，只能返回容量为1的
     */
    List<String> getPermissions(long id);

    List<Map<String, String>> getDetails(long id);

    User getUserById(long id);

    List<Integer> getUsersId(long page, long numberPerPage);

    Integer removeUser(long id);


}
