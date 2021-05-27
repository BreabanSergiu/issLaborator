package service;

import domain.Comanda;
import domain.Farmacist;
import domain.Medicament;
import domain.PersonalMedical;
import repository.ComandaRepository;
import repository.FarmacistRepository;

import repository.MedicamentRepository;
import repository.PersonalMedicalRepository;
import services.Observer;
import services.Services;

import java.rmi.RemoteException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FarmacieSpitalService implements Services {

    private FarmacistRepository farmacistRepository;
    private PersonalMedicalRepository personalMedicalRepository;
    private MedicamentRepository medicamenteRepository;
    private ComandaRepository comandaRepository;

    private Map<String,Observer> connectedClients = new ConcurrentHashMap<>();

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
    public synchronized Farmacist loginFarmacist(String username, String password, Observer client) throws Exception {
        Farmacist farmacist =  farmacistRepository.login(username,password);
        if(farmacist != null){
            if(connectedClients.get(farmacist.getUsername()) != null){
               throw new Exception("the chemist is already logged in!");
            }
            connectedClients.put(farmacist.getUsername(),client);
        }
        return farmacist;

    }

    @Override
    public synchronized  PersonalMedical loginPersonalMedical(String username, String password, Observer client) throws Exception {
        PersonalMedical personalMedical = personalMedicalRepository.login(username,password);
        if(personalMedical != null){
            if(connectedClients.get(personalMedical.getUsername()) != null){
                throw new Exception("the nurse is already logged in! ");
            }
            connectedClients.put(personalMedical.getUsername(),client);
        }
        return personalMedical;
    }


    @Override
    public Comanda deleteOrder(Long id) {

        Comanda comanda = comandaRepository.delete(id);
        notityClients();
        return comanda;
    }

    @Override
    public Medicament getOneMedicineByName(String name) {
        return medicamenteRepository.findOneMedicineByName(name);
    }

    @Override
    public void addOrder(Comanda comanda) {
        comandaRepository.add(comanda);
        notityClients();
    }

    @Override
    public void updateMedicines(Long id , Medicament medicamentToUpdate) {
        medicamenteRepository.update(id,medicamentToUpdate);
    }

    @Override
    public void updateOrders(Long id, Comanda orderToUpdate) {
        comandaRepository.update(id,orderToUpdate);
        notityClients();
    }

    @Override
    public void onoreazaComanda(Comanda selectedOrder) {
        selectedOrder.setStatus("realizata");
        selectedOrder.getMedicamentList().forEach(x->{
            Medicament medicament = medicamenteRepository.findOneMedicineByName(x.getNumeMedicament());
            medicament.setCantitatePeStoc(medicament.getCantitatePeStoc() - 1);
            this.updateMedicines(x.getId(),medicament);
        });
        this.updateOrders(selectedOrder.getId(),selectedOrder);
    }

    @Override
    public void comandaPartiala(Comanda selectedOrder) {
        selectedOrder.setStatus("partial");
        selectedOrder.getMedicamentList().forEach(x->{
            Medicament medicament = medicamenteRepository.findOneMedicineByName(x.getNumeMedicament());
            if(medicament.getCantitatePeStoc() > 0){
                medicament.setCantitatePeStoc(medicament.getCantitatePeStoc() - 1);
            }
            this.updateMedicines(x.getId(),medicament);
        });
        this.updateOrders(selectedOrder.getId(),selectedOrder);
    }

    private final int defaultThread = 5;
    private void notityClients() {
        ExecutorService executorService = Executors.newFixedThreadPool(defaultThread);
        connectedClients.forEach((username,client)->{
            if(client != null){
                try{
                    client.updateGraphicalInterface();
                } catch (RemoteException e) {
                    System.err.println("Eroor at updating the client: " +e);
                }
            }

        });
        executorService.shutdown();
    }
}
