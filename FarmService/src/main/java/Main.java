import repository.*;

public class Main {
    public static void main(String[] args) {
        FarmacistRepository farmacistRepository = new FarmacistRepositoryDB();
//        farmacistRepository.add(new Farmacist("das","dsa","username","parola"));
//        System.out.println("credentiale"+farmacistRepository.login("username","parola"));
        PersonalMedicalRepository personalMedicalRepository = new PersonalMedicalRepositoryDB() ;
//        System.out.println("credentiale"+personalMedicalRepository.login("ser","ser"));
        ComandaRepository comandaRepository = new ComandaRepositoryDB();
//        comandaRepository.findUnfulfilledOrders().forEach(x-> System.out.println(x));
        MedicamentRepository medicamentRepository  = new MedicamenteRepositoryDB();
        medicamentRepository.findAllMedicinesInStock().forEach(x-> System.out.println(x));

    }
}
