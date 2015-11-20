package Controller;

import Library.InfoClass;
import static Library.InfoClass.IsNumber;
import static Library.InfoClass.IsPembanding;
import Library.Method;
import Library.Stack;

public class Logika implements Method {
    
    @Override
    public boolean IsValid(String str) {
        boolean check = true;
        str = convert(str);
        String[] expArray = str.split("\\s");
        Stack<Double> stArit = new Stack<Double>();
        Stack<Boolean> stBool = new Stack<Boolean>();
// balik array karakter
        
        for (int i = 0; i < expArray.length / 2; i++) {

            String temp = expArray[i];
            expArray[i] = expArray[expArray.length - i - 1];
            expArray[expArray.length - i - 1] = temp;
        }
        
        for (int i = 0; i < expArray.length; i++) {
            if (check) {
                if (InfoClass.IsNumber(expArray[i])) {
                    stArit.push((Double.parseDouble(expArray[i])));
                } else if (InfoClass.IsOperator(expArray[i])) {
                    if (!stArit.isEmpty()) {
                        double operan1 = stArit.pop();
                        if (!stArit.isEmpty()) {

                        } else {
                            check = false;
                            
                        }
                    } else {
                        check = false;
                        
                    }

                } else if (InfoClass.IsPembanding(expArray[i])) {
                    if (!stArit.isEmpty()) {
                        stArit.pop();
                        if (!stArit.isEmpty()) {
                            stArit.pop();
                            stBool.push(true);
                        } else {
                            check = false;
                            
                        }
                    } else {
                        check = false;
                    }

                } else if (expArray[i].equalsIgnoreCase("!")) {
                    if (stBool.isEmpty()) {
                        check = false;
                        
                    }
                } else if (expArray[i].equalsIgnoreCase("true") || expArray[i].equalsIgnoreCase("false")) {
                    if (expArray[i].equalsIgnoreCase("true")) {
                        stBool.push(true);
                    } else {
                        stBool.push(false);
                    }
                } else {
                    check = false;
                    
                }
            }

        }
        if (check) {
            if (stArit.isEmpty()) {
                if (stBool.isEmpty()) {
                    check = false;
                    
                } else {
                    stBool.pop();
                    if (!stBool.isEmpty()) {
                        check = false;
                        
                    }
                }
            } else {
                check = false;
                
            }
        }

        return check;
    }

    

    @Override
    public String result(String str) {

        return "";
    }

    public boolean resultLogika(String str) {
        boolean result = false;
        str = convert(str);
        String[] expArray = str.split("\\s");
        Stack<Double> stArit = new Stack<Double>();
        Stack<Boolean> stBool = new Stack<Boolean>();
        // balik array karakter
        for (int i = 0; i < expArray.length / 2; i++) {

            String temp = expArray[i];
            expArray[i] = expArray[expArray.length - i - 1];
            expArray[expArray.length - i - 1] = temp;
        }
        for (int i = 0; i < expArray.length; i++) {
            if (InfoClass.IsNumber(expArray[i])) {
                stArit.push((Double.parseDouble(expArray[i])));
            } else if (InfoClass.IsOperator(expArray[i])) {
                double operan1 = stArit.pop();
                if (!stArit.isEmpty()) {
                    double hasil = 0;
                    double operan2 = stArit.pop();
                    if (expArray[i].equals("+")) {
                        hasil = operan1 + operan2;
                        stArit.push(hasil);
                    } else if (expArray[i].equals("-")) {
                        hasil = operan1 - operan2;
                        stArit.push(hasil);
                    } else if (expArray[i].equals("*")) {
                        hasil = operan1 * operan2;
                        stArit.push(hasil);
                    } else if (expArray[i].equals("/")) {
                        hasil = operan1 / operan2;
                        stArit.push(hasil);
                    } else if (expArray[i].equals("^")) {
                        hasil = operan1;
                        if (operan2 == 0) {
                            hasil = 1;
                        } else if (operan2 > 0) {
                            for (int j = 0; j < operan2 - 1; j++) {
                                hasil = hasil * operan1;
                            }
                        } else {
                            for (int j = 0; j < (operan2 * -1) - 1; j++) {
                                hasil = hasil * operan1;
                            }
                            hasil = 1 / hasil;
                        }

                        stArit.push(hasil);

                    }
                }
            } else if (InfoClass.IsPembanding(expArray[i])) {
                boolean hasil = false;
                double operan1 = stArit.pop();
                double operan2 = stArit.pop();
                if (expArray[i].equals(">")) {
                    hasil = operan1 > operan2;
                } else if (expArray[i].equals("<")) {
                    hasil = operan1 < operan2;
                } else if (expArray[i].equals(">=")) {
                    hasil = operan1 >= operan2;
                } else if (expArray[i].equals("<=")) {
                    hasil = operan1 <= operan2;
                } else if (expArray[i].equals("==")) {
                    hasil = operan1 == operan2;
                } else if (expArray[i].equals("!=")) {
                    hasil = operan1 != operan2;
                }
                stBool.push(hasil);

            } else if (expArray[i].equalsIgnoreCase("!")) {
                boolean hasil = stBool.pop();
                stBool.push(!hasil);
            } else if (expArray[i].equalsIgnoreCase("true") || expArray[i].equalsIgnoreCase("false")) {
                if (expArray[i].equalsIgnoreCase("true")) {
                    stBool.push(true);
                } else {
                    stBool.push(false);
                }
            }

        }
        result = stBool.pop();
        return result;
    }

    
    public static String convert(String str) {
        String[] expArr = str.split(" ");
        String temp = "";
        for (int i = 0; i < expArr.length; i++) {
            if (IsNumber(expArr[i]) || IsPembanding(expArr[i]) || expArr[i].equalsIgnoreCase("!") ||InfoClass.IsOperator(expArr[i]) || expArr[i].equalsIgnoreCase("false")||expArr[i].equalsIgnoreCase("true")) {
                temp += expArr[i];
                if (i < expArr.length) {
                    temp += " ";
                }
                
            } else {
                if (new CallAssignment().IsValid(expArr[i])) { // Jika Assignment
                    String resultAssignment = "";
                    if (IsNumber(new CallAssignment().result(expArr[i]))) {
                        resultAssignment = new CallAssignment().result(expArr[i]);
                    } else {
                        resultAssignment = new CallAssignment().result(expArr[i]).equals("true") ? "1" : "0";
                    }
                    str = str.replace(expArr[i], resultAssignment);
                } else if (str.indexOf('(') != -1 && str.indexOf(')') != -1 && new CallFungsi().IsValid(expArr[i] + " " + str.substring(str.indexOf('('), str.indexOf(')') + 1))) { // Jika Fungsi
                    str = str.replace(expArr[i] + " " + str.substring(str.indexOf('('), str.indexOf(')') + 1), new CallFungsi().result(expArr[i] + " " + str.substring(str.indexOf('('), str.indexOf(')') + 1)));
                    
                } else {
                    temp = "";
                    break;
                }
                i = -1;
                temp = "";
                expArr = str.split(" ");
                
            }
        }
        return temp;
    }

}
