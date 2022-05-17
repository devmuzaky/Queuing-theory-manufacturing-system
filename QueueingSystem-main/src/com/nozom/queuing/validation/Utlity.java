/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nozom.queuing.validation;

import org.mariuszgromada.math.mxparser.*;
/**
 *
 * @author 14mom
 */
public class Utlity {
    
    public static double inputEvaluation(String input) {
        Expression e = new Expression(input);
        return e.calculate();
    }
     public static boolean inputValidation(String input) {
        return (input.isEmpty() || input.matches(".*[a-zA-Z].*") )  ;
    }
}
