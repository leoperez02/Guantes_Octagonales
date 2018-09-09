class CodigoBilateral{
	
	String cifraMensaje(String[] mensaje){
		
		String mensajeCifrado="";
		String palabra="";
		String palabraCifrada="";
		char letra;
		byte octeto;
		for(int i=0;i<mensaje.length;i++){
			
			palabra=mensaje[i];
			for(int j=0;j<palabra.length();j++){
			
				if(Character.isLetter(palabra.charAt(j))){
					letra=Character.toLowerCase(palabra.charAt(j));
					if(letra=='ñ'){
						octeto=15;
					}
					else{
						octeto=(byte)letra;
						octeto-=96;
						if(octeto>14){
							octeto++;
						}
					}
					palabraCifrada=Integer.toBinaryString(octeto);
					while(palabraCifrada.length()<5){
						palabraCifrada="0"+palabraCifrada;
					}
					//System.out.println("Byte "+j+": "+palabraCifrada);
					mensajeCifrado+=palabraCifrada;
				}
				else{
					mensajeCifrado+=palabra.charAt(j);
				}
			}
			mensajeCifrado+=" ";
		}
		return mensajeCifrado;
	}
	public static void main(String[] mensaje){
	
		CodigoBilateral cifrador = new CodigoBilateral();
		String mensajeCifrado = cifrador.cifraMensaje(mensaje);
		System.out.println("Mensaje: "+mensajeCifrado);
	}
}