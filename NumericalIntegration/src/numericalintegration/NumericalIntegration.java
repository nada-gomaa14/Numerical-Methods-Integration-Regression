
package numericalintegration;

import java.util.*;
import net.objecthunter.exp4j.*;

public class NumericalIntegration {
    static String function;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Please enter the function f(x):");
        function = s.nextLine();
        Expression expression = new ExpressionBuilder(function).variable("x").build();

        System.out.println("\nPlease enter the interval [a, b].");
        System.out.printf("a:");
        double a = s.nextDouble();
        System.out.printf("b:");
        double b = s.nextDouble();
        
        System.out.println("\nPlease enter the interval step h:");
        double h = s.nextDouble();

        System.out.println("\nMIDPOINT METHOD");
        midpointMethod(expression, a, b, h);
        System.out.println("--------------------------");
    
        System.out.println("\nTRAPEZOIDAL METHOD");
        trapezoidalMethod(expression, a, b, h);
        System.out.println("--------------------------");
        
        System.out.println("\nSIMPSON'S METHOD");
        simpsonsMethod(expression, a, b, h);
    }
    
    public static void midpointMethod(Expression expression, double a, double b, double h){
        double sumMidpoint = 0;
        
        // Print table header
        System.out.printf("%-15s %-15s\n", "x*", "f(x*)");
        
        // Calculate and print integration table
        for(double i = a; i < b; i += h){
            double x = (2 * i + h) / 2;
            double fx = expression.setVariable("x", x).evaluate();
            sumMidpoint += fx;
            System.out.printf("%-15.4f %-15.4f\n", x, fx);
        }
        
        // Calculate and print equation
        System.out.printf("\nh * Σf(x*) = %.4f\n", (h * sumMidpoint));
    }
    
    public static void trapezoidalMethod(Expression expression, double a, double b, double h){
        double fa = expression.setVariable("x", a).evaluate();
        double fb = expression.setVariable("x", b).evaluate();
        double sumFx = 0;
        
        // Print table header and first row
        System.out.printf("%-15s %-15s\n", "x", "f(x*)");
        System.out.printf("%-15.4f %-15.4f\n", a, fa);

        // Calculate and print integration table
        for(double i = a + h; i < b; i += h){
            double fx = expression.setVariable("x", i).evaluate();
            sumFx += fx;
            System.out.printf("%-15.4f %-15.4f\n", i, fx);
        }
        System.out.printf("%-15.4f %-15.4f\n", b, fb);
        
        // Calculate and print equation
        double trapezoidal =  h / 2 * (fa + 2 * sumFx + fb);
        System.out.printf("\nh / 2 * (f(a) + 2Σf(xi) + f(b)) = %.4f\n", trapezoidal);
    }
    
    public static void simpsonsMethod(Expression expression, double a, double b, double h){
        double fa = expression.setVariable("x", a).evaluate();
        double fb = expression.setVariable("x", b).evaluate();
        
        // Print table header and first row
        System.out.printf("%-15s %-15s\n", "x", "f(x*)");
        System.out.printf("%-15.4f %-15.4f\n", a, fa);

        // Calculate and print integration table
        double sumOdd = 0, sumEven = 0;
        int count = 1;
        for(double i = a + h; i < b; i += h, count++){
            double fx = expression.setVariable("x", i).evaluate();
            
            if(count % 2 == 0){
                sumEven += fx;
                System.out.printf("%-15.4f %-15.4f\n", i, fx);
            }
            else{
                sumOdd += fx;
                System.out.printf("%-15.4f %-15.4f\n", i, fx);
            }
        }
        System.out.printf("%-15.4f %-15.4f\n", b, fb);

                
        double simpsons =  h / 3 * (fa + 4 * sumOdd + 2 * sumEven + fb);
        System.out.printf("\nh / 3 * (f(a) + 4f(xodd) + 2f(xeven) + f(b)) = %.4f\n", simpsons);
    }
}
