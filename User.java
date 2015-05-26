import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.TreeSet;

public class User
{
   private String email;
   private String pw;
   private String nome;
   private String genero;
   private String morada;
   private GregorianCalendar data;
   private int pontos;
   
   private TreeSet<String> amigos;
   private TreeSet<String> pedidosAmizade;
   
   
    /*
     * Construtores
     */
    
   //Construtor vazio
   public User () {
   this.email ="";
   this.pw= "";
   this.nome="";
   this.genero="";
   this.morada="";
   this.data= new GregorianCalendar();
   this.pontos=0; 
   }
   //Construtor parametro
   
   public User (String mail, String pwd, String nm, String g,String mor, GregorianCalendar dt, int pts, TreeSet amgs, TreeSet pedAmz)
   {
        
   this.email=mail;
   this.pw=pwd;
   this.nome=nm;
   this.genero=g;
   this.morada=mor;
   this.data=dt;
   this.pontos=pts;
   this.amigos=amgs;
   this.pedidosAmizade=pedAmz;
   }
   //Construtor copia
   
   public User (User u) {
   this.email=u.getEmail();
   this.pw = u.getPw();
   this.nome=u.getNome();
   this.morada=u.getMorada();
   this.data=u.getData();
   this.genero= u.getGenero();
   this.pontos=u.getPontos();
   this.amigos=u.getAmigos();
   this.pedidosAmizade=u.getPedidosAmizade();

    }
   
   //GETS
   
   public int getPontos(){
   return this.pontos;
   }
   public String getEmail(){
   return this.email;
   }
   public String getPw() {
   return this.pw;
   }
   public String getNome(){
   return this.nome;
   }
   public String getGenero(){
   return this.genero;
   }
   public String getMorada(){
   return this.morada;
   }
   public GregorianCalendar getData(){
   return this.data;
   }
   public TreeSet<String> getAmigos() {
   return this.amigos;
    }
   public TreeSet<String> getPedidosAmizade() {
   return this.pedidosAmizade;}
    
   //SETS
   public void setPontos(int a) {
   this.pontos=a;
   }
   public void setEmail(String e) {
   this.email=e;
   }
   public void setPw(String p) {
   this.pw = p;
   }
   public void setNome(String n) {
   this.nome = n;
   }
   public void setGenero(String g){
   this.genero = g;
   }
   public void setMorada(String m) {
   this.morada=m;
   }
   public void setData(GregorianCalendar d) {
   this.data = d;
   }
   public void setAmigos(TreeSet<String> a) {
   this.amigos = a;
   }
   public void setPedidosAmizade(TreeSet<String> pedA) {
   this.pedidosAmizade = pedA;
   }
    

   /*
    * Metodos
    */
   
   public String consultaInfo(){
   StringBuilder s = new StringBuilder();
  
   s.append(nome + "Informação Pessoal");
   s.append("Nome:" + nome);
   s.append("E-mail:" + email);
   s.append("Morada:" + morada);
   s.append("Data de Nascimento:" + data);
   
   return s.toString();
}
    
    //Método que permite consultar lista de amigos
    
    public void consultaAmigos(String email) {
    
    if(amigos.isEmpty()) {
    System.out.println("Utilizador não tem amigos.");
   } else
   {
   for(String s: amigos)
   {
   System.out.println(s + "\n");
   }
   }
   }
    
   public void consultaPedidosAmizade(String email) {
   if(pedidosAmizade.isEmpty()){
   System.out.println("Não tem pedidos de amizade.");
   } else {
   for(String pa : pedidosAmizade)
   {
   System.out.println(pa + "\n");}}
   }
   
   //Método que exibe a lista de amigos de um utilizador
   public void getListaAmigos() 
   {
   for (String s: amigos)
  {
   System.out.println(s);}
 }

   

    //Método Clone
    
    public User clone(){
    return new User(this);
}

}
    

    
    
    
    