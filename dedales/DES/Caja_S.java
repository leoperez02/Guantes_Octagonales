class Caja_S
	{
		Caja_S ( ) {		}
			
		int[][] s1 = 
			{
				{14,4,13,1,2,15,11,8,3,10,6,12,5,9,0,7},
				{0,15,7,4,14,2,13,1,10,6,12,11,9,5,3,8},
				{4,1,14,8,13,6,2,11,15,12,9,7,3,10,5,0},
				{15,12,8,2,4,9,1,7,5,11,3,14,10,0,6,13}
			};
		
		int[][] s2 =
			{
				{15,1,8,14,6,11,3,4,9,7,2,13,12,0,5,10},
				{3,13,4,7,15,2,8,14,12,0,1,10,6,9,11,5},
				{0,14,7,11,10,4,13,1,5,8,12,6,9,3,2,15},
				{13,8,10,1,3,15,4,2,11,6,7,12,0,5,14,9}
			};
		
		int[][] s3 =
			{
				{10,0,9,14,6,3,15,5,1,13,12,7,11,4,2,8},
				{13,7,0,9,3,4,6,10,2,8,5,14,12,11,15,1},
				{13,6,4,9,8,15,3,0,11,1,2,12,5,10,14,7},
				{1,10,13,0,6,9,8,7,4,15,14,3,11,5,2,12}
			};
		
		int[][] s4 = 
			{
				{7,13,14,3,0,6,9,10,1,2,8,5,11,12,4,15},
				{13,8,11,5,6,15,0,3,4,7,2,12,1,10,14,9},
				{10,6,9,0,12,11,7,13,15,1,3,14,5,2,8,4},
				{3,15,0,6,10,1,13,8,9,4,5,11,12,7,2,14}
			};
		
		int[][] s5 = 
			{
				{2,12,4,1,7,10,11,6,8,5,3,15,13,0,14,9},
				{14,11,2,12,4,7,13,1,5,0,15,10,3,9,8,6},
				{4,2,1,11,10,13,7,8,15,9,12,5,6,3,0,14},
				{11,8,12,7,1,14,2,13,6,15,0,9,10,4,5,3}
			};
		
		int[][] s6 = 
			{
				{12,1,10,15,9,2,6,8,0,13,3,4,14,7,5,11},
				{10,15,4,2,7,12,9,5,6,1,13,14,0,11,3,8},
				{9,14,15,5,2,8,12,3,7,0,4,10,1,13,11,6},
				{4,3,2,12,9,5,15,10,11,14,1,7,6,0,8,13}
			};
		
		int[][] s7 = 
			{
				{4,11,2,14,15,0,8,13,3,12,9,7,5,10,6,1},
				{13,0,11,7,4,9,1,10,14,3,5,12,2,15,8,6},
				{1,4,11,13,12,3,7,14,10,15,6,8,0,5,9,2},
				{6,11,13,8,1,4,10,7,9,5,0,15,14,2,3,12}
			};
		
		int[][] s8 = 
			{
				{13,2,8,4,6,15,11,1,10,9,3,14,5,0,12,7},
				{1,15,13,8,10,3,7,4,12,5,6,11,0,14,9,2},
				{7,11,4,1,9,12,14,2,0,6,10,13,15,3,5,8},
				{2,1,14,7,4,10,8,13,15,12,9,0,3,5,6,11}
			};
	
		String[] s_permutado = new String [32];
		
		String[] b1 = new String[6];						String[] s1_c = new String[4];
		String[] b2 = new String[6];						String[] s2_c = new String[4];
		String[] b3 = new String[6];						String[] s3_c = new String[4];
		String[] b4 = new String[6];						String[] s4_c = new String[4];
		String[] b5 = new String[6];						String[] s5_c = new String[4];
		String[] b6 = new String[6];						String[] s6_c = new String[4];
		String[] b7 = new String[6];						String[] s7_c = new String[4];
		String[] b8 = new String[6];						String[] s8_c = new String[4];
		
		String permuta_S ( String[] e_x )
			{
				for (int i = 0 ; i < 6 ; i++)
					b1[i] = (e_x[i]);
				for (int i = 6 , a = 0 ; i < 12 ; i++ , a++)
					b2[a] = (e_x[i]);
				for (int i = 12 , a = 0 ; i < 18 ; i++ , a++)
					b3[a] =(e_x[i]);
				for (int i = 18 , a = 0 ; i < 24 ; i++ , a++)
					b4[a] =(e_x[i]);
				for (int i = 24 , a = 0 ; i < 30 ; i++ , a++)
					b5[a] = (e_x[i]);
				for (int i = 30 , a = 0 ; i < 36 ; i++ , a++)
					b6[a] = (e_x[i]);
				for (int i = 36 , a = 0 ; i < 42 ; i++ , a++)
					b7[a] = (e_x[i]);
				for (int i = 42 , a = 0 ; i < 48 ; i++ , a++)
					b8[a] = (e_x[i]);
				
				{//-----------------------
				System.out.println("");
				System.out.println("");
				for (int i = 0 ; i < 6 ; i++)
					System.out.print(b1[i]);
				System.out.println("");
				for (int i = 0 ; i < 6 ; i++)
					System.out.print(b2[i]);
				System.out.println("");
				for (int i = 0 ; i < 6 ; i++)
					System.out.print(b3[i]);
				System.out.println("");
				for (int i = 0 ; i < 6 ; i++)
					System.out.print(b4[i]);
				System.out.println("");
				for (int i = 0 ; i < 6 ; i++)
					System.out.print(b5[i]);
				System.out.println("");
				for (int i = 0 ; i < 6 ; i++)
					System.out.print(b6[i]);
				System.out.println("");
				for (int i = 0 ; i < 6 ; i++)
					System.out.print(b7[i]);
				System.out.println("");
				for (int i = 0 ; i < 6 ; i++)
					System.out.print(b8[i]);
				}//----------
				
				s1_c = calcula_s_i(b1,1);
				s2_c = calcula_s_i(b2,2);
				s3_c = calcula_s_i(b3,3);
				s4_c = calcula_s_i(b4,4);
				s5_c = calcula_s_i(b5,5);
				s6_c = calcula_s_i(b6,6);
				s7_c = calcula_s_i(b7,7);
				s8_c = calcula_s_i(b8,8);
		
				String cadena_final = "";
				for (int i = 0; i<4 ; i++)
					cadena_final += s1_c[i]; 
				for (int i = 0; i<4 ; i++)
					cadena_final += s2_c[i];
				for (int i = 0; i<4 ; i++)
					cadena_final += s3_c[i];
				for (int i = 0; i<4 ; i++)
					cadena_final += s4_c[i];
				for (int i = 0; i<4 ; i++)
					cadena_final += s5_c[i];
				for (int i = 0; i<4 ; i++)
					cadena_final += s6_c[i];
				for (int i = 0; i<4 ; i++)
					cadena_final += s7_c[i];
				for (int i = 0; i<4 ; i++)
					cadena_final += s8_c[i];	
					
				return cadena_final;
			}
			
		String[] calcula_s_i(String[] bi, int caja)
			{
				String[] s_i = new String[4];
				int m = Integer.parseInt( ((bi[0]) + (bi[5]) ),2);
				int n = Integer.parseInt( ((bi[1]) + (bi[2]) +(bi[3]) + (bi[4]) ) ,2);
		
				System.out.println("");
				if ( caja== 1)
					{
						System.out.println("Este es s"+caja+" segun m y n " + s2[m][n]);
						if( s1[m][n] < 8)
							{
								if( s1[m][n] == 7)
									{
										s_i[0] = "0";
										s_i[1] = "1";
										s_i[2] = "1";
										s_i[3] = "1";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s1[m][n] == 6)
									{
										s_i[0] = "0";
										s_i[1] = "1";
										s_i[2] = "1";
										s_i[3] = "0";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s1[m][n] == 5)
									{
										s_i[0] = "0";
										s_i[1] = "1";
										s_i[2] = "0";
										s_i[3] = "1";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s1[m][n] == 4)
									{
										s_i[0] = "0";
										s_i[1] = "1";
										s_i[2] = "0";
										s_i[3] = "0";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s1[m][n] == 3)
									{
										s_i[0] = "0";
										s_i[1] = "0";
										s_i[2] = "1";
										s_i[3] = "1";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s1[m][n] == 2)
									{
										s_i[0] = "0";
										s_i[1] = "0";
										s_i[2] = "1";
										s_i[3] = "0";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s1[m][n] == 1)
									{
										s_i[0] = "0";
										s_i[1] = "0";
										s_i[2] = "0";
										s_i[3] = "1";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s1[m][n] == 0)
									{
										s_i[0] = "0";
										s_i[1] = "0";
										s_i[2] = "0";
										s_i[3] = "0";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
							}
						else
							{
								for (int i = 0, a = 1 ; i < 4 ; i++ , a++)
									s_i[i] = (Integer.toBinaryString(s1[m][n])).substring(i,a);
								for (int i= 0 ; i< 4; i++)
									System.out.print(s_i[i]);
							}
					}
				if ( caja== 2)
					{
						System.out.println("Este es s"+caja+" segun m y n " + s2[m][n]);
						if( s2[m][n] < 8)
							{
								if( s2[m][n] == 7)
									{
										s_i[0] = "0";
										s_i[1] = "1";
										s_i[2] = "1";
										s_i[3] = "1";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s2[m][n] == 6)
									{
										s_i[0] = "0";
										s_i[1] = "1";
										s_i[2] = "1";
										s_i[3] = "0";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s2[m][n] == 5)
									{
										s_i[0] = "0";
										s_i[1] = "1";
										s_i[2] = "0";
										s_i[3] = "1";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s2[m][n] == 4)
									{
										s_i[0] = "0";
										s_i[1] = "1";
										s_i[2] = "0";
										s_i[3] = "0";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s2[m][n] == 3)
									{
										s_i[0] = "0";
										s_i[1] = "0";
										s_i[2] = "1";
										s_i[3] = "1";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s2[m][n] == 2)
									{
										s_i[0] = "0";
										s_i[1] = "0";
										s_i[2] = "1";
										s_i[3] = "0";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s2[m][n] == 1)
									{
										s_i[0] = "0";
										s_i[1] = "0";
										s_i[2] = "0";
										s_i[3] = "1";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s2[m][n] == 0)
									{
										s_i[0] = "0";
										s_i[1] = "0";
										s_i[2] = "0";
										s_i[3] = "0";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
							}
						else
							{
								for (int i = 0, a = 1 ; i < 4 ; i++ , a++)
									s_i[i] = (Integer.toBinaryString(s2[m][n])).substring(i,a);
								for (int i= 0 ; i< 4; i++)
									System.out.print(s_i[i]);
							}
					}
				if ( caja== 3)
					{
						System.out.println("esete es s3 segun m yn" + s3[m][n]);
						if( s3[m][n] < 8)
							{
								if( s3[m][n] == 7)
									{
										s_i[0] = "0";
										s_i[1] = "1";
										s_i[2] = "1";
										s_i[3] = "1";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s3[m][n] == 6)
									{
										s_i[0] = "0";
										s_i[1] = "1";
										s_i[2] = "1";
										s_i[3] = "0";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s3[m][n] == 5)
									{
										s_i[0] = "0";
										s_i[1] = "1";
										s_i[2] = "0";
										s_i[3] = "1";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s3[m][n] == 4)
									{
										s_i[0] = "0";
										s_i[1] = "1";
										s_i[2] = "0";
										s_i[3] = "0";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s3[m][n] == 3)
									{
										s_i[0] = "0";
										s_i[1] = "0";
										s_i[2] = "1";
										s_i[3] = "1";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s3[m][n] == 2)
									{
										s_i[0] = "0";
										s_i[1] = "0";
										s_i[2] = "1";
										s_i[3] = "0";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s3[m][n] == 1)
									{
										s_i[0] = "0";
										s_i[1] = "0";
										s_i[2] = "0";
										s_i[3] = "1";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s3[m][n] == 0)
									{
										s_i[0] = "0";
										s_i[1] = "0";
										s_i[2] = "0";
										s_i[3] = "0";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
							}
						else
							{
								for (int i = 0, a = 1 ; i < 4 ; i++ , a++)
									s_i[i] = (Integer.toBinaryString(s3[m][n])).substring(i,a);
								for (int i= 0 ; i< 4; i++)
									System.out.print(s_i[i]);
							}
					}
				if ( caja== 4)
					{
						System.out.println("esete es s4 segun m yn" + s4[m][n]);
						if( s4[m][n] < 8)
							{
								if( s4[m][n] == 7)
									{
										s_i[0] = "0";
										s_i[1] = "1";
										s_i[2] = "1";
										s_i[3] = "1";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s4[m][n] == 6)
									{
										s_i[0] = "0";
										s_i[1] = "1";
										s_i[2] = "1";
										s_i[3] = "0";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s4[m][n] == 5)
									{
										s_i[0] = "0";
										s_i[1] = "1";
										s_i[2] = "0";
										s_i[3] = "1";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s4[m][n] == 4)
									{
										s_i[0] = "0";
										s_i[1] = "1";
										s_i[2] = "0";
										s_i[3] = "0";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s4[m][n] == 3)
									{
										s_i[0] = "0";
										s_i[1] = "0";
										s_i[2] = "1";
										s_i[3] = "1";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s4[m][n] == 2)
									{
										s_i[0] = "0";
										s_i[1] = "0";
										s_i[2] = "1";
										s_i[3] = "0";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s4[m][n] == 1)
									{
										s_i[0] = "0";
										s_i[1] = "0";
										s_i[2] = "0";
										s_i[3] = "1";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s4[m][n] == 0)
									{
										s_i[0] = "0";
										s_i[1] = "0";
										s_i[2] = "0";
										s_i[3] = "0";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
							}
						else
							{
								for (int i = 0, a = 1 ; i < 4 ; i++ , a++)
									s_i[i] = (Integer.toBinaryString(s4[m][n])).substring(i,a);
								for (int i= 0 ; i< 4; i++)
									System.out.print(s_i[i]);
							}
					}
				if ( caja== 5)
					{
						System.out.println("esete es s5 segun m yn" + s5[m][n]);
						if( s5[m][n] < 8)
							{
								if( s5[m][n] == 7)
									{
										s_i[0] = "0";
										s_i[1] = "1";
										s_i[2] = "1";
										s_i[3] = "1";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s5[m][n] == 6)
									{
										s_i[0] = "0";
										s_i[1] = "1";
										s_i[2] = "1";
										s_i[3] = "0";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s5[m][n] == 5)
									{
										s_i[0] = "0";
										s_i[1] = "1";
										s_i[2] = "0";
										s_i[3] = "1";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s5[m][n] == 4)
									{
										s_i[0] = "0";
										s_i[1] = "1";
										s_i[2] = "0";
										s_i[3] = "0";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s5[m][n] == 3)
									{
										s_i[0] = "0";
										s_i[1] = "0";
										s_i[2] = "1";
										s_i[3] = "1";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s5[m][n] == 2)
									{
										s_i[0] = "0";
										s_i[1] = "0";
										s_i[2] = "1";
										s_i[3] = "0";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s5[m][n] == 1)
									{
										s_i[0] = "0";
										s_i[1] = "0";
										s_i[2] = "0";
										s_i[3] = "1";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s5[m][n] == 0)
									{
										s_i[0] = "0";
										s_i[1] = "0";
										s_i[2] = "0";
										s_i[3] = "0";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
							}
						else
							{
								for (int i = 0, a = 1 ; i < 4 ; i++ , a++)
									s_i[i] = (Integer.toBinaryString(s5[m][n])).substring(i,a);
								for (int i= 0 ; i< 4; i++)
									System.out.print(s_i[i]);
							}
					}
				if ( caja== 6)
					{
						System.out.println("esete es s6 segun m yn" + s6[m][n]);
						if( s6[m][n] < 8)
							{
								if( s6[m][n] == 7)
									{
										s_i[0] = "0";
										s_i[1] = "1";
										s_i[2] = "1";
										s_i[3] = "1";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s6[m][n] == 6)
									{
										s_i[0] = "0";
										s_i[1] = "1";
										s_i[2] = "1";
										s_i[3] = "0";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s6[m][n] == 5)
									{
										s_i[0] = "0";
										s_i[1] = "1";
										s_i[2] = "0";
										s_i[3] = "1";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s6[m][n] == 4)
									{
										s_i[0] = "0";
										s_i[1] = "1";
										s_i[2] = "0";
										s_i[3] = "0";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s6[m][n] == 3)
									{
										s_i[0] = "0";
										s_i[1] = "0";
										s_i[2] = "1";
										s_i[3] = "1";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s6[m][n] == 2)
									{
										s_i[0] = "0";
										s_i[1] = "0";
										s_i[2] = "1";
										s_i[3] = "0";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s6[m][n] == 1)
									{
										s_i[0] = "0";
										s_i[1] = "0";
										s_i[2] = "0";
										s_i[3] = "1";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s6[m][n] == 0)
									{
										s_i[0] = "0";
										s_i[1] = "0";
										s_i[2] = "0";
										s_i[3] = "0";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
							}
						else
							{
								for (int i = 0, a = 1 ; i < 4 ; i++ , a++)
									s_i[i] = (Integer.toBinaryString(s6[m][n])).substring(i,a);
								for (int i= 0 ; i< 4; i++)
									System.out.print(s_i[i]);
							}
					}
				if ( caja== 7)
					{
						System.out.println("esete es s7 segun m yn" + s7[m][n]);
						if( s7[m][n] < 8)
							{
								if( s7[m][n] == 7)
									{
										s_i[0] = "0";
										s_i[1] = "1";
										s_i[2] = "1";
										s_i[3] = "1";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s7[m][n] == 6)
									{
										s_i[0] = "0";
										s_i[1] = "1";
										s_i[2] = "1";
										s_i[3] = "0";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s7[m][n] == 5)
									{
										s_i[0] = "0";
										s_i[1] = "1";
										s_i[2] = "0";
										s_i[3] = "1";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s7[m][n] == 4)
									{
										s_i[0] = "0";
										s_i[1] = "1";
										s_i[2] = "0";
										s_i[3] = "0";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s7[m][n] == 3)
									{
										s_i[0] = "0";
										s_i[1] = "0";
										s_i[2] = "1";
										s_i[3] = "1";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s7[m][n] == 2)
									{
										s_i[0] = "0";
										s_i[1] = "0";
										s_i[2] = "1";
										s_i[3] = "0";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s7[m][n] == 1)
									{
										s_i[0] = "0";
										s_i[1] = "0";
										s_i[2] = "0";
										s_i[3] = "1";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s7[m][n] == 0)
									{
										s_i[0] = "0";
										s_i[1] = "0";
										s_i[2] = "0";
										s_i[3] = "0";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
							}
						else
							{
								for (int i = 0, a = 1 ; i < 4 ; i++ , a++)
									s_i[i] = (Integer.toBinaryString(s7[m][n])).substring(i,a);
								for (int i= 0 ; i< 4; i++)
									System.out.print(s_i[i]);
							}
					}
				if ( caja== 8)
					{
						System.out.println("esete es s8 segun m yn" + s8[m][n]);
						if( s8[m][n] < 8)
							{
								if( s8[m][n] == 7)
									{
										s_i[0] = "0";
										s_i[1] = "1";
										s_i[2] = "1";
										s_i[3] = "1";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s8[m][n] == 6)
									{
										s_i[0] = "0";
										s_i[1] = "1";
										s_i[2] = "1";
										s_i[3] = "0";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s8[m][n] == 5)
									{
										s_i[0] = "0";
										s_i[1] = "1";
										s_i[2] = "0";
										s_i[3] = "1";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s8[m][n] == 4)
									{
										s_i[0] = "0";
										s_i[1] = "1";
										s_i[2] = "0";
										s_i[3] = "0";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s8[m][n] == 3)
									{
										s_i[0] = "0";
										s_i[1] = "0";
										s_i[2] = "1";
										s_i[3] = "1";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s8[m][n] == 2)
									{
										s_i[0] = "0";
										s_i[1] = "0";
										s_i[2] = "1";
										s_i[3] = "0";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s8[m][n] == 1)
									{
										s_i[0] = "0";
										s_i[1] = "0";
										s_i[2] = "0";
										s_i[3] = "1";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
								if( s8[m][n] == 0)
									{
										s_i[0] = "0";
										s_i[1] = "0";
										s_i[2] = "0";
										s_i[3] = "0";
										for(int j=0; j<4; j++)
											System.out.print(s_i[j]);
									}
							}
						else
							{
								for (int i = 0, a = 1 ; i < 4 ; i++ , a++)
									s_i[i] = (Integer.toBinaryString(s8[m][n])).substring(i,a);
								for (int i= 0 ; i< 4; i++)
									System.out.print(s_i[i]);
							}
					}
				
				return s_i;
			}
	}