package Controller;

import static Library.InfoClass.*;
import Library.Method;
import Library.Stack;
import Model.ModelFungsi;
import Model.ModelParameter;

public class Fungsi implements Method{

    @Override
    public boolean IsValid(String str) {
        String[] arrStr = str.split(" ");
        if(arrStr.length >= 3){ // Jika kata lebih dari 2
            if(arrStr[0].equalsIgnoreCase("func")){ // Jika kata index ke-0 bertuliskan func
                
                int i = 0, countKurung = 0;   
                boolean statusKurung = false;
                
                if(str.indexOf('(') != -1 // Jika mengandung buka kurung, dan
                    && str.indexOf(')') != -1 // Jika mengandung tutup kurung, dan
                    && str.charAt(str.indexOf('(')-1)==' ' /* Jika buka kurung setelah spasi */){
                    
                    while(i <= str.indexOf(')')){
                        if(str.charAt(i)=='('){ // Jika dibuka dengan kurung buka
                            countKurung++;
                        }
                        if(str.charAt(i)==')'){ // Jika ditutup dengan kurung tutup
                            countKurung--;
                            if(countKurung==0){
                                i = str.indexOf(')');
                            }
                        }
                        i++;
                    }
                    statusKurung = (countKurung==0) // Jika format parameter benar
                                   && str.indexOf(')')+2 == str.indexOf('(',str.indexOf(')')) // Jika implementasi dibuka dengan kurung buka
                                   && str.charAt(str.length()-1)==')' /* Jika implementasi ditutup dengan kurung tutup */;
                    
                    if(statusKurung){
                        
                        String fungsi = arrStr[1];
                        String param = str.substring(str.indexOf('(')+1, str.indexOf(')'));
                        String impl  = str.substring(str.indexOf('(',str.indexOf(')'))+1, str.length()-1);
                        String tempImpl = "";
                        
                        String[] arrParam = param.split("\\s");
                        String[] arrImpl  = impl.split("\\s");
                        
                        arrImpl  = impl.split("\\s");
                        boolean status = true;
                        
                        tempImpl = impl;
                        for (int j = 0; j < arrParam.length; j++) {
                            tempImpl = tempImpl.replace(arrParam[j], "1");
                        }
                            
                        Stack<ModelParameter> stackParameter = new Stack<>();
                        for (String p : arrParam) {
                            stackParameter.push(new ModelParameter(p, "0"));
                        }
                        
                        ModelFungsi modelFungsi = new ModelFungsi();
                        modelFungsi.setNamaFungsi(fungsi);
                        modelFungsi.setParameter(stackParameter);
                        modelFungsi.setImplementasi(impl);
                        modelFungsi.setIsReturnDouble(IsOperator(String.valueOf(impl.charAt(0))));

                        stackFungsi.push(modelFungsi);                                                
                        
                        if(new Aritmatika().IsValid(tempImpl) ||
                             new Logika().IsValid(tempImpl) ||
                             new Kondisional().IsValid(tempImpl) ||
                             new CallFungsi().IsValid(tempImpl) ||
                             new CallAssignment().IsValid(tempImpl)){
                            return true;
                        }else{
                            stackFungsi.pop();
                            return false;
                        }
                        
                    }else{
                        return false;
                    }
                    
                }else{
                    return false;
                }
                
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    @Override
    public String result(String str) {
        return str.split(" ")[1];
    }
    
}