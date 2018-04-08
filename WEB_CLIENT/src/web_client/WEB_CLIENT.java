/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web_client;

/**
 *
 * @author Shashi
 */
public class WEB_CLIENT {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        double num1 = 2;
        double num2 = 4;
       
        System.out.println(add(num1,num2));
        System.out.println(sub(num1,num2));
        System.out.println(mul(num1,num2));
        System.out.println(div(num1,num2));
        
        
        
       
    }

    private static double add(double number1, double number2) {
        pacc.Calculator_Service service = new pacc.Calculator_Service();
        pacc.Calculator port = service.getCalculatorPort();
        return port.add(number1, number2);
    }

    private static double div(double number1, double number2) {
        pacc.Calculator_Service service = new pacc.Calculator_Service();
        pacc.Calculator port = service.getCalculatorPort();
        return port.div(number1, number2);
    }

    private static double mul(double number1, double number2) {
        pacc.Calculator_Service service = new pacc.Calculator_Service();
        pacc.Calculator port = service.getCalculatorPort();
        return port.mul(number1, number2);
    }

    private static double sub(double number1, double number2) {
        pacc.Calculator_Service service = new pacc.Calculator_Service();
        pacc.Calculator port = service.getCalculatorPort();
        return port.sub(number1, number2);
    }
    
}
