package Model;

public class ModelParameter {

    private String namaVariabel;
    private String nilaiVariabel;

    public ModelParameter(String namaVariabel, String nilaiVariabel) {
        this.namaVariabel = namaVariabel;
        this.nilaiVariabel = nilaiVariabel;
    }

    public String getNamaVariabel() {
        return namaVariabel;
    }

    public void setNamaVariabel(String namaVariabel) {
        this.namaVariabel = namaVariabel;
    }

    public String getNilaiVariabel() {
        return nilaiVariabel;
    }

    public void setNilaiVariabel(String nilaiVariabel) {
        this.nilaiVariabel = nilaiVariabel;
    }
    
}
