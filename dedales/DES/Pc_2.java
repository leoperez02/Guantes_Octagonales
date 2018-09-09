class Pc_2
	{
		int[] Pc_2 = 
					 {14,17,11,24,1,5,3,28,
					  15,6,21,10,23,19,12,4,
					  26,8,16,7,27,20,13,2,
					  41,52,31,37,47,55,30,40,
					  51,45,33,48,44,49,39,56,
					  34,53,46,42,50,36,29,32};
					  
		int[] bloque_permutado = new int [48];
		
		Pc_2(){}
		
		int[] permuta_bloque(String[] bloque)
			{
				try
					{
						for (int i = 0 ; i<48 ; i++)
							bloque_permutado[i] = Integer.parseInt(bloque[ (Pc_2[i]-1) ]) ;
					}
				catch (ArrayIndexOutOfBoundsException e){};
				
				return bloque_permutado;
			}
	}