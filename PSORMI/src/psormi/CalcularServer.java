/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psormi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import psormi.CalcularImp;

/**
 *
 * @author Francisco
 */
public class CalcularServer {

    public CalcularServer() {
        try {
            /*
            Calcular c = new CalcularImp();
            Naming.rebind("rmi://127.0.0.1:1020/CalcularService", c);
                    */
            
            Calcular c = new CalcularImp();
            Registry reg = LocateRegistry.createRegistry(1099);
            System.out.println("Servidor está pronto!");
            reg.rebind("CalcularService", c);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new CalcularServer();
    }
}
