import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.HashMap;

public class MultiCache extends Caches
{
    ArrayList<Coordenadas> coord = new ArrayList<>();
    private String obj = "";
    
    /*
     * Construtores 
     */
    
    //Construtor vazio
    public MultiCache()
    {
      super();
      this.obj ="";
      this.coord = null;
    }
    //Construtor parametros
    public MultiCache(ArrayList<Coordenadas> coords, String objt, String codigo, String criador, GregorianCalendar data, String descricao_extra, Coordenadas coordenadas) {
    super(codigo, criador, data, descricao_extra, coordenadas);
    this.obj = objt;
    this.coord = new ArrayList<Coordenadas>(coords);
    }
    
    //Construtor cópia 
    public MultiCache(MultiCache mc)
    {
    super(mc.getCodigo(), mc.getCriador(), mc.getData(), mc.getDesc(), mc.getCoord());
    this.coord = mc.getCoordenadas();
    this.obj = mc.getObj();
    }
    
    //GETS
    public ArrayList<Coordenadas> getCoordenadas(){
    return this.coord;}
    
    public String getObj(){
    return this.obj;}
    
    //SETS
    
    public void setCoord(ArrayList<Coordenadas> coords){
    this.coord = coords;
    }
    
    public void setObj(String objt){
    this.obj = objt;}
    
    
    /*
     * Métodos
     */
    
    public String toString(){
    StringBuilder s = new StringBuilder();
    s.append("\nCódigo: " + this.getCodigo());
    s.append("\nDescrição: " + this.getDesc());
    s.append("\nObjeto secreto: " + this.getObj());
    s.append("\nLocalização: " + this.getCoordenadas());
    s.append("\nData de criação: " + this.getData());
    return s.toString();
    }
    
    
    
    
    
    
    
    
    }

