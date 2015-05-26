import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.*;


public class CacheMisterio extends Caches
{
    private String puzzle;

    /*
     * Construtores
     */
    
    //Construtor vazio
    public CacheMisterio() 
    {
        super();
        this.puzzle = "";
    }
    //Construtor parametros
    public CacheMisterio(String pz, String codigo, String criador, GregorianCalendar data, String descricao_extra, Coordenadas coordenadas)
    {
    super(codigo, criador, data, descricao_extra, coordenadas);
    this.puzzle = pz;
    }
    //Construtor copia
    public CacheMisterio(CacheMisterio cm)
    {
    super(cm.getCodigo(), cm.getCriador(), cm.getData(), cm.getDesc(), cm.getCoord());
    this.puzzle = cm.getPuzzle();
    }
    
    //GETS
    public String getPuzzle()
    {
    return this.puzzle;}
    //SETS
    public void setPuzzle(String pz)
    {
    this.puzzle = pz;}
    
    
    
    


}