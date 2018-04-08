/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cal;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

/**
 *
 * @author Shashi
 */
@WebService(serviceName = "Calculator")
@Stateless()
public class Calculator {

   
    @WebMethod(operationName = "Add")
    public double Add(@WebParam(name = "number1") double number1, @WebParam(name = "number2") double number2) {
        //TODO write your implementation code here:
         double total = number1 + number2;
        return total;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Sub")
    public double Sub(@WebParam(name = "number1") double number1, @WebParam(name = "number2") double number2) {
        //TODO write your implementation code here:
        double total = number1 - number2;
        return total;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Mul")
    public double Mul(@WebParam(name = "number1") double number1, @WebParam(name = "number2") double number2) {
        //TODO write your implementation code here:\
        double total = number1*number2;
        return total;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Div")
    public double Div(@WebParam(name = "number1") double number1, @WebParam(name = "number2") double number2) {
        //TODO write your implementation code here:
        double total = number1/number2;
        return total;
    }
    
    
}
