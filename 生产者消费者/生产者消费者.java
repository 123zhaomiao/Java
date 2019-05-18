

//notify notifyAll
//package 生产者消费者模型;
//class MyThread implements Runnable{
//    private Object object ;
//    private boolean flag;
//
//    public MyThread(Object object, boolean flag) {
//        this.object = object;
//        this.flag = flag;
//    }
//    public void waitMethod()
//    {
//        synchronized (object)
//        {
//            System.out.println("wait方法开始.."+Thread.currentThread().getName());
//            try {
//                object.wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("wait方法结束.."+Thread.currentThread().getName());
//        }
//    }
//    public void notifyMethod()
//    {
//        synchronized (object)
//        {
//            System.out.println("notify方法开始..."+Thread.currentThread().getName());
//            object.notifyAll();
//            System.out.println("notify方法结束..."+Thread.currentThread().getName());
//        }
//    }
//    @Override
//    public void run() {
//        if(flag){
//            waitMethod();
//        }else{
//            notifyMethod();
//        }
//    }
//}
//public class Test {
//    public static void main(String[] args) throws InterruptedException {
//        Object object = new Object();
//        MyThread myThread1 = new MyThread(object,true);
//        MyThread myThread2 = new MyThread(object,false);
//       for(int i=0 ;i<10;i++)
//       {
//           Thread waitThreadi = new Thread(myThread1,"等待线程"+i);
//           waitThreadi.start();
//       }
//        Thread notifyThread2 = new Thread(myThread2,"唤醒线程");
//        Thread.sleep(1000);
//        notifyThread2.start();
//    }
//}


////一个生产者一个消费者
//package 生产者消费者模型;
//////商品类
//class Goods{
//    private String goodsName;
//    private int count;
//
//    //生产商品方法
//    public synchronized void set(String goodsName){
//        //如果还有货物 就不用生产商品
//        if(this.count > 0)
//        {
//            System.out.println("库存已买");
//            try {
//                //等待消费者消费
//                wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        //如果count==0 生产获取 count++
//        this.goodsName = goodsName;
//        this.count++;
//        System.out.println(toString());
//        //唤醒消费者线程
//        notify();
//    }
//    //消费商品方法
//    public synchronized  void get(){
//        //库存为0
//        if(this.count==0)
//        {
//            System.out.println("商品已卖完");
//            try {
//                //等待生产者生产
//                wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        //库存不为0 则消费者消费产品
//        this.count--;
//        System.out.println(toString());
//        //唤醒生产者线程生产产品
//        notify();
//    }
//
//    @Override
//    public String toString() {
//        return "Goods{" +
//                "goodsName='" + goodsName + '\'' +
//                ", count=" + count +
//                '}';
//    }
//}
////消费者
//class Consumer implements Runnable{
//    private Goods goods;
//
//    public Consumer(Goods goods) {
//        this.goods = goods;
//    }
//
//    @Override
//    public void run() {
//        //消费产品
//        goods.get();
//    }
//}
////生产者线程
//class Producer implements Runnable{
//    private Goods goods;
//
//    public Producer(Goods goods) {
//        this.goods = goods;
//    }
//
//    @Override
//    public void run() {
//        //生产产品
//        goods.set("口红");
//    }
//}
//public class Test{
//    public static void main(String[] args) throws InterruptedException {
//        Goods goods = new Goods();
//        Thread producerThread = new Thread(new Producer(goods));
//        Thread consumerThread = new Thread(new Consumer(goods));
//        //两个线程谁先启动不确定
//        consumerThread.start();
//        producerThread.start();
//    }
//}


//多个生产者 多个消费者 生产者限制生产数量
//package 生产者消费者模型;
//import java.util.ArrayList;
//import java.util.List;
//
////商品类
//class Goods{
//    private String goodsName;
//    private int count;
//
//    //生产商品方法
//    public synchronized void set(String goodsName){
//        //如果还有货物 就不用生产商品
//        while(this.count > 0)
//        {
//            System.out.println("库存已满");
//            try {
//                //等待消费者消费
//                wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        //如果count==0 生产获取 count++
//        this.goodsName = goodsName;
//        this.count++;
//        System.out.println(Thread.currentThread().getName()+"生产"+toString());
//        //唤醒消费者线程
//        notifyAll();
//    }
//    //消费商品方法
//    public synchronized  void get(){
//        //不断判断执行条件
//        /**因为有多个线程 假设消费者线程先执行、由于不止一个消费者
//         * 此时可能有多个消费者在等待，如果此时一个生产者生产了商品
//         * 消费者线程均被唤醒、如果此时线程2消费产品 此时若用if判断
//         * 其余等待线程也会count-- 造成错误 所以需要用while判断
//        */
//        while(this.count==0)
//        {
//            System.out.println("商品已卖完");
//            try {
//                //等待生产者生产
//                wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        //库存不为0 则消费者消费产品
//        this.count--;
//        System.out.println(Thread.currentThread().getName()+"消费"+toString());
//        //唤醒生产者线程生产产品
//        notifyAll();
//    }
//
//    @Override
//    public String toString() {
//        return "Goods{" +
//                "goodsName='" + goodsName + '\'' +
//                ", count=" + count +
//                '}';
//    }
//}
////消费者
//class Consumer implements Runnable{
//    private Goods goods;
//
//    public Consumer(Goods goods) {
//        this.goods = goods;
//    }
//
//    @Override
//    public void run() {
//        while(true)
//        {
//            //不断消费产品
//            goods.get();
//        }
//    }
//}
////生产者线程
//class Producer implements Runnable{
//    private Goods goods;
//
//    public Producer(Goods goods) {
//        this.goods = goods;
//    }
//
//    @Override
//    public void run() {
//       while(true)
//       {
//           //不断生产产品
//           goods.set("特百惠杯子");
//       }
//    }
//}
//public class Test{
//    public static void main(String[] args) throws InterruptedException {
//        Goods goods = new Goods();
//        //存储多个生产、消费者线程
//        List<Thread> list = new ArrayList<>();
//        //10个消费者
//        for(int i =0;i<10;i++)
//        {
//            Thread thread = new Thread(new Consumer(goods),"消费者"+i);
//            list.add(thread);
//        }
//        //5个生产者
//        for(int i =0;i<5;i++)
//        {
//            Thread thread = new Thread(new Producer(goods),"生产者"+i);
//            list.add(thread);
//        }
//        for(Thread thread:list)
//        {
//            thread.start();
//        }
//    }
//}


package 生产者消费者模型;

import java.util.ArrayList;
import java.util.List;

//货物类
class Goods {
    private String goods;
    private int count;
    public Goods(String goods) {
        this.goods = goods;
    }

    /**
     * 生产者生产货物的方法
     */
    public synchronized void produce(){
        //不断判断执行条件
        /**
         * 因为有多个线程 假设现在已有商品，生产者线程均在等待
         * 消费者线程唤醒生产者线程后，假设此时生产者线程1生产产品
         * 此时count=1，若用if判断会造成count数量一直增加
         */
        while(count > 0){
            System.out.println("该商品还有库存....");
            //生产者等待
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //如果没有库存 商品数量+1
        this.count++;
        System.out.println(Thread.currentThread().getName()+"生产产品"+toString());
        //唤醒所有消费者
        notifyAll();
    }

    /**
     * 消费者消费商品
     */
    public synchronized void consumer(){
        //不断判断执行条件
        /**因为有多个线程 假设消费者线程先执行、由于不止一个消费者
         * 此时可能有多个消费者在等待，如果此时一个生产者生产了商品
         * 消费者线程均被唤醒、如果此时线程2消费产品 此时若用if判断
         * 其余等待线程也会count-- 造成错误 所以需要用while判断
         */
        while(count == 0){
            System.out.println("仓库已空...");
            //消费者等待
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //消费商品 数量-1
        this.count--;
        System.out.println(Thread.currentThread().getName()+"消费产品"+toString());
        //唤醒所有生产者
        notifyAll();
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goods='" + goods + '\'' +
                ", count=" + count +
                '}';
    }
}
//生产者线程
class Producer implements Runnable{
    //生产的货物
    private Goods goods;

    public Producer(Goods goods) {
        this.goods = goods;
    }

    @Override
    public void run() {
        while(true){
            this.goods.produce();
        }
    }
}
//消费者线程
class Consumer implements Runnable{
    //消费的货物
    private Goods goods;

    public Consumer(Goods goods) {
        this.goods = goods;
    }

    @Override
    public void run() {
        while(true){
            this.goods.consumer();
        }
    }
}
public class Test{
    public static void main(String[] args) {

        Goods goods = new Goods("特百惠杯子");
        List<Thread> list = new ArrayList<>();

        for(int i=0;i<10;i++){
            Thread thread = new Thread(new Producer(goods),"生产者"+i);
            list.add(thread);
        }

        for(int i=0;i<5;i++){
            Thread thread = new Thread(new Consumer(goods),"消费者"+i);
            list.add(thread);
        }

        for(Thread thread:list){
            //消费者、生产者谁先启动不一定
            thread.start();
        }
    }
}

//多个生产者 多个消费者 生产者不限制生产数量
//package 生产者消费者模型;
//
//import java.util.ArrayList;
//import java.util.List;
//
////货物类
//class Goods {
//    private String goods;
//    private int count;
//    public Goods(String goods) {
//        this.goods = goods;
//    }
//
//    public int getCount() {
//        return count;
//    }
//
//    /**
//     * 生产者生产货物的方法
//     */
//    public synchronized void produce(){
//        //如果没有库存 商品数量+1
//        if(this.count<10){
//            try {
//                Thread.sleep(20);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            this.count++;
//            System.out.println(Thread.currentThread().getName()+"生产产品"+toString());
//        }
//        //唤醒所有消费者
//        notifyAll();
//    }
//
//    /**
//     * 消费者消费商品
//     */
//    public synchronized void consumer(){
//        //不断判断执行条件
//         /**因为有多个线程 假设消费者线程先执行、由于不止一个消费者
//          * 此时可能有多个消费者在等待，如果此时一个生产者生产了商品
//          * 消费者线程均被唤醒、如果此时线程2消费产品 此时若用if判断
//          * 其余等待线程也会count-- 造成错误 所以需要用while判断
//         */
//        while(count == 0){
//            System.out.println("仓库已空...");
//            //消费者等待
//            try {
//                wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        //消费商品 数量-1
//        this.count--;
//        System.out.println(Thread.currentThread().getName()+"消费产品"+toString());
//        //唤醒所有生产者
//        notifyAll();
//    }
//
//    @Override
//    public String toString() {
//        return "Goods{" +
//                "goods='" + goods + '\'' +
//                ", count=" + count +
//                '}';
//    }
//}
////生产者线程
//class Producer implements Runnable{
//    //生产的货物
//    private Goods goods;
//
//    public Producer(Goods goods) {
//        this.goods = goods;
//    }
//
//    @Override
//    public void run() {
//        //最大库存为10
//        while(true){
//            this.goods.produce();
//        }
//    }
//}
////消费者线程
//class Consumer implements Runnable{
//    //消费的货物
//    private Goods goods;
//
//    public Consumer(Goods goods) {
//        this.goods = goods;
//    }
//
//    @Override
//    public void run() {
//        while(true){
//            this.goods.consumer();
//        }
//    }
//}
//public class Test{
//    public static void main(String[] args) {
//
//       Goods goods = new Goods("特百惠杯子");
//       List<Thread> list = new ArrayList<>();
//
//
//        for(int i=0;i<5;i++){
//            Thread thread = new Thread(new Consumer(goods),"消费者"+i);
//            list.add(thread);
//        }
//        for(int i=0;i<10;i++){
//            Thread thread = new Thread(new Producer(goods),"生产者"+i);
//            list.add(thread);
//        }
//       for(Thread thread:list){
//           //消费者、生产者谁先启动不一定
//           thread.start();
//       }
//    }
//}
