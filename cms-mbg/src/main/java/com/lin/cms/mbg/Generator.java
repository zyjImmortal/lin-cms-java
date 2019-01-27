package com.lin.cms.mbg;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouyajun on 2019/1/20
 */
public class Generator {
    public static void main(String[] args) throws Exception{
        List<String> warings = new ArrayList<String>();
        boolean overWrite = true;
        InputStream stream = Generator.class.getResourceAsStream("/generator-config.xml");
        ConfigurationParser parser = new ConfigurationParser(warings);
        Configuration configuration = parser.parseConfiguration(stream);
        stream.close();

        DefaultShellCallback callback = new DefaultShellCallback(overWrite);
        MyBatisGenerator generator = new MyBatisGenerator(configuration, callback, warings);
        generator.generate(null);
        for (String waring:warings) {
            System.out.println(waring);
        }
    }
}
