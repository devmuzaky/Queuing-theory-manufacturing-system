/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package queusystem;
import java.util.Scanner;
import com.nozom.queuing.validation.*;
/**
 *
 * @author 14mom
 */
public class QueueingSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner n = new Scanner(System.in);
        System.out.print("Enter Your Value: ");
        String x = n.nextLine();
        System.out.println("Value is:"  + Utlity.inputEvaluation(x));
        
    }
    
}
