class Myclass{
    public <T> void print(T t){
        System.out.println(t);
    }
    
    public <T> T print1(T t){
        return t;
    }
}
public class Test{
    public static void main(String[] args) {
        Myclass myClass = new Myclass();
        myClass.print("hello");
        myClass.print(12);
        myClass.print(1.2);
        System.out.println(myClass.print1("hello"));
        System.out.println(myClass.print1(12));
        System.out.println(myClass.print1(1.2));
    }
}
