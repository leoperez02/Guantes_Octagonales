class Cifra_clave
	{
		Cifra_clave() {}
		int numero=1;
		int [] corrido_c = new int [28];
		int [] corrido_d = new int [28];
		int[] c_0 = new int[28];
		int []d_0 = new int[28];
		int []c_i=new int[28];
		int []d_i=new int[28];
		Convierte_hex_bin obj_Convierte = new Convierte_hex_bin();
		String cadena_binaria = "";
		
		String[] arr_clave = new String[64];
		int[] arr_clave_56 = new int[56];
		String[] caracter_hex = new String[16];
		String[] claves_ci = new String [16] ;
		
		String[] recibe_clave(String clave)
			{
				for (int i = 0 , a = 1 ; i < clave.length() ; i++ , a++)
					caracter_hex[i] = clave.substring(i,a);
					
				cadena_binaria = obj_Convierte.convierte(caracter_hex);
				
				for (int i = 0 , a = 1 ; i < cadena_binaria.length() ; i++ , a++)
					arr_clave[i] = cadena_binaria.substring(i,a);
				
				Pc_1 obj_Pc_1 = new Pc_1();
				
				System.out.println("");
				
				arr_clave_56 = obj_Pc_1.permuta_clave_bin(arr_clave);
				for (int i = 0 ; i<56 ;i++ )
					System.out.print(arr_clave_56[i]);
					
				System.out.println("");
				
				//imprimiendo
				System.out.println("");
				System.out.print("C(0): = " );
				for (int i = 0 ; i<28 ;i++ )
					System.out.print(arr_clave_56[i]);
				System.out.println("");
				System.out.print("D(0): = " );	
				for (int j = 28 ; j<56 ;j++ )
					System.out.print(arr_clave_56[j]);
				System.out.println("");
				calculo_sub_k(arr_clave_56);
				
				return claves_ci;
			}
		void calculo_sub_k (int []arr_clave_56)
			{
				//dividir c y d
				try
					{
						for (int j = 0 ; j< 28 ; j ++)
							c_0[j] = arr_clave_56[j];
						for (int k = 28 , j=0; k< 56 ; k ++,j++)
							d_0[j] = arr_clave_56[k];
					}
				catch(ArrayIndexOutOfBoundsException e ){}
				
				//calculo de 16  subclaves
				for(int ronda = 1; ronda<17; ronda++)
				{
					primer_corrimiento_c(ronda);
				}
				
			}
		String primer_corrimiento_c(int ronda)
			{				
				if (ronda==1 || ronda==2 || ronda==9 || ronda==16 )
					{
						//corrmiento c
						int primero_c = c_0[0];
						int x;
						for(x= 0; x<c_0.length-1; x++)
						c_i[x] = c_0[x+1];
						c_i[x]= primero_c;
						
						//corrimiento d
						int primero_d = d_0[0];
						int y;
						for(y= 0; y<d_0.length-1; y++)
						d_i[y] = d_0[y+1];
						d_i[y]= primero_d;
						
						//actualiza c y d
						c_0 = c_i;
						d_0 = d_i;
					
					}
				
				if(ronda==3 || ronda==4 || ronda==5 || ronda==6 || ronda==7 || ronda==8 || ronda==10|| ronda==11 || ronda==12 || ronda==13 || ronda==14 || ronda==15)
					{
						//corrmiento c
						
						int primero_c = c_0[0];
						int x;
						for(x= 0; x<c_0.length-1; x++)
						c_i[x] = c_0[x+1];
						c_i[x]= primero_c;
				
						int segundo_c = c_0[0];
						int y;
						for(y= 0; y<c_0.length-1; y++)
						c_i[y] = c_0[y+1];
						c_i[y]= segundo_c;
						
						int primero_d = d_0[0];
						int k;
						for(k= 0; k<d_0.length-1; k++)
						d_i[k] = d_0[k+1];
						d_i[k]= primero_d;
										
						int segundo_d = d_0[0];
						int z;
						for(z= 0; z<d_0.length-1; z++)
						d_i[z] = d_0[z+1];
						d_i[z]= segundo_d;	
								
						//actualiza c y d
						c_0 = c_i;
						d_0 = d_i;
						
						//imprimiendo
						
						//
					}	
				System.out.println("");
						System.out.println("C"+"("+ numero+")");
						for(int i=0; i< c_i.length; i++)
						{
							System.out.print(c_i[i]);
						}	
				
						System.out.println("");
						System.out.println("D"+"("+ numero+")");
						for(int i=0; i< d_i.length; i++)
						{
							System.out.print(d_i[i]);
						}	
			
				String []juntos=new String[56];
				for (int i = 0 ; i < 28; i++)
					juntos[i]=String.valueOf(c_i[i]); 
				for (int i = 28 , j=0 ; i < 56; i++ , j++)
					juntos[i]=String.valueOf(d_i[j]);
				
				//permutar pc2
						Pc_2 obj_Pc_2 = new Pc_2();
					int[] nuevoalgo = new int [48]; 
					String nuevo_cadena = "";
					nuevoalgo = obj_Pc_2.permuta_bloque(juntos);
					for (int i = 0 ; i < 48;i++)
						nuevo_cadena += String.valueOf(nuevoalgo[i]);
					System.out.println('\n'+"subclave"+numero+":"+" " +nuevo_cadena);	
					
					claves_ci[(numero-1)] = nuevo_cadena ; 
					
					numero++;
				return nuevo_cadena;
			}				
						
		String[] obten_claves()
			{
				return claves_ci;
			}
	}