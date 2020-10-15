import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;

public class Proxy {
    public static Object newProxyInstance(Class infce,InvocationHandler h)
            throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException,
            InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        String rt = "\r\n";
        String methodStr = "";
        for (Method m : infce.getMethods()) {
            methodStr += "@Override" + rt + 
            " public void " + m.getName() + "() {" + rt+ 
             " m." + m.getName() + "();" + rt + 
             "h.invoke(this,)"+rt+
              "}";
        }
        String str = " public class $Proxy0 implements " + infce.getName() + "{" + rt + 
        " public $Proxy0(InvocationHandler h) {" + rt + 
        " super();" + rt + " this.h = h;" + rt + " }" + rt + 
        "private InvocationHandler h;"+rt+
            methodStr + rt + "}" + rt;

        //产生代理类的java文件
        String filename = System.getProperty("user.dir") + "/$Proxy0.java";
        System.out.println(filename);
        // File file = new File(filename);
        FileWriter writer;
        try {
            writer = new FileWriter(filename);
            writer.write(str);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            // TODO: handle exception
        }

        //编译
        //拿到编译器
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        //文件管理者
        StandardJavaFileManager fileManager = 
                compiler.getStandardFileManager(null, null, null);
        //获取文件
        Iterable units =  fileManager.getJavaFileObjects(filename);
        // 编译任务
        CompilationTask t = compiler.getTask(null, fileManager, null, null, null, units);
        //进行编译
        t.call();
        fileManager.close();
        //load到内存
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        Class c = cl.loadClass("$Proxy0");


        Constructor ctr = c.getConstructor(infce);
        System.out.println(c.getName());
        
        return ctr.newInstance(new Car());
    }
}
