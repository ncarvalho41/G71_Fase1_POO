import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.*;


public class CacheMisterio extends Caches
{
    private String adivinhaP;
    private String adivinhaR;
    private String obj;

    /*
     * Construtores
     */
    
    //Construtor vazio
    public CacheMisterio() 
    {
        super();
        this.adivinhaP = "";
        this.adivinhaR = "";
        
    }
    //Construtor parametros
    public CacheMisterio(String adP, String adR, String objt, String codigo, String criador, GregorianCalendar data, String descricao_extra, Coordenadas coordenadas)
    {
    super(codigo, criador, data, descricao_extra, coordenadas);
    this.adivinhaP = adP;
    this.adivinhaR = adR;
    this.obj = objt;
    }
    //Construtor copia
    public CacheMisterio(CacheMisterio cm)
    {
    super(cm.getCodigo(), cm.getCriador(), cm.getData(), cm.getDesc(), cm.getCoord());
    this.adivinhaP = cm.getAdP();
    this.adivinhaR = cm.getAdR();
    this.obj = cm.getObj();
    }
    
    //GETS
    public String getAdP()
    {
    return this.adivinhaP;}
    
    public String getAdR()
    {
    return this.adivinhaR;}
    
    public String getObj() {
    return this.obj;}
    
    //SETS
    public void setAdP(String adP)
    {
    this.adivinhaP = adP;}
    
    public void setAdR(String adR)
    {
    this.adivinhaP = adR;}
    
    public void setObj(String objt) {
    this.obj = objt;}
    
    public String toString(){
    StringBuilder s = new StringBuilder();
    s.append("\nCódigo: " + this.getCodigo());
    s.append("\nDescrição: " + this.getDesc());
    s.append("\nObjeto secreto: " + this.getObj());
    s.append("\nLocalização: " + this.getCoord());
    s.append("\nData de criação: " + this.getData());
    return s.toString();
    }


}