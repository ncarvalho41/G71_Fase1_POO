import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Calendar;

public class Coordenadas implements Serializable
{
    private Coord latitude;
    private Coord longitude;
    
    /*
     * Construtores
     */
    
    //Construtor vazio
    
    public Coordenadas()
    {
    this.latitude = null;
    this.longitude = null;
    }
    //Construtor Parametros
    
    public Coordenadas (Coord lat, Coord longt)
    {
    this.latitude = lat;
    this.longitude = longt;
    }
    
    //Construtores Copia
    
    public Coordenadas(Coordenadas c)
    {
    this.latitude = c.getLat();
    this.longitude = c.getLongt();
    }

   //GETS
   
   public Coord getLat()
   {return this.latitude;}
   
   public Coord getLongt()
   {return this.longitude;}
   
   //SETS
   
   public void setLat(Coord lat)
   {this.latitude = lat;}
   
   public void setLongt(Coord longt)
   {this.longitude = longt;}
   
}
