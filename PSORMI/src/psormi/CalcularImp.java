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
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
import java.util.Vector;

import javax.swing.JOptionPane;

public class CalcularImp extends UnicastRemoteObject implements Calcular{

    static Vector<Particula> exame = new Vector<Particula>();
    static Random gerador = new Random();
    static double MELHOR_X;
    static double MELHOR_Y;

    public CalcularImp() throws RemoteException {
        super();
    }

    
    public void calcular(int TAMANHO_ENXAME, int MAX_ITERACAO, int DIMENSAO, double C1, double C2, double W_LIMITESUPERIOR, double W_LIMITEINFERIOR)  throws RemoteException{
        double[] pMelhor = new double[TAMANHO_ENXAME];
        Vector<Localizacao> pMelhorLocalizacao = new Vector<Localizacao>();
        double gMelhor = 0;
        Localizacao gMelhorLocalizacao = null;
        double[] fitnessValorLista = new double[TAMANHO_ENXAME];

        Random gerador = new Random();

        iniciarEnxame(TAMANHO_ENXAME, DIMENSAO);

        for (int i = 0; i < TAMANHO_ENXAME; i++) {
            fitnessValorLista[i] = exame.get(i).getValorFitness();
        } // Atualizar lista de Fitness

        for (int i = 0; i < TAMANHO_ENXAME; i++) {
            pMelhor[i] = fitnessValorLista[i];
            pMelhorLocalizacao.add(exame.get(i).getLocation());
        }

        int t = 0;
        double w;
        double err = 9999;

        while (t < MAX_ITERACAO && err > SetProblema.ERR_TOLERANCE) {
            // step 1 - update pBest
            for (int i = 0; i < TAMANHO_ENXAME; i++) {
                if (fitnessValorLista[i] < pMelhor[i]) {
                    pMelhor[i] = fitnessValorLista[i];
                    pMelhorLocalizacao.set(i, exame.get(i).getLocation());
                }
            }

            // step 2 - update gBest
            int bestParticleIndex = getMinPos(fitnessValorLista);
            if (t == 0 || fitnessValorLista[bestParticleIndex] < gMelhor) {
                gMelhor = fitnessValorLista[bestParticleIndex];
                gMelhorLocalizacao = exame.get(bestParticleIndex).getLocation();
            }

            w = W_LIMITESUPERIOR - (((double) t) / MAX_ITERACAO) * (W_LIMITESUPERIOR - W_LIMITEINFERIOR);

            for (int i = 0; i < TAMANHO_ENXAME; i++) {
                double r1 = gerador.nextDouble();
                double r2 = gerador.nextDouble();

                Particula p = exame.get(i);

                // step 3 - update velocity
                double[] newVel = new double[DIMENSAO];
                newVel[0] = (w * p.getVelocidade().getPos()[0])
                        + (r1 * C1) * (pMelhorLocalizacao.get(i).getLoc()[0] - p.getLocation().getLoc()[0])
                        + (r2 * C2) * (gMelhorLocalizacao.getLoc()[0] - p.getLocation().getLoc()[0]);
                newVel[1] = (w * p.getVelocidade().getPos()[1])
                        + (r1 * C1) * (pMelhorLocalizacao.get(i).getLoc()[1] - p.getLocation().getLoc()[1])
                        + (r2 * C2) * (gMelhorLocalizacao.getLoc()[1] - p.getLocation().getLoc()[1]);
                Velocidade vel = new Velocidade(newVel);
                p.setVelocidade(vel);

                // step 4 - update location
                double[] newLoc = new double[DIMENSAO];
                newLoc[0] = p.getLocation().getLoc()[0] + newVel[0];
                newLoc[1] = p.getLocation().getLoc()[1] + newVel[1];
                Localizacao loc = new Localizacao(newLoc);
                p.setLocalizacao(loc);
            }

            err = SetProblema.evaluate(gMelhorLocalizacao) - 0; // minimizing the functions means it's getting closer to 0

            System.out.println("ITERATION " + t + ": ");
            System.out.println("     Best X: " + gMelhorLocalizacao.getLoc()[0]);
            System.out.println("     Best Y: " + gMelhorLocalizacao.getLoc()[1]);
            System.out.println("     Value: " + SetProblema.evaluate(gMelhorLocalizacao));

            t++;
            for (int i = 0; i < TAMANHO_ENXAME; i++) {
                fitnessValorLista[i] = exame.get(i).getValorFitness();
            } // Atualizar lista de Fitness
        }

        //System.out.println("\nSolution found at iteration " + (t - 1) + ", the solutions is:");
        setMelhorX(gMelhorLocalizacao.getLoc()[0]);
        setMelhorY(gMelhorLocalizacao.getLoc()[1]);
        System.out.println("     Best X: " + gMelhorLocalizacao.getLoc()[0]);
        System.out.println("     Best Y: " + gMelhorLocalizacao.getLoc()[1]);
    }

    public void setMelhorX(double melhorX)  throws RemoteException {
        MELHOR_X = melhorX;
    }

    public void setMelhorY(double melhorY)  throws RemoteException{
        MELHOR_Y = melhorY;
    }
    
    public double getMelhorX(){
        return MELHOR_X;
    }
    
    public double getMelhorY(){
        return MELHOR_Y;
    }

    public void iniciarEnxame(int TAMANHO_ENXAME, int DIMENSAO)  throws RemoteException{
        Particula p;
        for (int i = 0; i < TAMANHO_ENXAME; i++) {
            p = new Particula();

            // aleatorizar a localiza��o dentro de um espa�o definido no Conjunto de Problemas.
            double[] loc = new double[DIMENSAO];
            loc[0] = SetProblema.LOC_X_BAIXO + gerador.nextDouble() * (SetProblema.LOC_X_ALTO - SetProblema.LOC_X_BAIXO);
            loc[1] = SetProblema.LOC_Y_BAIXO + gerador.nextDouble() * (SetProblema.LOC_Y_ALTO - SetProblema.LOC_Y_BAIXO);
            Localizacao localizacao = new Localizacao(loc);

            // aleatorizar a velocidade no intervalo definido no Conjunto de Problemas
            double[] vel = new double[DIMENSAO];
            vel[0] = SetProblema.VEL_BAIXA + gerador.nextDouble() * (SetProblema.VEL_ALTA - SetProblema.VEL_BAIXA);
            vel[1] = SetProblema.VEL_BAIXA + gerador.nextDouble() * (SetProblema.VEL_ALTA - SetProblema.VEL_BAIXA);
            Velocidade velocidade = new Velocidade(vel);

            p.setLocalizacao(localizacao);
            p.setVelocidade(velocidade);
            exame.add(p);
        }
    }

    public int getMinPos(double[] lista)  throws RemoteException{
        int pos = 0;
        double minValor = lista[0];

        for (int i = 0; i < lista.length; i++) {
            if (lista[i] < minValor) {
                pos = i;
                minValor = lista[i];
            }
        }

        return pos;
    }
}
