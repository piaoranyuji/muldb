package com.test.svc.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Properties;

@Slf4j
public class PropertyUtil {

    /**
     * 从指定配置文件获取指定属性值
     */
    public static String getProperties(String path, String key) {

        Properties prop = new Properties();
        InputStream in = null;
        String value = null;
        try {
            // 通过输入缓冲流进行读取配置文件
            in = new BufferedInputStream(new FileInputStream(new File(path)));
            // 加载输入流
            prop.load(in);
            // 根据关键字获取value值
            value = prop.getProperty(key);
        } catch (Exception e) {
            log.error("读取配置文件异常", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    log.error("读取配置文件异常", e);
                }
            }
        }
        return value;
    }
}
