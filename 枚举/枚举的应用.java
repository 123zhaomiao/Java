class Person{
    private String name;
    private Integer age;
    private  Gender gender;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Person(String name, Integer age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}
enum Gender{
    //实际开发中需要根据业务情况决定此处枚举对象的数量
    MALE("男"),
    FEMALE("女");
    private String flag;
    Gender(String flag){
        this.flag = flag;
    }

    public String getFlag() {
        return flag;
    }
}

public class Test {
    public static void main(String[] args) {
        Person person = new Person("张三",18,Gender.FEMALE);
        System.out.println(person);
        switch (person.getGender()){
            case MALE:
                System.out.println("男");
                break;
            case FEMALE:
                System.out.println("女");
                break;
            default:
                System.out.println("保密");break;
        }
    }
    
}
