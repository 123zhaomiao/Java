class Myclass<T>{
   private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
public class Test{
    public static void main(String[] args) {
        Myclass<String> myClass = new Myclass<>();
        myClass.setT("hello");
        print(myClass);
        Myclass<Integer> myClass1 = new Myclass<>();
        myClass1.setT(123);
        print(myClass1);
    }

    /**
     * 此方法的参数是个泛型
     * @param myClass
     */
    public static void print(Myclass<?> myClass){
        System.out.println(myClass.getT());
    }
}
