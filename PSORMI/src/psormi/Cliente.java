/*	Execu��o do C�digo:
 * 			1. Execute a classe Servidor;
 * 			2. Execute a classe Cliente;
 * 			3. informe os dados pedidos;
 * 				Ex: TAMANHO_ENXAME: 30;
 MAX_ITERACAO: 100;
 DIMENSAO: 2;
 C1: 2.0;
 C2: 2.0;
 W_LIMITESUPERIOR: 1.0;
 W_LIMITEINFERIOR: 0.0;
    				
 Uma poss�vel solu��o (varia por causa do Rand):
    				
 Solucao Encontrada:
 Melhor X: 3.0000000000073443
 Melhor Y: 0.5000000000211879
    				
 Por: Leandro Silva.
 */
package psormi;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JOptionPane;

public class Cliente {

    public static void main(String[] args) throws UnknownHostException, IOException {

        int TAMANHO_ENXAME; //= 30;
        int MAX_ITERACAO; //= 100;
        int DIMENSAO; //= 2;
        double C1; // = 2.0;
        double C2; // = 2.0;
        double W_LIMITESUPERIOR; // = 1.0;
        double W_LIMITEINFERIOR; // = 0.0;

        try {
            
            //Calcular c = (Calcular) Naming.lookup("//127.0.0.1:1020/CalcularService");
            Registry registry = LocateRegistry.getRegistry("localhost");
            Calcular c = (Calcular) registry.lookup("CalcularService");
            System.out.println("CalcularService encontrado...\n");
            
            TAMANHO_ENXAME = Integer.parseInt(JOptionPane.showInputDialog("Tamanho do Enxame"));
            MAX_ITERACAO = Integer.parseInt(JOptionPane.showInputDialog("Maxima Itera��o"));
            DIMENSAO = Integer.parseInt(JOptionPane.showInputDialog("Dimensao"));
            C1 = Double.parseDouble(JOptionPane.showInputDialog("C1"));
            C2 = Double.parseDouble(JOptionPane.showInputDialog("C2"));
            W_LIMITESUPERIOR = Double.parseDouble(JOptionPane.showInputDialog("W_LIMITESUPERIOR"));
            W_LIMITEINFERIOR = Double.parseDouble(JOptionPane.showInputDialog("W_LIMITEINFERIOR"));
                    
            c.calcular(TAMANHO_ENXAME, MAX_ITERACAO, DIMENSAO, C1, C2, W_LIMITESUPERIOR, W_LIMITEINFERIOR);
            System.out.println("Melhor X: "+c.getMelhorX());
            System.out.println("Melhor Y: "+c.getMelhorY());
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
