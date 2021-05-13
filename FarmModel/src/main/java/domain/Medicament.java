package domain;

import java.io.Serializable;
import java.util.Date;

public class Medicament implements Entity<Long>, Serializable {

    private Long id;
    private String numeMedicament;
    private Date dataExpirarii;
    private Date dataFabricarii;
    private Integer cantitatePeStoc;

    public Medicament(String numeMedicament, Date dataExpirarii, Date dataFabricarii, Integer cantitatePeStoc) {
        this.numeMedicament = numeMedicament;
        this.dataExpirarii = dataExpirarii;
        this.dataFabricarii = dataFabricarii;
        this.cantitatePeStoc = cantitatePeStoc;
    }

    public Medicament(){

    }

    public String getNumeMedicament() {
        return numeMedicament;
    }

    public void setNumeMedicament(String numeMedicament) {
        this.numeMedicament = numeMedicament;
    }

    public Date getDataExpirarii() {
        return dataExpirarii;
    }

    public void setDataExpirarii(Date dataExpirarii) {
        this.dataExpirarii = dataExpirarii;
    }

    public Date getDataFabricarii() {
        return dataFabricarii;
    }

    public void setDataFabricarii(Date dataFabricarii) {
        this.dataFabricarii = dataFabricarii;
    }

    public Integer getCantitatePeStoc() {
        return cantitatePeStoc;
    }

    public void setCantitatePeStoc(Integer cantitatePeStoc) {
        this.cantitatePeStoc = cantitatePeStoc;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long aLong) {
        id = aLong;
    }

    @Override
    public String toString() {
        return "Medicament{" +
                "numeMedicament='" + numeMedicament + '\'' +
                ", dataExpirarii=" + dataExpirarii +
                ", dataFabricarii=" + dataFabricarii +
                ", cantitatePeStoc=" + cantitatePeStoc +
                '}';
    }
}
