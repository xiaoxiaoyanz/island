package com.wucc.island.codegenerate;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.wucc.island.function.ToVarName;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class CodeGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

   // private static final String YES = "yes";
    //private static final String NO = "no";
    private static final String DEFALUT_FTL_PATH = "/templates/island";
    //private static final String MOUDLE_PATH = "/com.wucc.island";

    public static void main(String[] args) {

        //String isInProject = scanner("是否将产生的文件输入到项目中：yes or no");
        //String moduleName = scanner("模块名");
        String businessName = scanner("功能名");
        String projectPath = System.getProperty("user.dir");
        String parentPath = projectPath;
       /* if (YES.equals(isInProject)) {
            parentPath = projectPath + MOUDLE_PATH;
        } else {
            projectPath = getParentDir(projectPath);
        }*/

        Map<String, String[]> map = new HashMap<>();
        String ftlPath = "";
        String[] tableNames = null;
        /*String isBillAndDetail = scanner("是否主子表：yes or no");
        if (!YES.equals(isBillAndDetail) && !NO.equals(isBillAndDetail)) {
            throw new MybatisPlusException("请输入正确的答案，提示 yes or no！");
        }*/
        /*if (YES.equals(isBillAndDetail)) {
            String[] tableNameBill = scanner("主表名，多个英文逗号分割").split(",");
            String[] tableNameDetail = scanner("子表名，多个英文逗号分割").split(",");
            String ftlPathBill = DEFALUT_FTL_PATH + "/bill";
            String ftlPathDetail = DEFALUT_FTL_PATH + "/detail";
            map.put(ftlPathBill, tableNameBill);
            map.put(ftlPathDetail, tableNameDetail);

        } else {
            String[] tableNameBill = scanner("表名，多个英文逗号分割").split(",");
            String ftlPathBill = DEFALUT_FTL_PATH + "/bill";
            map.put(ftlPathBill, tableNameBill);
        }*/

        String[] tableNameBill = scanner("表名，多个英文逗号分割").split(",");
        String ftlPathBill = DEFALUT_FTL_PATH;
        map.put(ftlPathBill, tableNameBill);
        if (null != map && !map.isEmpty()) {
            Iterator<Map.Entry<String, String[]>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {

                // 代码生成器
                AutoGenerator mpg = new AutoGenerator();

                // 全局配置
                GlobalConfig gc = new GlobalConfig();
                gc.setOutputDir(projectPath + "/src/main/java");
                gc.setAuthor("wucc");
                gc.setOpen(false);
                gc.setSwagger2(true);// 实体属性 Swagger2 注解
                gc.setServiceName("%sServiceI");
                gc.setServiceImplName("%sService");
                gc.setFileOverride(true);
                gc.setBaseResultMap(true);
                gc.setBaseColumnList(true);
                gc.setDateType(DateType.ONLY_DATE);
                mpg.setGlobalConfig(gc);

                // 数据源配置
                DataSourceConfig dsc = new DataSourceConfig();
                dsc.setUrl(
                    "jdbc:mysql://127.0.0.1:3306/island?allowMultiQueries=true&serverTimezone=Asia/Shanghai&useSSL=false");
                // dsc.setSchemaName("public");
                dsc.setDriverName("com.mysql.cj.jdbc.Driver");
                dsc.setUsername("root");
                dsc.setPassword("wuqwe123");
                mpg.setDataSource(dsc);

                // 包配置
                PackageConfig pc = new PackageConfig();
                pc.setModuleName("island");
                pc.setParent("com.wucc");
                pc.setEntity("entity." + businessName);
                pc.setService("api." + businessName);
                pc.setServiceImpl("service." + businessName);
                pc.setMapper("repository." + businessName);
                pc.setController("controller." + businessName);
                mpg.setPackageInfo(pc);

                // 自定义配置
                InjectionConfig cfg = new InjectionConfig() {
                    @Override
                    public void initMap() {
                        // 加入自定义变量
                        Map<String, Object> configMap = new HashMap<String, Object>();
                        configMap.put("toVarName", new ToVarName());
                        configMap.put("businessName", businessName);
                        this.setMap(configMap);
                    }
                };

                Map.Entry<String, String[]> entry = iterator.next();
                ftlPath = entry.getKey();
                tableNames = entry.getValue();
                // 如果模板引擎是 freemarker
                String templatePath = ftlPath + "/controller.java.ftl";
                // 如果模板引擎是 velocity
                // String templatePath = "/templates/controller.xml.vm";

                // 自定义输出配置
                List<FileOutConfig> focList = new ArrayList<>();
                // 自定义配置会被优先输出
                String finalParentPath = parentPath;
                focList.add(new FileOutConfig(templatePath) {
                    @Override
                    public String outputFile(TableInfo tableInfo) {
                        // // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                        return finalParentPath
                                + "/com.wucc.island.controller/src/main/java/com/wucc/island/controller/"
                            + businessName + "/" + tableInfo.getControllerName() + StringPool.DOT_JAVA;
                    }
                });

                // api模板
                templatePath = ftlPath + "/service.java.ftl";
                String finalParentPath1 = parentPath;
                focList.add(new FileOutConfig(templatePath) {
                    @Override
                    public String outputFile(TableInfo tableInfo) {
                        return finalParentPath
                                + "/com.wucc.island.api/src/main/java/com/wucc/island/api/"
                                + businessName + "/" + tableInfo.getServiceName() + StringPool.DOT_JAVA;
                    }
                });

                // entity模板
                templatePath = ftlPath + "/entity.java.ftl";
                String finalParentPath2 = parentPath;
                focList.add(new FileOutConfig(templatePath) {
                    @Override
                    public String outputFile(TableInfo tableInfo) {
                        return finalParentPath
                                + "/com.wucc.island.api/src/main/java/com/wucc/island/entity/" + businessName
                            + "/" + tableInfo.getEntityName() + StringPool.DOT_JAVA;
                    }
                });

                // service模板
                templatePath = ftlPath + "/serviceImpl.java.ftl";
                String finalParentPath3 = parentPath;
                focList.add(new FileOutConfig(templatePath) {
                    @Override
                    public String outputFile(TableInfo tableInfo) {
                        return finalParentPath
                                + "/com.wucc.island.service/src/main/java/com/wucc/island/service/"
                            + businessName + "/" + tableInfo.getServiceImplName() + StringPool.DOT_JAVA;
                    }
                });

                // repository模板
                templatePath = ftlPath + "/mapper.java.ftl";
                String finalParentPath4 = parentPath;
                focList.add(new FileOutConfig(templatePath) {
                    @Override
                    public String outputFile(TableInfo tableInfo) {
                        return finalParentPath
                                + "/com.wucc.island.service/src/main/java/com/wucc/island/repository/"
                            + businessName + "/" + tableInfo.getMapperName() + StringPool.DOT_JAVA;
                    }
                });

                // xml模板
                templatePath = ftlPath + "/mapper.xml.ftl";
                String finalParentPath5 = parentPath;
                focList.add(new FileOutConfig(templatePath) {
                    @Override
                    public String outputFile(TableInfo tableInfo) {
                        return finalParentPath5
                            + "/com.wucc.island.service/src/main/resources/mappings/mysql/" + businessName + "/"
                            + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                    }
                });
               /* // Vo模板
                if (tableNames[0].endsWith("bill")) {
                    templatePath = ftlPath + "/vo.java.ftl";
                    String finalParentPath6 = parentPath;
                    focList.add(new FileOutConfig(templatePath) {
                        @Override
                        public String outputFile(TableInfo tableInfo) {
                            return finalParentPath6
                                + "/com.wucc.apsl." + moduleName + ".api/src/main/java/com/wucc/apsl/"
                                + pc.getModuleName() + "/vo/" + businessName + "/" + tableInfo.getServiceImplName()
                                    .substring(0, tableInfo.getServiceImplName().length() - 7)
                                + "VO" + StringPool.DOT_JAVA;
                        }
                    });
                }*/

                /*
                 * cfg.setFileCreate(new IFileCreate() {
                 *
                 * @Override public boolean isCreate(ConfigBuilder configBuilder,
                 * FileType fileType, String filePath) { // 判断自定义文件夹是否需要创建
                 * checkDir("调用默认方法创建的目录"); return false; } });
                 */
                cfg.setFileOutConfigList(focList);
                mpg.setCfg(cfg);

                // 配置模板
                TemplateConfig templateConfig = new TemplateConfig();

                // 配置自定义输出模板
                // 指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
                // templateConfig.setEntity("templates/entity2.java");
                // templateConfig.setService();
                templateConfig.setController(null);
                templateConfig.setService(null);
                templateConfig.setEntity(null);
                templateConfig.setServiceImpl(null);
                templateConfig.setMapper(null);
                templateConfig.setXml(null);
                mpg.setTemplate(templateConfig);

                // 策略配置
                StrategyConfig strategy = new StrategyConfig();
                strategy.setNaming(NamingStrategy.underline_to_camel);
                strategy.setColumnNaming(NamingStrategy.underline_to_camel);
                // strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
                strategy.setEntityLombokModel(true);
                strategy.setRestControllerStyle(true);
                // 公共父类
                // strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
                // 写于父类中的公共字段
                // strategy.setSuperEntityColumns("id");
                strategy.setInclude(tableNames);
                strategy.setControllerMappingHyphenStyle(true);
                strategy.setTablePrefix(pc.getModuleName() + "_");
                // 乐观锁
                strategy.setVersionFieldName("VERSION");

                mpg.setStrategy(strategy);
                mpg.setTemplateEngine(new FreemarkerTemplateEngine());
                mpg.execute();
            }
        }

    }

    private static String getParentDir(String parh) {
        return parh.substring(0, parh.lastIndexOf(System.getProperty("file.separator")));
    }

}
