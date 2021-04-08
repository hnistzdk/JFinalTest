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
        System.out.println("执行了"+invocation.getMethodName()+"方法");
        invocation.invoke();
        UserController target = invocation.getTarget();
        boolean checkLogin = target.checkLogin();
        if(checkLogin){
            controller.render("/view/loginedPage.html");
        }
        else {
            controller.renderError(403);
        }
        //invocation.invoke();
    }
}
