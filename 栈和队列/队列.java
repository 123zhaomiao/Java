import java.util.LinkedList;
import java.util.Queue;
public class Test {
    
    public static void main(String[] args) {
        //1.创建一个队列
        Queue<String> queue = new LinkedList<>();
        //2.入对列
        queue.add("辣条");
        queue.add("鸡爪");
        queue.add("翅尖");
        //3.输出队列中的元素
        System.out.println(queue.size());
        //4 输出队顶元素
        System.out.println(queue.peek());
        //输出
        System.out.println(queue);
        //4.出队列 根据先进先出的原则来看 此次辣条出队列
        System.out.println(queue.poll());
        //根据先进先出的原则来看 此次鸡爪出队列
        System.out.println(queue.poll());
        //根据先进先出的原则来看 此次翅尖出队列
        System.out.println(queue.poll());
        //5.判断队列是否为空
        System.out.println(queue.isEmpty());
        //6.继续出队列 观察结果
        System.out.println(queue.poll());
    } 
}
