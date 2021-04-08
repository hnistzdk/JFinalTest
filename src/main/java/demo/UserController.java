package demo;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.core.Path;

/**
 * @author zdk
 * @date 2021/4/4 20:14
 */
@Path("/user")
public class UserController extends Controller {
    @Before(LoginInterceptor.class)
    public boolean login(){
        String username=get("username");
        String password=get("password");
        if(username.equals("zdk")&&password.equals("123")){
            setSessionAttr("username","zdk");
            System.out.println("set后username的值"+getSessionAttr("username"));
            return true;
        }else {
            renderText("用户名或密码错误");
            return false;
        }
    }

    public void logout(){
        removeSessionAttr("username");
        System.out.println("remove后username的值："+getSessionAttr("username"));
        render("/view/login.html");
    }
}
