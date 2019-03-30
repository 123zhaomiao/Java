interface IFruit{
    void eat();
}
class Apple implements IFruit{
    @Override
    public void eat() {
        System.out.println("吃苹果");
    }
}
class Pear implements IFruit{
    @Override
    public void eat() {
        System.out.println("吃梨");
    }
}
class Factory{

    public static IFruit getInstance(String name){
        IFruit fruit = null;
        if("apple".equals(name)){
            fruit =  new Apple();
        }else if("pear".equals(name)){
            fruit =  new Pear();
        }
        return fruit;
    }
}
public class Test{
    public static void main(String[] args) {
        IFruit fruit = Factory.getInstance("apple");
        fruit.eat();
    }
}
