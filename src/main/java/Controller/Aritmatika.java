package Controller;

import Library.InfoClass;
import static Library.InfoClass.IsNumber;
import static Library.InfoClass.IsOperator;
import Library.Method;
import Library.Stack;

public class Aritmatika implements Method {

    @Override
    public boolean IsValid(String str) {
        str = convert(str);

        boolean check = true;
        String[] expArray = str.split("\\s");
        Stack<Double> stArit = new Stack<Double>();
        // balik array karakter
        for (int i = 0; i < expArray.length / 2; i++) {

            String temp = expArray[i];
            expArray[i] = expArray[expArray.length - i - 1];
            expArray[expArray.length - i - 1] = temp;
        }
        //cek masing-masing ekspresi
        for (int i = 0; i < expArray.length; i++) {
            if (InfoClass.IsNumber(expArray[i])) {
                stArit.push((Double.parseDouble(expArray[i])));
            } else if (InfoClass.IsOperator(expArray[i])) {
                double operan1 = stArit.pop();
                if (!stArit.isEmpty()) {
                    
                } else {
                    check = false;
                }

            } else {
                check = false;
            }
        }
        if (check) {
            if (stArit.isEmpty()) {
                check = false;
            } else {
                double value = stArit.pop();
                //System.out.println("value " + value);
                if (!stArit.isEmpty()) {
                    check = false;
                    stArit.clear();
                }

            }
        }

        //System.out.println("Check 1 " + check);
        return check;
    }

    @Override
    public String result(String str) {

        str = convert(str);
        String[] expArray = str.split("\\s");
        String result = "";

        Stack<Double> stArit = new Stack<Double>();

        // balik array karakter
        for (int i = 0; i < expArray.length / 2; i++) {
            String temp = expArray[i];
            expArray[i] = expArray[expArray.length - i - 1];
            expArray[expArray.length - i - 1] = temp;
        }

        //proses kalkulasi disini
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
                            for (int j = 0; j < (operan2 *-1)-1  ; j++) {
                                hasil = hasil * operan1;
                            }
                            hasil = 1/ hasil;
                        }

                        stArit.push(hasil);

                    }
                }

            }
        }

        if (!stArit.isEmpty()) {
            result = String.valueOf(stArit.pop());
            if (!stArit.isEmpty()) {
                stArit.clear();
            }

        }
        // System.out.println("REsult "+result);
        return result;
    }

    public static String convert(String str) {
        String[] expArr = str.split(" ");
        String temp = "";
        for (int i = 0; i < expArr.length; i++) {
            if (IsNumber(expArr[i]) || IsOperator(expArr[i])) {
                temp += expArr[i];
                if (i < expArr.length) {
                    temp += " ";
                }
            } else {
                if (new CallAssignment().IsValid(expArr[i])) { // Jika Assignment
                    str = str.replace(expArr[i], new CallAssignment().result(expArr[i]));
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
