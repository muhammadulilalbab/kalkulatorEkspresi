package Model;

public class ModelAssignment {
    
    private String konstanta;
    private String value;
    private boolean IsReturnDouble;

    public ModelAssignment() {
    }

    public ModelAssignment(String konstanta, String value) {
        this.konstanta = konstanta;
        this.value = value;
    }

    public String getKonstanta() {
        return konstanta;
    }

    public void setKonstanta(String konstanta) {
        this.konstanta = konstanta;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isIsReturnDouble() {
        return IsReturnDouble;
    }

    public void setIsReturnDouble(boolean IsReturnDouble) {
        this.IsReturnDouble = IsReturnDouble;
    }
    
}
