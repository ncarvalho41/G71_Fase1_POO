
import java.util.GregorianCalendar;
import java.util.Calendar;

public class Actividades
{
    private String nomeAt;    
    private String codCache;   
    private String descricao; 
    private Meteorologia meteo;   
    private String objRetirado;
    private String objColocado; 
    private GregorianCalendar data; 
    private int dificuldade;

    /*
     *  Construtores
     */
    //Construtor vazio
    
    public Actividades () {
    this.nomeAt = "";
    this.codCache = "";
    this.descricao = "";
    this.meteo = new Meteorologia();
    this.objRetirado = "";
    this.objColocado = "";
    this.data = new GregorianCalendar();
    this.dificuldade = 0;
    }
    
    //Construtor parâmetro
    
    public Actividades(String nome, String cod, String desc, Meteorologia met, String objR, String objC, GregorianCalendar dt, int dif){
    this.nomeAt = nome;
    this.codCache = cod;
    this.descricao = desc;
    this.meteo = met;
    this.objRetirado = objR;
    this.objColocado = objC;
    this.data = dt;
    this.dificuldade = dif;
    }
    
    //Construtor cópia
    public Actividades(Actividades a){
    this.nomeAt = a.getNome();
    this.codCache = a.getCod();
    this.descricao = a.getDesc();
    this.meteo = a.getMeteo();
    this.objRetirado = a.getObjR();
    this.objColocado = a.getObjC();
    this.data = a.getData();
    this.dificuldade = a.getDif();
    }
    
    //GETS
    
    public String getNome(){
    return this.nomeAt;}
    
    public String getCod(){
    return this.codCache;}
    
    public String getDesc(){
    return this.descricao;}
    
    public Meteorologia getMeteo(){
    return this.meteo;}
    
    public String getObjR(){
    return this.objRetirado;}
    
    public String getObjC(){
    return this.objColocado;}
    
    public GregorianCalendar getData(){
    return this.data;}
    
    public int getDif(){
    return this.dificuldade;}
    //SETS
    
    public void setNome(String nm){
    this.nomeAt = nm;
    }
    
    public void setCod(String cod){
    this.codCache = cod;}
    
    public void setDesc(String desc){
    this.descricao = desc;}
    
    public void setMeteo(Meteorologia met){
    this.meteo = met;}
    
    public void setObjR(String objR){
    this.objRetirado = objR;}
    
    public void setObjC(String objC){
    this.objColocado = objC;}
    
    public void setData(GregorianCalendar dt){
    this.data = dt;}
    
    public void setDif(int dific){
    this.dificuldade = dific;}
    
    
    /*
     *  Métodos
     */
    
    public String toString(){
    
    StringBuilder s = new StringBuilder();
    
    s.append("Nome: " + this.nomeAt + "\n");
    s.append("Código: " + this.codCache + "\n");
    s.append("Descrição: " +this.descricao + "\n");
    s.append("Objeto Recolhido: " + this.objRetirado + "\n");
    s.append("Objeto Colocado: " + this.objColocado + "\n");
    s.append("Data :" + this.data.get(Calendar.DAY_OF_MONTH) + "/" + this.data.get(Calendar.MONTH) + "/" + this.data.get(Calendar.YEAR) + "\n");
    s.append("Nível de dificuldade: " + this.dificuldade + "\n");
    return s.toString(); 
   }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
    
    
    
    
    
