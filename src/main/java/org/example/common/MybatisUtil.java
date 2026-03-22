package org.example.common;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.IOException;
import java.io.InputStream;

public class MybatisUtil {

    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // ⚠️ 添加这行，确认配置加载成功
            System.out.println(" MyBatis 配置文件加载成功！");
            System.out.println(" 配置文件路径：" + resource);

        } catch (IOException e) {
            System.out.println(" MyBatis 配置文件找不到：" + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("MyBatis 初始化失败", e);
        }
    }

    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }
}