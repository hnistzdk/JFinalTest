package demo;

import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import com.jfinal.core.Controller;
import com.jfinal.core.Path;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zdk
 * @date 2021/4/4 20:14
 */
@Path("/myAjax")
public class UserController extends Controller {
    public void index(){
        String name=get("name");
        System.out.println("name:"+name);
        if("zdk".equals(name)){
            renderText("ok");
        }else {
            renderText("error");
        }
    }
}
