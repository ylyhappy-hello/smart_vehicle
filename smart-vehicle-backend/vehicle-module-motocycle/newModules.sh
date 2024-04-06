# read -p "请输入生成模块位置" targerDir
# read -p "请输入生成模块名称" moduleName read -p "请输入父模块名称"   parentModuleName
targerDir=$1 
moduleName=$2
parentModuleName=$3
echo $targerDir/$moduleName
mkdir -p $targerDir/$moduleName 
echo '<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <packaging>pom</packaging>
  <parent>
    <groupId>com.ncst.smart</groupId>
    <artifactId>HEDHED</artifactId>
    <version>${revision}</version>
  </parent>
  <groupId>com.ncst.smart</groupId>
  <artifactId>EDEDED</artifactId>
    <name>${project.artifactId}</name> <!-- 3. 新增 name 为 ${project.artifactId} -->
    <description> <!-- 4. 新增 description 为该模块的描述 -->
        demo 模块，主要实现 XXX、YYY、ZZZ 等功能。    </description>
  <modules>  
    <module>EDEDED-api</module>
    <module>EDEDED-biz</module>
  </modules>
</project>
' > $targerDir/$moduleName/pom.xml
sed -i "s!EDEDED!$moduleName!g" $targerDir/$moduleName/pom.xml 
sed -i "s!HEDHED!$parentModuleName!g" $targerDir/$moduleName/pom.xml

mkdir -p $targerDir/$moduleName/$moduleName-api
echo '<?xml version="1.0"?>
<project xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd" xmlns="http://maven.apache.org/POM/4.0.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
  <modelVersion>4.0.0</modelVersion>
  <parent>
    <groupId>com.ncst.smart</groupId>
    <artifactId>EDEDED</artifactId>
    <version>${revision}</version>
  </parent>
  <groupId>com.ncst.smart</groupId>
  <artifactId>EDEDED-api</artifactId>
  <packaging>jar</packaging>
  <name>EDEDED-api</name>
  <url>http://maven.apache.org</url>
  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  </properties>
  <dependencies>
    <dependency>
      <groupId>com.ncst.smart</groupId>
      <artifactId>vehicle-common</artifactId>
    </dependency>
  </dependencies>
</project>
' > $targerDir/$moduleName/$moduleName-api/pom.xml

sed -i "s!EDEDED!$moduleName!" $targerDir/$moduleName/$moduleName-api/pom.xml


mkdir -p $targerDir/$moduleName/$moduleName-biz
echo '<?xml version="1.0"?>
<project xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd" xmlns="http://maven.apache.org/POM/4.0.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
  <modelVersion>4.0.0</modelVersion>
  <parent>
    <groupId>com.ncst.smart</groupId>
    <artifactId>EDEDED</artifactId>
    <version>${revision}</version>
  </parent>
  <groupId>com.ncst.smart</groupId>
  <artifactId>EDEDED-biz</artifactId>
  <name>EDEDED-biz</name>
  <packaging>jar</packaging>
  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  </properties>
  <dependencies>
           <dependency>
            <groupId>com.ncst.smart</groupId>
            <artifactId>EDEDED-api</artifactId>
            <version>${revision}</version>
        </dependency>

        <!-- 业务组件 -->
        <dependency>
            <groupId>com.ncst.smart</groupId>
            <artifactId>vehicle-spring-boot-starter-biz-operatelog</artifactId>
        </dependency>

        <!-- Web 相关 -->
        <dependency>
            <groupId>com.ncst.smart</groupId>
            <artifactId>vehicle-spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>com.ncst.smart</groupId>
            <artifactId>vehicle-spring-boot-starter-security</artifactId>
        </dependency>

        <!-- DB 相关 -->
        <dependency>
            <groupId>com.ncst.smart</groupId>
            <artifactId>vehicle-spring-boot-starter-mybatis</artifactId>
        </dependency>

        <!-- Test 测试相关 -->
        <dependency>
            <groupId>com.ncst.smart</groupId>
            <artifactId>vehicle-spring-boot-starter-test</artifactId>
        </dependency>

        <!-- Excel 相关 -->
        <dependency>
            <groupId>com.ncst.smart</groupId>
            <artifactId>vehicle-spring-boot-starter-excel</artifactId>
        </dependency>
  </dependencies>
</project>
' > $targerDir/$moduleName/$moduleName-biz/pom.xml

sed -i "s!EDEDED!$moduleName!" $targerDir/$moduleName/$moduleName-biz/pom.xml
