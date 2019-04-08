/**
 * 枚举类
 * 1.枚举类型不能被用户实例化(用户不能new)
 * 2.定义枚举对象(通常名字是大写)
 * 3.使用枚举对象通过  枚举类.属性
 * 4.枚举类是典型的多例设计、每个枚举对象有且只有一个
 * 5.列举所有枚举对象 枚举类.values()
 * 6.枚举对象名称 枚举对象.name()
 * 7.通过枚举类的valueOf方法获取枚举对象(枚举常量)，name必须要存在否则会有非法参数异常
 * 8.枚举类和普通类有很多相似的地方、可以定义成员-静态属性-方法
 * 9.枚举对象有一个方法ordinal可以表示一个下标，从0开始依次加1 在开发中避免使用，它的值基于枚举对象定义的顺序
 * 10.枚举类继承自java.lang.Enum,不能继承其他类
 * 11.枚举类的构造方法是私有的
 * 12.枚举类可以实现接口
 */
enum Color{
    RED("#dd0000"),GREEN("#00dd00"),BLUE("#0000dd");
    private String rgb;
    Color(String rgb) {
        this.rgb = rgb;
    }
    //为什么不提供set方法 枚举对象全局唯一 有且只有一个
    //要是有set方法，意味着我们可以改枚举对象，那么所有使用到它的地方都被改了
    public String getRgb() {
        return rgb;
    }

    public void print(){
        System.out.println(this+" 颜色的rgb为:"+this.rgb);
    }
}
public class Test {
    public static void main(String[] args) {
        Color color = Color.RED;
        Color color1 = Color.RED;
        System.out.println(color == color1);
        Color[] colors = Color.values();
        for(Color c:colors){
            System.out.println(c.name());
        }
        System.out.println(Color.valueOf("RED"));
        Color.RED.print();
        System.out.println(Color.RED.ordinal());
        System.out.println(Color.GREEN.ordinal());
        System.out.println(Color.BLUE.ordinal());
        System.out.println(Color.class.getSuperclass());
    }
}
