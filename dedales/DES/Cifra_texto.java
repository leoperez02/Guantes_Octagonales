class Cifra_texto
	{
		String[] bloque = new String[64];
		String[] caracter_hex = new String[16];
		
		String cadena_binaria = "";
		String L_0 = "";
		String R_0 = "";
		String[] L_0_arr = new String[32];
		String[] R_0_arr = new String[32];
		
		Caja_IP obj_Caja_IP = new Caja_IP();
		Caja_E obj_Caja_E = new Caja_E();
		Caja_S obj_Caja_S = new Caja_S();
		Caja_P obj_Caja_P = new Caja_P();
		Caja_IP_inv obj_Caja_IP_inv = new Caja_IP_inv();
		Convierte_hex_bin obj_Convierte = new Convierte_hex_bin();
		String[] claves = new String [16];
					
		Cifra_texto(){}
		
		void recibe_texto (String texto)
			{
				for (int i = 0 , a = 1 ; i < texto.length() ; i++ , a++)
					caracter_hex[i] = texto.substring(i,a);
					
				cadena_binaria = obj_Convierte.convierte(caracter_hex);
				
				for (int i = 0 , a = 1 ; i < cadena_binaria.length() ; i++ , a++)
					bloque[i] = cadena_binaria.substring(i,a); 
				
				int[] arr_bloque_permutado = obj_Caja_IP.permuta_bloque(bloque);
				
				for (int i = 0 , j = 32 ; i < 32 ; i++ , j++)
					{
						L_0 += arr_bloque_permutado[i];
						R_0 += arr_bloque_permutado[j];
						L_0_arr[i]=String.valueOf(arr_bloque_permutado[i]);
						R_0_arr[i]=String.valueOf(arr_bloque_permutado[j]);
					}
				System.out.println("");
				System.out.println("");
				System.out.println("Esta es la cadena L(0): "+L_0);
				System.out.println("Esta es la cadena R(0): "+R_0);
				
				int[] E_R_i = new int[48];
				
				//16 rondas de feistel XD
				String[] res_xor_k = new String[48];
				String s_permutado_cadena = "";
				String[] s_permutado = new String[32];
				int[] p_sb = new int[32];
				int numero_clave = 0;
				for (; numero_clave < 16 ; )
					{
						E_R_i = obj_Caja_E.expansion_E(R_0_arr);
						//-----------	
							System.out.println("");
							System.out.println("Expansion E de R("+(numero_clave+1)+"): ");
							for(int j = 0 ; j < 48 ; j++)
								System.out.print(E_R_i[j]);
						//---------------
						res_xor_k = Xor_Ki(E_R_i,numero_clave,claves);
						
						s_permutado_cadena = obj_Caja_S.permuta_S(res_xor_k);
						//--------------
							System.out.println("");
							System.out.println("permutacuion s del xor("+(numero_clave+1)+"): ");
							String blabla = "";
							System.out.println("");		
						//-----------------------------	
						for (int i = 0 , a = 1 ; i < 32 ; i++ , a++)
							s_permutado[i] = s_permutado_cadena.substring(i,a);
						
						p_sb = obj_Caja_P.permutacion_P(s_permutado);
						R_0_arr = Xor_L_0(p_sb,L_0_arr,numero_clave);
						numero_clave++;
					}
				//---------------------fin de rondas => r(16) y l(16)
				//concaternar R_0_arr[] con L_0_arr[]
				String[] bloque_final_64 = new String[64];
				for (int i = 0 ; i < 32 ;i++)
					bloque_final_64[i] = R_0_arr[i];
				for (int i = 32 , a = 0 ; i < 64 ; i++ , a++)
					bloque_final_64[i] = L_0_arr[a];
				//------------------------------------
				int[] ultimo_algo = new int[64];
				ultimo_algo = obj_Caja_IP_inv.permuta_LR(bloque_final_64);	
				//	
				System.out.println("");
				System.out.println("Cifrado binario ");
				System.out.println("");
				for (int i = 0; i<64 ; i++)
					System.out.print(ultimo_algo[i]);
				System.out.println("");
				System.out.println("Cifrado en hexadecimal: ");
				String hex_final = "";
				hex_final = obj_Convierte.convierte_bin_hex(ultimo_algo);
				
				System.out.println(hex_final);	
			}
		String[] Xor_Ki(int[] E_R_i, int num_clave, String[] claves)
			{
				String[] res_k = new String[48];
				String clave = "";
				clave = claves[num_clave];
				String cadena_ER_i = "";
				
				for (int i = 0 ; i < 48 ; i++)
					cadena_ER_i += String.valueOf(E_R_i[i]);
				
				System.out.println("");
				
				long xor = 0;
				//------------NumberFormatException :'(  ..... demasiados ceros y unos XD
				try 
					{
						xor = Long.parseLong(cadena_ER_i,2) ^ Long.parseLong(clave,2);
					}
				catch (NumberFormatException e){}
				
				System.out.println("");
				System.out.println("");
				System.out.println("Resultado de xor con k"+(num_clave + 1)+": "+Long.toBinaryString(xor));

				//agregando los ceros que se pierden en el xor (long ) :S
				String xor_cadena = "";
				xor_cadena = Long.toBinaryString(xor);
				if ( xor_cadena.length() == 48 )
					for (int i = 0 ,a = 1 ; i < 48 ; i++ , a++)
						res_k[i] = (Long.toBinaryString(xor).substring(i,a));
				else
					{
						int diferencia = 48 - xor_cadena.length() ; 
						String cero = "0";
						String faltante = "";
						for (int i = 0 ; i < diferencia ; i++)
							faltante += cero ;
						String xor_final = faltante + Long.toBinaryString(xor) ;
						for (int i = 0 ,a = 1 ; i < 48 ; i++ , a++)
							res_k[i] = (xor_final.substring(i,a));
					}
				//------------------------------------
				return res_k;
			}
		String[] Xor_L_0(int[] p_sb, String[] L_0_arr_1 , int ronda)
			{
				String[] R_arr_nueva = new String[32];
				String cadena1 = "";
				String cadena2 = "";
				long res_xor = 0;
				L_0_arr = R_0_arr;
				
				System.out.println("L("+(ronda+1)+"): ");
				for (int i = 0 ; i < 32 ; i++)
					System.out.print(L_0_arr[i]);
				
				for (int i = 0 ; i < 32 ; i++)
					cadena1 += String.valueOf(p_sb[i]);
					
				for (int i = 0 ; i < 32 ; i++)
					cadena2 += L_0_arr_1[i];
				//------------NumberFormatException :'(  ..... demasiados ceros y unos XD
				try
					{
						res_xor = Long.parseLong(cadena2,2) ^ Long.parseLong(cadena1,2) ;
					}
				catch (NumberFormatException e) {}
				//------------------------------------------------
				//agregando los ceros que se pierden en el xor (long ) :S
				System.out.println("");				
				String xor_cadena = "";
				xor_cadena = Long.toBinaryString(res_xor);
				if ( xor_cadena.length() == 32 )
					{
						for (int i = 0 ,a = 1 ; i < 32 ; i++ , a++)
							R_arr_nueva[i] = (Long.toBinaryString(res_xor).substring(i,a));	
						System.out.println("R("+(ronda+1)+"): "+'\n'+Long.toBinaryString(res_xor));
					}
				else
					{
						int diferencia = 32 - xor_cadena.length() ; 
						String cero = "0";
						String faltante = "";
						for (int i = 0 ; i < diferencia ; i++)
							faltante += cero ;
						String xor_final = faltante + Long.toBinaryString(res_xor) ;
						for (int i = 0 ,a = 1 ; i < 32 ; i++ , a++)
							R_arr_nueva[i] = (xor_final.substring(i,a));
						System.out.println("R("+(ronda+1)+"): "+'\n'+xor_final);
					}
				//------------------------------------
				return R_arr_nueva;
			}
	}