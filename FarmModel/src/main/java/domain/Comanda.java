package domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

public class Comanda implements Entity<Long>, Serializable {

    private Long id;
    private String nivelulComenzii;
    private LocalDate dataCreeriComenzii;
    private String status;
    private Set<Medicament> medicamentList;

    public Comanda(){

    }

    public Comanda(String nivelulComenzii, LocalDate dataCreeriComenzii, String status, Set<Medicament> medicamentList) {
        this.nivelulComenzii = nivelulComenzii;
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

    public String getNivelulComenzii() {
        return nivelulComenzii;
    }

    public void setNivelulComenzii(String nivelulComenzii) {
        this.nivelulComenzii = nivelulComenzii;
    }

    public LocalDate getDataCreeriComenzii() {
        return dataCreeriComenzii;
    }

    public void setDataCreeriComenzii(LocalDate dataCreeriComenzii) {
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
                ", nivelulComenzii='" + nivelulComenzii + '\'' +
                ", dataCreeriComenzii=" + dataCreeriComenzii +
                ", status='" + status + '\'' +
                ", medicamentList=" + medicamentList +
                '}';
    }
}
