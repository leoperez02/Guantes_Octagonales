package lecturaserial;

public class Sensor{

    private int R,Rf,Ra,n_bits;
    private double E,y2,y1,x2,x1,m,b,E1,Av,v_rango,resolucion; 

    public Sensor(){

        //Punto 1 -> P1(10k, 0)
        y1 = 10000;
        x1 = 0;
        //Punto 2 -> P2(1,3.9)
        y2 = 1;
        x2 = 3.9;
        m = (double)(y2-y1) / (double)(x2-x1);
        b = y1 - m*x1;
        // Fuente de voltaje
        E = 5.1 ;
        //Resistencia del puente de weastone
        R = 10000;
        Rf = 33000;
        Ra = 82000;
        n_bits = 8 ;
        E1 = (double)E / (double)2;
        Av = (double)Rf / (double)Ra ;
        v_rango = E1 * Av ;
        resolucion = v_rango / (exp(2,n_bits) - 1) ;
    }
	
    public double calculaDistancia(String bits){

            int decimal = (int) Integer.parseInt(bits,2);
            //System.out.println(decimal);
            double Va_rec = decimal * resolucion ;
            //System.out.println(Va_rec);
            double E1_E2 = Va_rec / Av ;
            //System.out.println(E1_E2);
            double E2 = E1 - E1_E2 ;
            //System.out.println(E2);
            double delta_R = R * (E - 2*E2)/(E-E2) ;
            //System.out.println(delta_R);
            double Rsen = R - delta_R ;
            //System.out.println(Rsen);
            double distancia = (Rsen - b) / m ;
            //System.out.println(distancia);
            return distancia;
    }

    public int exp(int base,int potencia){
            int res = 1;
            for(int i=0;i<potencia;i++){
                    res *= base;
            }
            return res;
    }

    public String formateaDouble(double distancia, int decimales){

        return null;
    }

    public static void main(String args[]){
            Sensor obj1 = new Sensor();
            System.out.println(obj1.calculaDistancia("00000001"));
            System.out.println(obj1.calculaDistancia("10000000"));
            //System.out.println(obj1.calculaDistancia(""));
            System.out.println(obj1.calculaDistancia("11111111"));
    }
}