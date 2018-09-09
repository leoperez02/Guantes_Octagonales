import java.io.*;
import java.net.*;

class DESReceptor2
	{
		public static void main(String args[])
			{
				if (args.length != 1)
					System.out.println("Introduce el puerto");	
				else
					{
						int puerto = Integer.parseInt(args[0]);
						final int MAX_LON = 900;
						String[] claves_f = new String [16];
						String textofin = "";
						try
							{
								DatagramSocket miSocket = new DatagramSocket(puerto);
								byte[] clavetext = new byte[MAX_LON];
								DatagramPacket datagrama = new DatagramPacket(clavetext, MAX_LON);
								miSocket.receive(datagrama);
								
								String clavesytexto = new String(clavetext);
								System.out.println ("Datagrama: " + clavesytexto);
								
								claves_f[0] = clavesytexto.substring(0,48);
								claves_f[1] = clavesytexto.substring(48,96);
								claves_f[2] = clavesytexto.substring(96,144);
								claves_f[3] = clavesytexto.substring(144,192);
								claves_f[4] = clavesytexto.substring(192,240);
								claves_f[5] = clavesytexto.substring(240,288);
								claves_f[6] = clavesytexto.substring(288,336);
								claves_f[7] = clavesytexto.substring(336,384);
								claves_f[8] = clavesytexto.substring(384,432);
								claves_f[9] = clavesytexto.substring(432,480);
								claves_f[10] = clavesytexto.substring(480,528);
								claves_f[11] = clavesytexto.substring(528,576);
								claves_f[12] = clavesytexto.substring(576,624);
								claves_f[13] = clavesytexto.substring(624,672);
								claves_f[14] = clavesytexto.substring(672,720);
								claves_f[15] = clavesytexto.substring(720,768);
								textofin = clavesytexto.substring(768,784);
								//-----------------------
								System.out.println("shalala "+ textofin);
								System.out.println("");
								System.out.println("claves: ");
								System.out.println("");
								for (int i = 0 ; i < 16 ; i++)
									System.out.println(claves_f[i]);
								//---------------------------
								Cifra_texto obj_Cifra_texto = new Cifra_texto();
								
								obj_Cifra_texto.claves = claves_f;
								obj_Cifra_texto.recibe_texto(textofin);
		
								miSocket.close();	
							}
						catch (Exception ex)
							{
								ex.printStackTrace();
							}
					}
			}
	}