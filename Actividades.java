
import java.util.GregorianCalendar;

public class Actividades
{
  
    private GregorianCalendar inicio;
    private GregorianCalendar fim;
    private String tipo;
     
    public Actividades(){
    this.inicio=new GregorianCalendar();
    this.fim=new GregorianCalendar();
    this.tipo="";
    }
    
   public Actividades (GregorianCalendar inicio, GregorianCalendar fim, String tipo) {
   this.inicio = inicio;
   this.fim = fim;
   this.tipo = tipo;
   } 
    
   public Actividades(Actividades u) {
   this.inicio=u.getInicio();
   this.fim=u.getFim();
   this.tipo=u.getTipo();
 
   }
   
   public GregorianCalendar getInicio(){
   return this.inicio;
   }
   
   public GregorianCalendar getFim(){
   return this.fim;
   }
   
   public String getTipo(){
   return this.tipo;}

   public void setInicio (GregorianCalendar i) {
   this.inicio= i;}

   public void setFim (GregorianCalendar f) {
   this.fim= f;}
   
   public void setTipo (String t) {
   this.tipo = t;}
}
    
    
    
    
    
    
