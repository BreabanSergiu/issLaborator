package domain;

public class PersonalMedical extends Utilizator {
    public PersonalMedical(String nume, String prenume, String username, String parola) {
        super(nume, prenume, username, parola);
    }

    public PersonalMedical() {
    }

    @Override
    public String getNume() {
        return super.getNume();
    }

    @Override
    public void setNume(String nume) {
        super.setNume(nume);
    }

    @Override
    public String getPrenume() {
        return super.getPrenume();
    }

    @Override
    public void setPrenume(String prenume) {
        super.setPrenume(prenume);
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public void setUsername(String username) {
        super.setUsername(username);
    }

    @Override
    public String getParola() {
        return super.getParola();
    }

    @Override
    public void setParola(String parola) {
        super.setParola(parola);
    }

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public void setId(Long aLong) {
        super.setId(aLong);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
