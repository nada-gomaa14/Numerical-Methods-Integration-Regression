package linearregression;

import java.util.*;

public class LinearRegression {
    static double a, b;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        
        System.out.println("Please enter number of given points:\t");
        int n = s.nextInt();
        
        // Gather points from user
        System.out.println("Please enter the points.");
        double x[] = new double[n];
        double y[] = new double[n];
        for(int i = 0; i < n; i++){
            System.out.println("Point " + (i + 1) + ":");
            System.out.print("x" + i + ":");
            x[i] = s.nextDouble();
            System.out.print("y" + i + ":");
            y[i] = s.nextDouble();
            System.out.print("\n");
        }
        
        System.out.println("--------------------------------");
        linearRegression(n, x, y);
        
        System.out.print("\nPlease enter a value for x:");
        double test = s.nextDouble();
        System.out.printf("At x = %.6f, y = %.6f\n", test, (b * test + a));
    }
    
    public static void linearRegression(int n, double x[], double y[]){
        double sumX = 0, sumY = 0, sumXY = 0, sumX2 = 0;
        
        // Print table header
        System.out.printf("%-15s %-15s %-15s %-15s\n", "x", "y", "xy", "x^2");
        
        // Calculate sums and print table
        for(int i =0; i < n; i++){
            sumX += x[i];
            sumY += y[i];
            sumXY += x[i] * y[i];   
            sumX2 += Math.pow(x[i], 2);
            System.out.printf("%-15s %-15s %-15s %-15s\n", x[i], y[i], x[i] * y[i], Math.pow(x[i], 2));
        }
        
        // Calculate a & b
        b = (n * sumXY - sumX * sumY) / (n * sumX2 - Math.pow(sumX, 2));
        a = (sumY / n) - (b * sumX / n);
        
        // Print linear regression equation
        System.out.println("\nLINEAR REGRESSION EQUATION");
        System.out.printf("y = %.6fx + %.6f\n", b, a);
    }
}
