 public class $Proxy0 implements Moveable{
 public $Proxy0(Moveable m) {
 super();
 this.m = m;
 }
 private Moveable m;
@Override
 public void move() {
 long startTime = System.currentTimeMillis();
 System.out.println("汽车开始行驶...");
 m.move();
 long endTime = System.currentTimeMillis();
 System.out.println("汽车结束行驶...汽车行驶时间：" + (endTime - startTime) + "毫秒！");
}
}
