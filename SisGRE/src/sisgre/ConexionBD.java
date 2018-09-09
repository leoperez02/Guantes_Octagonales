/**
* Esta clase java ya no se utiliza
*
* La version actual del sistema generador de reportes
* NO incluye una base de datos para almacenar los correos
* y los reportes que se generan
* 
*/

package sisgre;

import java.sql.*;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;

public class ConexionBD{

    private Connection conexion;
    private Statement statement;

    public ConexionBD(String host,String bd,String user,String psw){

        conexion=null;
        host="jdbc:mysql://"+host+"/"+bd;

        try{
            Class.forName("com.mysql.jdbc.Driver");
            conexion=DriverManager.getConnection(host,user,psw);
            System.out.println(
                            "Conexión con la base de datos "+bd+" en "+host+
                            " creada correctamente");
        }catch(SQLException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void cerrarConexion(){
        try{
            conexion.close();
        }
        catch(SQLException sql){
            sql.printStackTrace();
        }
    }

    public ResultSet consulta(String query){

        ResultSet result=null;
        try{
                statement=conexion.createStatement();
                result=statement.executeQuery(query);			
        }
        catch(Exception e){
                e.printStackTrace();
        }
        return result;
    }
    
    public void finalizaConsulta(){
        try{statement.close();}catch(SQLException e){}
    }
   
    public boolean modifica(String query){
   	
   	boolean flag;
   	try{
            PreparedStatement pstmt=conexion.prepareStatement(query);
            pstmt.executeUpdate();
            pstmt.close();
            flag=true;
   	}
   	catch(Exception e){
            flag=false;
            e.printStackTrace();
        }
        return flag;
    }
    
    public boolean guardaCorreo(Correo c){
        String query="INSERT INTO ";
        if(c.isRecibido()) //Se carga en la tabla recibidos
            query+="recibidos(De,Asunto,Recibido_el,Folio) VALUES ";
        else  //se carga en la tabla enviados
            query+="enviados(Para,Asunto,Enviado_el,Folio) VALUES ";
        System.out.println(query+"("+c.toQuery()+")");
        return modifica(query+"("+c.toQuery()+")");
    }
   
    public void cargarHoja(List sheetData, int hoja){
        //Contador de correos;
        int correos_guardados=0;
        //Query example:
        // INSERT INTO enviados(Para,Asunto,Enviado_el) VALUES
        //('Pista chalma','TEST 2 FOLIO 345648','Mon Dec 19 00:00:00 CST 2016');
        String query="INSERT INTO ";
        if(hoja==0){
            //Se carga en la tabla recibidos
            query+="recibidos(De,Asunto,Recibido_el,Folio) VALUES ";
        }
        else{
            //se carga en la tabla enviados
             query+="enviados(Para,Asunto,Enviado_el,Folio) VALUES ";
        }
        //Recorrer el array
        String data="";
        for (int i = 0; i < sheetData.size(); i++) { 
            List list = (List) sheetData.get(i); //fila 1
            String fila="(";
            String folio="";
            for (int j = 0; j < list.size(); j++) { 
                Cell cell = (Cell) list.get(j); //cada celda
                try{
                    switch (j){
                        case 0: // de
                            fila += "'"+cell.getStringCellValue()+"',";
                            break;
                        case 1: //asunto
                            String asunto=cell.getStringCellValue();
                            folio=Buscador.buscaFolio(asunto);
                            fila += "'"+asunto+"',";    
                            break;
                        case 2: //fecha
                            java.util.Date f = cell.getDateCellValue();
                            fila+="'";
                            fila+=(f.getYear()+1900)+"/";
                            fila+=(f.getMonth()+1)+"/";
                            fila+=f.getDate()+"', '";
                            
                            break;
                        default:
                            break;
                    }
                }catch(IllegalStateException e){
                    int asunto=(int) cell.getNumericCellValue();
                    String asu = String.valueOf(asunto);
                    folio=Buscador.buscaFolio(asu);
                    fila += "'"+asu+"',";
                }
            }
            fila += folio+"');";
            //System.out.println(fila);
            //System.out.println(query+fila);
            if (modifica(query+fila)){
               correos_guardados++;
            } 
        }
        System.out.println(correos_guardados+" correos leídos correctamente.");
        
            
   }
   
    public static void main(String args[]){
    //java -cp :mysql-connector-java-5.1.15-bin.jar ConexionBD host bd usr psw  

        // MAIN TEST
////        String host=args[0];
////        String bd=args[1];
////        String user=args[2];
////        String psw=args[3];
        String host="localhost";
        String bd="album";
        String user="root";
        String psw="root";
        ConexionBD lain=new ConexionBD(host,bd,user,psw);

        ResultSet datos=lain.consulta("SHOW TABLES;");
        try{
            while(datos.next())
                System.out.println(datos.getString("Tables_in_album"));
            lain.finalizaConsulta();
        }
        catch(SQLException e){e.printStackTrace();}

        lain.cerrarConexion();
    }
}