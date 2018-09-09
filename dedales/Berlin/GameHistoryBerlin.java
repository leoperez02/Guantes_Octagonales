import java.io.*;

public class GameHistoryBerlin {
	public static void main(String a[]) {
		try {
			String cadena;
			FileReader f = new FileReader("TotalDiamantes.txt");
			BufferedReader b = new BufferedReader(f);
			if((cadena = b.readLine())!=null) {
				new MenuInicial(Integer.parseInt(cadena));
			} else {
				new MenuInicial(0);
			}
			b.close();
		} catch(Exception e) {e.printStackTrace();}
	}
}