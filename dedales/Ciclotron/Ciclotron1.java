class Ciclotron1
	{
		public static void main (String a[])
			{
				int vuelta = 1 ;
				double radio = 0 ;
				double contador = 0 ;
				double suma_radio = 0 ;
				double cte = 0 ;
				cte = 0.03674352491 ;
				while ( radio <= 0.53 )
					{
						System.out.print("Vuelta: ") ;
						System.out.println(vuelta) ;
						System.out.print("Radio: ") ;
						radio = cte * (Math.sqrt(vuelta)) ;
						System.out.println(radio) ;
						if (vuelta == 208)
							break;
						else
							{
								suma_radio = suma_radio + radio ;
								vuelta ++;
							}
					}
				System.out.println( suma_radio*3.1416 ) ;
			}
	}