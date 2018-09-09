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
public class Propiedad {
    
    private final String nombre;
    private final int precio;
    private final int valorHipoteca;
    private final int lote;
    private final int[] rentas;
    private final int renta;
    private boolean enVenta;
    private String propietario;
    
    public Propiedad(
            String nombre,int precio,int valorHipoteca,int lote,int[] rentas){
        this.nombre=nombre;
        this.precio=precio;
        this.valorHipoteca=valorHipoteca;
        this.lote=lote;
        this.rentas=rentas;
        this.renta=0;
        this.enVenta=true;
        this.propietario="";
    }
    
    public Propiedad(
            String nombre,int precio,int valorHipoteca,int lote,int renta){
        this.nombre=nombre;
        this.precio=precio;
        this.valorHipoteca=valorHipoteca;
        this.lote=lote;
        this.renta=renta;
        this.rentas=null;
        this.enVenta=true;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    public int getPrecio(){
        return this.precio;
    }
    public int getValorHipoteca(){
        return this.valorHipoteca;
    }
    public int getLote(){
        return this.lote;
    }
    public String getPropietario(){
        return this.propietario;
    }
    public int getRenta(){
        if (this.rentas==null){
            return this.renta;
        }
        else{
            return this.rentas[0];
        }
    }
    public boolean isEnVenta(){
        return this.enVenta;
    }
    public void setPropietario(String propietario){
        this.propietario=propietario;
    }
    public void setEnVenta(boolean enVenta){
        this.enVenta=enVenta;
    }
}
