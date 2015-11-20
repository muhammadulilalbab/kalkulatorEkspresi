package Library;

import Controller.Aritmatika;
import Controller.Assignment;
import Controller.CallAssignment;
import Controller.CallFungsi;
import Controller.Fungsi;
import Controller.Kondisional;
import Controller.Logika;
import Model.ModelAssignment;
import Model.ModelFungsi;

public class InfoClass { 
    
    public static final String SIGNED_DOUBLE = "(-?(\\d)+(\\.)?(\\d)*)";
    public static Stack<ModelFungsi> stackFungsi = new Stack<>();  
    public static Stack<ModelAssignment> stackAssignment = new Stack();
    
    public static String FormatDouble(double x){
        return String.format("%.3g", x).replaceFirst("\\.?0+(e|$)", "$1");
    }
    public static int Ekspresion(String str) {
        if (new Aritmatika().IsValid(str)) {
            return 1;
        } else if (new Logika().IsValid(str)) {
            return 2;
        } else if (new Fungsi().IsValid(str)) {
            return 3;
        } else if (new Kondisional().IsValid(str)) {
            return 4;
        } else if (new CallAssignment().IsValid(str)) {
            return 5;
        }else if (new CallFungsi().IsValid(str)) {
            return 6;
        }        else if (IsNumber(str)) {
            return -1;
        } else {
            return 0;
        }
    }
    /**
     * menghasilkan true jika ekspresi merupakan operator operator = (+ - / * ^)
     *
     * @param str inputan ekspresi
     * @return
     */
    public static boolean IsOperator(String str) {
        boolean check = false;
        if (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/") || str.equals("^")) {
            check = true;
        }
        return check;
    }

    /**
     * menghasilkan true jika ekspresi merupakan ekspresi pembanding
     *
     * @param str
     * @return
     */
    public static boolean IsPembanding(String str) {
        boolean check = false;
        if (str.equals(">") || str.equals("<") || str.equals(">=") || str.equals("<=") || str.equals("==") || str.equals("!=")) {
            check = true;
        }
        return check;
    }

    /**
     * menghasilkan true jika ekspresi merupakan angka (double)s
     *
     * @param str
     * @return
     */
    public static boolean IsNumber(String str) {
        boolean check = false;
        if (str.matches(SIGNED_DOUBLE)) {
            check = true;
        }
        return check;
    }
    
}
