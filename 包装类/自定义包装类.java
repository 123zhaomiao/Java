//将int封装到类中
class IntDemo{
    private int value;
    //通过构造方法传入int值
    public IntDemo(int value) {
        this.value = value;
    }
    //取出int值
    public int intValue(){
        return this.value;
    }
}
public class Test {
    public static void main(String[] args) {
        //所有引用数据类型都可以直接赋值给Object
        Object obj = new IntDemo(10);
        IntDemo demo = (IntDemo)obj;
        int data = demo.intValue();
        System.out.println(data);
    }
}
