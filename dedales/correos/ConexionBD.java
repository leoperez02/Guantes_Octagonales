import java.sql.*;

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
		}
   	catch(SQLException e){
			e.printStackTrace();
   	}
   	catch(Exception e){
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
   
	public static void main(String args[]){
	//java -cp :mysql-connector-java-5.1.15-bin.jar ConexionBD host bd usr psw  
	
		// MAIN TEST
		String host=args[0];
		String bd=args[1];
		String user=args[2];
		String psw=args[3];
		ConexionBD lain=new ConexionBD(host,bd,user,psw);
		
		ResultSet datos=lain.consulta("SHOW TABLES;");
		try{
			while(datos.next())
				System.out.println(datos.getString("Tables_in_Reporte_correos"));
			lain.finalizaConsulta();
		}
		catch(SQLException e){e.printStackTrace();}
		
		lain.cerrarConexion();
	}
}
