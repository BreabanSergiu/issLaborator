package services;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Observer extends Remote {
    void updateGraphicalInterface() throws RemoteException;
}
