/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sisgre;

import java.io.BufferedReader;
import java.io.FileReader; 
import java.io.IOException;
import java.util.ArrayList;
import java.util.List; 

/**
 *
 * @author Leo
 */
public class SisGRE implements Runnable{

    
    private static boolean isFinish = false;
    private static String estado = "";
    private static Thread hilo;
    private static String fileRecibidos;
    private static String fileEnviados;
    private static String fileReporte;
    
    public void run(){
        generaReporte();
    }
    
    public static boolean isFinish(){
        return isFinish;
    }
    
    public static String getEstado(){
        return estado;
    }
    
    public static void setData(String fileR, String fileE, String fileRep){
        //Variables globales para archivos de trabajo
        fileRecibidos = fileR;
        fileEnviados = fileE;
        fileReporte = fileRep+".xlsx";
        System.out.println("Correos recibidos: "+fileRecibidos);
        System.out.println("Correos enviados: "+fileEnviados);
        System.out.println("Archivo de salida: "+fileReporte);
        hilo = new Thread(new SisGRE());
    }
    
    public static void start(){
        hilo.start();
    }
    
    private static int empataCorreos(List recibidos, List enviados){
        
        int count=0;
        CorreoEnviado enviado;
        CorreoRecibido recibido;
        String folio;
        List empates = new ArrayList();
        for(int i = 0 ; i<enviados.size() ; i++){
            enviado = (CorreoEnviado)enviados.get(i);
            folio = enviado.getFolio();
            if(!folio.equals("Sin folio")){
                //Buscar correo con el mismo folio en la otra lista
                for(int j=0 ; j<recibidos.size() ; j++){
                    recibido = (CorreoRecibido)recibidos.get(j);
                    if(folio.equals(recibido.getFolio())){
                        empates.add(recibido.toLine()+"\t0\t"+enviado.toLine());
                        enviados.remove(enviado);
                        recibidos.remove(recibido);
                        i--;
                        count++;
                        break;
                    }
                }
            }
        }
        
        // Empatando por nombres 
        String nombre;
        for(int i = 0 ; i<enviados.size() ; i++){
            enviado = (CorreoEnviado)enviados.get(i);
            nombre = enviado.getPara();
            // 
            // Si el correo enviado tiene mas de un destinatario, se separan
            // con ; y se compara el primer destinatario solamente
            if (nombre.contains(";")){
                nombre= nombre.split(";")[0];
            }
            //
            if(!nombre.equals("")){
                //Buscar correo con el mismo destinatario/remitente en la otra lista
                for(int j=0 ; j<recibidos.size() ; j++){
                    recibido = (CorreoRecibido)recibidos.get(j);
                    if(nombre.trim().equals(recibido.getDe().trim())){
                        empates.add(recibido.toLine()+"\t0\t"+enviado.toLine());
                        enviados.remove(enviado);
                        recibidos.remove(recibido);
                        i--;
                        count++;
                        break;
                    }
                }
            }
        }
        
        
        
        //Escribe los correos empatados en una nueva hoja de excel y la nombra
        JavaExcel.escribeHoja(fileReporte,recibidos,"Recibidos faltantes");
        JavaExcel.escribeHoja(fileReporte,enviados,"Enviados faltantes");
        JavaExcel.escribeHoja(fileReporte,empates,"x Tmpo. Resp.");
        return count;
    }
    
     public static void generaReporte(){
        
        estado = "Iniciando reporte ...";
        
        //Variables para leer correos
        BufferedReader data;
        String linea;
        String[] atrib;
        Correo correo;
        List recibidosList = new ArrayList();
        List enviadosList = new ArrayList();
        
        //1. Leer archivo de correos recibidos
        estado = "Leyendo correos recibidos";
        try{
            data = new BufferedReader(new FileReader(fileRecibidos));
            while((linea=data.readLine())!=null){
                atrib = linea.split("\t");
                correo = new CorreoRecibido(atrib);
                recibidosList.add(correo);
                //conexion.guardaCorreo(correo);
                System.out.println(correo.toLine());
            }
            data.close();
        }
        catch(IOException e){
            estado = "Error leyendo correos recibidos";
        }
        estado="Correos recibidos leídos y guardados";
        
        //2. Leer archivo de correos enviados
        estado = "Leyendo correos enviados";
        try{
            data = new BufferedReader(new FileReader(fileEnviados));
            while((linea=data.readLine())!=null){
                atrib = linea.split("\t");
                correo = new CorreoEnviado(atrib);
                //conexion.guardaCorreo(correo);
                enviadosList.add(correo);
                System.out.println(correo.toLine());
            }
            data.close();
        }
        catch(IOException E){
            estado = "Error leyendo correos enviados";
        }
        estado = "Correos enviados leídos y guardados";
       
        //3. Relacionar correos recibidos contra enviados por folio
        /*
        *   1. Tomar el primer correo recibido.
        *   2. Verificar que tenga folio, si no tiene se omite
            3. Buscar el folio del correo en la tabla de enviados
                caso 1: no hay resultados:
                    pendiente de procesar
                
                caso 2: hay una coincidencia: se empata
                    3.2.1. Tomar el correo recibido y ponerlo en la tabla 
                            Relacionados con el correo enviado
                    3.2.2. Borrar el correo recibido de la tabla recibidos
                    3.2.3. Borrar el correo enviado de la tabla enviados
                
                caso 3: hay más de un resultado:
        *           pendiente de pensar
        */
        
        estado="Empatando correos recibidos contra enviados por folio";
        int emparejados = empataCorreos(recibidosList,enviadosList);
        
        //Reporte del sistema
        System.out.println("Reporte del sistema: ");
        System.out.println("Correos recibidos emparejados por folio: "+emparejados);
       
        estado="Empatando correos recibidos contra enviados por remitente";
        
        
//        //Seleccionar correos recibidos sin folio
//        query="select * from recibidos where Folio='Sin folio';";
//        recibidos=conexion.consulta(query);
//        recibidos_folio=0;
//        //Consulta para buscar el par
//        
//        emparejados=0;
//       
//        //Buscar
//        try{
//            while(recibidos.next()){
//                idRecibido=recibidos.getInt("idRecibido");
//                De=recibidos.getString("De");
//                AsuntoRecibido=recibidos.getString("Asunto");
//                Recibido_el=recibidos.getString("Recibido_el");
//                FolioRecibido=recibidos.getString("Folio");
//                
//                ex_query ="Select * from enviados where Para='";
//                ex_query += De+"';";
//                //System.out.println(ex_query);
//                //Buscar en enviados
//                ResultSet respuestas=conexion.consulta(ex_query);
//                
//                if(respuestas.next()){
//                    //Emparejar correos en relación
//                    idEnviado=respuestas.getInt("idEnviado");
//                    Para=respuestas.getString("Para");
//                    AsuntoEnviado=respuestas.getString("Asunto");
//                    Enviado_el=respuestas.getString("Enviado_el");
//                    FolioEnviado=respuestas.getString("Folio");
//                    
//                    //System.out.println("Par hecho");
//                    //Insertar relacion
//                    //Query para relacionar correos
//                    String matchQuery="INSERT INTO relacion(";
//                    matchQuery+="idRecibido,De,AsuntoRecibido,Recibido_el,FolioRecibido,";
//                    matchQuery+="idEnviado,Para,AsuntoEnviado,Enviado_el,FolioEnviado) ";
//                    matchQuery+="VALUES ";
//                    matchQuery+=
//                    "("+idRecibido+",'"+De+"','"+AsuntoRecibido+"','"+Recibido_el+"','"+FolioRecibido+"',";
//                    matchQuery+=
//                    idEnviado+",'"+Para+"','"+AsuntoEnviado+"','"+Enviado_el+"','"+FolioEnviado+"');";
//                    //Ejecutar matchQuery   
//                    //System.out.println(matchQuery);
//                    conexion.modifica(matchQuery);
//                    //Borrando correos emparejados de enviados y recibidos
//                    conexion.modifica("delete from recibidos where idRecibido="+idRecibido+";");
//                    conexion.modifica("delete from enviados where idEnviado="+idEnviado+";");
//                
//                    emparejados++;
//                }
//               recibidos_folio++; 
//            }
//            conexion.finalizaConsulta();
//        }
//        catch(SQLException e){e.printStackTrace();}
//        
//        
//        
//        //Reporte del sistema
//        System.out.println("Correos recibidos sin folio: "+recibidos_folio);
//        System.out.println("Correos recibidos emparejados por remitente: "+emparejados);
//        
//        estado="Escribiendo reporte en:"+fileReporte;
//        
//        
//        int hoja=2; //x Tmpo Res
//        //JavaExcel.escribirLibro(fileReporte,conexion,hoja);
//        //JavaExcel.escribirLibro2(fileReporte, conexion);
//        //JavaExcel.escribirLibro3(fileReporte, conexion); 
//        
//        conexion.modifica("truncate table recibidos;");
//        conexion.modifica("truncate table enviados;");
//        conexion.modifica("truncate table relacion;");
//        conexion.cerrarConexion();
        isFinish = true;
    }
   
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        /*
        *   0. Crear una conexión con la BD reporte_correos.
        *
        *   1. Leer archivo de correos recibidos separado por tabulaciones(.txt)
        *       1.1 Crear un objeto CorreoRecibido por cada línea.
        *           1.1.1 Insertar cada línea(registro) en la BD.
        *
        *   2. Leer archivo de correos enviados separado por tabulaciones(.txt)
        *       2.1 Crear un objeto CorreoEnviado por cada línea.
        *           2.1.1 Insertar cada línea(registro) en la BD.
        *
        *   3. Relacionar recibidos contra enviados buscando por folio.
        *       3.1 Tomar el primer correo recibido.
        *       3.2 Verificar que tenga folio, si no tiene se omite
        *       3.3 Buscar el folio del correo en la tabla de enviados
        *            caso 1: no hay resultados:
        *                pendiente de procesar
        *        
        *            caso 2: hay una coincidencia: se empata
        *                3.2.1. Tomar el correo recibido y ponerlo en la tabla 
        *                        Relacionados con el correo enviado
        *                3.2.2. Borrar el correo recibido de la tabla recibidos
        *                3.2.3. Borrar el correo enviado de la tabla enviados
        *        
        *            caso 3: hay más de un resultado:
        *               pendiente de pensar
        *
        *   4. Relacionar recibidos contra enviados buscando por Para/De.
        */
        //Iniciar interfaz
        SisGREJFrame.creaVentana();
    }
}