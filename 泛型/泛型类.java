class Point<T>{
   private T x;
   private T y;
    /**
     * 这不叫泛型方法，只是返回值是一个泛型
     */
    public T getX() {
        return x;
    }

    public void setX(T x) {
        this.x = x;
    }
    public T getY() {
        return y;
    }

    public void setY(T y) {
        this.y = y;
    }
}
public class Test{
    public static void main(String[] args) {
        Point<Integer> point = new Point<>();
        point.setX(10);
        point.setY(20);
        int x = point.getX();
        int y  = point.getY();
        System.out.println(x+"、"+y);

        Point<String> point1 = new Point<>();
        point1.setX("好好学习");
        point1.setY("天天向上");
        String x1 = point1.getX();
        String x2 = point1.getY();
        System.out.println(x1+"、"+x2);
    }
}
