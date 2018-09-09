package sisgre;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Buscador{
    
    //Expresión regular de los errores: R1, R2, R3 ... R23
    private static final String  
        errorRegexp = "[Rr]2[0123]|[Rr]1[0-9]|[Rr][1-9]";
    
    //Expresión regular de los folios: 346678, 368189, 238093 ... etc
    private static final String
        folioRegexp = "[0-9]{5,7}";

    public static String buscaError(String asunto){
        
        String error;
        if ((error=buscar(asunto,errorRegexp))!=null)
            return error.toUpperCase();
        else
            return "Sin error";
    }
    
    public static String buscaFolio(String asunto){
        
        String folio;
        if ((folio=buscar(asunto,folioRegexp))!=null)
            return folio;
        else
            return "Sin folio";
    }
    
    private static String buscar(String texto, String regexp){
        
        //Generar patrón de búsqueda a partir de una expresión regular 
        Pattern p = Pattern.compile(regexp);
        //Buscar el patrón en el texto proporcionado
        Matcher matcher = p.matcher(texto);

        if(matcher.find()){
            //Se encuentró la expresión regular en el asunto, se quitan espacios
            return matcher.group().trim();
        }
        else{
            //No se encontró la expresión regular en el asunto
            return null;
        }
    }   
    
    public static void main(String args[]){

        //MAIN TEST
        
        //Prueba para buscar folio en asunto
        String folio;
        folio = Buscador.buscaFolio("364486 error al descargar");
        System.out.println(folio);
        folio = Buscador.buscaFolio("Folio 364513_ticketnoexiste");
        System.out.println(folio);
        folio = Buscador.buscaFolio("RE: FOLIO DEL TICKET 113739");
        System.out.println(folio);
        folio = Buscador.buscaFolio("364516");
        System.out.println(folio);
        folio = Buscador.buscaFolio("NO EXISTEN TICKETS");
        System.out.println(folio);
        
        //Prueba para buscar error en asunto
        String error;
        error = Buscador.buscaError("R14 error al descargar");
        System.out.println(error);
        error = Buscador.buscaError("Error_R15 ticketnoexiste");
        System.out.println(error);
        error = Buscador.buscaError("RE: FOLIO DEL TICKET R22");
        System.out.println(error);
        error = Buscador.buscaError("R14");
        System.out.println(error);
        error = Buscador.buscaError("RV: NO EXISTEN TICKETS");
        System.out.println(error);
    }
}