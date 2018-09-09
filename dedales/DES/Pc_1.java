class Pc_1
	{
		int[] Pc_1 = 
					{57,49,41,33,25,17,9,1,
					58,50,42,34,26,18,10,2,
					59,51,43,35,27,19,11,3,
					60,52,44,36,63,55,47,39,
					31,23,15,7,62,54,46,38,30,
					22,14,6,61,53,45,37,29,21,
					13,5,28,20,12,4};
					  
		int[] clave_bin_permutado = new int [56];
		
		Pc_1(){}
		
		int[] permuta_clave_bin(String [] arr_claves)
			{
				try{
					for (int i = 0 ; i<56 ; i++)
						clave_bin_permutado[i] = Integer.parseInt(arr_claves[(Pc_1[i])-1]) ;
				}
				catch (ArrayIndexOutOfBoundsException e){};
				
				return clave_bin_permutado;
			}
	}