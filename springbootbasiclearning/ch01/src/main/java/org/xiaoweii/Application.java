package org.xiaoweii;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 创建启动类
 *
 * @author xiaoweii
 * @create 2024-12-07 17:41
 */

/**
 * @SpringBootApplication 来标注一个主程序类，
 * 说明这是一个SpringBoot应用自动装配就是从这里开始的
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
