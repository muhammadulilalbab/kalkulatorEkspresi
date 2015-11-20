package Model;

import Library.Stack;

public class ModelFungsi<T> {
    
    private String namaFungsi;
    private Stack parameter;
    private String implementasi;
    private boolean IsReturnDouble;

    public ModelFungsi() {
    }

    public ModelFungsi(String namaFungsi, Stack parameter, String implementasi, boolean IsReturnDouble) {
        this.namaFungsi = namaFungsi;
        this.parameter = parameter;
        this.implementasi = implementasi;
        this.IsReturnDouble = IsReturnDouble;
    }

    public String getImplementasi() {
        return implementasi;
    }

    public void setImplementasi(String implementasi) {
        this.implementasi = implementasi;
    }

    public Stack getParameter() {
        return parameter;
    }

    public void setParameter(Stack parameter) {
        this.parameter = parameter;
    }

    public String getNamaFungsi() {
        return namaFungsi;
    }

    public void setNamaFungsi(String namaFungsi) {
        this.namaFungsi = namaFungsi;
    }

    public boolean isIsReturnDouble() {
        return IsReturnDouble;
    }

    public void setIsReturnDouble(boolean IsDouble) {
        this.IsReturnDouble = IsDouble;
    }
    
}
