package services;

import domain.Comanda;
import domain.Farmacist;
import domain.Medicament;
import domain.PersonalMedical;

public interface Services {

    public Iterable<Comanda> getAllComenzi();
    public Iterable<Comanda> getUnfulfilledOrders();
    public Iterable<Medicament> getAllMedicinesInStock();
    public Farmacist loginFarmacist(String username,String password);
    public PersonalMedical loginPersonalMedical(String username, String password);
}
