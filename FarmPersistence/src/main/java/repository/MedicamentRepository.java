package repository;

import domain.Medicament;

public interface MedicamentRepository extends Repository<Long, Medicament> {

    public Iterable<Medicament> findAllMedicinesInStock();
}
