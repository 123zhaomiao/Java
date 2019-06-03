package 处理器调度;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
//假定系统有五个进程，每一个进程用一个进程控制块PCB来代表
class Process{
    private String name;//进程名称
    private Process next;//指针
    private int runtime;//要求运行时间
    private int precedence;//优先数
    private String state;//状态
    //构造方法
    public Process(String name,int runtime, int precedence, String state) {
        this.name = name;
        this.runtime = runtime;
        this.precedence = precedence;
        this.state = state;
    }

    //进程的get、set方法
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Process getNext() {
        return next;
    }
    public void setNext(Process next) {
        this.next = next;
    }
    public int getRuntime() {
        return runtime;
    }
    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }
    public int getPrecedence() {
        return precedence;
    }
    public void setPrecedence(int precedence) {
        this.precedence = precedence;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

}
public class Test{
    static List <Process> list=new ArrayList<>();
    public static void main(String[] args) {
        Test text=new Test();
        text.init();
        text.run();
    }
    public  void init(){//初始化进程链表
        //R表示运行状态 E表示结束进程
        Random a=new Random();
        Process P1=new Process("P1",a.nextInt(100),a.nextInt(10),"R");
        Process P2=new Process("P2",a.nextInt(100),a.nextInt(10),"R");
        Process P3=new Process("P3",a.nextInt(100),a.nextInt(10),"R");
        Process P4=new Process("P4",a.nextInt(100),a.nextInt(10),"R");
        Process P5=new Process("P5",a.nextInt(100),a.nextInt(10),"R");
        System.out.println("P1进程的运行时间："+P1.getRuntime()+",优先数为："+P1.getPrecedence());
        System.out.println("P2进程的运行时间："+P2.getRuntime()+",优先数为："+P2.getPrecedence());
        System.out.println("P3进程的运行时间："+P3.getRuntime()+",优先数为："+P3.getPrecedence());
        System.out.println("P4进程的运行时间："+P4.getRuntime()+",优先数为："+P4.getPrecedence());
        System.out.println("P5进程的运行时间："+P5.getRuntime()+",优先数为："+P5.getPrecedence());
        P1.setNext(null);//根据实验要求排成队列
        P2.setNext(P4);
        P3.setNext(P5);
        P4.setNext(P3);
        P5.setNext(P1);
        Process temp=P2;
        while(temp !=null){
            list.add(temp);
            temp=temp.getNext();
        }
    }
    public void run(int curPosition)
    {
        Process cur = list.get(curPosition);
        System.out.println(cur.getName()+"正在执行");
        cur.setPrecedence(cur.getPrecedence()-1);
        cur.setRuntime(cur.getRuntime()-1);
        Process temp = list.remove(curPosition);//获得删除的进程
        if(temp.getRuntime()>0)//若进程优先数大于0加到列表中
            list.add(temp);
        else {
            temp.setState("E");
            System.out.println(temp.getName()+"执行完成");
        }
        if(list.size()>0){
            System.out.print("此时列表中有进程：");
            for(int i=0;i<list.size();i++){
                System.out.print(list.get(i).getName()+",");
            }
            System.out.println();
        }else{
            System.out.println("进程全部执行完成");
        }
    }
    public int Max(Process current,List<Process> list) {
        if (!list.contains(current)) {
            int t = list.get(0).getPrecedence();
            int max = 0;
            for (int i = 1; i < list.size(); i++) {
                if (t < list.get(i).getPrecedence()) {
                    t = list.get(i).getPrecedence();
                    max = i;
                }
            }
            return max;
        }else if(current.getPrecedence()>=list.get(0).getPrecedence())
            return list.indexOf(current);

        else
            return 0;
    }
    public void run(){
        Process cur=this.list.get(0);
        this.run(0);
        while (list.size()!=0){
            int a=this.Max(cur, list);//找到优先数最大的进程
            cur = this.list.get(a);
            this.run(a);

        }
    }
}
