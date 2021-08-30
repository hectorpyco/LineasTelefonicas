/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id$ 
 * Universidad de los Andes (Bogotá - Colombia) 
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1  
 * 
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co) 
 * Ejercicio: n1_lineasTelefonicas
 * Autor: Pablo Barvo - Dec 6, 2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.lineasTelefonicas.mundo;
import com.opencsv.*;

import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Clase que representa una línea telefónica
 */
public class LineaTelefonica
{

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Número de llamadas realizadas
     */
    private int numeroLlamadas;

    /**
     * Número de minutos consumidos
     */
    private int numeroMinutos;

    /**
     * Costo total de las llamadas
     */
    private double costoLlamadas;
    /**
     * Precio llamada local
     */
    private double precioLocal;//C.C
    
    /**
      Precio llamada a larga distancia 
     */
    private double precioDistancia;//C.C
    
    /**
     * Precio llamada por celular
     */
    private double precioCelular;//C.C
    /**
     * Lista de precios
     */
    private ArrayList<String[]> allData = new ArrayList<String[]>();//C.C
    

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Inicializa la línea telefónica <br>
     * <b>post: </b> La línea se inicializó con valores en cero.
     */
    public void inicializar( )
    {
        //
        //Inicializa los valores
        numeroLlamadas = 0;
        numeroMinutos = 0;
        costoLlamadas = 0;
        precios_defaul();
    }

    /**
     * Reinicia la línea telefónica, dejando todos sus valores en cero.
     */
    public void reiniciar( )
    {
        inicializar();
        
    }
    /**
     * Devuelve el costo total de las llamadas realizadas
     * @return Costo total de las llamadas realizadas
     */
    public double darCostoLlamadas( )
    {
        return costoLlamadas;
    }

    /**
     * Devuelve el número de llamadas realizadas por esta línea
     * @return Número de llamadas realizadas por esta línea
     */
    public int darNumeroLlamadas( )
    {
        return numeroLlamadas;
    }

    /**
     * Devuelve el número de minutos consumidos
     * @return Número de minutos consumidos
     */
    public int darNumeroMinutos( )
    {
        return numeroMinutos;
    }

    /**
     * Agrega una llamada local a la línea telefónica <br>
     * <b>post: </b> Se incremento en 1 numeroDeLlamadas, se incremento numeroDeMinutos en minutos, costoLlamadas aumento en ( minutos * 35 )
     * @param minutos Número de minutos de la llamada. minutos >0.
     */
    public void agregarLlamadaLocal( int minutos )
    {
        //
        //Una llamada más
        numeroLlamadas = numeroLlamadas + 1;
        //
        //Suma los minutos consumidos
        numeroMinutos = numeroMinutos + minutos;
        //
        //Suma el costo
        costoLlamadas = costoLlamadas + ( minutos * precioLocal);
    }

    /**
     * Agrega una llamada de larga distancia a la línea telefónica <br>
     * <b>post: </b> Se incremento en 1 numeroDeLlamadas, se incremento numeroDeMinutos en minutos, costoLlamadas aumento en ( minutos * 380 )
     * @param minutos Número de minutos de la llamada. minutos >0.
     */
    public void agregarLlamadaLargaDistancia( int minutos )
    {
        //
        //Una llamada más
        numeroLlamadas = numeroLlamadas + 1;
        //
        //Suma los minutos consumidos
        numeroMinutos = numeroMinutos + minutos;
        //
        //Suma el costo
        costoLlamadas = costoLlamadas + ( minutos * precioDistancia );
    }

    /**
     * Agrega una llamada a celular a la línea telefónica <br>
     * <b>post: </b> Se incremento en 1 numeroDeLlamadas, se incremento numeroDeMinutos en minutos, costoLlamadas aumento en ( minutos * 999 )
     * @param minutos Número de minutos de la llamada. minutos >0.
     */
    public void agregarLlamadaCelular( int minutos )
    {
        //
        //Una llamada más
        numeroLlamadas = numeroLlamadas + 1;
        //
        //Suma los minutos consumidos
        numeroMinutos = numeroMinutos + minutos;
        //
        //Suma el costo
        costoLlamadas = costoLlamadas + ( minutos * precioCelular );
    }

    public void agregarNuevoPrecioLlamadaLocal(int nuevoPrecio) {
    	precioLocal = nuevoPrecio;//C.C
    	escribe();//C.C
    }
    public void agregarNuevoPrecioLlamadaDistancia(int nuevoPrecio) {
    	precioDistancia = nuevoPrecio;//C.C
    	escribe();//C.C
    }
    public void agregarNuevoPrecioLlamadaCelular(int nuevoPrecio) {
    	precioCelular = nuevoPrecio;//C.C
    	escribe();//C.C
    }
  //Metodo para retornar un array con los precios
    //C.C
    public String[] getArray(){
        String[] data = new String[]{String.valueOf(precioLocal),String.valueOf(precioDistancia),String.valueOf(precioCelular)};
        return data;
    } 
    //Metodo para leer los precios del archivo cvs
    //C.C
     public void precios_defaul(){
      	try {
              CSVReader lector = new CSVReader(new FileReader("Precios.csv"), ';', '"', 1);
              String[]nextline;
              nextline = lector.readNext();
              precioLocal=Integer.parseInt(nextline[0]);
              precioDistancia=Integer.parseInt(nextline[1]);
              precioCelular =Integer.parseInt(nextline[2]);	
      	}catch (IOException e) {
              System.out.println(e);
              JOptionPane.showMessageDialog(null, "No se a podido guardar los datos");
  	}
      	
      }
     //Metodo para escribir los precios en el archivo cvs
     //C.C
      public void escribe() {
          try{
              String csv = "Precios.csv";
              CSVWriter writer = new CSVWriter(new FileWriter(csv),';');
              String[] header= new String[]{"Local","Distancia","Celular"};
              writer.writeNext(header);
              String[] header_1= new String[]{"35","380","999"};
              writer.writeNext(header_1);
              String[] precios= getArray();
              allData.add(precios);
              writer.writeAll(allData);
              writer.close();
          }catch(IOException e) {
              System.out.println(e);
              JOptionPane.showMessageDialog(null, "No se a podido guardar los datos");
          }   
      }
      
}