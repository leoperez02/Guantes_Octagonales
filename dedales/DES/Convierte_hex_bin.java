class Convierte_hex_bin
	{
		Convierte_hex_bin() {		}
		String cadena_binaria = "";
		
		String convierte(String[] caracter_hex)
			{
				for (int i = 0 ; i < caracter_hex.length ; i++)
					{	
						if (caracter_hex[i].equals("0"))
							cadena_binaria += "0000" ;
						if (caracter_hex[i].equals("1"))                 
							cadena_binaria = cadena_binaria += "0001" ;
						if (caracter_hex[i].equals("2"))                 
							cadena_binaria = cadena_binaria += "0010" ;
						if (caracter_hex[i].equals("3"))                 
							cadena_binaria = cadena_binaria += "0011" ;
						if (caracter_hex[i].equals("4"))                 
							cadena_binaria = cadena_binaria += "0100" ;
						if (caracter_hex[i].equals("5"))                 
							cadena_binaria = cadena_binaria += "0101" ;
						if (caracter_hex[i].equals("6"))                 
							cadena_binaria = cadena_binaria += "0110" ;
						if (caracter_hex[i].equals("7"))                 
							cadena_binaria = cadena_binaria += "0111" ;
						if (caracter_hex[i].equals("8"))                 
							cadena_binaria = cadena_binaria += "1000" ;
						if (caracter_hex[i].equals("9"))                 
							cadena_binaria = cadena_binaria += "1001" ;
						if (caracter_hex[i].equals("a") || caracter_hex[i].equals("A"))                 
							cadena_binaria = cadena_binaria += "1010" ;
						if (caracter_hex[i].equals("b")|| caracter_hex[i].equals("B"))                 
							cadena_binaria = cadena_binaria += "1011" ;
						if (caracter_hex[i].equals("c")|| caracter_hex[i].equals("C"))                 
							cadena_binaria = cadena_binaria += "1100" ;
						if (caracter_hex[i].equals("d")|| caracter_hex[i].equals("D"))                 
							cadena_binaria = cadena_binaria += "1101" ;
						if (caracter_hex[i].equals("e")|| caracter_hex[i].equals("E"))                 
							cadena_binaria = cadena_binaria += "1110" ;
						if (caracter_hex[i].equals("f")|| caracter_hex[i].equals("F"))                 
							cadena_binaria = cadena_binaria += "1111" ;			
					}
				return cadena_binaria;
			}
		String convierte_bin_hex(int[] algo)
			{
				String cadena_final_hexa= "";
				String cadena_aux1 = String.valueOf(algo[0])+String.valueOf(algo[1])+String.valueOf(algo[2])+String.valueOf(algo[3]);
				String cadena_aux2 = String.valueOf(algo[4])+String.valueOf(algo[5])+String.valueOf(algo[6])+String.valueOf(algo[7]);
				String cadena_aux3 = String.valueOf(algo[8])+String.valueOf(algo[9])+String.valueOf(algo[10])+String.valueOf(algo[11]);
				String cadena_aux4 = String.valueOf(algo[12])+String.valueOf(algo[13])+String.valueOf(algo[14])+String.valueOf(algo[15]);
				String cadena_aux5 = String.valueOf(algo[16])+String.valueOf(algo[17])+String.valueOf(algo[18])+String.valueOf(algo[19]);
				String cadena_aux6 = String.valueOf(algo[20])+String.valueOf(algo[21])+String.valueOf(algo[22])+String.valueOf(algo[23]);
				String cadena_aux7 = String.valueOf(algo[24])+String.valueOf(algo[25])+String.valueOf(algo[26])+String.valueOf(algo[27]);
				String cadena_aux8 = String.valueOf(algo[28])+String.valueOf(algo[29])+String.valueOf(algo[30])+String.valueOf(algo[31]);
				String cadena_aux9 = String.valueOf(algo[32])+String.valueOf(algo[33])+String.valueOf(algo[34])+String.valueOf(algo[35]);
				String cadena_aux10 = String.valueOf(algo[36])+String.valueOf(algo[37])+String.valueOf(algo[38])+String.valueOf(algo[39]);
				String cadena_aux11= String.valueOf(algo[40])+String.valueOf(algo[41])+String.valueOf(algo[42])+String.valueOf(algo[43]);
				String cadena_aux12 = String.valueOf(algo[44])+String.valueOf(algo[45])+String.valueOf(algo[46])+String.valueOf(algo[47]);
				String cadena_aux13 = String.valueOf(algo[48])+String.valueOf(algo[49])+String.valueOf(algo[50])+String.valueOf(algo[51]);
				String cadena_aux14 = String.valueOf(algo[52])+String.valueOf(algo[53])+String.valueOf(algo[54])+String.valueOf(algo[55]);
				String cadena_aux15 = String.valueOf(algo[56])+String.valueOf(algo[57])+String.valueOf(algo[58])+String.valueOf(algo[59]);
				String cadena_aux16 = String.valueOf(algo[60])+String.valueOf(algo[61])+String.valueOf(algo[62])+String.valueOf(algo[63]);				
				
				String[] cadena_hex={cadena_aux1,cadena_aux2,cadena_aux3,cadena_aux4,cadena_aux5,cadena_aux6,cadena_aux7,cadena_aux8,cadena_aux9,
				cadena_aux10,cadena_aux11,cadena_aux12,cadena_aux13,cadena_aux14,cadena_aux15,cadena_aux16};
				
				for (int i = 0 ; i<16;i++)
					{
						if(cadena_hex[i].equals("0000"))
							cadena_final_hexa += "0";
						if(cadena_hex[i].equals("0001"))
							cadena_final_hexa += "1";
						if(cadena_hex[i].equals("0010"))
							cadena_final_hexa += "2";
						if(cadena_hex[i].equals("0011"))
							cadena_final_hexa += "3";
						if(cadena_hex[i].equals("0100"))
							cadena_final_hexa += "4";
						if(cadena_hex[i].equals("0101"))
							cadena_final_hexa += "5";
						if(cadena_hex[i].equals("0110"))
							cadena_final_hexa += "6";
						if(cadena_hex[i].equals("0111"))
							cadena_final_hexa += "7";
						if(cadena_hex[i].equals("1000"))
							cadena_final_hexa += "8";
						if(cadena_hex[i].equals("1001"))
							cadena_final_hexa += "9";
						if(cadena_hex[i].equals("1010"))
							cadena_final_hexa += "a";
						if(cadena_hex[i].equals("1011"))
							cadena_final_hexa += "b";
						if(cadena_hex[i].equals("1100"))
							cadena_final_hexa += "c";
						if(cadena_hex[i].equals("1101"))
							cadena_final_hexa += "d";
						if(cadena_hex[i].equals("1110"))
							cadena_final_hexa += "e";
						if(cadena_hex[i].equals("1111"))
							cadena_final_hexa += "f";
					}
				return cadena_final_hexa;					
			}
	}