import java.sql.*;
public class Editor{
	
	public static void main(String args[]){
		
		String host="localhost:3306";
		String bd="Reporte_correos";
		String user="root";
		String psw=args[0];
		ConexionBD lain=new ConexionBD(host,bd,user,psw);
		
		String regexp="[1234567890]{6,6}";
		String caso1="^[1234567890]{6,6}[^1234567890].*$";
		String caso2="^.*[^1234567890][1234567890]{6,6}$";
		String caso3="^.*[^1234567890][1234567890]{6,6}[^1234567890].*$";
		regexp+="|"+caso1+"|"+caso2+"|"+caso3;
		
		String alt=caso2+"|"+caso3;
		
		String campos="idEnviado,asunto";
		String tabla="correoEnviado";
		String query;
		query="SELECT "+campos+" FROM "+tabla+" WHERE asunto regexp '"+alt+"'";
		
		ResultSet datos=lain.consulta(query);
		int c=0;
		int id;
		String folio;
		String asunto;
		String asunto_nuevo;
		String ex_query;
		try{
			while(datos.next()){
				id=datos.getInt("idEnviado");
				asunto=datos.getString("asunto");
				folio=Buscador.buscaFolio(asunto);
				asunto_nuevo=folio+" | "+asunto;
				System.out.print(id+"	");
				System.out.println(asunto_nuevo);
				
				ex_query="UPDATE "+tabla+" SET asunto='"+
					asunto_nuevo+"' WHERE idEnviado="+id;
				
				if(lain.modifica(ex_query))
					c++;
			}
			lain.finalizaConsulta();
		}
		catch(SQLException e){e.printStackTrace();}
		System.out.println(c);
		lain.cerrarConexion();
	}
}
