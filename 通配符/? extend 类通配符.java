class Myclass<T extends Number>{
    private T t;
    public T getT() { return t; }
    public void setT(T t) { this.t = t; }
}
public class Test{
    public static void main(String[] args) {
        Myclass<String> myClass = new Myclass<>();
        myClass.setT("hello");
        print(myClass);
    }
    /**
     * 此方法的参数是个泛型 必须接受Number类或其子类
     * @param myClass
     */
    public static void print(Myclass<? extends Number> myClass){
        System.out.println(myClass.getT());
    }
}
