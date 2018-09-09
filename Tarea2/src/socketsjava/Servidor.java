package socketsjava;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Servidor{
    //Puerto del servidor para escuchar las conexiones entrantes al servidor    
    public static final int PUERTO=5000;
    public static void main(String args[]){
        System.out.println("Iniciando servidor...");
        try {
            //Creando instancia del objeto ServerSocket para escuchar peticiones
            //en el puerto 5000
            ServerSocket socketServidor=new ServerSocket(PUERTO);
            //El socketservidor espera indefinidamente que un cliente se conecte
            System.out.println("Servidor funcionando");
            while(true){
                //Cuando un socket cliente se conecta, el socketservidor acepta
                //el socket entrante y puede crear los flujos de comunicación
                Socket socketCliente=socketServidor.accept();
                //Se obtiene un stream de salida para enviar información al cliente
                //En este caso se usa un PrintWriter() para escribir objetos
                PrintWriter pw=new PrintWriter(socketCliente.getOutputStream());
                //Se imprime un mensaje en el flujo de salida para enviarlo al
                //socket cliente
                pw.println("Hola mundo!");
                //Se cierra el flujo de salida
                pw.close();
                //Se finaliza la conexión con el socket cliente
                socketCliente.close();
                //El servidor vuelve a esperar a que se conecte un cliente
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
