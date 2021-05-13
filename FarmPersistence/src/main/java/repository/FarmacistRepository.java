package repository;

import domain.Farmacist;

public interface FarmacistRepository extends Repository<Long, Farmacist> {

    public Farmacist login(String username,String parola);
}
