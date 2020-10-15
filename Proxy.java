import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class Proxy {
    public static Object newProxyInstance(Class infce) {
        String rt = "\r\n";
        String methodStr = "";
        for (Method m : infce.getMethods()) {
            methodStr += "@Override" + rt + " public void " + m.getName() + "() {" + rt
                    + " long startTime = System.currentTimeMillis();" + rt + " System.out.println(\"汽车开始行驶...\");" + rt
                    + " m." + m.getName() + "();" + rt + " long endTime = System.currentTimeMillis();" + rt
                    + " System.out.println(\"汽车结束行驶...汽车行驶时间：\" + (endTime - startTime) + \"毫秒！\");" + rt + "}";
        }
        String str = " public class $Proxy0 implements " + infce.getName() + "{" + rt + " public $Proxy0("
                + infce.getName() + " m) {" + rt + " super();" + rt + " this.m = m;" + rt + " }" + rt + " private "
                + infce.getName() + " m;" + rt + methodStr + rt + "}" + rt;

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
        //
        fileManager.getJavaFileObjects(files)
        return null;
    }
}
