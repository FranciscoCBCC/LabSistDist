/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psormi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Francisco
 */
public interface Calcular extends Remote {

    public void calcular(int TAMANHO_ENXAME, int MAX_ITERACAO, int DIMENSAO, double C1, double C2, double W_LIMITESUPERIOR, double W_LIMITEINFERIOR) throws RemoteException;
    public void setMelhorX(double melhorX) throws RemoteException;
    public void setMelhorY(double melhorY) throws RemoteException;
    public void iniciarEnxame(int TAMANHO_ENXAME, int DIMENSAO) throws RemoteException;
    public int getMinPos(double[] lista) throws RemoteException;
    public double getMelhorX() throws RemoteException;
    public double getMelhorY() throws RemoteException;
            
}
