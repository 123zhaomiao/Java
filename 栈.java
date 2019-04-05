import java.util.Stack;

public class Test {
    public static void main(String[] args) {
        //1.创建一个栈对象
        Stack<String> stack = new Stack<>();
        //2.入栈操作
        stack.push("辣条");
        stack.push("鸡爪");
        stack.push("翅尖");
        //3.返回栈的大小
        System.out.println(stack.size());
        //打印栈
        System.out.println(stack);
        //4.出栈操作 ---根据先进后出 此次应该翅尖出栈
        System.out.println(stack.pop());
        //根据先进后出 此次应该鸡爪出栈
        System.out.println(stack.pop());
        //根据先进后出 此次应该辣条出栈
        System.out.println(stack.pop());
        //5.输出栈是否为空---此时应该为空
        System.out.println(stack.isEmpty());
        //6.继续出栈 观察结果
        System.out.println(stack.pop());
    }
}
