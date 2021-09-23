import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LeerArchivo {
	  public static void main(String args[]) {
		    leerCarpeta("C:\\Users\\JessicaSarahiEspinos\\Desktop\\qna17");
		  }
	  
	  public static void  leerCarpeta (String ruta){
		  File f = new File(ruta);
		  File[] ficheros = f.listFiles();
		  for (int x=0;x<ficheros.length;x++){
		     String pathCompleta = ruta + "\\" + ficheros[x].getName();
		     String nombreNuevo = ruta + "\\new_" + ficheros[x].getName();
		     System.out.println(pathCompleta);
		     System.out.println(nombreNuevo);
		     leerArchivo(pathCompleta,nombreNuevo);
		  }
		  System.out.println("Fin");
		  
	  }

	public static void leerArchivo(String ruta, String nombreNuevo){
		 int numLinea=0;
		 FileWriter fichero = null;
		 PrintWriter pw = null;
		 try {
		 FileReader fr = new FileReader(ruta);
		 BufferedReader br = new BufferedReader(fr);
		 fichero = new FileWriter(nombreNuevo);
		 pw = new PrintWriter(fichero);
		 String linea;
		 String lineaNew;
		 while((linea = br.readLine()) != null){
		 numLinea++;
			lineaNew = linea.replaceAll(" +", " ");
			lineaNew = lineaNew.replaceAll(" \\|","|");
			lineaNew = lineaNew.replaceAll("\\| ", "|");
			lineaNew = lineaNew.replaceAll("^ ", "");
			lineaNew = lineaNew.replaceAll(" $", "");
				lineaNew = lineaNew.replaceAll("\n|","");
				lineaNew = lineaNew.replaceAll("|\r","");

//lineaNew = cambiarFechaNomina(lineaNew);
// lineaNew = linea.replaceAll(" ", "");

// lineaNew = cambiarFechaPlazas(lineaNew);
		 pw.println(lineaNew);
		   }
		   fichero.close();
		   fr.close();
		 }
		 catch(Exception e) {
		 System.out.println("Excepcion leyendo fichero "+ ruta + ": " + e);
		 }
	 }
	  
	  
	  //para los archivos de ANLPLAza por la ubicacion de los '|'
	  public static String cambiarFechaNomina(String cadena) {
	        int posicion, contador = 0;
	        char caracter = '|';
	        posicion = cadena.indexOf(caracter);
	        String fechaNueva;
	        String cadenaTratada = "";
	        while (posicion != -1) { //mientras se encuentre el caracter
	            contador++;           //se cuenta
	            //se sigue buscando a partir de la posici�n siguiente a la encontrada
	            posicion = cadena.indexOf(caracter, posicion + 1);
	            if(contador == 20){//	            	
	             String fecha = cadena.substring(posicion+1,posicion+9);
		         fechaNueva=ParseFecha(fecha);
		         cadenaTratada = (cadena.substring(0, posicion+1))+fechaNueva;
	          //   System.out.println("Cadena Nueva :" +cadenaTratada);
	            }
	        }
	        return cadenaTratada;
	}
	  
	  //para los archivos de ANLPLAza por la ubicacion de los '|'
	  public static String cambiarFechaPlazas(String cadena) {
	        int posicion, contador = 0;
	        char caracter = '|';
	        posicion = cadena.indexOf(caracter);
	        String fechaNueva;
	        String cadenaTratada = "";
	        while (posicion != -1) { //mientras se encuentre el caracter
	            contador++;           //se cuenta
	            //se sigue buscando a partir de la posici�n siguiente a la encontrada
	            posicion = cadena.indexOf(caracter, posicion + 1);
	           
	            if(contador == 13 || contador == 14){
	            	if(contador == 13  && cadena.charAt(posicion+1)!='|'){
	            		String fecha = cadena.substring(posicion+1,posicion+9);
		              	fechaNueva=ParseFecha(fecha);
		             cadenaTratada =cadena.replace(fecha,fechaNueva);
	            	}
	            	if(contador == 14 && (posicion+1)!=cadena.length()){
	            		String fecha = cadena.substring(posicion+1,posicion+9);
		              	fechaNueva=ParseFecha(fecha);
		            cadenaTratada=	cadenaTratada.replace(fecha,fechaNueva);
	            	}else if((posicion+1)==cadena.length()){
	            		cadenaTratada = cadena; 
	            	}
	            	//System.out.println("Cadena Nueva :" +cadenaTratada);
	            }
	        }
	        return cadenaTratada;
	}
	  
	    public static String ParseFecha(String fecha)
	    {
	    	//"01/04/17"
	        String fechaSeparada [] = fecha.split("/"); 
	        StringBuilder fechaChida  = new StringBuilder();
	        String anio = "20" +fechaSeparada[2] + "/";
	        fechaChida.append("20"+ fechaSeparada[2] +"/");
	        fechaChida.append(fechaSeparada[1]+"/");
	        fechaChida.append(fechaSeparada[0]);
	        return fechaChida.toString();
	        
	    }

	    
	    public static String FechaCeroANull(String fechaCero){
	    	//0000-00-00
	    	String cadenaNueva = null;
	    	cadenaNueva = fechaCero.replace("0000-00-00", "NULL");
	    	 return cadenaNueva;
	    }
	    
	    public static String LimpiarCadena (String cadena){
	    	String[] arrayCadenas = cadena.split("|");
	    	StringBuilder cadenaNueva = new StringBuilder();
	    	for(int i = 0; i < arrayCadenas.length;i++){
	    		String temp = arrayCadenas[i].trim();
	    		cadenaNueva.append(temp);
	    	}
	    	
	    	return cadenaNueva.toString();
	    }
}
