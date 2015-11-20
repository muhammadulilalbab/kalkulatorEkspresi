package Controller;

import Library.InfoClass;
import Library.Method;
import Model.ModelAssignment;

public class Assignment implements Method {

    @Override
    public boolean IsValid(String str) {
        String[] expArray = str.split("\\s");
        if (expArray.length >= 3 && expArray[0].equals("=") && !expArray[1].equals("=") && !InfoClass.IsNumber(expArray[1]) && !InfoClass.IsOperator(expArray[1]) && !InfoClass.IsPembanding(expArray[1])) {
            String variabel = str.substring(str.indexOf(expArray[1]) + (expArray[1].length() + 1), str.length());
            return new Aritmatika().IsValid(variabel)
                    || new CallAssignment().IsValid(variabel)
                    || new CallFungsi().IsValid(variabel)
                    || new Logika().IsValid(variabel)
                    || new Kondisional().IsValid(variabel);
        } else {
            return false;
        }
    }

    @Override
    public String result(String str) {
        String[] expArray = str.split("\\s");
        if (new CallAssignment().IsValid(expArray[1])) {
            String temp = "";
            int i = 1;
            ModelAssignment modelAssignment = (ModelAssignment) InfoClass.stackAssignment.infoTop(1);
                
            while (i <= InfoClass.stackAssignment.size() && temp.equals("")) {
                modelAssignment = (ModelAssignment) InfoClass.stackAssignment.infoTop(i);
                if (modelAssignment.getKonstanta().equals(expArray[1])) {
                    if (modelAssignment.isIsReturnDouble()) {
                        temp = modelAssignment.getValue();
                    } else {
                        temp = modelAssignment.getValue().equals(String.valueOf(Double.MAX_VALUE)) ? "true" : "false";
                    }
                }
                i++;
            }
            String variabel = str.substring(str.indexOf(expArray[1]) + (expArray[1].length() + 1), str.length());
            if (new Aritmatika().IsValid(variabel)) {
                modelAssignment.setValue(new Aritmatika().result(variabel));
                modelAssignment.setIsReturnDouble(true);
            }else if (new Logika().IsValid(variabel)) {
                modelAssignment.setValue(new Logika().resultLogika(variabel) ? String.valueOf(Double.MAX_VALUE) : String.valueOf(Double.MIN_VALUE));
                modelAssignment.setIsReturnDouble(false);
            } else if (new CallAssignment().IsValid(variabel)) {
                if (InfoClass.IsNumber(new CallAssignment().result(variabel))) {
                    modelAssignment.setValue(new CallAssignment().result(variabel));
                    modelAssignment.setIsReturnDouble(true);
                } else {
                    String hasil = new CallAssignment().result(variabel);
                    if(hasil.equalsIgnoreCase("true"))
                    {
                        modelAssignment.setValue(String.valueOf(Double.MAX_VALUE));
                    }else
                    {
                        modelAssignment.setValue(String.valueOf(Double.MIN_VALUE));
                    }
                    
                    modelAssignment.setIsReturnDouble(false);

                }
            } else if (new CallFungsi().IsValid(variabel)) {
                if (InfoClass.IsNumber(new CallFungsi().result(variabel))) {
                    modelAssignment.setValue(new CallFungsi().result(variabel));
                    modelAssignment.setIsReturnDouble(true);
                } else {
                    
                    String hasil = new CallFungsi().result(variabel);
                    if(hasil.equalsIgnoreCase("true"))
                    {
                        modelAssignment.setValue(String.valueOf(Double.MAX_VALUE));
                    }else
                    {
                        modelAssignment.setValue(String.valueOf(Double.MIN_VALUE));
                    }
                    
                    modelAssignment.setIsReturnDouble(false);

                }
            } else if (new Kondisional().IsValid(variabel)) {
                if (InfoClass.IsNumber(new Kondisional().result(variabel))) {
                    modelAssignment.setValue(new Kondisional().result(variabel));
                    modelAssignment.setIsReturnDouble(true);
                } else {
                    String hasil = new Kondisional().result(variabel);
                    if(hasil.equalsIgnoreCase("true"))
                    {
                        modelAssignment.setValue(String.valueOf(Double.MAX_VALUE));
                    }else
                    {
                        modelAssignment.setValue(String.valueOf(Double.MIN_VALUE));
                    }
                    
                    modelAssignment.setIsReturnDouble(false);
                }

            }
            InfoClass.stackAssignment.replace(i-1, modelAssignment);

        } else {

            ModelAssignment modelAssignment = new ModelAssignment();
            modelAssignment.setKonstanta(expArray[1]);
            modelAssignment.setIsReturnDouble(true);

            String variabel = str.substring(str.indexOf(expArray[1]) + (expArray[1].length() + 1), str.length());

            if (new Aritmatika().IsValid(variabel)) {
                modelAssignment.setValue(new Aritmatika().result(variabel));
            } else if (new CallAssignment().IsValid(variabel)) {
                if (InfoClass.IsNumber(new CallAssignment().result(variabel))) {
                    modelAssignment.setValue(new CallAssignment().result(variabel));
                    modelAssignment.setIsReturnDouble(true);
                } else {
                    String hasil = new CallAssignment().result(variabel);
                    if(hasil.equalsIgnoreCase("true"))
                    {
                        modelAssignment.setValue(String.valueOf(Double.MAX_VALUE));
                    }else
                    {
                        modelAssignment.setValue(String.valueOf(Double.MIN_VALUE));
                    }
                    
                    modelAssignment.setIsReturnDouble(false);

                }
            } else if (new CallFungsi().IsValid(variabel)) {
                if (InfoClass.IsNumber(new CallFungsi().result(variabel))) {
                    modelAssignment.setValue(new CallFungsi().result(variabel));
                    modelAssignment.setIsReturnDouble(true);
                } else {
                    String hasil = new CallFungsi().result(variabel);
                    if(hasil.equalsIgnoreCase("true"))
                    {
                        modelAssignment.setValue(String.valueOf(Double.MAX_VALUE));
                    }else
                    {
                        modelAssignment.setValue(String.valueOf(Double.MIN_VALUE));
                    }
                    
                    modelAssignment.setIsReturnDouble(false);

                }
            } else if (new Logika().IsValid(variabel)) {
                modelAssignment.setValue(new Logika().resultLogika(variabel) ? String.valueOf(Double.MAX_VALUE) : String.valueOf(Double.MIN_VALUE));
                modelAssignment.setIsReturnDouble(false);
            } else if (new Kondisional().IsValid(variabel)) {
                if (InfoClass.IsNumber(new Kondisional().result(variabel))) {
                    modelAssignment.setValue(new Kondisional().result(variabel));
                    modelAssignment.setIsReturnDouble(true);
                } else {
                     String hasil = new Kondisional().result(variabel);
                    if(hasil.equalsIgnoreCase("true"))
                    {
                        modelAssignment.setValue(String.valueOf(Double.MAX_VALUE));
                    }else
                    {
                        modelAssignment.setValue(String.valueOf(Double.MIN_VALUE));
                    }
                    
                    modelAssignment.setIsReturnDouble(false);
                }
            }
            InfoClass.stackAssignment.push(modelAssignment);

        }

        return expArray[1];
    }

}
