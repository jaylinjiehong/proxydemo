import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class THandler implements InvocationHandler {
    private Object target;

    public THandler(Object target) {
        super();
        this.target = target;
    }

    @Override
    public void invoke(Object o, Method m) {
        try {
            long startTime = System.currentTimeMillis();
            System.out.println("汽车开始行驶...");
            m.invoke(target);
            long endTime = System.currentTimeMillis();
            System.out.println("汽车结束行驶...汽车行驶时间：" + (endTime - startTime) + "毫秒！");
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
