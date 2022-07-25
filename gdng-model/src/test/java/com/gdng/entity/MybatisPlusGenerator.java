package com.gdng.entity;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

public class MybatisPlusGenerator {

    @Test
    public void generateTableRelative() {
//        generate(DatabaseEnum.GOODS);
//        generate(DatabaseEnum.MERCHANT);
//        generate(DatabaseEnum.ORDER);
//        generate(DatabaseEnum.USER);
        generate(DatabaseEnum.PAYMENT);
//        generate(DatabaseEnum.TASK);
    }

    private void generate(DatabaseEnum database) {
        AutoGenerator mpg = new AutoGenerator();

        //全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(System.getProperty("user.dir")+"/src/main/java");
        gc.setFileOverride(true);
        gc.setActiveRecord(false);
        gc.setSwagger2(true);
        gc.setAuthor("gc");


        //自定义文件命名，注意%s 会自动填充表实体属性
/*        gc.setControllerName("%sController");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");*/
        gc.setEntityName("%sPO");
        gc.setMapperName("%sDao");

        mpg.setGlobalConfig(gc);

        //数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setTypeConvert(new MySqlTypeConvert() {
            @Override
            public DbColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                if (fieldType.equals("datetime") || fieldType.equals("date") || fieldType.equals("timestamp"))
                    return DbColumnType.DATE;
                if (fieldType.equals("time"))
                    return DbColumnType.TIME;
                if (fieldType.equals("tinyint") || fieldType.equals("tinyint(1)"))
                    return DbColumnType.INTEGER;
                if (fieldType.equals("bit"))
                    return DbColumnType.BOOLEAN;
                else
                    return (DbColumnType) super.processTypeConvert(globalConfig,fieldType);
            }
        });

        dsc.setDriverName("com.mysql.cj.jdbc.Driver");

        /*dsc.setUrl("jdbc:mysql://127.0.0.1:3306/" + database.getDatabase() + "?serverTimezone=UTC&useSSL=false&useUnicode=true&autoReconnect=true&characterEncoding=utf8&allowMultiQueries=true");
        dsc.setUsername("root");
        dsc.setPassword("#Aa19932909");*/
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/" + database.getDatabase() + "?serverTimezone=UTC&useSSL=false" +
                "&useUnicode=true&autoReconnect=true&characterEncoding=utf8&allowMultiQueries=true");
        dsc.setUsername("root");
        dsc.setPassword("a19932909");

        mpg.setDataSource(dsc);

        //策略配置
        StrategyConfig strategy = new StrategyConfig();
        //表名生成策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //需要生成的表

        switch (database) {
            case GOODS:
                strategy.setInclude(
//                        "mao_product",
//                        "mao_product_sku",
//                        "mao_category",
//                        "mao_store_product",
//                        "mao_store_product_sku",
                        "mao_carousel"
                );
                break;
            case MERCHANT:
                strategy.setInclude(
                        "mao_business",
                        "mao_store"
                );
                break;
            case ORDER:
                strategy.setInclude(
//                        "mao_order",
//                        "mao_order_detail",
                        "mao_order_cart"
                );
                break;
            case USER:
                strategy.setInclude(
                        "mao_user",
                        "mao_user_role",
                        "mao_role",
                        "mao_role_permission",
                        "mao_permission"
                );
                break;
            case PAYMENT:
                strategy.setInclude(
                        "mao_account",
                        "mao_order_pay",
                        "mao_order_pay_detail",
                        "mao_order_refund",
                        "mao_task_pay"
                );
            case TASK:
                strategy.setInclude(
                        "mao_task",
                        "mao_task_prize",
                        "mao_task_record",
                        "mao_task_record_detail",
                        "mao_task_strategy"
                );
        }

        /*strategy.setSuperServiceClass("");
        strategy.setSuperServiceImplClass("");
        strategy.setSuperEntityClass("");
        strategy.setSuperControllerClass("");
        strategy.setSuperMapperClass(null);
        strategy.setRestControllerStyle(false);
        strategy.setEntitySerialVersionUID(true);*/

        strategy.setControllerMappingHyphenStyle(true);
//        strategy.setTablePrefix("mao_");
        strategy.setTablePrefix("mao_");
        strategy.setEntityLombokModel(true);
        strategy.setEntityTableFieldAnnotationEnable(true);

        mpg.setStrategy(strategy);
        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        templateConfig.setController(null);
        templateConfig.setService(null);
        templateConfig.setServiceImpl(null);
        mpg.setTemplate(templateConfig);

        //包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(database.getParentPath());
        /*pc.setController("controller");
        pc.setService("service");
        pc.setServiceImpl("service.impl");*/
        pc.setEntity("po");
        pc.setMapper("dao");
        mpg.setPackageInfo(pc);

        //执行生成
        mpg.execute();
    }

    enum DatabaseEnum {
        GOODS("maomao_mall_goods", "com.gdng.entity.goods"),
        MERCHANT("maomao_mall_merchant", "com.gdng.entity.merchant"),
        ORDER("maomao_mall_order", "com.gdng.entity.order"),
        USER("maomao_mall_user", "com.gdng.entity.user"),
        PAYMENT("maomao_mall_payment", "com.gdng.entity.payment"),
        TASK("maomao_mall_task", "com.gdng.entity.task"),
        ;

        private final String database;
        private final String parentPath;

        DatabaseEnum(String database, String parentPath) {
            this.database = database;
            this.parentPath = parentPath;
        }

        public String getDatabase() {
            return database;
        }

        public String getParentPath() {
            return parentPath;
        }
    }

}
