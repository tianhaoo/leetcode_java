package lanqiaobei.f11;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Integer n = scan.nextInt();
        Integer a = 0; // 及格人数
        Integer b = 0; // 优秀人数

        // 判断是否还有输入
        for (int i=0; i<n; i++) {
            int grade = scan.nextInt();
            if(grade >= 60){
                a ++;
            }
            if(grade >= 85){
                b ++;
            }
        }
        System.out.printf("%.0f%%\n", (a.floatValue() / n.floatValue())*100);
        System.out.printf("%.0f%%\n", (b.floatValue() / n.floatValue())*100);

        scan.close();
    }
}
