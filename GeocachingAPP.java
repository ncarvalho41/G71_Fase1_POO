
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
    
    /*
     *                                                                          MENU INICIAL DA APLICAÇÃO
     */
    
    //Método que cria o menu inicial

    public static void carregaMenuInicial()
    {
        int op = 0;
        boolean ativo = true;

        while(ativo){
        System.out.println('\f');
        System.out.println("Bem-vindo à GeocachingAPP! \n\n1-Carregar ficheiro existente\n2-Criar novo ficheiro\n3-Sair");
        op = input.lerInt();
        switch(op){
        case 1: { carregaFicheiro(); break;}
        
        case 2: { gravaFicheiro();
                  criaHomepage();
                  break;}
        
        case 3 : {ativo = false; 
                 break;}
        }
       }
       System.exit(0); 
    }
    /*
     *                                                              MÉTODOS DE CARREGAMENTO E CRIAÇÃO DE FICHEIRO .JOC
     */
    
    //Método que carrega ficheiro guardado
    public static void carregaFicheiro(){
    
    
    String filename = "";
    System.out.println('\f');
    
        try{
        
    System.out.println("Introduza o nome do ficheiro a carregar:\n");
    filename = input.lerString();
    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename + ".joc"));
    rede = (Rede) ois.readObject();
    ois.close();
    criaHomepage();
    }
    catch(Exception ex) {
    ex.printStackTrace();
    System.out.println("Ficheiro inexistente. Prima Enter para retroceder\n");
    carregaMenuInicial();
    }
    }

    //Método que cria o ficheiro de trabalho
    public static void gravaFicheiro()
    {
    String filename = "";
    System.out.println('\f');
    
    try{
    System.out.println("Introduza o nome do ficheiro a ser criado:\n");
    filename = input.lerString();
    FileOutputStream fout = new FileOutputStream(filename + ".joc");
    
    ObjectOutputStream obj = new ObjectOutputStream(fout);
    
    obj.writeObject(rede);
    obj.flush();
    obj.close();
    }
    
    catch(Exception ex) {
    ex.printStackTrace();
    }
    }
    
    /*
     *                                                                                       HOMEPAGE
     */
    
    //Método que cria Homepage
    
    public static void criaHomepage(){
    
    boolean sair = false;
    
    while(!sair) {
    System.out.println('\f');    
    System.out.println("GeocachingAPP --- Homepage\n\n\n");
    System.out.println("1 - Fazer login como utilizador normal\n");
    System.out.println("2 - Fazer login como Administrador\n");
    System.out.println("3 - Criar nova conta\n");
    System.out.println("4 - Gravar\n");
    System.out.println("0 - Sair\n");
    
    int opc = input.lerInt();
    
    switch (opc) {
    case 1 : { userLogin(); break;}
    
    case 2 : { adminLogin(); break;}
    
    case 3 :{ criaUser(); break;}
    
    case 4 : {gravaFicheiro(); break;}
    
    case 0 :{
        guardaAPP();
        sair = true;
        carregaMenuInicial();
        break; }
    }
    }
    }
    
    /*
     *                                                                     MÉTODOS DE VALIDAÇÃO DE LOGINS DE USER E ADMIN
     */
    
    //Método que executa login de utilizador normal
    public static void userLogin()
    {
    String usr = "";
    String pwd = "";
    
    System.out.println('\f');
    System.out.println("Insira email Utilizador\n");
    usr = input.lerString();
    System.out.println("Insira a password\n");
    pwd = input.lerString();
    
    boolean login = rede.validaLogin(usr, pwd);
    
   if(login) { 
       user = rede.getUser(usr);
       paginaPessoalUser(user);
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
   System.out.println('\f');
   
   System.out.println("Insira email do Admin\n");
   adm = input.lerString();
   System.out.println("Insira password Admin\n\n");
   pwd = input.lerString();
   
   boolean log = rede.validaLoginAdmin(adm, pwd);
   
   if(log) {
   paginaAdmin(adm);
   }
   }
    
   /*
    *                                                                   MÉTODOS DA ÁREA PESSOAL DO UTILIZADOR
    */
   
   //Método que permite consultar página pessoal de Utilizador normal
    
    public static void paginaPessoalUser(User user)
    {
    int log = 1;    
    while(log == 1){  
    System.out.println('\f');
    System.out.println("Utilizador " + user.getNome() + " # Página Pessoal\n\n");
    System.out.println("1 - Consultar informação pessoal\n");
    System.out.println("2 - Consultar atividade mensal\n");
    System.out.println("3 - Consultar lista de amigos\n");
    System.out.println("4 - Atividades de Geocaching\n");
    System.out.println("5 - Sair\n");
    
    int op = input.lerInt();
    
    switch(op){
    
    case 1 : { consultaInfo(user.getEmail()); break;}
    
    case 3 : { paginaAmigos(); break;}
    
    case 5 : {log = 0;
             criaHomepage(); break;}
   }
   } 
   }
   
   //Método que permite criar novo utilizador
    
    public static void criaUser(){
        
    User u;
    GregorianCalendar dt = new GregorianCalendar();
    
    String nome = "";
    String email = "";
    String pwd = "";
    String genero = "";
    String morada = "";
    int diaN = 0;
    int mesN = 0;
    int anoN = 0;
    try{
    System.out.println('\f');
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
    
    dt = new GregorianCalendar(anoN, mesN-1,diaN);
    
    u = new User(email, pwd, nome, genero, morada, dt, 0);
    
    rede.registaUser(u);
    System.out.println("Utilizador " + nome + " criado com sucesso! Prima enter para ir para a sua página pessoal.\n\n\n");
    input.lerString();
    paginaPessoalUser(u);
    }
    
    catch (Exception ex) {
    ex.printStackTrace();
    }
    
    }
   
    //Método que permite consultar informação pessoal     

    public static void consultaInfo(String email){
    if (rede.existeUser(email))
        {
        User u = rede.getUser(email);
        System.out.println(u.toString());
        System.out.println("Prima enter para retroceder\n");
        input.lerString();
        paginaPessoalUser(u);
        ;
    } else 
    {   System.out.println("Utilizador inexistente!");}
    }  
    
    
   //Método que cria página de administração da APP
   
   public static void paginaAdmin(String adm) {
   System.out.println("Admin");
   }
   /*
    *                                                                    MÉTODOS DE GESTÃO DE AMIGOS DE UM UTILIZADOR
    */
   
   //Método que cria página de amigos de um utilizador
   
   public static void paginaAmigos() {
   
   boolean ativo = true;    
   while(ativo){ 
   System.out.println('f');
   System.out.println(user.getNome() + " # Página de Amigos\n\n");
   System.out.println("1 - Consultar lista de amigos\n");
   System.out.println("2 - Consultar pedidos de amizade\n");
   System.out.println("3 - Enviar pedido de amizade\n");
   System.out.println("0 - Sair\n");
   
   int op = input.lerInt();
   
   switch(op) {
   
   case 1 : {consultaListaAmigos(user.getEmail()); 
             break;}
   }
   }
   }
   
   //Método que permite consultar amigos de um utilizador
   public static void consultaListaAmigos(String email)
    {
    TreeSet<String> amigos = new TreeSet<>(rede.getAmigos(email));
    
    if(amigos.size() == 0) {
    System.out.println("Utilizador ainda não tem amigos! Prima enter para retroceder\n");
    input.lerString();
    }
    else{
    for (String s: amigos) {
    System.out.println(s + "\n");
    System.out.println("Prima enter para retroceder");
    }
   }
   paginaAmigos();
   }

   
   
   /*
    *                                                                       MÉTODO DE SALVAGUARDA DA APLICAÇÃO
    */
   
   //Método que permite guardar alterações na aplicação
   
   public static void guardaAPP(){
    
   }
    
    
    
    
    }
    
    