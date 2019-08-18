class Sex{
    private String name;
    public Sex(String name) {
        this.name = name;
    }
    
    private static Sex male = new Sex("男");
    private static Sex female = new Sex("女");

    public static Sex getInstance(String name){
        if(name.equals("male")){
            return male;
        }else if(name.equals("female")){
            return female;
        }else{
            return null;
        }
    }

    @Override
    public String toString() {
        return this.name;
    }
}
public class Test{
    public static void main(String[] args) {
        Sex male = Sex.getInstance("male");
        Sex female = Sex.getInstance("female");
        System.out.println(male);
        System.out.println(female);
        System.out.println(male == female);
    }
}
