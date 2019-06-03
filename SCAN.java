package 磁盘调度;


//电梯调度算法
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
class Re
{
    private int Number;  //访问的磁道号

    public Re(int number) {
        Number = number;
    }

    public int getNumber() {
        return Number;
    }
}

class Miao
{
    private Re[] requests; //磁道请求数
    private int start;  //起始磁道号
    private int end;    //终止磁道号
    private int num;    //磁道请求数
    private int cur=100;  //当前磁头在100磁道

    public Miao(int start, int end, int num) {
        this.start = start;
        this.end = end;
        this.num = num;
    }

    //设置请求的磁道号
    public void setRandomRequests()
    {
        requests=new Re[num];
        Set<Integer> reqSet=new TreeSet<>(); //Set不存放重复数据，TreeSet是有序存储
        int n=num;
        //先将随机磁道号放在TreeSet中
        int randomReq=0;
        while((n--)>0)
        {
            Random random=new Random();
            randomReq=random.nextInt(end-start)+start;

            //注意：因为访问的磁道号是随机生成，但是磁道号不能重复，所以需要加此循环
            while(reqSet.contains(randomReq))
            {
                randomReq=random.nextInt(end-start)+start;
            }
            reqSet.add(randomReq);  //生成start-end之间的随机整数
            System.out.println(randomReq);
        }
        int i=0;

        //初始化每一个磁道请求
        for(int tmp:reqSet)
        {
            requests[i]=new Re(tmp);
            i++;
        }
    }

    public float realSCAN()
    {
        float sum=0;
        TreeSet<Integer> Big=new TreeSet<>();  //存放高于当前磁道号的磁道号
        TreeSet<Integer> small=new TreeSet<>();  //存放低于当前磁道号的磁道号
        for(int i=0;i<num;i++)
        {
            //存放高于当前磁道号的磁道号
            if(i<num&&requests[i].getNumber()>=cur)
            {
                Big.add(requests[i].getNumber());
            }
            //存放低于当前磁道号的磁道号
            else
            {
                small.add(requests[i].getNumber());
            }
        }
        System.out.println("被访问的下一个磁道号  移动距离（磁道数）");
        //将要访问的磁道在当前位置内未距离最近者，也就是Big的下一个数字
        for(int tmp:Big)
        {
            System.out.println("  "+tmp+"   "+(tmp-cur));
            sum=sum+(tmp-cur);
            cur=tmp;
        }
        for(int tmp:small.descendingSet())
        {
            System.out.println(" "+tmp+"  "+(cur-tmp));
            sum=sum+(cur-tmp);
            cur=tmp;
        }
        return sum/num;
    }
}
public class SCAN {
    private static Scanner scanner =new Scanner(System.in);
    public static void main(String[] args) {
        System.out.print("请输入起始磁道号和终止磁道号:");
        int start=scanner.nextInt();
        int end=scanner.nextInt();
        System.out.println("请输入磁盘请求序列数");
        int num=scanner.nextInt();
        Miao Miao=new Miao(start,end,num);
        Miao.setRandomRequests();
        float avglen=Miao.realSCAN();
        System.out.println("平均寻道长度:"+avglen);
    }
}