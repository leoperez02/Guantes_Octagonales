/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package turistamundial;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Leo
 */
public class TuristaMundial {
    
    private static ArrayList<Propiedad> propiedades;
    private static ArrayList<Jugador> jugadores;
    private static ArrayList<String> tablero;
    private static TuristaMundialJFrame ventana;
    
    public static Propiedad getPropiedad(String nombre){
        for (int i = 0; i < propiedades.size(); i++) {
            if (propiedades.get(i).getNombre().equals(nombre)){
                return propiedades.get(i);
            }
        }
        return null;
    }
    
    public static Jugador sorteaTurnos(ArrayList<Jugador> jugadores){
        //Cada jugador lanza un dado y el que obtega en número más alto, sale
        //primero
        int tiro,mayor=0,tiro_ant=0;
        Jugador player;
        String jug="";
        
        for(int i=0; i<jugadores.size();i++){
            player=jugadores.get(i);
            tiro=player.tiraDados(1);        
            System.out.println(
                "El jugador "+player.getNombre()+" lanzó un "+tiro);
            try{Thread.sleep(2000);}catch(InterruptedException e){}
            if(tiro>tiro_ant){
                mayor=i;
                jug=mayor+"";
                tiro_ant=tiro;
            }
            else{
                if(tiro==tiro_ant){
                    mayor=i;
                    jug+=mayor;
                }   
            }
        }
        if(jug.length()==1){
            System.out.println(
                    "Primer turno: "+jugadores.get(mayor).getNombre()
            );
            return jugadores.get(mayor);
        }
        else{
            ArrayList<Jugador> empatados = new ArrayList<>();
            System.out.println("Empate entre los jugadores: ");
            for(int i=0;i<jug.length();i++){
                System.out.println(
                  jugadores.get(Integer.parseInt(jug.charAt(i)+"")).getNombre()
                );
                empatados.add(
                        jugadores.get(Integer.parseInt(jug.charAt(i)+""))
                );
                try{Thread.sleep(2000);}catch(InterruptedException e){}
            }
            return TuristaMundial.sorteaTurnos(empatados);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //1. Crear propiedades
        propiedades = new ArrayList<>();
        
        //Cargar países desde el archivo propiedades.txt
        BufferedReader data;
        String linea,name,atrib[];
        int precio,hipoteca,lote,restaurante,rentas[];
        try{
            data = new BufferedReader(new FileReader("propiedades.txt"));
            while((linea=data.readLine())!=null){
                atrib = linea.split("\t");
                name=atrib[0];
                precio=Integer.parseInt(atrib[1]);
                hipoteca=Integer.parseInt(atrib[2]);
                restaurante=Integer.parseInt(atrib[3]);
                lote=Integer.parseInt(atrib[4]);
                rentas=new int[atrib.length-5];
                for(int i=0;i<rentas.length;i++){
                    rentas[i]=Integer.parseInt(atrib[5+i]);
                }                    
                propiedades.add(
                    new Pais(name,precio,hipoteca,lote,restaurante,rentas)
                );
            }
            data.close();
        }
        catch(IOException e){}
        
        //Creando líneas aéreas, embaja y consulado
        Propiedad laAmericana,laEuropea,laAsiatica,laAfricana;
        Propiedad embajada,consulado;
        
        int[] rentasLA=new int[4];
        rentasLA[0]=2500;
        rentasLA[1]=5000;
        rentasLA[2]=10000;
        rentasLA[3]=20000;
        
        laAmericana=new Propiedad(
            "LINEA AEREA AMERICANA",20000,10000,Lotes.LINEA_AEREA,rentasLA);
        laEuropea=new Propiedad(
            "LINEA AEREA EUROPEA",20000,10000,Lotes.LINEA_AEREA,rentasLA);
        laAsiatica=new Propiedad(
            "LINEA AEREA ASIATICA",20000,10000,Lotes.LINEA_AEREA,rentasLA);
        laAfricana=new Propiedad(
            "LINEA AEREA AFRICANA",20000,10000,Lotes.LINEA_AEREA,rentasLA);
        
        //Creación de consulado y embajada
        embajada=new Propiedad("EMBAJADA",15000,7500,Lotes.NEUTRALES,1000);
        consulado=new Propiedad("CONSULADO",15000,7500,Lotes.NEUTRALES,1000);
     
        //Agregando líneas aéreas, embajada y consulado a propiedades
        propiedades.add(laAmericana);
        propiedades.add(laEuropea);
        propiedades.add(laAsiatica);
        propiedades.add(laAfricana);
        propiedades.add(embajada);
        propiedades.add(consulado);
        
        //2. Crear jugadores
        jugadores = new ArrayList<>();
        for(int i=0;i<Lotes.NUM_JUGADORES;i++){
            jugadores.add(new Jugador("Player "+(i+1),Lotes.CAPITAL_INICIAL));
            
        }
        
        //3. Crear un tablero de juego
        // El orden de las casillas está en el archivo tablero.txt
        tablero = new ArrayList<>();
        try{
            data = new BufferedReader(new FileReader("tablero.txt"));
            while((linea=data.readLine())!=null){
                tablero.add(linea);
            }
        }
        catch(IOException e){}
        
        //4. Crear ventana y paneles de juagadores
        ventana = new TuristaMundialJFrame();
        for (int i = 0; i < jugadores.size(); i++) {
            Jugador jugador = jugadores.get(i);
            TableroPanel panel = new TableroPanel(jugador);
            panel.setVisible(true);
            ventana.add(panel,BorderLayout.CENTER);
        }
        ventana.pack();
        ventana.setVisible(true);
        
        //5. Sortear turnos para ver que jugador inicia
        int turno=1;
        int index;
        Jugador jugadorActual = TuristaMundial.sorteaTurnos(jugadores);
        //System.out.println("Ganador: "+jugadorActual.getNombre());
        
        //Asignar primer turno al jugador ganador y el resto de los turnos
        //en secuencia...
        index=jugadores.indexOf(jugadorActual);
        for(int i=0;i<jugadores.size();i++){
            jugadores.get(index).setTurno(turno);
            index++;
            turno++;
            if(index==jugadores.size()){
                index=0;
            }
        }
        //Mostrar turnos
        for (int i = 0; i < jugadores.size(); i++) {
            System.out.println(
                "Turno "+jugadores.get(i).getTurno()+
                ": "+jugadores.get(i).getNombre()
            );
        }
        
        //5. Empezar el juego
        System.out.println("\n\tINICIANDO EL JUEGO DE TURISTA MUNDIAL\n");
        int dados,ubicacion;
        String casilla;
        Propiedad propiedadActual;
        try{Thread.sleep(3000);}catch(InterruptedException e){}
        
        dados=jugadorActual.tiraDados(2);
        System.out.println(jugadorActual.getNombre()+" tira un "+dados);
        jugadorActual.avanzar(dados);
        ubicacion=jugadorActual.getUbicacion();
        
        System.out.print(
                jugadorActual.getNombre()+" llega a la casilla "+ubicacion);
        casilla=tablero.get(ubicacion);
        System.out.println(": "+casilla);
        
        try{Thread.sleep(3000);}catch(InterruptedException e){}
        
        //La casilla puede ser propiedad o no propiedad
        //No propiedades:
        //  FAX, EMAIL, PLATINO, DEPORTADO, OCEANIA, GROENLANDIA, ADUANA, MEXICO
        propiedadActual=TuristaMundial.getPropiedad(casilla);
        if (propiedadActual!=null){
            //La casilla es una propiedad
            if (propiedadActual.isEnVenta()){
                //propiedad en venta puede comprarse
                System.out.println(
                "La propiedad "+propiedadActual.getNombre()+" está en venta"+
                " y cuesta $"+propiedadActual.getPrecio()
                );
                System.out.println(
                    "El jugador"+jugadorActual.getNombre()+" desea comprarla?"
                );
                //Esperar 5 segundos
                try{ Thread.sleep(5000);}catch(InterruptedException e){}
                if(jugadorActual.quiereComprar(propiedadActual)){
                    //Comprar propiedad
                    jugadorActual.compraPropiedad(propiedadActual);
                    System.out.println(
                        "El jugador"+jugadorActual.getNombre()+
                        " ha comprado "+propiedadActual.getNombre()
                    );
                }
                else{
                    //Subastar propiedad
                    System.out.println(
                        "La propiedad "+propiedadActual.getNombre()+
                        " se subasta al mejor postor."
                    );
                }
            }
            else{
                //la propiedad tiene dueño
                System.out.println(
                "La propiedad "+propiedadActual.getNombre()+" le pertenece a "+
                 propiedadActual.getPropietario()
                );
                
            }
        }
        else{
            //la casilla no es una propiedad
        }
    }
    
}
