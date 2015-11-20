package Controller;

import Library.InfoClass;
import Library.Method;
import Model.ModelFungsi;
import Model.ModelParameter;

public class CallFungsi implements Method{

    @Override
    public boolean IsValid(String str) {
        String[] arrStr = str.split(" ");
        if(arrStr.length >= 2 && !arrStr[0].equalsIgnoreCase("func")){ // Jika kata lebih dari 2
            boolean status = false;
            if(str.indexOf('(') != -1 // Jika mengandung buka kurung, dan
                && str.indexOf(')') != -1 // Jika mengandung tutup kurung, dan
                && str.charAt(str.indexOf('(')-1)==' ' /* Jika buka kurung setelah spasi */
                && !arrStr[0].equalsIgnoreCase("ifelse")){
                
                int i = 1;
                String parameter = str.substring(str.indexOf('(')+1, str.length()-1);
                
                if(new CallAssignment().IsValid(parameter)){
                    parameter = new CallAssignment().result(parameter);
                }else if(new CallFungsi().IsValid(parameter)){
                    parameter = new CallFungsi().result(parameter);
                }else if(new Aritmatika().IsValid(parameter)){
                    parameter = new Aritmatika().result(parameter);
                }else if(new Kondisional().IsValid(parameter)){
                    parameter = new Kondisional().result(parameter);
                }
                
                while(i <= InfoClass.stackFungsi.size() && !status){
                    ModelFungsi modelFungsi = (ModelFungsi) InfoClass.stackFungsi.infoTop(i);
                    if(modelFungsi.getNamaFungsi().equals(arrStr[0]) /*Jika ada nama fungsi*/
                       && modelFungsi.getParameter().size() == parameter.split(" ").length /*Jika format parameter benar*/){
                       status = true;
                    }
                    i++;
                }
            }else{
                status = false;
            }
            return status;
        }else{
            return false;
        }
    }

    @Override
    public String result(String str) {
        int i = 1;
        String temp = "";
        String[] arrStr = str.split(" ");
        while(i <= InfoClass.stackFungsi.size() && temp.equals("")){
            ModelFungsi modelFungsi = (ModelFungsi) InfoClass.stackFungsi.infoTop(i);       
            /*Memeriksa fungsi*/
            if(modelFungsi.getNamaFungsi().equals(arrStr[0])){            
                /*Mengisi parameter*/
                String parameter    = str.substring(str.indexOf('(')+1, str.length()-1);
                String implementasi = modelFungsi.getImplementasi();
                
                if(new CallAssignment().IsValid(parameter)){
                    parameter = new CallAssignment().result(parameter);
                }else if(new CallFungsi().IsValid(parameter)){
                    parameter = new CallFungsi().result(parameter);
                }else if(new Aritmatika().IsValid(parameter)){
                    parameter = new Aritmatika().result(parameter);
                }else if(new Kondisional().IsValid(parameter)){
                    parameter = new Kondisional().result(parameter);
                }
                
                for (int j = 1; j <= modelFungsi.getParameter().size(); j++) {
                    ModelParameter modelParameter = (ModelParameter) modelFungsi.getParameter().infoTop(j);
                    modelParameter.setNilaiVariabel(parameter.split(" ")[j-1]);
                    implementasi        = implementasi.replace(modelParameter.getNamaVariabel(), modelParameter.getNilaiVariabel());
                }
                
                /*Mecetak hasil*/
                temp = implementasi;
            }
            i++;
        }
        
        if(new Aritmatika().IsValid(temp)){
            return new Aritmatika().result(temp);
        }else if(new Logika().IsValid(temp)){
            return String.valueOf(new Logika().resultLogika(temp));
        }else if(new Kondisional().IsValid(temp)){
            return String.valueOf(new Kondisional().result(temp));
        }
        
        return temp;
    }
    
}