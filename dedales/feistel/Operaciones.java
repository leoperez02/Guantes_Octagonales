//Mauro Leonardo Pérez Bravo 5IM8 
class Operaciones 
	{
		int anterior =-1;
		int lado_d = 0;
		int lado_i = 0;
		int lado_d_1 = 0; 
		int And (int num, int cla)
			{
				if (anterior == -1){
					lado_d = num & 15 ;
					lado_i = (num & 255) >> 4 ;
				}
				else{
					lado_d = anterior & 15 ;
					lado_i = (anterior & 255) >> 4 ;
				}
			    lado_d_1 = lado_d & cla ;
				System.out.println("And "+cla+" R.= "+Xor(lado_d_1,lado_i,lado_d)+" "+Integer.toBinaryString(Xor(lado_d_1,lado_i,lado_d)));
				return Xor(lado_d_1,lado_i,lado_d);
			}
		void Or (int num, int cla)
			{
				num = anterior;
				lado_d = num & 15 ;
				lado_i = (num & 255) >> 4 ;
				lado_d_1 = lado_d | cla ;
				System.out.println("Or "+cla+" R.="+Xor(lado_d_1,lado_i,lado_d)+" "+Integer.toBinaryString(Xor(lado_d_1,lado_i,lado_d)));
			}
		void Not ( int num)
			{
				if (anterior == -1)
					num = num;
				else
					num = anterior;
				lado_d = num & 15 ;
				lado_i = (num & 255) >> 4 ;
				lado_d_1 = ~lado_d & 15;
				System.out.println("Not R.="+Xor(lado_d_1,lado_i,lado_d)+" "+Integer.toBinaryString(Xor(lado_d_1,lado_i,lado_d)));
			}
		int Xor (int lado_d_1,int lado_i,int lado_d)
			{
				int lado_derecho = lado_d_1 ^ lado_i;
				int lado_izquierdo = lado_d << 4;
				int numtot = lado_derecho + lado_izquierdo ;
				anterior = numtot;
				return numtot;
			}
		int Nand(int num, int cla)
			{
				if (anterior == -1)
					num = num;
				else
					num = anterior;
				lado_d = num & 15 ;
				lado_i = (num & 255) >> 4 ;
				lado_d_1 = ~(lado_d & cla) & 15 ;
				int tot = Xor(lado_d_1,lado_i,lado_d);
				System.out.println("Probando nand "+tot+"  "+Integer.toBinaryString(tot) );
				return tot;
			}
		int Voltear(int tot)
			{
				int izquierda = (tot & 15)<<4;
				int derecha = ( tot & 255 ) >> 4;
				int res_final =   izquierda + derecha;
				return res_final;
			}
	}