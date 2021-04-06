package demo;

import com.jfinal.config.*;
import com.jfinal.json.MixedJsonFactory;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.ViewType;
import com.jfinal.server.undertow.UndertowServer;
import com.jfinal.template.Engine;
import com.zdk.pojo._MappingKit;

/**
 * @author zdk
 * @date 2021/3/25 18:29
 */
public class DemoConfig extends JFinalConfig {
    static Prop p;

    static void loadConfig(){
        if(p==null){
            p=PropKit.useFirstFound("db.properties");
        }
    }
    @Override
    public void configConstant(Constants me) {
        // 配置开发模式，true 值为开发模式
        me.setDevMode(true);

        // 配置 aop 代理使用 cglib，否则将使用 jfinal 默认的动态编译代理方案
        //me.setToCglibProxyFactory();

        // 配置依赖注入
        me.setInjectDependency(true);

        // 配置依赖注入时，是否对被注入类的超类进行注入
        me.setInjectSuperClass(true);

        // 配置为 slf4j 日志系统，否则默认将使用 log4j
        // 还可以通过 me.setLogFactory(...) 配置为自行扩展的日志系统实现类
        //me.setToSlf4jLogFactory();

        // 设置 Json 转换工厂实现类，更多说明见第 12 章
        me.setJsonFactory(new MixedJsonFactory());

        // 配置视图类型，默认使用 jfinal enjoy 模板引擎
        me.setViewType(ViewType.JFINAL_TEMPLATE);

        // 配置基础下载路径，默认为 webapp 下的 download
        //me.setBaseDownloadPath();

        // 配置基础上传路径，默认为 webapp 下的 upload
        //me.setBaseUploadPath();

        // 配置 404、500 页面
//        me.setError404View("/common/404.html");
//        me.setError500View("/common/500.html");


        // 配置 encoding，默认为 UTF8
        me.setEncoding("UTF8");

        // 配置 json 转换 Date 类型时使用的 data parttern
        me.setJsonDatePattern("yyyy-MM-dd HH:mm");

        // 配置是否拒绝访问 JSP，是指直接访问 .jsp 文件，与 renderJsp(xxx.jsp) 无关
        me.setDenyAccessJsp(true);

        // 配置上传文件最大数据量，默认 10M
        me.setMaxPostSize(10 * 1024 * 1024);

        // 配置验证码缓存 cache，配置成集中共享缓存可以支持分布式与集群
        //me.setCaptchaCache();

        // 配置 urlPara 参数分隔字符，默认为 "-"
        me.setUrlParaSeparator("-");
    }

    @Override
    public void configRoute(Routes me) {
        // jfinal 4.9.03 版新增了路由扫描功能，不必手动添加路由
        // me.add("/hello", HelloController.class);

        // 使用路由扫描，参数 "demo." 表示只扫描 demo 包及其子包下的路由
        me.scan("demo.");

        // 如果要将控制器超类中的 public 方法映射为 action 配置成 true，一般不用配置
        me.setMappingSuperClass(false);

        // 配置 baseViewPath，可以让 render(...) 参数省去 baseViewPath 这部分前缀
        me.setBaseViewPath("/view");


        // baseViewPath 为 "/_view"，该 Routes 对象之下映射的所有 Controller 都将取这个值
        //me.setBaseViewPath("/_view");

        // basePath 为第三个参数 "/index"
        me.add("/", BaseTestController.class, "/test");

        // 第三个参数省略时， basePath 取第一个参数的值 : "/project"
        //me.add("/project", ProjectController.class);

        // 配置作用于该 Routes 对象内配置的所有 Controller 的拦截器
        //me.addInterceptor(new FrontInterceptor());

        // 路由扫描，jfinal 4.9.03 新增功能。参数 "com.xxx." 表示扫描被限定的包名，
        // 扫描仅会在该包以及该包的子包下进行
        //me.scan("com.xxx.");

        // 手工添加路由。注意：使用了路由扫描就不要再使用手工添加路由，两者选其一
        //me.add("/hello", HelloController.class);
    }

    @Override
    public void configEngine(Engine me) {
        //添加共享模板
//        me.addSharedFunction("/view/common/layout.html");
//        me.addSharedFunction("/view/common/paginate.html");
//        me.addSharedFunction("/view/admin/common/layout.html");
        //配置是否支持模板热加载
        me.setDevMode(true);
        //配置极速模式
        Engine.setFastMode(true);
        //配置支持中文表达式、变量名、方法名、模板函数名
        Engine.setChineseExpression(true);
        //是否开启HTML压缩功能
        //me.setCompressorOn(' ');

        //配置#include指令中参数以/打头时  将以以下设置的路径为相对路径去寻找文件
        //me.setBaseTemplatePath();
    }

    @Override
    public void configPlugin(Plugins me) {
//        DruidPlugin dp = new DruidPlugin(p.get("url"), p.get("username"), p.get("password"));
//        me.add(dp);
//        ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
//        _MappingKit.mapping(arp);
//        me.add(arp);
//        dp.start();
//        arp.start();
    }
    public static DruidPlugin createDruidPlugin(){
        loadConfig();
        return new DruidPlugin(p.get("url"), p.get("username"), p.get("password"));
    }
    @Override
    public void configInterceptor(Interceptors me) {
        // 以下两行代码配置作用于控制层的全局拦截器
        //me.add(new AuthInterceptor());
        //me.addGlobalActionInterceptor(new AaaInterceptor());
        // 以下一行代码配置业务层全局拦截器
        //me.addGlobalServiceInterceptor(new BbbInterceptor());
        //me.addGlobalActionInterceptor(new TestInterceptor());
    }
    @Override
    public void configHandler(Handlers me) {

    }

    /**
    * 这两个方法可以很方便地在项目启动后与关闭前让开发者有机会进行额外操作，
     * 如在系统启动后创建调度线程或在系统关闭前写回缓存。
    *
    */

    // 系统启动完成后回调
    @Override
    public void onStart() {
    }

    // 系统关闭之前回调
    @Override
    public void onStop() {
    }
}
