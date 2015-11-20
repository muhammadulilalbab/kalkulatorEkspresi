package Main;

import Controller.Aritmatika;
import Controller.Assignment;
import Controller.CallAssignment;
import Controller.CallFungsi;
import Controller.Fungsi;
import Controller.Kondisional;
import Controller.Logika;
import java.util.Scanner;

public class TBAlpro {
    
    public static void main(String[] args) {
        
        Scanner sc      = new Scanner(System.in);
        System.out.print("$ "); String input = sc.nextLine();
        
        while(!input.equalsIgnoreCase("exit")){
            if(new Assignment().IsValid(input)){
                System.out.println("$ " + ToNumber(new Assignment().result(input)));
            }else if(new CallAssignment().IsValid(input)){
                System.out.println("$ " + ToNumber(new CallAssignment().result(input)));
            }else if(new Kondisional().IsValid(input)){
                System.out.println("$ "+ ToNumber(new Kondisional().result(input)));
            }else if(new Fungsi().IsValid(input)){  
                System.out.println("$ " + ToNumber(new Fungsi().result(input)));
            }else if(new CallFungsi().IsValid(input)){              
                System.out.println("$ " + ToNumber(new CallFungsi().result(input)));
            }else if(new Aritmatika().IsValid(input)){
                System.out.println("$ " + ToNumber(new Aritmatika().result(input)));
            }else if(new Logika().IsValid(input)){
                System.out.println("$ " + new Logika().resultLogika(input));
            }else{
                System.out.println("$ Ekspresi tidak valid");
            }
            
            System.out.print("$ "); input = sc.nextLine();
        }
        
    }
    
    private static String ToNumber(String nilai) {
        return !nilai.contains(".") ? nilai : nilai.replaceAll("0*$", "").replaceAll("\\.$", "");
    }
    
}
