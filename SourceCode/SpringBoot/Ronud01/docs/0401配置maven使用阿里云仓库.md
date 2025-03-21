# 配置maven使用阿里云仓库

## 1、在项目的pom.xml里直接加入

```xml

<repositories><!-- 代码库 -->
    <repository>
        <id>maven-ali</id>
        <url>http://maven.aliyun.com/nexus/content/groups/public//</url>;
        <releases>
            <enabled>true</enabled>
        </releases>
        <snapshots>
            <enabled>true</enabled>
            <updatePolicy>always</updatePolicy>
            <checksumPolicy>fail</checksumPolicy>
        </snapshots>
    </repository>
</repositories>
```

## 2、在$MAVEN_HOME的conf文件夹的setting.xml的标签里加入

```xml

<mirror>
    <id>alimaven</id>
    <name>aliyun maven</name>
    <url>http://maven.aliyun.com/nexus/content/groups/public/</url>;
    <mirrorOf>central</mirrorOf>
</mirror>
```