//Mauro Leonardo Pérez Bravo 5IM8 
class Al_Feistel 
	{
		public static void main (String args[]) 
			{
				char menu = 'n' ;
				int num = 0;
				int cla = 0;
				int cifrado = 0;
				int c_final = 0;
				char opc ;
				while ( menu == 'n' || menu == 'N' ) 
					{
						Operaciones objeto = new Operaciones();
						System.out.println("\nEste programa puede cifrar mediante el algoritmo Feistel numeros en el rango [0,100]. \nUsando claves en el rango [1,10].");
						System.out.print("\n\nEscriba el numero a cifrar o descifrar: ");
						num=Leer.datoInt();
						System.out.print("\nEscriba la clave a usar: ");
						cla = Leer.datoInt();
						System.out.print("\nElija si desea cifrar o descifrar (c/d) : ");
						opc = Leer.datoChar();
						if (opc == 'c' || opc =='C')
							{
								//cifrado
								for (int i = 0 ; i < 5 ; i++) 
									{
										objeto.And(num,cla);
										cla ++;
										objeto.Or(num,cla);
										cla++;
										objeto.Not(num);
									}
								cifrado = objeto.Nand(num,cla);
								c_final = objeto.Voltear(cifrado);
								System.out.println("\nResultado final: "+c_final+". En binario: "+Integer.toBinaryString(c_final));
							}
						else
							{
								if (opc == 'd' || opc == 'D')
									{
										cla = cla +10 ;
										int num_1 = objeto.Nand(num,cla);
										cla--;
										int tot_1 = 0;
										for (int i = 0; i <5 ; i++)
											{
												objeto.Not(num_1);
												objeto.Or(num,cla);
												cla --;
												tot_1 = objeto.And(num,cla);
												cla--;
											}
										c_final = objeto.Voltear(tot_1);
								System.out.println("\nResultado final: "+c_final+". En binario: "+Integer.toBinaryString(c_final));
									}
							}
						System.out.print("\nDesea salir? (S/N): ");
						menu = Leer.datoChar();
					}
			}
	}