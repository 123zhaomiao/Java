package 存储器的分配与回收;
//最先适应算法来分配空间
import java.util.ArrayList;
import java.util.Scanner;

//作业
class Works
{
    private String name;  //作业名字
    private int size;     //作业申请存储空间大小

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}

//存储块
class Memory
{
    private  String name;
    private int size;      //存储块大小
    private int startAddress;  //存储块起始地址
    private String state="空闲";  //存储块的状态

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(int startAddress) {
        this.startAddress = startAddress;
    }
    public String toString()
    {
        return "\t"+this.startAddress+"\t"+this.size+"\t"+this.state;
    }
}

//存储块的存储与排序
class  MenMeth
{
    public void memSort(ArrayList<Memory> list)
    {
        //使用冒泡排序
        int flag=1;
        for(int i=1;i<list.size();i++)
        {
            for(int j=0;j<list.size()-i;j++)
            {
                if(list.get(j).getSize()>list.get(j+1).getSize())
                {
                    flag=0;
                    Memory memorys=list.get(j);  //因为交换需要将整个存储块单元进行交换，包括存储块的大小和状态

                    list.set(j,list.get(j+1));
                    list.set(j+1,memorys);
                    if(j==0)
                    {
                        list.get(0).setStartAddress(0);
                        list.get(1).setStartAddress(list.get(0).getSize());
                    }
                    else

                    {
                        list.get(j).setStartAddress(list.get(j-1).getStartAddress()+list.get(j-1).getSize());
                        list.get(j+1).setStartAddress(list.get(j).getStartAddress()+list.get(j).getSize());
                    }

                }
            }
            if(flag==1)
                break;
        }
    }

    //打印存储块
    public void printMem(ArrayList<Memory> list)
    {
        System.out.println("\t起始地址\t存储大小\t状态");
        for(int i=0;i<list.size();i++)
        {
            System.out.println("\t"+list.get(i));
        }
    }
}

//作业申请内存和释放内存过程
class Process
{
    Scanner input=new Scanner(System.in);
    //使用最先适应算法分配及回收内存
    public void mainPro(ArrayList<Works> worksList, ArrayList<Memory> memorysList) {
        while (true) {

            System.out.println("请输入你要操作的步骤：");
            System.out.println("1.申请内存");
            System.out.println("2.回收内存");
            int n = input.nextInt();

            //申请内存
            if (n == 1) {
                System.out.println("请输入作业名");
                String workName = input.next();
                int i = 0;
                for (i = 0; i < worksList.size(); i++) {
                    //先找到申请内存的作业
                    if (workName.equals(worksList.get(i).getName())) {
                        //将该作业与存储块大小依次比较
                        int j = 0;
                        for (j = 0; j < memorysList.size(); j++) {
                            //只有当作业大小小于等于存储块大小并且存储块是空闲状态才可以将该存储块分配给该作业
                            if (worksList.get(i).getSize() <= memorysList.get(j).getSize()
                                    && "空闲".equals(memorysList.get(j).getState())) {
                                int beyond = memorysList.get(j).getSize() - worksList.get(i).getSize();
                                memorysList.get(j).setState(workName + "处于运行状态");
                                if (beyond != 0) {
                                    memorysList.get(j).setSize(worksList.get(i).getSize());
                                    Memory memorys = new Memory();
                                    memorys.setSize(beyond);
                                    memorys.setName("子存储块");
                                    memorys.setStartAddress(memorysList.get(j).getStartAddress() +
                                            memorysList.get(j).getSize());
                                    memorysList.add(j + 1, memorys);
                                }
                                MenMeth menMeth = new MenMeth();
                                menMeth.printMem(memorysList);
                                break;
                            }
                        }
                        if (j == memorysList.size())
                            System.out.println("没有空闲区分配内存");
                        else
                            break;
                    }
                }
                if (i == worksList.size()) {
                    System.out.println("没有该作业");
                }
            }

            //结束作业，回收内存
            else {
                System.out.println("请输入作业名");
                String workName = input.next();
                int i=0;
                for ( i = 0; i < memorysList.size(); i++) {
                    if ((workName + "正在运行").equals(memorysList.get(i).getState())) {
                        memorysList.get(i).setState("空闲");
                        int size1 = memorysList.get(i).getSize();
                        int size2 = 0;
                        if (i > 0 && memorysList.get(i - 1).getState() == "空闲") {
                            size2 = memorysList.get(i - 1).getSize();
                            memorysList.get(i - 1).setSize(size1 + size2);
                            memorysList.remove(i);
                        }
                        if (i + 1 < memorysList.size() && memorysList.get(i + 1).getState() == "空闲") {
                            size1 = memorysList.get(i).getSize();
                            size2 = memorysList.get(i + 1).getSize();
                            memorysList.get(i).setSize(size1 + size2);
                            memorysList.remove(i + 1);
                        }
                        MenMeth menMeth = new MenMeth();
                        menMeth.printMem(memorysList);
                        break;
                    }
                }
                if(i == memorysList.size())
                {
                    System.out.println("该作业没有申请内存，无法释放内存");
                }
            }
        }
    }
}


public class Test {
    private static Scanner  input=new Scanner(System.in);

    public static void main(String[] args)
    {
        ArrayList<Works> workList=new ArrayList<>();
        ArrayList<Memory> memorysList=new ArrayList();
        System.out.println("请输入你的作业数");
        int n=input.nextInt();
        for(int i=0;i<n;i++)
        {
            System.out.println("请输入第"+(i+1)+"作业需要的内存空间大小");
            Works work=new Works();
            work.setName("作业"+(i+1)); //作业名
            work.setSize(input.nextInt());  //作业所占内存大小
            workList.add(work);
        }
        System.out.println("请输入存储块数量");
        n=input.nextInt();
        for(int i=0;i<n;i++)
        {
            System.out.println("请输入第"+(i+1)+"存储块即存储块大小");
            Memory memorys=new Memory();
            memorys.setName("内存"+(i+1));
            memorys.setSize(input.nextInt());
            int startAddress=0;
            for(int j=0;j<i;j++)
            {
                startAddress+=memorysList.get(j).getSize();
            }
            memorys.setStartAddress(startAddress);
            memorysList.add(memorys);
        }
        MenMeth menMeth=new MenMeth();
        menMeth.memSort(memorysList);
        menMeth.printMem(memorysList);
        Process process=new Process();
        process.mainPro(workList,memorysList);

    }
}
