package 位运算.如何用移位操作实现乘法运算;
import java.util.Scanner;
public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            System.out.println(a+"乘以"+b+"="+solution(a,b));
        }
    }
    /**
     * 首先将b分解为: 2的n次方数+剩余的数
     * 9可以分解为：2^3+1
     * 8可以分解为：2^3+0
     * a*(2的n次方数+剩余的数) = a << n + a*剩余的数
     * @param a 乘数
     * @param b 被乘数
     * @return 乘积
     */
    private static int solution(int a, int b) {
        int mul = 1;
        //计算离b最近但是比b小的 满足2^n的数
        int count = num(b);

        mul = a << count + a*(b-(1<<count));
        return mul;
    }
    private static int num(int b){
        int sum = 1;
        int i;
        for(i = 0 ; i < b ; i++){
            if((sum << i) > b){
                break;
            }
        }
        return i-1;
    }
}
