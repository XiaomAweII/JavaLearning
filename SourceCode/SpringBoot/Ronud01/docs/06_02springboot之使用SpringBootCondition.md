> 原文链接：https://blog.csdn.net/zhanglu1236789/article/details/78999496

# springboot之使用SpringBootCondition

## Conditional

@Conditional 是 SpringFramework 的功能， SpringBoot 在它的基础上定义了 @ConditionalOnClass ， @ConditionalOnProperty
的一系列的注解来实现更丰富的内容。

### 定义一个自定义标签

```java
import com.example.conditional.MyConditional;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(MyConditional.class)
public @interface MyConditionalIAnnotation {
    String key();

    String value();
}
```

### 自定义Conditional

```java
import com.example.conditional.interfaceI.MyConditionalIAnnotation;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;


public class MyConditional extends SpringBootCondition {

    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(MyConditionalIAnnotation.class.getName());
        Object key = annotationAttributes.get("key");//
        Object value = annotationAttributes.get("value");
        if (key == null || value == null) {
            return new ConditionOutcome(false, "error");
        }

        //获取environment中的值
        String key1 = context.getEnvironment().getProperty(key.toString());
        if (value.equals(key1)) {
            //如果environment中的值与指定的value一致，则返回true
            return new ConditionOutcome(true, "ok");
        }
        return new ConditionOutcome(false, "error");

    }
}
```

### config配置

```java
import com.example.conditional.interfaceI.MyConditionalIAnnotation;
import com.example.conditional.service.MyConditionalService;
import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConditionalConfig {
    public static Logger logger = Logger.getLogger(MyConditionalService.class);

    /**
     * 判断MyConditional 是否符合条件，是则运行MyConditionalService
     * @return
     */
    @MyConditionalIAnnotation(key = "com.example.conditional", value = "lbl")
    @ConditionalOnClass(MyConditionalService.class)
    @Bean
    public MyConditionalService initMyConditionService() {
        logger.info("MyConditionalService已加载。");
        return new MyConditionalService();
    }
}
```

### 配置文件：application.propeties

```properties
spring.application.name=gateway
server.port=8084
#conditional 动态配置，判断该值是否等于lbl，是则创建MyConditionalService实例
com.example.conditional=lbl
#支持自定义aop
spring.aop.auto=true
```