package com.hblolj.spring.custom;

import sun.reflect.CallerSensitive;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @auther Ori
 * @Date 2019/1/8 22 34
 * @Description 生成代理对象的代码
 */
public class MyProxy {

    private static final String ln = "\r\n";

    @CallerSensitive
    public static Object newProxyInstance(MyClassLoader loader,
                                          Class<?>[] interfaces,
                                          MyInvocationHandler h) throws IllegalArgumentException {
        // 1. 生成源代码
        String proxySrc = generateSrc(interfaces[0]);

        // 2. 将生成的源代码输出到磁盘，保存为 .java 文件
        String filePath = MyProxy.class.getResource("").getPath();
        File file = new File(filePath + "$Proxy0.java");
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(proxySrc);
            fileWriter.flush();
            fileWriter.close();

            // 3. 编译源代码，并且生成 .class 文件
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manager = compiler.getStandardFileManager(null, null, null);
            Iterable<? extends JavaFileObject> iterable = manager.getJavaFileObjects(file);
            JavaCompiler.CompilationTask task = compiler.getTask(null, manager, null, null, null, iterable);
            task.call();
            manager.close();

            // 4. 将 class 文件中的内容，动态加载到 JVM 中
            Class<?> proxyClass = loader.findClass("$Proxy0");

            // 5. 返回被代理后的对象
            Constructor<?> constructor = proxyClass.getConstructor(MyInvocationHandler.class);
            file.delete();
            return constructor.newInstance(h);
        } catch (IOException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String generateSrc(Class<?> interfaces){

        StringBuffer src = new StringBuffer();
        src.append("package com.hblolj.spring.custom;" + ln);
        src.append("import java.lang.reflect.Method;" + ln);
        src.append("public class $Proxy0 implements " + interfaces.getName() + "{" + ln);

        src.append("MyInvocationHandler h;" + ln);
        src.append("public $Proxy0(MyInvocationHandler h){" + ln);
        src.append("this.h = h;" + ln);
        src.append("}" + ln);

        for (Method method : interfaces.getMethods()) {
            src.append("public " + method.getReturnType().getName() + " " + method.getName() + "() throws Throwable{" + ln);
            src.append("Method m = " + interfaces.getName() + "." + "class.getMethod(\"" + method.getName() + "\", new Class[]{});" + ln);
            if (!method.getReturnType().getName().equals("void")){
                src.append("return (" + method.getReturnType().getName() + ")this.h.invoke(this, m, null);"  + ln);
            }else {
                src.append("this.h.invoke(this, m, null);"  + ln);
            }
            src.append("}" + ln);
        }

        src.append("}");

        return src.toString();
    }
}
