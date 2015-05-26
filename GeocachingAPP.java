
/**
 * 
 * @NelsonVieira
 * 
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.HashMap;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;

public class GeocachingAPP implements Serializable
    {
        public User user;
        public Input input;
        
        private Rede rede;
        
    /*
     * Construtores
     */
     
    public GeocachingAPP(){
    user = new User();
    input = new Input();
    rede = new Rede();
    }
    
    //Construtor clone
    public GeocachingAPP (GeocachingAPP geo){
    GeocachingAPP copia = new GeocachingAPP();
    copia.setUser(this.getUser());
    copia.setRede(this.getRede());
    copia.setInput(this.getInput());
    }
    
    //GETS
    public User getUser(){
    return this.user;}
    
    public Input getInput(){
    return this.input;}
    
    public Rede getRede(){
    return this.rede;}
    
    //SETS 
    
    public void setUser(User u) {
    this.user = u;
    }
    
    public void setInput(Input i) {
    this.input = i;}
    
    public void setRede(Rede r){
    this.rede = r;}
    
    
    
    //
 /*
 * Métodos
 */

    //Método que cria o menu inicial

    public void carregaMenuInicial()
    {
        int op = 0;
        boolean ativo = true;

        //while(ativo){
        System.out.println("Bem-vindo à GeocachingAPP! \n\n 1-Carregar ficheiro existente\n2-Criar novo ficheiro\n3-Sair");
        op = input.lerInt();
        switch(op){
        case 1: carregaFicheiro();
        
        case 2: criaFicheiro();
        }
       }
    
    //Método que carrega ficheiro guardado
    public void carregaFicheiro(){
    try{
    ObjectInputStream ois = new ObjectInputStream(new FileInputStream("geocachingPOO.joc"));
    GeocachingAPP geoAPP = (GeocachingAPP) ois.readObject();
    this.user = geoAPP.getUser();
    this.rede = geoAPP.getRede();
    this.input = geoAPP.getInput();
    ois.close();
    } 
    catch(Exception ex) {
    ex.printStackTrace();
    }
    
    }
    //Método que cria o ficheiro de trabalho
    public void criaFicheiro()
    {
    try{
    FileOutputStream fout = new FileOutputStream("geocachingPOO.joc");
    ObjectOutputStream obj = new ObjectOutputStream(fout);
    
    obj.writeObject(this.clone());
    obj.close();
    }
    
    catch(Exception ex) {
    ex.printStackTrace();
    }
    }
    
    //Método que executa login de utilizador normal
    public void userLogin()
    {
    String usr = "";
    String pwd = "";
    
    System.out.println("Insira Utilizador\n");
    usr = input.lerString();
    System.out.println("Insira a password\n");
    pwd = input.lerString();
    
    boolean login = rede.validaLogin(usr, pwd);
    
   // if(login) { paginaPessoal(usr);
    //}
     //else 
     { 
    System.out.println("\nUtilizador ou password inválidos!");
    System.out.println("\n 1 - Tentar Novamente       0 - Sair");        
    int opc = input.lerInt();
    switch(opc){
    case 1: userLogin(); break;
    case 0: carregaMenuInicial(); break;  
                   
    }
    
    }
   }
    
    //Método que permite consultar informação pessoal     

    public void consultaInfo(String email){
    if (rede.existeUser(email))
        {
        User u = rede.getUser(email);
        System.out.println(u.toString())
        ;
    } else 
    {   System.out.println("Utilizador inexistente!");}
    }  
    
    //Método que permite consultar lista de amigos de um utilizador     
    public void consultaListaAmigos(String email)
    {
    rede.getUser(email).getListaAmigos();
    }
    
    //Método que cria página pessoal de utilizador normal
    
    

    
    
    public GeocachingAPP clone(){
    return new GeocachingAPP(this);
    }
    
    }
    
    