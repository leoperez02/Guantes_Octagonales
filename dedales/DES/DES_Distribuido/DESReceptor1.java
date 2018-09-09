import java.io.*;
import java.net.*;

class DESReceptor1
	{
		public static void main(String args[])
			{
				if (args.length != 2)
					System.out.println("Este programa requiere de 2 argumentos  de línea de mandato: Direccion IP, puerto receptor");
				else
					{
						int puerto = Integer.parseInt(args[1]);
						final int MAX_LON = 900;
						String[] claves_f = new String [16];
						try
							{
							DatagramSocket miSocket = new DatagramSocket(puerto);
							byte[] clavetext = new byte[MAX_LON];
							DatagramPacket datagrama = new DatagramPacket(clavetext, MAX_LON);
							miSocket.receive(datagrama);
							
							String claveytexto = new String(clavetext);
							String clave =  claveytexto.substring(0,16);
							String texto =  claveytexto.substring(16,32);
							String opc = claveytexto.substring(32,33);
							System.out.println("Datagrama: " + clave + "     "+texto);
							
						    Cifra_clave obj_Cifra_clave = new Cifra_clave();
							claves_f = obj_Cifra_clave.recibe_clave(clave);
							DESEmisor2 emisor2 = new DESEmisor2 ();
							
							if ("c".equals(opc))
								emisor2.envia(claves_f,texto,args);
							else
								{
									String[] claves_f_inv = new String[16];
									for (int i = 0 , a = 15 ; i < 16 ; i++ , a-- )
										claves_f_inv[i] = claves_f[a];
									emisor2.envia(claves_f_inv,texto,args);
								}
							miSocket.close();
							}
						catch (Exception ex)
							{
								ex.printStackTrace();
							}
					}
			}
	}