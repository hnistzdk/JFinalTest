package demo;

import com.jfinal.core.Controller;
import com.jfinal.core.Path;

/**
 * @author zdk
 * @date 2021/3/25 18:34
 */
@Path("/hello")
public class HelloController extends Controller {
    public void index(){
        renderText("Hello JFinal World");
    }
    public void test(){
        render("/view/enjoyTest.html");
    }
}
