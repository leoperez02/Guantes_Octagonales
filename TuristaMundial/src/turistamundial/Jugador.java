/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package turistamundial;

import java.util.ArrayList;

/**
 *
 * @author Leo
 */
public class Jugador {
    
    private final String nombre;
    private int capital;
    private final ArrayList<Propiedad> propiedades;
    private int ubicacion;
    private boolean quebrado;
    private int turno;
    
    //constructor de la clase
    public Jugador(String nombre,int capital){
        this.nombre=nombre;
        this.capital=capital;
        this.propiedades=new ArrayList<>();
        this.ubicacion=0;
        this.quebrado=false;
        this.turno=0;
    }
    
    //Métodos de la clase
    public void avanzar(int dados){
       this.ubicacion+=dados;
       this.ubicacion%=Lotes.CASILLAS;
    }
    public void cobrar(Jugador player1, int monto){
        this.capital+=monto;
        if(player1!=Lotes.BANCO)
            player1.pagar(monto);
    }
    public void compraPropiedad(Propiedad propiedad){
        this.capital-=propiedad.getPrecio();
        propiedad.setPropietario(this.nombre);
        propiedad.setEnVenta(false);
        this.propiedades.add(propiedad);
    }
    public int getCapital(){
        return this.capital;
    }
    public String getNombre(){
        return this.nombre;
    }
    public ArrayList<Propiedad> getPropiedades(){
        return this.propiedades;
    }
    public int getUbicacion(){
        return this.ubicacion;
    }
    public int getTurno(){
        return this.turno;
    }    
    public boolean isQuebrado(){
        return this.quebrado;
    }
    public void pagar(int monto){
        this.capital-=monto;
    }
    public boolean quiereComprar(Propiedad p){
        //70% de probabilidad de que el jugador compre una propiedad en venta
        int x=(int)(1+Math.random()*10);
        if (x>0 && x<8)
            return true;
        else
            return false;
    }
    public void setQuebrado(boolean quebrado){
        this.quebrado=quebrado;
    }
    public void setTurno(int turno){
        this.turno=turno;
    }
    public int tiraDados(int numDados){
        if(numDados==1){
            return (int)(1+Math.random()*6);
        }
        else{
            return (int)(1+Math.random()*6)+(int)(1+Math.random()*6);
        }
        
    }
}
