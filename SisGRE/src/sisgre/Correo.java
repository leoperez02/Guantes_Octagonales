/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sisgre;

/**
 *
 * @author Leo
 */
public class Correo {
    
    private final String asunto;
    private final String fecha;
    private final String folio;
    private final boolean isRecibido;
    
    public Correo(String[] atrib, boolean isRecibido){
        //String[] atrib = "Asunto,Fecha" ;
        this.asunto = atrib[0];
        this.fecha = atrib[1].trim();
        this.folio = Buscador.buscaFolio(this.asunto);
        this.isRecibido = isRecibido;
    }
    
    public Correo(String asunto, String fecha, boolean isRecibido){
        this.asunto = asunto;
        this.fecha = fecha.trim();
        this.folio = Buscador.buscaFolio(this.asunto);
        this.isRecibido = isRecibido;
    }
    
    public String getAsunto(){
        return this.asunto;
    }
    
    public String getFecha(){
        return this.fecha;
    }
     
    public String getFolio(){
        return this.folio;
    }
    
    public String toQuery(){
        return "'"+this.asunto+"','"+formatea(this.fecha)+"','"+this.folio+"'";
    }
    
    public String toLine(){
        return this.asunto+"\t"+this.fecha+"\t"+this.folio;
    }
    
    public boolean isRecibido(){
        return isRecibido;
    }
    
    public boolean isEnviado(){
        return !isRecibido;
    }
    
    private String formatea(String f){
        //Convertir esto "29/12/2017" en esto "2017/12/29"
        try{
            return f.substring(6)+f.substring(2,6)+f.substring(0,2);
        }
        catch(Exception e){
            return "0000/00/00";
        }
    }
}