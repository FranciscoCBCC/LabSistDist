package labsistdist;

// Este � o problema a ser resolvido
// para encontrar um x e um y que minimizem a fun��o abaixo:
// f(x, y) = (2.8125 - x + x * y^4)^2 + (2.25 - x + x * y^2)^2 + (1.5 - x + x*y)^2
// Onde 1 <= x <= 4, and -1 <= y <= 1

// voc� pode modificar a fun��o depende das suas necessidades
// se o seu espa�o de problemas for maior do que o espa�o bidimensional
// voc� precisa introduzir uma nova vari�vel (diferente de x e y)

public class SetProblema {
	/*public static final double LOC_X_BAIXO = 1;
	public static final double LOC_X_ALTO = 4;
	public static final double LOC_Y_BAIXO = -1;
	public static final double LOC_Y_ALTO = 1;
	public static final double VEL_BAIXA = -1;
	public static final double VEL_ALTA = 1;
*/
        private double LOC_X_BAIXO;
	private double LOC_X_ALTO;
	private double LOC_Y_BAIXO;
	private double LOC_Y_ALTO;
	private double VEL_BAIXA;
	private double VEL_ALTA;
	
	private double ERR_TOLERANCE; // Quanto menor a toler�ncia, mais preciso o resultado,
													  // mas o n�mero de itera��es � aumentado

        public double getLOC_X_BAIXO() {
            return LOC_X_BAIXO;
        }

        public void setLOC_X_BAIXO(double LOC_X_BAIXO) {
            this.LOC_X_BAIXO = LOC_X_BAIXO;
        }

        public double getLOC_X_ALTO() {
            return LOC_X_ALTO;
        }

        public void setLOC_X_ALTO(double LOC_X_ALTO) {
            this.LOC_X_ALTO = LOC_X_ALTO;
        }

        public double getLOC_Y_BAIXO() {
            return LOC_Y_BAIXO;
        }

        public void setLOC_Y_BAIXO(double LOC_Y_BAIXO) {
            this.LOC_Y_BAIXO = LOC_Y_BAIXO;
        }

        public double getLOC_Y_ALTO() {
            return LOC_Y_ALTO;
        }

        public void setLOC_Y_ALTO(double LOC_Y_ALTO) {
            this.LOC_Y_ALTO = LOC_Y_ALTO;
        }

        public double getVEL_BAIXA() {
            return VEL_BAIXA;
        }

        public void setVEL_BAIXA(double VEL_BAIXA) {
            this.VEL_BAIXA = VEL_BAIXA;
        }

        public double getVEL_ALTA() {
            return VEL_ALTA;
        }

        public void setVEL_ALTA(double VEL_ALTA) {
            this.VEL_ALTA = VEL_ALTA;
        }

        public double getERR_TOLERANCE() {
            return ERR_TOLERANCE;
        }

        public void setERR_TOLERANCE(double ERR_TOLERANCE) {
            this.ERR_TOLERANCE = ERR_TOLERANCE;
        }
	
	public double evaluate(Localizacao localizacao) {
		double resultado = 0;
		double x = localizacao.getLoc()[0]; // a parte "x" da localiza��o
		double y = localizacao.getLoc()[1]; // a parte "y" da localiza��o
		
		resultado = Math.pow(2.8125 - x + x * Math.pow(y, 4), 2) + 
				Math.pow(2.25 - x + x * Math.pow(y, 2), 2) + 
				Math.pow(1.5 - x + x * y, 2);
		
		return resultado;
	}
}