package com.zdk.utils;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.zdk.pojo._MappingKit;
import demo.DemoConfig;

/**
 * @author zdk
 * @date 2021/4/4 11:48
 */
public class DpAndArpHelp {
    public static void help(){
        DruidPlugin dp = DemoConfig.createDruidPlugin();
        ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
        _MappingKit.mapping(arp);
        // 与web环境唯一的不同是要手动调用一次相关插件的start()方法
        dp.start();
        arp.start();
    }
}
