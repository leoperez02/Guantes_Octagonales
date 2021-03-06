import java.net.*;
import java.io.*;

class DESEmisor1{
	
	public static void main(String[] args) {
	
		if (args.length !=5){
			System.out.println(
				"Este programa requiere de 5 argumentos de l�nea de mandato: Direccion IP, puerto receptor, clave de cifrado, numero en hexadecimal y opcion para descifrar o cifrar");
		}		
		else{
			try{
				//InetAddress --> getByName(String)
				InetAddress maquinaReceptora =InetAddress.getByName(args[0]);
				int puertoReceptor = Integer.parseInt(args[1]);
				String clave = args[2];
				String texto1 = args[3];
				String opc = args[4];
				String clavetext = clave + texto1 + opc;
				DatagramSocket  miSocket= new DatagramSocket ();
				
				byte[] almacen = clavetext.getBytes();
				DatagramPacket datagrama= new DatagramPacket(almacen, almacen.length, maquinaReceptora, puertoReceptor);
																		
				miSocket.send(datagrama);
				System.out.println("Enviado: " + datagrama);
				miSocket.close();
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}   
	}
}
