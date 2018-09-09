class Main_DES
	{
		public static void main(String args[])
			{
				char menu = 'n' ; 
				while ( menu == 'n' || menu=='N')
					{
						String texto, clave;
						String[] claves = new String[16];
						
						Cifra_clave obj_Cifra_clave ;
						obj_Cifra_clave = new Cifra_clave();
					
						Cifra_texto obj_Cifra_texto ;
						obj_Cifra_texto = new Cifra_texto();
						System.out.println("Si el numero o clave que utilizaras tiene menos de 16 caracteres, completa con ceros antes del numero o clave\n");
						System.out.print("Escribe un numero en hexadecimal: ");
						texto = Leer.dato();
						while (texto.length() != 16)
							{
								System.out.print("\nEl numero en hexadecimal no tiene 16 caracteres, escribelo de nuevo: ");
								texto = Leer.dato();
							}
						System.out.print("\nEscribe la clave en hexadecimal: ");
						clave = Leer.dato(); 
						while (clave.length() != 16)
							{
								System.out.print("\nLa clave en hexadecimal no tiene 16 caracteres, escribela de nuevo: ");
								clave = Leer.dato();
							}
						System.out.print("\nDeseas cifrar  o descifrar el mensaje? (c/d): ");
						char opc = Leer.datoChar();
						while ( !(opc == 'c' || opc =='C') && !(opc == 'd' || opc =='D') )
							{
								System.out.print("\nLa opcion es incorrecta, escribela de nuevo (c/d): ");
								opc = Leer.datoChar();
							}
						if (opc == 'c')
							{
								claves = obj_Cifra_clave.recibe_clave(clave);
								obj_Cifra_texto.claves = claves ;
								obj_Cifra_texto.recibe_texto(texto);
							}
						else 
							{
								claves = obj_Cifra_clave.recibe_clave(clave);
								String[] claves_inv = new String[16];
								for (int i = 15 , a = 0 ; a < 16 ; i-- , a++)
									claves_inv[a] = claves[i];
								obj_Cifra_texto.claves = claves_inv ;
								obj_Cifra_texto.recibe_texto(texto);
							}
						System.out.print("\n\nDeseas salir del programa? (s/n): ");
						menu = Leer.datoChar(); 
					}
			}
	}