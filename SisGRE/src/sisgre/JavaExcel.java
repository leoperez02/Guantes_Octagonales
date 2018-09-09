/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sisgre;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Leo
 */
public class JavaExcel {
    
    public static void escribeHoja(String filename,List data,String sheetName){
        
        FileInputStream fis;
        XSSFWorkbook workbook;
        XSSFSheet hoja ;
        
        //Intentar abrir el archivo de excel 'filename'
        try {
            fis = new FileInputStream(filename);
            workbook = new XSSFWorkbook(fis);
        }
        catch(FileNotFoundException e){
            //Si el archivo 'filename' no se pudo abrir, se crea.
            workbook = new XSSFWorkbook(); 
        }
        catch(IOException e){
            //Si ocurre otro error, se crea el archivo
            workbook = new XSSFWorkbook();
        } 
        
        //Escribir resultados en excel
        try{
            hoja= workbook.createSheet(sheetName);
        }  
        catch(java.lang.IllegalArgumentException e){
            hoja = workbook.createSheet(sheetName+" (2)");
        }
        
        XSSFRichTextString texto;
        XSSFCell celda;
        XSSFRow fila;
        String atrib[];
        String linea;
        Correo correo;
        int rownum=0;
        
        while(rownum<data.size()){

            // Se crea una fila dentro de la hoja 
            fila = hoja.createRow(rownum);

            //Se recupera el objeto de la lista
            Class<?> objeto = data.get(rownum).getClass();
            if("String".equals(objeto.getSimpleName())){
                linea = (String)data.get(rownum);
                atrib = linea.split("\t");
            }
            else{
                correo = (Correo)data.get(rownum);
                atrib = correo.toLine().split("\t");
            }

            //Se escribe la fila de datos en el libro
            for(int i=0 ; i<atrib.length ; i++){
                // Se crea una celda dentro de la fila 
                celda = fila.createCell(i); 
                // Se crea el contenido de la celda y se mete en ella. 
                texto = new XSSFRichTextString(atrib[i]); 
                celda.setCellValue(texto);
            }
            rownum++;
        }
       
        // Se salva el libro. 
        try { 
            FileOutputStream elFichero = new FileOutputStream(filename); 
            workbook.write(elFichero);  
            elFichero.close(); 
        } 
        catch (IOException e) { 
            e.printStackTrace();
        }   
    }
   
    public static void escribirLibro3(String filename,ConexionBD con){
        
        try (FileInputStream fis = new FileInputStream(filename)) { 
            // Open an excel workbook from the file system. 
            XSSFWorkbook workbook = new XSSFWorkbook(fis); 
            // Nota: la hoja 0 corresponde a 'Enviados faltantes'
            XSSFSheet hoja = workbook.getSheetAt(1); 
            
            //Escribir resultados en excel
        
            //Obtener resultados de BD
            String query="select Para,Asunto,Enviado_el,Folio from enviados";
            ResultSet relacionados=con.consulta(query);
            try{
                XSSFRichTextString texto;
                XSSFCell celda;
                XSSFRow fila;
                int rownum=0;
                while(relacionados.next()){
                    
                    // Se crea una fila dentro de la hoja 
                    fila = hoja.createRow(rownum);
 
                    // Se crea una celda dentro de la fila 
                    celda = fila.createCell(0); 
                     // Se crea el contenido de la celda y se mete en ella. 
                    texto = new XSSFRichTextString(relacionados.getString("Para")); 
                    celda.setCellValue(texto); 

                    celda = fila.createCell(1); 
                     // Se crea el contenido de la celda y se mete en ella. 
                    texto = new XSSFRichTextString(relacionados.getString("Asunto")); 
                    celda.setCellValue(texto);
                    
                    celda = fila.createCell(2); 
                     // Se crea el contenido de la celda y se mete en ella. 
                    texto = new XSSFRichTextString(relacionados.getString("Enviado_el")); 
                    celda.setCellValue(texto);
                    
                    celda = fila.createCell(3); 
                     // Se crea el contenido de la celda y se mete en ella. 
                    texto = new XSSFRichTextString(relacionados.getString("Folio")); 
                    celda.setCellValue(texto);
                    
                    rownum++;
                }
            }catch(SQLException e){

            }
            con.finalizaConsulta();
            // Se salva el libro. 
            try { 
                FileOutputStream elFichero = new FileOutputStream(filename); 
                workbook.write(elFichero);  
                elFichero.close(); 
            } catch (IOException e) { 
                e.printStackTrace();
            } 
           
        }catch(IOException e) {
        }
    }
    
    public static void escribirLibro2(String filename,ConexionBD con){
        
        try (FileInputStream fis = new FileInputStream(filename)) { 
            // Open an excel workbook from the file system. 
            XSSFWorkbook workbook = new XSSFWorkbook(fis); 
            // Nota: la hoja 0 corresponde a 'Recibidos faltantes'
            XSSFSheet hoja = workbook.getSheetAt(0); 
            
            //Escribir resultados en excel
        
            //Obtener resultados de BD
            String query="select De,Asunto,Recibido_el,Folio from recibidos";
            ResultSet relacionados=con.consulta(query);
            try{
                XSSFRichTextString texto;
                XSSFCell celda;
                XSSFRow fila;
                int rownum=0;
                while(relacionados.next()){
                    
                    // Se crea una fila dentro de la hoja 
                    fila = hoja.createRow(rownum);
 
                    // Se crea una celda dentro de la fila 
                    celda = fila.createCell(0); 
                     // Se crea el contenido de la celda y se mete en ella. 
                    texto = new XSSFRichTextString(relacionados.getString("De")); 
                    celda.setCellValue(texto); 

                    celda = fila.createCell(1); 
                     // Se crea el contenido de la celda y se mete en ella. 
                    texto = new XSSFRichTextString(relacionados.getString("Asunto")); 
                    celda.setCellValue(texto);
                    
                    celda = fila.createCell(2); 
                     // Se crea el contenido de la celda y se mete en ella. 
                    texto = new XSSFRichTextString(relacionados.getString("Recibido_el")); 
                    celda.setCellValue(texto);
                    
                    celda = fila.createCell(3); 
                     // Se crea el contenido de la celda y se mete en ella. 
                    texto = new XSSFRichTextString(relacionados.getString("Folio")); 
                    celda.setCellValue(texto);
                    
                    rownum++;
                }
            }catch(SQLException e){

            }
            con.finalizaConsulta();
            // Se salva el libro. 
            try { 
                FileOutputStream elFichero = new FileOutputStream(filename); 
                workbook.write(elFichero);  
                elFichero.close(); 
            } catch (IOException e) { 
                e.printStackTrace();
            } 
           
        }catch(IOException e) {
        }
    }
    
    public static void escribirLibro(String filename, ConexionBD con,int index){
        
        
            // Open an excel workbook from the file system. 
            XSSFWorkbook workbook = new XSSFWorkbook(); 
            // Nota: la hoja 2 corresponde a 'x Tmpo. Resp.'
            
            XSSFSheet hoja ;
            hoja= workbook.createSheet("Recibidos faltantes");
            hoja= workbook.createSheet("Enviados faltantes");
            hoja= workbook.createSheet("x Tmpo. Resp."); 
            
            //Escribir resultados en excel
        
            //Obtener resultados de BD
            String query="select De,AsuntoRecibido,Recibido_el,tiempoRespuesta,";
            query+="Para,AsuntoEnviado,Enviado_el from relacion";
            ResultSet relacionados=con.consulta(query);
            try{
                XSSFRichTextString texto;
                XSSFCell celda;
                XSSFRow fila;
                int rownum=0;
                while(relacionados.next()){
                    
                    // Se crea una fila dentro de la hoja 
                    fila = hoja.createRow(rownum);
 
                    // Se crea una celda dentro de la fila 
                    celda = fila.createCell(0); 
                     // Se crea el contenido de la celda y se mete en ella. 
                    texto = new XSSFRichTextString(relacionados.getString("De")); 
                    celda.setCellValue(texto); 

                    celda = fila.createCell(1); 
                     // Se crea el contenido de la celda y se mete en ella. 
                    texto = new XSSFRichTextString(relacionados.getString("AsuntoRecibido")); 
                    celda.setCellValue(texto);
                    
                    celda = fila.createCell(2); 
                     // Se crea el contenido de la celda y se mete en ella. 
                    texto = new XSSFRichTextString(relacionados.getString("Recibido_el")); 
                    celda.setCellValue(texto);
                    
                    celda = fila.createCell(3); 
                     // Se crea el contenido de la celda y se mete en ella. 
                    texto = new XSSFRichTextString(Integer.toString(relacionados.getInt("tiempoRespuesta"))); 
                    celda.setCellValue(texto);
                    
                    celda = fila.createCell(4); 
                     // Se crea el contenido de la celda y se mete en ella. 
                    texto = new XSSFRichTextString(relacionados.getString("Para")); 
                    celda.setCellValue(texto);
                    
                    celda = fila.createCell(5); 
                     // Se crea el contenido de la celda y se mete en ella. 
                    texto = new XSSFRichTextString(relacionados.getString("AsuntoEnviado")); 
                    celda.setCellValue(texto);
                    
                    celda = fila.createCell(6); 
                     // Se crea el contenido de la celda y se mete en ella. 
                    texto = new XSSFRichTextString(relacionados.getString("Enviado_el")); 
                    celda.setCellValue(texto);
                    
                    rownum++;
                }
            }catch(SQLException e){

            }
            con.finalizaConsulta();
            // Se salva el libro. 
            try { 
                FileOutputStream elFichero = new FileOutputStream(filename); 
                workbook.write(elFichero);  
                elFichero.close(); 
            } catch (IOException e) { 
                e.printStackTrace();
            } 
           
       
    }
    
    public static List leerLibro(String filename,int hoja){
        
        List sheetData = new ArrayList();
        
        try (FileInputStream fis = new FileInputStream(filename)) { 
            // Create an excel workbook from the file system. 
            XSSFWorkbook workbook = new XSSFWorkbook(fis); 
            // Get the first sheet on the workbook.
            // Nota: la hoja cero corresponde a "Recibidos"
            XSSFSheet sheet = workbook.getSheetAt(hoja);
            // each sheet's rows and on each row's cells. We store the 
            // data read on an ArrayList so that we can printed the 
            // content of the excel to the console.
            Iterator rows = sheet.rowIterator(); 

            while (rows.hasNext()) { 
                //Recorrer cada fila mientras haya
                XSSFRow row = (XSSFRow) rows.next(); 
                Iterator cells = row.cellIterator();
                List data = new ArrayList(); //Almacena celdas de una sola fila
                while (cells.hasNext()) {
                    //Recorrer cada celda de la fila mientras haya
                    XSSFCell cell = (XSSFCell) cells.next(); 
                //  System.out.println("Añadiendo Celda: " + cell.toString()); 
                    data.add(cell); 
                } 
                sheetData.add(data);//Agrega la fila completada a la lista
            }
            fis.close();
        } catch (IOException e) { 
        }
        return sheetData;    
    }
    
    public static void showExelData(List sheetData) { 
        // Iterates the data and print it out to the console. 
        for (int i = 0; i < sheetData.size(); i++) { 
            List list = (List) sheetData.get(i); 
            for (int j = 0; j < list.size(); j++) { 
                Cell cell = (Cell) list.get(j); 
                try{
                    switch (j){
                        case 0: // de
                            System.out.print(cell.getStringCellValue());
                            break;
                        case 1: //asunto
                            System.out.print(cell.getStringCellValue());    
                            break;
                        case 2: //fecha
                            Date fecha = cell.getDateCellValue();
                            System.out.print(cell.getDateCellValue());
                            break;
                        default:
                            break;
                    }
                }catch(IllegalStateException e){
                    System.out.print(cell.getNumericCellValue());
                }
                if (j < list.size() - 1) { 
                    System.out.print(", "); 
                } 
            } 
        System.out.println(""); 
        } 
    } 
    
}
