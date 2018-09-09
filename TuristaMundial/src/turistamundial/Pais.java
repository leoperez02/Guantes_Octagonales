/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package turistamundial;

/**
 *
 * @author Leo
 */
public class Pais extends Propiedad{
    
    private final int valorRestaurante;
    private boolean isFincado;
    private int numRestaurantes;
    private int numHoteles;
    
    public Pais(
            String nombre, int precio, int valorHipoteca,int lote,
            int valorRestaurante,int[] rentas){
        super(nombre,precio,valorHipoteca,lote,rentas);
        this.valorRestaurante=valorRestaurante;
        this.isFincado=false;
        this.numRestaurantes=0;
        this.numHoteles=0;
    }
    
    public int getValorRestaurante(){
        return this.valorRestaurante;
    }
    
    public boolean isFincado(){
        return this.isFincado;
    }
    
    public int getNumRestaurantes(){
        return this.numRestaurantes;
    }
    
    public int getNumHoteles(){
        return this.numHoteles;
    }
    
}
