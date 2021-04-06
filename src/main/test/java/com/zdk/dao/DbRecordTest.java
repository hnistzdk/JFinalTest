package com.zdk.dao;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.zdk.pojo.Teacher;
import com.zdk.pojo.User;
import com.zdk.utils.DpAndArpHelp;
import org.junit.Test;

import java.util.List;

/**
 * @author zdk
 * @date 2021/4/3 19:19
 */
public class DbRecordTest {

    @Test
    public void ModelTest(){
        //配置中的不生效  在要使用的方法里要调用以下方法才生效  原因未知
        DpAndArpHelp.help();


        //以下测试成功
//        User user=User.dao.findByIdLoadColumns(1, "username,password");
//        System.out.println(user.getStr("username"));
//        System.out.println(user.getStr("password"));
        //=====================================

        //直接输出user会展示为全部为null  实际时查出来了  需要用getStr方法获取属性才能不显示为null
//        List<User> userList = User.dao.find("select * from user");
//        for (User user : userList) {
//            System.out.println(user.getStr("username"));
//===========================================
//            }
        //================================
        //测试成功
//        boolean result = User.dao.deleteById(5);
//        System.out.println(result);
        //========================

        List<User> userList=User.dao.find("select * from user where id>=3");
        for (User user : userList) {
            System.out.println(user.getStr("username"));
        }
        //==================
        Teacher teacher= new Teacher().dao().findByIdLoadColumns(1,"name");
        System.out.println(teacher.getStr("name"));
    }

    @Test
    public void recordTest(){
        DpAndArpHelp.help();
        //success
        List<Object[]> list = Db.query("select * from user");
        for (Object[] user : list) {
            for(Object attribute: user){
                System.out.println(attribute);
            }
        }

        //success
//        boolean save = Db.save("user", new Record().set("id", 5).set("username", "吴凯").set("password", "000000"));
//        System.out.println("save："+save);
    }

    @Test
    public void paginateTest(){
        DpAndArpHelp.help();
        Page<User> paginate = User.dao.paginate(1, 2, "select *", "from user where id>=?", 1);
        List<User> userList = paginate.getList();
        for (User user : userList) {
            System.out.println(user.getStr("username"));
        }
    }
}
