# Servlet 3.0：注解配置
### 好处：
支持注解配置。可以不需要web.xml了。

依赖
```xml
<dependency>
  <groupId>javax.servlet</groupId>
  <artifactId>javax.servlet-api</artifactId>
  <version>3.1.0</version>
  <scope>provided</scope>
</dependency>
```

### 步骤：
1. 创建JavaEE项目，选择Servlet的版本3.0以上，**可以不创建web.xml**
2. 定义一个类，实现Servlet接口
3. 复写方法
4. 在类上使用@WebServlet注解，进行配置
				- `@WebServlet("资源路径")`

```java
package cn.ys.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;


@WebServlet("/demo")
public class ServletDemo implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("Servlet3.0来了.....");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
```

**测试：**
启动 Tomcat，浏览器访问：http://localhost:8080/demo

### @WebServlet 注解

```java
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package javax.servlet.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WebServlet {
    String name() default "";                    // 相当于<Servlet-name>

    String[] value() default {};                 // 代表urlPatterns()属性配置

    String[] urlPatterns() default {};           // 相当于<url-pattern>

    int loadOnStartup() default -1;              // 相当于<load-on-startup>

    WebInitParam[] initParams() default {};

    boolean asyncSupported() default false;

    String smallIcon() default "";

    String largeIcon() default "";

    String description() default "";

    String displayName() default "";
}

```

# Servlet的体系结构

		Servlet -- 接口
			|
		GenericServlet -- 抽象类
			|
		HttpServlet  -- 抽象类

- GenericServlet：将Servlet接口中其他的方法做了默认空实现，只将service()方法作为抽象
  - 将来定义Servlet类时，可以继承GenericServlet，实现service()方法即可

- HttpServlet：对http协议的一种封装，简化操作。（**强烈推荐使用**）
			1. 定义类继承HttpServlet
			2. 复写doGet/doPost方法

![在这里插入图片描述](https://img-blog.csdnimg.cn/20190122201204479.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MjExMjYzNQ==,size_16,color_FFFFFF,t_70)

# Servlet 相关配置
## urlpartten ：Servlet访问路径
1. 一个Servlet可以定义多个访问路径 ： @WebServlet({"/d4","/dd4","/ddd4"})
2. 路径定义规则：
- `/xxx`：路径匹配
- `/xxx/xxx`:多层路径，目录结构
- `*.do`：扩展名匹配
- `/`：缺省路径

>**注：**
>web 应用中所有的资源的响应都是 servlet 负责，包括静态资源。
>通常情况访问 html 页面时，首先从当前 web 项目的 web.xml 文件寻找匹配路径，如果都没有找到，再从 tomcat 默认的 web.xml 匹配，将使用缺省 servlet。
>优先级为：`1>2>3>4`

```java
package cn.itcast.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet路径配置
 */
//@WebServlet({"/d4","/dd4","/ddd4"})
//@WebServlet("/user/demo4")
//@WebServlet("/user/*")
//@WebServlet("/*")
@WebServlet("*.do")
public class ServletDemo4 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("demo4...");
        System.out.println(req);
    }
    
}
```

### 欢迎页面
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190122205004452.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MjExMjYzNQ==,size_16,color_FFFFFF,t_70)

## ServletContext 对象
### 什么是 ServletContext 对象
ServletContext 代表是**一个web应用的环境（上下文）对象**，ServletContext对象内部封装是该web应用的信息 ServletContext对象一个web应用只有一个。

问题：一个web应用有几个servlet对象？----多个

### ServletContext对象的生命周期？
创建：该web应用被加载（服务器启动或发布web应用（前提，服务器启动状态））
销毁：web应用被卸载（服务器关闭，移除该web应用）

### 怎么获得ServletContext对象

    ServletContext servletContext = config.getServletContext();
    ServletContext servletContext = this.getServletContext();

### ServletContext 的作用
① 获得web应用全局的初始化参数
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190122205205174.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MjExMjYzNQ==,size_16,color_FFFFFF,t_70)

② 获得web应用中任何资源的绝对路径（重要）
方法：String path = context.getRealPath(相对于该web应用的相对地址);

③ ServletContext是一个域对象（重要）
什么是域对象？什么是域？
存储数据的区域就是域对象

> 注：
> ServletContext域对象的作用范围：整个web应用（所有的web资源都可以随意向	servletcontext域中存取数据，数据可以共享）

域对象的通用的方法：
- setAtrribute(String name,Object obj);
- getAttribute(String name);
- removeAttribute(String name);

![在这里插入图片描述](https://img-blog.csdnimg.cn/20190122205308490.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MjExMjYzNQ==,size_16,color_FFFFFF,t_70)
