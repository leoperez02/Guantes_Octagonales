/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conjuntos_v2;

import java.util.ArrayList;

/**
 *
 * @author LEO
 */
public class Conjuntos {
    ArrayList <Elemento> pideUniverso()
			{
				ArrayList <Elemento> universo = new ArrayList <Elemento>();
				int cdeA = 0;
				int contu = 1;
				System.out.print("\n\nEscribe cuantos elementos tendra el conjunto Universo: ");
				cdeA = Leer.datoInt();
				System.out.print("\n\nA continuacion escribe los elementos que conformaran el conjunto universo... ");
				while (universo.size()<cdeA)
					{
						System.out.print("\nTeclea el elemento numero "+contu+": ");
						Elemento elemento = new Elemento();
						elemento.valor = Leer.dato();
						if (!elemento.valor.equals(""))
							{
								universo.add(elemento);
								contu++;
							}
						else
							{System.out.println("Dato vacio. Ingresa el dato...");}
					}
				return universo;
			}
		ArrayList <Elemento> pideConjunto()
			{
				ArrayList <Elemento> conjunto = new ArrayList <Elemento>();
				int cdeA = 0 ;
				int conta = 1;
				System.out.print("\nA continuacion escribe el numero de elementos que conformaran el conjunto: ");
				cdeA = Leer.datoInt();
				System.out.print("\n\nA continuacion escribe los elementos que conformaran el conjunto... ");
				while (conjunto.size()<cdeA)
					{
						System.out.print("\nTeclea el elemento numero "+conta+": ");
						Elemento elemento = new Elemento();
						elemento.valor = Leer.dato();
						if (!elemento.valor.equals(""))
							{
								conjunto.add(elemento);
								conta++;
							}
						else
							{System.out.println("Dato vacio.");}
					}
				return conjunto;
			}
}
