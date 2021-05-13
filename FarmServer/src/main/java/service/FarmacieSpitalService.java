package service;

import domain.Comanda;
import domain.Farmacist;
import domain.Medicament;
import domain.PersonalMedical;
import repository.ComandaRepository;
import repository.FarmacistRepository;

import repository.MedicamentRepository;
import repository.PersonalMedicalRepository;
import services.Services;

public class FarmacieSpitalService implements Services {

    private FarmacistRepository farmacistRepository;
    private PersonalMedicalRepository personalMedicalRepository;
    private MedicamentRepository medicamenteRepository;
    private ComandaRepository comandaRepository;

    public FarmacieSpitalService(FarmacistRepository farmacistRepository, PersonalMedicalRepository personalMedicalRepository, MedicamentRepository medicamenteRepository, ComandaRepository comandaRepository) {
        this.farmacistRepository = farmacistRepository;
        this.personalMedicalRepository = personalMedicalRepository;
        this.medicamenteRepository = medicamenteRepository;
        this.comandaRepository = comandaRepository;
    }


    @Override
    public Iterable<Comanda> getAllComenzi() {
        return comandaRepository.findAll();
    }

    @Override
    public Iterable<Comanda> getUnfulfilledOrders() {
        return comandaRepository.findUnfulfilledOrders();
    }

    @Override
    public Iterable<Medicament> getAllMedicinesInStock() {
        return medicamenteRepository.findAllMedicinesInStock();
    }

    @Override
    public Farmacist loginFarmacist(String username, String password) {
        return farmacistRepository.login(username,password);
    }

    @Override
    public PersonalMedical loginPersonalMedical(String username, String password) {
        return personalMedicalRepository.login(username,password);
    }
}
