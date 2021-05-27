package repository;

import domain.Medicament;

public interface MedicamentRepository extends Repository<Long, Medicament> {

    Iterable<Medicament> findAllMedicinesInStock();
    Medicament findOneMedicineByName(String name);
}
