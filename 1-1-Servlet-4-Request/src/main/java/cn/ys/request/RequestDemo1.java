package cn.ys.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 演示Request对象获取请求行数据
 */
@WebServlet("/requestDemo1")
public class RequestDemo1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1. 获取请求方式 ：GET
        String method = request.getMethod();
        System.out.println(method);
        //2.(*)获取虚拟目录：如果tomcat配置有虚拟路径/yang，会显示为：/yang
        String contextPath = request.getContextPath();
        System.out.println(contextPath);
        //3. 获取Servlet路径: /requestDemo1
        String servletPath = request.getServletPath();
        System.out.println(servletPath);
        //4. 获取get方式请求参数：name=zhangsan
        String queryString = request.getQueryString();
        System.out.println(queryString);
        //5.(*)获取请求URI：/requestDemo1 如果tomcat配置有虚拟路径/yang，会显示为：/yang/requestDemo1
        String requestURI = request.getRequestURI(); // 显示为：/yang/requestDemo1
        StringBuffer requestURL = request.getRequestURL(); // ip:port ，显示为：http://localhost:8083/requestDemo1
        System.out.println(requestURI);
        System.out.println(requestURL);
        //6. 获取协议及版本：HTTP/1.1
        String protocol = request.getProtocol();
        System.out.println(protocol);
        //7. 获取客户机的IP地址：0:0:0:0:0:0:0:1
        String remoteAddr = request.getRemoteAddr();
        System.out.println(remoteAddr);
    }
}
