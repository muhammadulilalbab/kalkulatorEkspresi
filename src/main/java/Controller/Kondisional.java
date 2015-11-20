package Controller;

import Library.InfoClass;
import Library.Method;

public class Kondisional implements Method {

    @Override
    public boolean IsValid(String expression) {
        boolean check = true;
        String[] expArray = expression.split("\\s");
        if (expArray[0].equalsIgnoreCase("ifelse")) {
            int kurungPembuka = 0;
            int kurungPenutup = 0;
            int kurungStatus = 0;
            int jumlahKurungKondisional = 0;

            String strKondisi = "";
            String strTrue = "";
            String strFalse = "";

            String tempString = "";
            for (int i = 1; i < expArray.length; i++) {
                tempString = tempString + " " + expArray[i];
            }

            for (int i = 0; i < tempString.length(); i++) {
                if (check) {

                    if (tempString.charAt(i) == ')') {
                        if (kurungStatus == 0 && kurungPenutup == 0 && jumlahKurungKondisional == 3) {
                            kurungPenutup++;

                        } else {
                            kurungStatus--;
                            if (tempString.length() > i + 1 && jumlahKurungKondisional <= 3 && kurungStatus == 0) {
                                if ((tempString.charAt(i + 1) != ' ' && jumlahKurungKondisional < 3)) {
                                    check = false;
                                }
                                if (jumlahKurungKondisional == 3 && (tempString.charAt(i + 1) != ')')) {
                                    check = false;
                                }

                            }

                        }
                    }

                    if (kurungStatus > 0) {
                        if (jumlahKurungKondisional == 1) {
                            strKondisi = strKondisi + tempString.charAt(i);
                        } else if (jumlahKurungKondisional == 2) {
                            strTrue = strTrue + tempString.charAt(i);
                        } else {
                            strFalse = strFalse + tempString.charAt(i);
                        }
                    }
                    if (tempString.charAt(i) == '(') {
                        if (kurungPembuka == 0) {
                            kurungPembuka++;
                        } else if (kurungStatus == 0 && kurungPenutup == 0) {
                            jumlahKurungKondisional++;
                            kurungStatus++;
                        } else {
                            kurungStatus++;
                        }

                    }
                    if (kurungStatus == 0) {
                        if (tempString.charAt(i) == '(' || tempString.charAt(i) == ')' || tempString.charAt(i) == ' ') {

                        } else {
                            check = false;
                        }
                    }
                }

            }
            if (check) {//Pengecekkan format kurung
                if (!(kurungPembuka == 1 && kurungPenutup == 1 && kurungStatus == 0 && jumlahKurungKondisional == 3)) {
                    check = false;
                }
                if (check) { //Jika format benar
                    int rs = InfoClass.Ekspresion(strKondisi);
                    if (rs == 2) { //Jika format kondisional benar
                        rs = InfoClass.Ekspresion(strTrue);
                        if (rs == 0) {
                            check = false;
                        }
                        rs = InfoClass.Ekspresion(strFalse);
                        if (rs == 0) {
                            check = false;
                        }
                    } else if (rs == 4) {
                        if (InfoClass.IsNumber(new Kondisional().result(strKondisi))) {
                            check = false;
                        }
                        rs = InfoClass.Ekspresion(strTrue);
                        if (rs == 0) {
                            check = false;
                        }
                        rs = InfoClass.Ekspresion(strFalse);
                        if (rs == 0) {
                            check = false;
                        }
                    } else if (rs == 5) {
                        if (InfoClass.IsNumber(new CallAssignment().result(strKondisi))) {
                            check = false;
                        }
                        rs = InfoClass.Ekspresion(strTrue);
                        if (rs == 0) {
                            check = false;
                        }
                        rs = InfoClass.Ekspresion(strFalse);
                        if (rs == 0) {
                            check = false;
                        }
                    } else if (rs == 6) {
                        if (InfoClass.IsNumber(new CallFungsi().result(strKondisi))) {
                            check = false;
                        }
                        rs = InfoClass.Ekspresion(strTrue);
                        if (rs == 0) {
                            check = false;
                        }
                        rs = InfoClass.Ekspresion(strFalse);
                        if (rs == 0) {
                            check = false;
                        }
                    } else {
                        check = false;
                    }
                }
            }

        } else {
            check = false;
        }

        return check;
    }

    @Override
    public String result(String expression) {
        String[] expArray = expression.split("\\s");
        int kurungPembuka = 0;
        int kurungPenutup = 0;
        int kurungStatus = 0;
        int jumlahKurungKondisional = 0;

        String tempString = "";
        for (int i = 1; i < expArray.length; i++) {
            tempString = tempString + " " + expArray[i];
        }
        String strKondisi = "";
        String strTrue = "";
        String strFalse = "";
        for (int i = 0; i < tempString.length(); i++) {
            if (tempString.charAt(i) == ')') {
                if (kurungStatus == 0 && kurungPenutup == 0 && jumlahKurungKondisional == 3) {
                    kurungPenutup++;

                } else {
                    kurungStatus--;
                }
            }
            if (kurungStatus > 0) {
                if (jumlahKurungKondisional == 1) {
                    strKondisi = strKondisi + tempString.charAt(i);
                } else if (jumlahKurungKondisional == 2) {
                    strTrue = strTrue + tempString.charAt(i);
                } else {
                    strFalse = strFalse + tempString.charAt(i);
                }
            }
            if (tempString.charAt(i) == '(') {
                if (kurungPembuka == 0) {
                    kurungPembuka++;
                } else if (kurungStatus == 0 && kurungPenutup == 0) {
                    jumlahKurungKondisional++;
                    kurungStatus++;
                } else {
                    kurungStatus++;
                }

            }

        }

        int rs = InfoClass.Ekspresion(strKondisi);
        if (rs == 4) {
            String kondisi = new Kondisional().result(strKondisi);
            if (kondisi.equalsIgnoreCase("true")) {
                rs = InfoClass.Ekspresion(strTrue);
                if (rs == 1) {
                    return new Aritmatika().result(strTrue);
                } else if (rs == 2) {
                    if (new Logika().resultLogika(strTrue)) {
                        return "true";
                    } else {
                        return "false";
                    }
                } else if (rs == 6) {
                    return new CallFungsi().result(strTrue);
                } else if (rs == 4) {
                    return new Kondisional().result(strTrue);
                } else if (rs == 5) {
                    return new CallAssignment().result(strTrue);
                } else {
                    return strTrue;
                }
            } else {
                rs = InfoClass.Ekspresion(strFalse);
                if (rs == 1) {
                    return new Aritmatika().result(strFalse);
                } else if (rs == 2) {
                    if (new Logika().resultLogika(strFalse)) {
                        return "true";
                    } else {
                        return "false";
                    }
                } else if (rs == 3) {
                    return new CallFungsi().result(strFalse);
                } else if (rs == 4) {
                    return new Kondisional().result(strFalse);
                } else if (rs == 5) {
                    return new CallAssignment().result(strFalse);
                } else {
                    return strFalse;
                }
            }
        } else if (rs == 5) {
            String kondisi = new CallFungsi().result(strKondisi);
            if (kondisi.equalsIgnoreCase("true")) {
                rs = InfoClass.Ekspresion(strTrue);
                if (rs == 1) {
                    return new Aritmatika().result(strTrue);
                } else if (rs == 2) {
                    if (new Logika().resultLogika(strTrue)) {
                        return "true";
                    } else {
                        return "false";
                    }
                } else if (rs == 3) {
                    return new CallFungsi().result(strTrue);
                } else if (rs == 4) {
                    return new Kondisional().result(strTrue);
                } else if (rs == 5) {
                    return new CallAssignment().result(strTrue);
                } else {
                    return strTrue;
                }
            } else {
                rs = InfoClass.Ekspresion(strFalse);
                if (rs == 1) {
                    return new Aritmatika().result(strFalse);
                } else if (rs == 2) {
                    if (new Logika().resultLogika(strFalse)) {
                        return "true";
                    } else {
                        return "false";
                    }
                } else if (rs == 3) {
                    return new CallFungsi().result(strFalse);
                } else if (rs == 4) {
                    return new Kondisional().result(strFalse);
                } else if (rs == 5) {
                    return new CallAssignment().result(strFalse);
                } else {
                    return strFalse;
                }
            }
        } else {
            if (new Logika().resultLogika(strKondisi)) {
                rs = InfoClass.Ekspresion(strTrue);
                if (rs == 1) {
                    return new Aritmatika().result(strTrue);
                } else if (rs == 2) {
                    if (new Logika().resultLogika(strTrue)) {
                        return "true";
                    } else {
                        return "false";
                    }
                } else if (rs == 3) {
                    return new CallFungsi().result(strTrue);
                } else if (rs == 4) {
                    return new Kondisional().result(strTrue);
                } else if (rs == 5) {
                    return new CallAssignment().result(strTrue);
                } else {
                    return strTrue;
                }

            } else {
                rs = InfoClass.Ekspresion(strFalse);
                if (rs == 1) {
                    return new Aritmatika().result(strFalse);
                } else if (rs == 2) {
                    if (new Logika().resultLogika(strFalse)) {
                        return "true";
                    } else {
                        return "false";
                    }
                } else if (rs == 3) {
                    return new CallFungsi().result(strFalse);
                } else if (rs == 4) {
                    return new Kondisional().result(strFalse);
                } else if (rs == 5) {
                    return new CallAssignment().result(strFalse);
                } else {
                    return strFalse;
                }
            }
        }

    }


}
