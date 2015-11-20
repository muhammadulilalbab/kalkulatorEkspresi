package Controller;

import Library.InfoClass;
import Library.Method;
import Model.ModelAssignment;

public class CallAssignment implements Method{

    @Override
    public boolean IsValid(String str) {
        return !result(str).equals("");
    }

    @Override
    public String result(String str) {
        String temp = "";
        int i = 1;
        while(i <= InfoClass.stackAssignment.size() && temp.equals("")){
            ModelAssignment modelAssignment = (ModelAssignment) InfoClass.stackAssignment.infoTop(i);
            if(modelAssignment.getKonstanta().equals(str)){
                if(modelAssignment.isIsReturnDouble()){                    
                    temp = modelAssignment.getValue();
                }else{
                    temp = modelAssignment.getValue().equals(String.valueOf(Double.MAX_VALUE)) ? "true" : "false";
                }
                
            }
            i++;
        }
        return temp;
    }
    
}