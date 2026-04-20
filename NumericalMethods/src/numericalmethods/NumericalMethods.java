
package numericalmethods;

import java.util.*;
import net.objecthunter.exp4j.*;

public class NumericalMethods {
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

        System.out.println("\nPlease enter the tolerance:");
        double tolerance = s.nextDouble();
        
        System.out.println("\nBISECTION METHOD");
        bisection(expression, a, b, tolerance);
        System.out.println("_________________________________________________________________________________________________________");
        
        System.out.println("\nSECANT METHOD");
        secant(expression, a, b, tolerance);
        System.out.println("_________________________________________________________________________________________________________");

        System.out.println("\nMODIFIED SECANT METHOD");
        modifiedSecant(expression, a, b, tolerance);
        System.out.println("_________________________________________________________________________________________________________");
    
        System.out.println("\nPlease enter the initial x0:");
        double x0 = s.nextDouble();
       
        System.out.println("\nPlease enter the maximum number of iterations:");
        double max = s.nextDouble();
       
        System.out.println("\nNEWTON RAPHSON METHOD");
        newtonRaphson(expression, x0, max, tolerance);
        System.out.println("_________________________________________________________________________________________________________");
        
        System.out.println("\nFIXED POINT METHOD");
        fixedPoint(expression, x0, max, tolerance);
        System.out.println("_________________________________________________________________________________________________________");
    
    }
    
    static void bisection(Expression expression, double a, double b, double tolerance){
        double fa = expression.setVariable("x", a).evaluate();
        double fb = expression.setVariable("x", b).evaluate();
        //Check that f(a)*f(b) < 0
        if(fa * fb >= 0){
            System.out.println("Unable to solve this function using the bisection method.");
            return;
        }
        
        //Calculate the maximum number of iterations
        int max = (int) Math.ceil((Math.log((b - a)/tolerance))/Math.log(2));
        System.out.println("Maximum number of iterations: " + max);
        String stop, iteration;
        double c = 0, fc;
        
        System.out.printf("\n%-5s %-10s %-10s %-10s %-10s %-10s %-10s %-20s %-10s\n", "i", "a", "b", "c", "f(a)", "f(b)", "f(c)", "|f(c)| < tolerance", "i = N");        
        System.out.println("---------------------------------------------------------------------------------------------------");
        for(int i = 1; i <= max; i++){
            if(i == max)
                    iteration = "YES";
                else
                    iteration = "NO";
            
            //Calculate the midpoint c
            c = (a + b) / 2;
            
            //Calculate f(a), f(b) and f(c)
            fa = expression.setVariable("x", a).evaluate();
            fb = expression.setVariable("x", b).evaluate();
            fc = expression.setVariable("x", c).evaluate();
        
            //Check stop condition
            if(Math.abs(fc) < tolerance){
                stop = "YES";
                System.out.printf("%-5s %-10.4f %-10.4f %-10.4f %-10.4f %-10.4f %-10.4f %-20s %-10s\n", i, a, b, c, fa, fb, fc, stop, iteration);
                break;
            }
            else
                stop = "NO";
            
            System.out.printf("%-5s %-10.4f %-10.4f %-10.4f %-10.4f %-10.4f %-10.4f %-20s %-10s\n", i, a, b, c, fa, fb, fc, stop, iteration);
        
            //Update the values of a & b
            if(fa * fc > 0)
                a = c;
            else
                b = c;
        }     
        System.out.printf("\nx* = %.4f\n", c);
    }
    
    static void secant(Expression expression, double a, double b, double tolerance){
        //Calculate the maximum number of iterations
        int max = (int) Math.ceil((Math.log((b - a) / tolerance)) / Math.log(2));
        System.out.println("Maximum number of iterations: " + max);
        String stop, iteration;
        double c = 0, fa, fb, fc;
        
        System.out.printf("\n%-5s %-10s %-10s %-10s %-10s %-10s %-10s %-20s %-10s\n", "i", "a", "b", "c", "f(a)", "f(b)", "f(c)", "|f(c)| < tolerance", "i = N");        
        System.out.println("---------------------------------------------------------------------------------------------------");
        for(int i = 1; i <= max; i++){
            if(i == max)
                    iteration = "YES";
                else
                    iteration = "NO";
            
            //Calculate f(a) and f(b)
            fa = expression.setVariable("x", a).evaluate();
            fb = expression.setVariable("x", b).evaluate();
            
            //Calculate the midpoint c and f(c)
            c = b - fb *((b - a) / (fb - fa));
            fc = expression.setVariable("x", c).evaluate();
        
            //Check stop condition
            if(Math.abs(fc) < tolerance){
                stop = "YES";
                System.out.printf("%-5s %-10.4f %-10.4f %-10.4f %-10.4f %-10.4f %-10.4f %-20s %-10s\n", i, a, b, c, fa, fb, fc, stop, iteration);
                break;
            }
            else
                stop = "NO";
            
            System.out.printf("%-5s %-10.4f %-10.4f %-10.4f %-10.4f %-10.4f %-10.4f %-20s %-10s\n", i, a, b, c, fa, fb, fc, stop, iteration);
        
            //Update the values of a & b
            a = b;
            b = c;
        }   
        System.out.printf("\nx* = %.4f\n", c);
    }
    
    static void modifiedSecant(Expression expression, double a, double b, double tolerance){
        double fa = expression.setVariable("x", a).evaluate();
        double fb = expression.setVariable("x", b).evaluate();
        //Check that f(a)*f(b) < 0
        if(fa * fb >= 0){
            System.out.println("Unable to solve this function using the modified secant method.");
            return;
        }
        
        //Calculate the maximum number of iterations
        int max = (int) Math.ceil((Math.log((b - a)/tolerance))/Math.log(2));
        System.out.println("Maximum number of iterations: " + max);
        String stop, iteration;
        double c = 0, fc;
        
        System.out.printf("\n%-5s %-10s %-10s %-10s %-10s %-10s %-10s %-20s %-10s\n", "i", "a", "b", "c", "f(a)", "f(b)", "f(c)", "|f(c)| < tolerance", "i = N");        
        System.out.println("---------------------------------------------------------------------------------------------------");
        for(int i = 1; i <= max; i++){
            if(i == max)
                    iteration = "YES";
                else
                    iteration = "NO";
            
            //Calculate f(a) and f(b)
            fa = expression.setVariable("x", a).evaluate();
            fb = expression.setVariable("x", b).evaluate();

            //Calculate the midpoint c and f(c)
            c = b - fb *((b - a) / (fb - fa));
            fc = expression.setVariable("x", c).evaluate();
        
            //Check stop condition
            if(Math.abs(fc) < tolerance){
                stop = "YES";
                System.out.printf("%-5s %-10.4f %-10.4f %-10.4f %-10.4f %-10.4f %-10.4f %-20s %-10s\n", i, a, b, c, fa, fb, fc, stop, iteration);
                break;
            }
            else
                stop = "NO";
            
            System.out.printf("%-5s %-10.4f %-10.4f %-10.4f %-10.4f %-10.4f %-10.4f %-20s %-10s\n", i, a, b, c, fa, fb, fc, stop, iteration);
        
            //Update the values of a & b
            if(fa * fc > 0)
                a = c;
            else
                b = c;
        }     
        System.out.printf("\nx* = %.4f\n", c);
    }
    
    static void newtonRaphson(Expression expression, double x0, double max, double tolerance){
        System.out.printf("\n%-5s %-10s %-10s %-10s %-20s %-10s\n", "i", "xi", "f(xi)", "f'(xi)", "|f(xi)| < tolerance", "i = N");        
        System.out.println("-------------------------------------------------------------------------------------------");
        
        int i = 1;
        double x = x0, fx, der;
        String stop, iteration;
        do {
            if(i == max)
                iteration = "YES";
            else
                iteration = "NO";
            
            //Calculate f(x)
            fx = expression.setVariable("x", x).evaluate();
            
            //Calculate f'(x)
            double h = 1e-5;
            der = (expression.setVariable("x", x + h).evaluate() - expression.setVariable("x", x).evaluate()) / h;
            
            //Check stop condition
            if(Math.abs(fx) < tolerance){
                stop = "YES";
                System.out.printf("%-5s %-10.4f %-10.4f %-10.4f %-20s %-10s\n", i, x, fx, der, stop, iteration);
                break;
            }
            else
                stop = "NO";
            
            System.out.printf("%-5s %-10.4f %-10.4f %-10.4f %-20s %-10s\n", i, x, fx, der, stop, iteration);
           
            //Calculate the value of new x
            x = x - (fx / der);
            
            //Increment i
            i++;
        } while(i <= max);
        
        System.out.printf("\nx* = %.4f\n", x);
    }   
    
    static void fixedPoint(Expression expression, double x0, double max, double tolerance){
        System.out.printf("\n%-5s %-10s %-10s %-10s %-20s %-10s\n", "i", "xi", "f(xi)", "g(xi)", "|f(xi)| < tolerance", "i = N");        
        System.out.println("-------------------------------------------------------------------------------------------");
        
        //Determine g(x)
        String modifiedExpression = function + " + x";
        Expression exp = new ExpressionBuilder(modifiedExpression).variable("x").build();
        
        int i = 1;
        double x = x0, fx, gx;
        String stop, iteration;
        do {
            if(i == max)
                iteration = "YES";
            else
                iteration = "NO";
            
            //Calculate f(x) and g(x)
            fx = expression.setVariable("x", x).evaluate();
            gx = exp.setVariable("x", x).evaluate();

            //Check stop condition
            if(Math.abs(fx) < tolerance){
                stop = "YES";
                System.out.printf("%-5s %-10.4f %-10.4f %-10.4f %-20s %-10s\n", i, x, fx, gx, stop, iteration);
                break;
            }
            else
                stop = "NO";
            
            System.out.printf("%-5s %-10.4f %-10.4f %-10.4f %-20s %-10s\n", i, x, fx, gx, stop, iteration);
           
            //Calculate the value of new x
            x = gx;
            
            //Increment i
            i++;
        } while(i <= max);
        
        System.out.printf("\nx* = %.4f\n", gx);
    }   
}