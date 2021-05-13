package repository;

import domain.PersonalMedical;

public interface PersonalMedicalRepository extends Repository<Long, PersonalMedical> {

    public PersonalMedical login(String username,String parola);
}
