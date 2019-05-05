import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            int n = scanner.nextInt();
            System.out.println(num(n));
        }
    }

    /**
     * 模2除2法
     * @param n
     * @return
     */
    private static int num(int n) {
        int count = 0;
        while(n != 0){
            if(n % 2 != 0){
                count++;
            }
            n = n/2;
        }
        return count;
    }
    }
