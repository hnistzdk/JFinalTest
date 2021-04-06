import com.jfinal.server.undertow.UndertowServer;
import demo.DemoConfig;

/**
 * @author zdk
 * @date 2021/3/25 18:30
 */
public class Start{
    public static void main(String[] args) {
        UndertowServer.create(DemoConfig.class).configWeb(builder->{
            builder.addFilter("MyFilter", "com.zdk.filter.MyFilter");
            builder.addFilterUrlMapping("MyFilter", "/view/Test/loginedPage.html");
        }).start();
        //UndertowServer.start(DemoConfig.class,80, true);
    }
}
