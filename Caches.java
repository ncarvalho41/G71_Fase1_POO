import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Calendar;

public abstract class Caches implements Serializable
{
   private String codigo;
   private String criador;
   private GregorianCalendar data;
   private String descricao_extra;
   private Coordenadas coordenadas;

   /*
    * Construtores
    */
   
   //Construtor vazio
   
   public Caches()
   {
   this.codigo = "";
   this.criador = "";
   this.data = new GregorianCalendar();
   this.descricao_extra = "";
   this.coordenadas = new Coordenadas();
  }
  
  //Construtor parametros
  
  public Caches(String cod, String cr, GregorianCalendar dt, String desc_xt, Coordenadas coord)
  {
  this.codigo = cod;
  this.criador = cr;
  this.data = dt;
  this.descricao_extra = desc_xt;
  this.coordenadas = new Coordenadas(coord);
 }
 
 //Construtor copia
 
 public Caches(Caches c)
 {
 this.codigo = c.getCodigo();
 this.criador = c.getCriador();
 this.data = c.getData();
 this.descricao_extra = c.getDesc();
 this.coordenadas = c.getCoord();
}

//GETS

public String getCodigo()
{
return this.codigo;
}
public String getCriador(){
return this.criador;
}
public GregorianCalendar getData(){
return this.data;
}
public String getDesc(){
return this.descricao_extra;
}
public Coordenadas getCoord(){
return this.coordenadas;
}

//SETS

public void setCodigo(String cd) {
this.codigo = cd;}
public void setCriador(String cr){
this.criador = cr;}
public void setData(GregorianCalendar dt){
this.data = dt;}
public void setDesc(String desc_x){
this.descricao_extra = desc_x;}
public void setCoord(Coordenadas crd){
this.coordenadas = crd;
}








 
}

