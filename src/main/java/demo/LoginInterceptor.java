package demo;


import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

/**
 * @author zdk
 * @date 2021/4/2 19:14
 */
public class LoginInterceptor implements Interceptor {
    @Override
    public void intercept(Invocation invocation) {
        Controller controller = invocation.getController();
        invocation.invoke();
        boolean returnValue = invocation.getReturnValue();
//        String username = controller.get("username");
//        String password = controller.get("password");
//        if (username.equals("zdk")&&password.equals("123")) {
//            controller.render("loginedPage.html");
//        }
        if(returnValue){
            controller.render("/view/loginedPage.html");
        }
        else {
            controller.removeSessionAttr("username");
            controller.renderError(403);
        }
        //invocation.invoke();
    }
}
