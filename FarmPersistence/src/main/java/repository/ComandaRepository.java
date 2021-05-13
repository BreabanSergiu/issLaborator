package repository;

import domain.Comanda;

public interface ComandaRepository extends Repository<Long, Comanda> {

    public Iterable<Comanda> findUnfulfilledOrders();
}
