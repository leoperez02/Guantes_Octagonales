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
public class CorreoRecibido extends Correo{
    
    private final String De;
    
    public CorreoRecibido(String[] atrib){
        //String[] atrib = "De,Asunto,Fecha" ;
        super(atrib[1],atrib[2],true);
        this.De = atrib[0];
    }
    
    public CorreoRecibido(String De, String asunto, String fecha){
        super(asunto,fecha,true);
        this.De = De;
    }
    
    public String getDe(){
        return this.De;
    }
        
    @Override
    public String toQuery(){
        return "'"+this.De+"',"+super.toQuery();
    }
    
    @Override
    public String toLine(){
        return this.De+"\t"+super.toLine();
    }
}