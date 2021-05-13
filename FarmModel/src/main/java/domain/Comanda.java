package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Comanda implements Entity<Long>, Serializable {

    private Long id;
    private String nivelulMedicamentului;
    private Date dataCreeriComenzii;
    private String status;
    private Set<Medicament> medicamentList;

    public Comanda(){

    }

    public Comanda(String nivelulMedicamentului, Date dataCreeriComenzii, String status, Set<Medicament> medicamentList) {
        this.nivelulMedicamentului = nivelulMedicamentului;
        this.dataCreeriComenzii = dataCreeriComenzii;
        this.status = status;
        this.medicamentList = medicamentList;
    }

    public Set<Medicament> getMedicamentList() {
        return medicamentList;
    }

    public void setMedicamentList(Set<Medicament> medicamentList) {
        this.medicamentList = medicamentList;
    }

    public String getNivelulMedicamentului() {
        return nivelulMedicamentului;
    }

    public void setNivelulMedicamentului(String nivelulMedicamentului) {
        this.nivelulMedicamentului = nivelulMedicamentului;
    }

    public Date getDataCreeriComenzii() {
        return dataCreeriComenzii;
    }

    public void setDataCreeriComenzii(Date dataCreeriComenzii) {
        this.dataCreeriComenzii = dataCreeriComenzii;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        return "Comanda{" +
                "id=" + id +
                ", nivelulMedicamentului='" + nivelulMedicamentului + '\'' +
                ", dataCreeriComenzii=" + dataCreeriComenzii +
                ", status='" + status + '\'' +
                ", medicamentList=" + medicamentList +
                '}';
    }
}
