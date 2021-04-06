package com.zdk.pojo;

import com.jfinal.plugin.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

/**
 * @author zdk
 * @date 2021/3/28 15:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("hello")
public class User extends Model<User> {
    private int id;
    private String username;
    private String password;
    public static final User dao=new User().dao();
}
