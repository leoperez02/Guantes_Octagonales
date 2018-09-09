public class Buscador{

	public static String buscaFolio(String asunto){
	
		String folio="";
		String regexp="[1234567890]{6,6}";
		String caso1="^[1234567890]{6,6}[^1234567890].*$";
		String caso2="^.*[^1234567890][1234567890]{6,6}$";
		String caso3="^.*[^1234567890][1234567890]{6,6}[^1234567890].*$";
		regexp+="|"+caso1+"|"+caso2+"|"+caso3;
     	if(asunto.matches(regexp)){
      	//System.out.println("El asunto tiene folio");
      	if(asunto.length()==6){//El asunto sólo es el folio
      		folio=asunto;
      	}
      	else{
      		//Comprobar caso1
      		int index;
      		for(index=0;Character.isDigit(asunto.charAt(index));index++);
      		if(index==6)
      			folio=asunto.substring(0,6);
      		else{
      			//Comprobar caso 2
      			index=asunto.length()-1;
     				for(;Character.isDigit(asunto.charAt(index));index--);
     				if(asunto.length()-index==7)
      					folio=asunto.substring(index+1);
      			else{
      				//Comprobar caso 3
      				//System.out.print("El folio está dentro del asunto");
      				folio=Buscador.buscaFolio(asunto.substring(1));
      			}
      		}
      	}
     	} 
     	else {
         //System.out.println("El asunto NO tiene folio");
         folio="Sin folio";
		}
		return folio;
	}

	public static void main(String args[]){
		
		//MAIN TEST
		String folio = Buscador.buscaFolio(args[0]);
		System.out.println(folio);
	}
}
