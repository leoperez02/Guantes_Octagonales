import java.net.*;
import java.io.*;

class DESEmisor2 
	{
		void envia(String[] claves, String texto, String[] args) 
			{	
				try{
					InetAddress maquinaReceptora = InetAddress.getByName(args[0]);
					int puertoReceptor = Integer.parseInt(args[1]);
					DatagramSocket miSocket = new DatagramSocket ();
					
					String todo_junto = "";
					String claves_cadena = "";
					for (int i = 0 ; i <16 ; i++)
						claves_cadena += claves[i];
					todo_junto = claves_cadena + texto;
				
					byte[] almacen = todo_junto.getBytes();
					DatagramPacket datagrama= new DatagramPacket(almacen, almacen.length, maquinaReceptora, ++puertoReceptor);
					
					miSocket.send(datagrama);
					System.out.println("Enviado: " + datagrama);
				
					miSocket.close();
					}
				catch (Exception ex)
					{
						ex.printStackTrace();			
					}
			}  
	}