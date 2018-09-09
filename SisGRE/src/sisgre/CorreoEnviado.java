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
public class CorreoEnviado extends Correo{

    private final String Para;
    private final String error;
    
    public CorreoEnviado(String[] atrib) {
        //String[] atrib = "Para,Asunto,Fecha" ;
        super(atrib[1],atrib[2],false);
        // Eliminar todas las comillas que pueda haber en la cadena
        this.Para = atrib[0].replaceAll("'", "");
        this.error=Buscador.buscaError(atrib[1]);
    }
    
    public CorreoEnviado(String Para, String asunto, String fecha){
        super(asunto, fecha,false);
        this.Para = Para;
        this.error=Buscador.buscaError(asunto);
    }
    
    public String getPara(){
        return this.Para;
    }
    
    public String getError(){
        return this.error;
    }
        
    @Override
    public String toQuery(){
        return "'"+this.Para+"',"+super.toQuery();
    }   
    
    @Override
    public String toLine(){
        return this.Para+"\t"+super.toLine()+"\t"+this.error;
    }
}