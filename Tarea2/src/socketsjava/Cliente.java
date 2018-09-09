package socketsjava;
import java.net.Socket;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Cliente{
	public static void main(String arg[]){
            System.out.println("Conectándose con el servidor...");
            try {
                //El cliente crea un socket para conectarse al servidor en la 
                //dirección 127.0.0.1 (localhost) en el puerto del servidor(5000)
                Socket socket=new Socket("127.0.0.1",Servidor.PUERTO);
                System.out.println("Cliente conectado\nLeyendo respuesta:");
                //Se obtiene el flujo de entrada para recibir los datos enviados
                //por el servidor, en este caso, en un ImputStreamReader
                InputStreamReader is=new InputStreamReader(socket.getInputStream());
                //Se crea un objeto BufferedReader para leer los objetos del
                //flujo de entrada como caracteres o cadenas
                BufferedReader br=new BufferedReader(is);
                //Se hace la lectura del buffer y se imprime en consola el mensaje
                //devuelto por el servidor
                System.out.println(br.readLine());
                //Se cierra el flujo de entrada
                br.close();
                //Se cierra la conexión con el servidor
                socket.close();
                //Finaliza la aplicación cliente
            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
}