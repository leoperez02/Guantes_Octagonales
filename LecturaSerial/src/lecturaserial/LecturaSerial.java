package lecturaserial;

import java.util.List;
import giovynet.nativelink.*;
import giovynet.serial.*;
import giovynet.serial.ActionListenerReadPort;
import giovynet.serial.Buffer;

public class LecturaSerial implements ActionListenerReadPort{

    private static final int n_bits = 8 ;
    private SerialPort puerto; 
    private List<String> listaPuertos; 
    Com comN;
    private StringBuilder dato;
    private Parameters settings;
    private String lectura;
    
    public static void main(String[] args){
        
        LecturaSerial lectorSerial = new LecturaSerial();
        List<String> lista;
        lista = lectorSerial.getFreePorts();
        System.out.println("Puertos COM disponibles:");
        lista.stream().forEach((listaPuerto) -> {
            System.out.println(listaPuerto);
        });
        if(lista.size()==1){
            lectorSerial.getConexion(lista.get(0));
            System.out.println("Conexión creada en "+lista.get(0));
        }
        else{
            lectorSerial.getConexion(lista.get(1));
            System.out.println("Conexión creada en "+lista.get(1));
        }
        System.out.println("Esperando datos del puerto ...");
        String s;
        try{
            while((s=lectorSerial.getLectura()) != null){
                if(!s.equals("")){
                    System.out.println("byte:"+s);
                    Thread.sleep(100);
                }
                else{
                    System.out.println("Nada de nada");
                    Thread.sleep(100);
                }
            }
        }catch(Exception e){
        }
        System.out.println("Conexión terminada");
        lectorSerial.close();
        System.exit(0);
    }
    
    public LecturaSerial(){
        
        try{
            dato = new StringBuilder(n_bits);
            lectura = "";
            puerto = new SerialPort();
            listaPuertos = puerto.getFreeSerialPort();
            settings = new Parameters();
        }
        catch(Exception e){
        }
    }
    
    public void getConexion(String com){
        
        try{
            settings.setPort(com); 
            settings.setBaudRate(Baud._9600);
            comN = new Com(settings);
            comN.addActionListenerReadPort(this);
        }
        catch(Exception e){
        }
    }
    
    public void close(){
        try{
            comN.close();
        }
        catch(Exception e){}
    }
    
    List<String> getFreePorts(){
        return listaPuertos;
    }
    public String getLectura(){
        return lectura;
    }
    
    @Override
    public void tryActionPerformed(Buffer b){
        System.out.println("Algo por aquí ...");
        if(dato.length()==n_bits){
            lectura = dato.toString();
            dato = new StringBuilder(n_bits);
        }
        else{    
            try{
                comN.receiveToStringBuilder(n_bits,dato);
            }
            catch(Exception e){
            }
        }
    }
    
    @Override
    public void catchActionPerformed(Exception e){
        System.out.println("Algo por acá ...");
        lectura = null;
    }

}