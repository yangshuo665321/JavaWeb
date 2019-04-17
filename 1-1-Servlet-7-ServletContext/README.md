## ServletContext 对象
### 什么是 ServletContext 对象
概念：代表整个web应用，可以和程序的容器(服务器)来通信

ServletContext 代表是**一个web应用的环境（上下文）对象**，ServletContext对象内部封装是该web应用的信息 ServletContext对象一个web应用只有一个。

问题：一个web应用有几个servlet对象？
----多个

### ServletContext对象的生命周期？
创建：该web应用被加载（服务器启动或发布web应用（前提，服务器启动状态））
销毁：web应用被卸载（服务器关闭，移除该web应用）

### 怎么获得ServletContext对象

    1. 通过request对象获取
    	request.getServletContext();
    2. 通过HttpServlet获取
    	this.getServletContext();

### ServletContext 的作用

	3. 功能：
		1. 获取MIME类型：
			* MIME类型:在互联网通信过程中定义的一种文件数据类型
				* 格式： 大类型/小类型   text/html		image/jpeg

			* 获取：String getMimeType(String file)  
		2. 域对象：共享数据
			1. setAttribute(String name,Object value)
			2. getAttribute(String name)
			3. removeAttribute(String name)

			* ServletContext对象范围：所有用户所有请求的数据
		3. 获取文件的真实(服务器)路径
			1. 方法：String getRealPath(String path)  
				 String b = context.getRealPath("/b.txt");//web目录下资源访问
		         System.out.println(b);
		
		        String c = context.getRealPath("/WEB-INF/c.txt");//WEB-INF目录下的资源访问
		        System.out.println(c);
		
		        String a = context.getRealPath("/WEB-INF/classes/a.txt");//src目录下的资源访问
		        System.out.println(a);

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
