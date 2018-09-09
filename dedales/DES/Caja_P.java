class Caja_P
	{
		int[] p =
					{16,7,20,21,29,12,28,17,
					   1,15,23,26,5,18,31,10,
					   2,8,24,14,32,27,3,9,
					 19,13,30,6,22,11,4,25    } ;
				
		int[] s_permutado = new int [32];
		
		Caja_P ( ) 
			{		
			}
	
		int[] permutacion_P ( String[] s )
			{
				try
					{
						for ( int i = 0 ; i < 32 ; i++ )
							s_permutado[ i ] = Integer.parseInt (  s[ ( p[ i ] -1) ]  ) ;
					}
				catch  (ArrayIndexOutOfBoundsException e ) {		};
				
				return s_permutado;
			}
	}