import java.util.Date;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        Date date = new Date();
        //1. 任何类的实例化对象 通过Object提供的getClass()取得Class对象
        System.out.println(date.getClass());
        //2.类名.class取得Class类对象
        System.out.println(Date.class);
        //3.通过Class类提供的静态方法forName()传入类的全名称取得Class对象
        System.out.println(Class.forName("java.util.Date"));
    }
}
