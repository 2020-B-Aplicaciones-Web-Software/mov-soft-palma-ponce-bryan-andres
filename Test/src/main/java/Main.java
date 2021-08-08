import java.text.DecimalFormat;

public class Main {
    public static void main(String args[]){
        System.out.println(sqrtR(5530,0.0001,25));
    }
    public static double sqrtR (long x, double e, double a) {
        //e=0.000001
        //a=x
        if (Math.abs(a * a - x) <= e) {
            return a;
        } else {
            a = (a * a + x) / (2 * a);
            return (sqrtR(x, e, a));
        }
    }
}
