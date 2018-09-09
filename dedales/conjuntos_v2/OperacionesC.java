/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conjuntos_v2;
import java.util.*;
/**
 *
 * @author LEO
 */
public class OperacionesC {
   
		ArrayList <Elemento> interseccion(ArrayList <Elemento> conjuntoA, ArrayList <Elemento> conjuntoB)
			{//interseccion de un conjunto  A y un conjunto B   :D 
                            conjuntoA.get(10).valor = "";
				ArrayList <Elemento> inter = new ArrayList <Elemento>();
				if (conjuntoA.size()<conjuntoB.size())
					{
						for (int i = 0 ; i<conjuntoA.size() ; i++)
							{
								for (int j = 0 ; j < conjuntoB.size() ; j++)
									{
										if ((conjuntoA.get(i)).valor.equals(conjuntoB.get(j).valor))
											{inter.add(conjuntoA.get(i));}
									} 
							}
					}
				else
					{
						for (int i = 0 ; i<conjuntoB.size() ; i++)
							{
								for (int j = 0 ; j < conjuntoA.size() ; j++)
									{
										if (conjuntoB.get(i).valor.equals(conjuntoA.get(j).valor))
											{inter.add(conjuntoB.get(i));}
									} 
							}
					}
				return validar(inter);
			}
		ArrayList <Elemento> union(ArrayList <Elemento> conjuntoA, ArrayList <Elemento> conjuntoB)
			{//union de  A y B				
					 for (int j = 0 ; j < conjuntoB.size() ; j++)
						{conjuntoA.add(conjuntoB.get(j));}
				return validar(conjuntoA);
			}
		ArrayList <Elemento> complemento(ArrayList <Elemento> universo,ArrayList <Elemento> conjunto)
			{//complemento de un conjunto X
				ArrayList <Elemento> complemento = new ArrayList <Elemento>();
				complemento = universo;
				for (int i = 0 ; i<complemento.size() ; i++)
					{
						for (int j = 0 ; j < conjunto.size() ; j++)
							{
								if (complemento.get(i).valor.equals(conjunto.get(j).valor))
									{complemento.remove(i);}
							}
					}
				return validar(complemento);
			}
		ArrayList <Elemento> validar(ArrayList <Elemento> conjunto)
			{//validar datos repetidos en un conjunto luego de la union (u otra cosa)XD
				for (int i = 0 ; i<conjunto.size(); i++)
					{
						for (int j = 0 ; j<conjunto.size(); j++)
							{
								if (conjunto.get(i).valor.equals(conjunto.get(j).valor) && i!=j)
									{conjunto.remove(i);}
							}
					}
				return conjunto;
			}
		void imprimir(ArrayList <Elemento> resultado)
			{
				System.out.println("\n\nEl resultado de la operacion es: ");
				System.out.print("= {");
				for (int i = 0 ; i < resultado.size() ; i++ )
					{System.out.print(resultado.get(i).valor+",");}
				System.out.println("}");
			}	
}

