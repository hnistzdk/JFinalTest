package com.zdk.utils;

import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.activerecord.generator.Generator;
import com.jfinal.plugin.druid.DruidPlugin;
import demo.DemoConfig;

import javax.sql.DataSource;

/**
 * @author zdk
 * @date 2021/4/4 10:35
 */
public class JfinalGenerator {
    public static DataSource getDataSource(){
        DruidPlugin druidPlugin= DemoConfig.createDruidPlugin();
        druidPlugin.start();
        return druidPlugin.getDataSource();
    }

    public static void main(String[] args) {
        //model所使用的包名
        String modelPackageName="com.zdk.pojo";

        //base model所使用的包名
        String baseModelPackageName=modelPackageName+".base";

        //base model文件保存路径
        String baseModelOutputDir= PathKit.getWebRootPath()+"/src/main/java/"+baseModelPackageName.replace('.', '/');
        System.out.println("输出路径:"+baseModelOutputDir);

        //model文件保存路径(MappingKit和dataDictionary文件默认保存路径)
        String modelOutputDir=baseModelOutputDir+"/..";

        //创建生成器
        Generator generator=new Generator(getDataSource(), baseModelPackageName,baseModelOutputDir,modelPackageName,modelOutputDir);

        //设置数据库方言
        generator.setDialect(new MysqlDialect());

        // 设置是否生成链式 setter 方法，强烈建议配置成 false，否则 fastjson 反序列化会跳过有返回值的 setter 方法
        generator.setGenerateChainSetter(false);

        // 添加不需要生成的表名
        generator.addExcludedTable("adv");

        // 设置是否在 Model 中生成 dao 对象
        generator.setGenerateDaoInModel(false);

        // 设置是否生成字典文件
        generator.setGenerateDataDictionary(false);

        // 设置需要被移除的表名前缀用于生成modelName。例如表名 "osc_user"，移除前缀 "osc_"后生成的model名为 "User"而非 OscUser
        generator.setRemovedTableNamePrefixes("t_");

        // 生成
        generator.generate();



    }

}
