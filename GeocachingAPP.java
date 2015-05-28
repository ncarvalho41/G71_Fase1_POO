
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
        private static User user;
        private static Input input;
        private static Admin admin;
        private static Rede rede;
    
    public static void main(){
    init();
    carregaMenuInicial();
    }    
    
    
    /*
     * Construtores
     */
     
    public static void init(){
    user = new User();
    input = new Input();
    rede = new Rede();
    }

    //
 /*
 * Métodos
 */

    //Método que cria o menu inicial

    public static void carregaMenuInicial()
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
    public static void carregaFicheiro(){
    try{
    ObjectInputStream ois = new ObjectInputStream(new FileInputStream("geocachingPOO.joc"));
    rede = (Rede) ois.readObject();
    ois.close();
    } 
    catch(Exception ex) {
    ex.printStackTrace();
    }
    criaHomepage();
    }
    //Método que cria o ficheiro de trabalho
    public static void criaFicheiro()
    {
    try{
    FileOutputStream fout = new FileOutputStream("geocachingPOO.joc");
    
    ObjectOutputStream obj = new ObjectOutputStream(fout);
    
    obj.writeObject(rede);
    obj.flush();
    obj.close();
    }
    
    catch(Exception ex) {
    ex.printStackTrace();
    }
    }
    
    //Método que cria Homepage
    
    public static void criaHomepage(){
    System.out.println("GeocachingAPP --- Homepage\n\n\n");
    System.out.println("1 - Fazer login como utilizador normal\n");
    System.out.println("2 - Fazer login como Administrador\n");
    System.out.println("3 - Criar nova conta\n");
    
    int opc = input.lerInt();
    
    switch (opc) {
    case 1 : userLogin();
    
    case 2 : adminLogin();
    
    case 3 : criaUser();
    }
    }
    
    //Método que executa login de utilizador normal
    public static void userLogin()
    {
    String usr = "";
    String pwd = "";
    
    System.out.println("Insira email Utilizador\n");
    usr = input.lerString();
    System.out.println("Insira a password\n");
    pwd = input.lerString();
    
    boolean login = rede.validaLogin(usr, pwd);
    
   if(login) { 
       user = rede.getUser(usr);
       paginaPessoalUser();
   } else 
     { 
    System.out.println("\nUtilizador ou password inválidos!");
    System.out.println("\n 1 - Tentar Novamente       2 - Sair");        
    int opc = input.lerInt();
    switch(opc){
    case 1: userLogin(); break;
    case 2: criaHomepage(); break;  
                   
    }
    
    }
   }
    
   //Método que executa login do administrador
   public static void adminLogin(){
   String adm = "";
   String pwd = "";
   System.out.println("Insira email do Admin\n");
   adm = input.lerString();
   System.out.println("Insira password Admin\n");
   pwd = input.lerString();
   
   boolean log = rede.validaLoginAdmin(adm, pwd);
   
   if(log) {
   //admin = rede.getAdmin(adm);
   //paginaAdmin();
   }
   }
    
    //Método que permite criar novo utilizador
    
    public static void criaUser(){
    
    String nome = "";
    String email = "";
    String pwd = "";
    String genero = "";
    String morada = "";
    int diaN = 0;
    int mesN = 0;
    int anoN = 0;
    try{
    System.out.println("Criar novo Utilizador\n\n");
    System.out.println("Introduza o seu nome: ");
    nome = input.lerString();
    System.out.println("\nIntroduza o seu email: ");
    email = input.lerString();
    System.out.println("\nIntroduza a sua password: ");
    pwd = input.lerString();
    System.out.println("\nIntroduza o seu género: (M - Masculino, F- Feminino)");
    genero = input.lerString();
    System.out.println("\nIntroduza a sua morada: ");
    morada = input.lerString();
    System.out.println("\nIntroduza o seu dia de nascimento:\n");
    diaN = input.lerInt();
    System.out.println("\nIntroduza o seu mês de nascimento:\n");
    mesN = input.lerInt();
    System.out.println("\nIntroduza o seu ano de nascimento:\n\n");
    anoN = input.lerInt();
    
    GregorianCalendar data = new GregorianCalendar(anoN, mesN-1,diaN);
    
    User u = new User(email, pwd, nome, genero, morada, data, 0);
    
    rede.registaUser(u);
    System.out.println("Utilizador " + nome + " criado com sucesso! Prima enter para ir para a sua página pessoal.");
    }
    catch (Exception ex) {
    ex.printStackTrace();
    }
    
    }
   
    //Método que permite consultar página pessoal de Utilizador normal
    
    public static void paginaPessoalUser()
    {
    
    }
   
    //Método que permite consultar informação pessoal     

    public static void consultaInfo(String email){
    if (rede.existeUser(email))
        {
        User u = rede.getUser(email);
        System.out.println(u.toString())
        ;
    } else 
    {   System.out.println("Utilizador inexistente!");}
    }  
    
    //Método que permite consultar lista de amigos de um utilizador     
    public static void consultaListaAmigos(String email)
    {
    rede.getUser(email).getListaAmigos();
    }
    
    }
    
    