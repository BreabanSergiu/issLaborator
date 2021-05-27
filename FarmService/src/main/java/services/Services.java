package services;

import domain.Comanda;
import domain.Farmacist;
import domain.Medicament;
import domain.PersonalMedical;

public interface Services {

    Iterable<Comanda> getAllComenzi();
    Iterable<Comanda> getUnfulfilledOrders();
    Iterable<Medicament> getAllMedicinesInStock();
    Farmacist loginFarmacist(String username,String password,Observer client) throws Exception;
    PersonalMedical loginPersonalMedical(String username, String password,Observer client) throws Exception;
    Comanda deleteOrder(Long id);
    Medicament getOneMedicineByName(String name);
    void addOrder(Comanda comanda);
    void updateMedicines(Long id,Medicament medicamentToUpdate);
    void updateOrders(Long id, Comanda orderToUpdate);
    void onoreazaComanda(Comanda selectedOrder);
    void comandaPartiala(Comanda selectedOrder);
}
