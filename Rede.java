
/**
 * Escreva a descrição da classe Rede aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.HashMap;

public class Rede implements Serializable {
    
   private HashMap<String, User> users;
   private Admin admin;
   private User u;
   private HashMap<String, Caches> caches;
   private Input input;
   private ArrayList<String> reportedCaches;
   
/*
 * Construtores
 */   
   public Rede(){
       this.users = new HashMap<>();
       this.admin = new Admin();
       this.caches = new HashMap<>();
       this.reportedCaches = new ArrayList<>();
   }
        
/*
 * 
 */

   //Método que procura utiizador
   public boolean existeUser(String email)
   {   
       return users.containsKey(email);
    }
   
    
   //Método que devolve lista de amigos de um utilizador 
   public TreeSet<String> getAmigos(String email) {
   return users.get(email).getAmigos();
    }
    
   //Método que devolve pedidos de amizade de um utilizador
   public TreeSet<String> getPedidos(String email) {
   return users.get(email).getPedidos(); 
    }
   //Método que permite criar novo utilizador
   
   public void registaUser(User u){
       this.users.put(u.getEmail(), u);
   }
   
   public void registaMicroCache(String codCache, String criador, GregorianCalendar data, String desc_x, Coordenadas coord){
       MicroCache c = new MicroCache(codCache, criador, data, desc_x, coord);
       this.caches.put(codCache, c);}
       
   public void registaMultiCache(String codCache, String criador, GregorianCalendar data, String desc_x, Coordenadas coord , String obj, ArrayList<Coordenadas> cor){
    MultiCache mc = new MultiCache(cor, obj, codCache, criador, data, desc_x, coord);
    this.caches.put(codCache, mc);
    }
   
   public void registaCacheMist(String codCache, String criador, GregorianCalendar data, String desc_x, Coordenadas coord , String obj, String adP, String adR) {
    CacheMisterio cm = new CacheMisterio(obj, adP, adR, codCache, criador, data, desc_x, coord);
    this.caches.put(codCache, cm);
    }
   //Método que devolve utilizador
   public User getUser(String email)
   {
       return users.get(email).clone();
    }
    
  public HashMap<String, Caches> getCaches(){
    return this.caches;}
    
  public boolean isMicro(String codCache){
   Caches c = caches.get(codCache);
    if (c instanceof MicroCache) {
    return true;} else return false;
   }  
   
   public boolean isMulti(String codCache){
    Caches c = caches.get(codCache);
   if (c instanceof MultiCache) {
    return true;} else return false;}
    
   public boolean isMistery(String codCache){
    Caches c = caches.get(codCache);
    if (c instanceof CacheMisterio){
    return true;} else return false;}
    
   public boolean adivinhaCache(String codCache){
   String adP = "";
   CacheMisterio cm = (CacheMisterio) caches.get(codCache);
   adP = cm.getAdP();
   
   System.out.println(adP.toString());
   System.out.println("Resposta: \n");
   String resp = input.lerString();
   
   if (cm.getAdR().equals(resp)) {
    return true;} else return false;
   } 
   
   public void addReport(String report){
   reportedCaches.add(report);
    }
    
   public void getReported(){
   for(String s: reportedCaches){
    System.out.println(s + "\n");}}
   
   //Método que valida login do utilizador
   
   public boolean validaLogin(String usr, String pwd){
       
   try{    
   return(this.users.containsKey(usr) && this.users.get(usr).getPw().equals(pwd));
   } 
   catch (Exception e){
   return false;}
   
  }

    public boolean validaLoginAdmin(String adm, String pwd){
    try{
    return(this.admin.getEmail().equals(adm) && this.admin.getPw().equals(pwd));
    }
    catch (Exception e)
    {
    return false;
    }
    }
    
   public ArrayList<String> listaUsers() {
   ArrayList <String> res = new ArrayList<>();
   User u;
   for(String e: users.keySet()){
    u = users.get(e);
    res.add(u.getEmail());}
   return res;
   }
    
    
    
    
    
    
    
    
    
    
}