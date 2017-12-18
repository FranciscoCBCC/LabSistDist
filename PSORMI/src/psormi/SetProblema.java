package psormi;
// Este � o problema a ser resolvido
// para encontrar um x e um y que minimizem a fun��o abaixo:
// f(x, y) = (2.8125 - x + x * y^4)^2 + (2.25 - x + x * y^2)^2 + (1.5 - x + x*y)^2
// Onde 1 <= x <= 4, and -1 <= y <= 1

// voc� pode modificar a fun��o depende das suas necessidades
// se o seu espa�o de problemas for maior do que o espa�o bidimensional
// voc� precisa introduzir uma nova vari�vel (diferente de x e y)

public class SetProblema {
	public static final double LOC_X_BAIXO = 1;
	public static final double LOC_X_ALTO = 4;
	public static final double LOC_Y_BAIXO = -1;
	public static final double LOC_Y_ALTO = 1;
	public static final double VEL_BAIXA = -1;
	public static final double VEL_ALTA = 1;
	
	public static final double ERR_TOLERANCE = 1E-20; // Quanto menor a toler�ncia, mais preciso o resultado,
													  // mas o n�mero de itera��es � aumentado
	
	public static double evaluate(Localizacao localizacao) {
		double resultado = 0;
		double x = localizacao.getLoc()[0]; // a parte "x" da localiza��o
		double y = localizacao.getLoc()[1]; // a parte "y" da localiza��o
		
		resultado = Math.pow(2.8125 - x + x * Math.pow(y, 4), 2) + 
				Math.pow(2.25 - x + x * Math.pow(y, 2), 2) + 
				Math.pow(1.5 - x + x * y, 2);
		
		return resultado;
	}
}