package demo;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.core.NotAction;
import com.jfinal.core.Path;

/**
 * @author zdk
 * @date 2021/4/4 20:14
 */
@Path("/user")
public class UserController extends Controller {
    @Before(LoginInterceptor.class)
    public void login(){
        String username=get("username");
        String password=get("password");
        setSessionAttr("logined", false);
        if(username.equals("zdk")&&password.equals("123")){
            setSessionAttr("username",username);
            setSessionAttr("logined", true);
            System.out.println("set后username的值"+getSessionAttr("username"));
        }else {
            renderText("用户名或密码错误");
        }
    }
    public void logout(){
        removeSessionAttr("username");
        System.out.println("remove后username的值："+getSessionAttr("username"));
        render("/view/login.html");
    }
    @NotAction
    public boolean checkLogin(){
        return getSessionAttr("logined");
    }
}
