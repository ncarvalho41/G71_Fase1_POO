
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
        private static Caches cache;
        
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
    input.lerString();
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
    
    public static void paginaPessoalUser()
    {
    int log = 1;    
    while(log == 1){  
    System.out.println('\f');
    System.out.println("Utilizador " + user.getNome() + " # Página Pessoal\n\n");
    System.out.println("1 - Consultar informação pessoal\n");
    System.out.println("2 - Consultar lista de amigos\n");
    System.out.println("3 - Atividades de Geocaching\n");
    System.out.println("0 - Sair\n");
    
    int op = input.lerInt();
    
    switch(op){
    
    case 1 : { consultaInfo(user.getEmail()); break;}
    
    case 2 : { paginaAmigos(); break;}
    
    case 3 : { menuAtividades(); break;}
    
    case 0 : {
             guardaAPP();
             log = 0; 
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
    user = u;
    System.out.println("Utilizador " + nome + " criado com sucesso! Prima enter para ir para a sua página pessoal.\n\n\n");
    input.lerString();
    paginaPessoalUser();
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
        System.out.println("\n\n1 - Atualizar informação pessoal\n");
        System.out.println("0 - Retroceder");
        
        int op = input.lerInt();
        
        switch(op) {
        case 1 :{ atualizaInfo(); break;
        }
        
        case 0 : {paginaPessoalUser(); break;}
    }
        input.lerString();
        paginaPessoalUser();
        ;
    } else 
    {   System.out.println("Utilizador inexistente!");}
    }  
    
   public static void atualizaInfo(){
   
   User a = user;
   
   String nome = "";
   String email = "";
   String pwd = "";
   String gnr = "";
   String mord = "";
   int diaN = 0;
   int mesN = 0;
   int anoN = 0;
    
   System.out.println(user.getNome() + " # Editar dados pessoais\n\n");
   System.out.println("Alterar Nome:\n");
   nome = input.lerString();
   System.out.println("Alterar e-mail:\n");
   email = input.lerString();
   System.out.println("Alterar password:\n");
   pwd = input.lerString();
   System.out.println("Alterar género:\n");
   gnr = input.lerString();
   System.out.println("Alterar morada:\n");
   mord = input.lerString();
   System.out.println("\nIntroduza o seu dia de nascimento:\n");
   diaN = input.lerInt();
   System.out.println("\nIntroduza o seu mês de nascimento:\n");
   mesN = input.lerInt();
   System.out.println("\nIntroduza o seu ano de nascimento:\n\n");
   anoN = input.lerInt();
   
   GregorianCalendar new_data = new GregorianCalendar(anoN, mesN-1, diaN);
   

   a.setEmail(email);
   
   
    
   
    }
    
   //Método que cria página de administração da APP
   
   public static void paginaAdmin(String adm) {
   System.out.println("Admin");
   }
   /*
    *                                                       MÉTODOS DE GESTÃO DE AMIGOS DE UM UTILIZADOR
    */
   
   //Método que cria página de amigos de um utilizador
   
   public static void paginaAmigos() {
   
   boolean ativo = true;    
   while(ativo){ 
   System.out.println('\f');
   System.out.println(user.getNome() + " # Página de Amigos\n\n");
   System.out.println("1 - Consultar lista de amigos\n");
   System.out.println("2 - Consultar pedidos de amizade\n");
   System.out.println("3 - Enviar pedido de amizade\n");
   System.out.println("0 - Retroceder\n");
   
   int op = input.lerInt();
   
   switch(op) {
   
   case 1 : {consultaListaAmigos(user.getEmail()); 
             break;}
   
   case 2 : {consultaPedidos(user.getEmail()); 
             break;}
             
   case 3 : {enviaPedido(); 
             break;}
             
   case 0 : {paginaPessoalUser(); 
             break;
            }
   }
   }
   }
   
   //Método que permite consultar amigos de um utilizador
   public static void consultaListaAmigos(String email)
    {
   user.consultaAmigos(user.getEmail());
   System.out.println("Prima enter para retroceder");
   input.lerString();
   paginaAmigos();
   }
   
   public static void consultaPedidos(String email) 
   {
   int flag = 1;
   user.consultaPedidosAmizade(email);
   input.lerString();
   while(flag == 1){
   System.out.println("Introduza o email de um pedido que pretenda confirmar. Prima enter para continuar");
   String mail = input.lerString();
   if(email.length() > 1) {user.confirmaAmigo(mail);
                           System.out.println("Inseri");
                        if(user.getPedidos().size() == 0) flag = 0;}
   else {flag = 0;}
   }
   paginaAmigos();
   }
   
   public static void enviaPedido() {
   ArrayList<String> list = new ArrayList<>(rede.listaUsers());
   {
    for(String s: list) {
    System.out.println(s + "\n");}
   }
   System.out.println("Introduza o email do utilizador que pretende adicionar:\n");
   String email = input.lerString();
   user.pedidoAmigo(email);
   
   }
   
   
   /*
    *                                                        MÉTODOS DE GESTÃO DE ATIVIDADE DE GEOCACHING
    */
   
   //Método que cria menu inicial de atividades de um utilizador
   
   public static void menuAtividades()
   {
   int ativo = 1;
   
   while(ativo == 1) {
   System.out.println('\f');
   System.out.println(user.getNome() + " # Atividades de Geocaching\n\n");
   System.out.println("1 - Consultar atividade recente\n");
   System.out.println("2 - Inserir nova cache\n");
   System.out.println("3 - Registar descoberta de cache\n");
   //System.out.println("4 - Eliminar cache\n");
   //System.out.println("5 - Fazer <<report abuse>> de cache\n");
   System.out.println("6 - Consultar caches existentes\n");
   System.out.println("0 - Voltar para o menu inicial\n");
   
   int op = input.lerInt();
   
   switch(op){
   case 1 : {consultaAtividades(); break;}
   case 2 : {insereCache(); break;}
   case 3 : {descobreCache(); break;}
   //case 4 : {eliminaCache(); break;}
   //case 5 : {reportCache(); break;}
   
   case 6 : {consultaCaches(); break;}
   case 0 : {
            ativo = 0;
            paginaPessoalUser(); break;}
   }
   
   }
   }
   
   
   
   
   public static void consultaAtividades(){
   System.out.println('\f');
   System.out.println(user.getNome() + " # Atividades de Geocaching\n\n");
   System.out.println("1 - Consultar atividades pessoais\n");
   System.out.println("2 - Consultar atividades de amigos\n");
   System.out.println("0 - Voltar ao menu anterior\n");
   
   int op = input.lerInt();
   
   switch(op){
   case 1 : {consultaAtividadesPessoais(); break;}
   case 2 : {consultaAtividadesAmigos(); break;}
   case 3 : {menuAtividades(); break;}
   }
   
    }
   
   public static void consultaAtividadesPessoais(){
   System.out.println('\f');
   System.out.println(user.getNome() + " # Atividades recentes\n\n");
   
   }
    
    
   
   public static void insereCache(){
   System.out.println('\f');
   System.out.println(user.getNome() + " # Inserir cache\n\n");
   System.out.println("1 - Inserir micro-cache\n");
   System.out.println("2 - Inserir multi-cache\n");
   System.out.println("3 - Inserir cache-mistério\n");
   System.out.println("0 - Voltar ao menu anterior\n");
   
   int op = input.lerInt();
   

   switch(op){
   case 1 : {insereMicroC(); break;}
   
   case 2 : {insereMultiC(); break;}
   
   case 3 : {insereCacheM(); break;}
   
   case 0 : {menuAtividades(); break;}
}
   }
   

    
    
   public static void insereMicroC(){
    
    String codCache = "";
    int diaM = 0;
    int mes = 0;
    int ano = 0;
    String desc_x = "";
    
    
    try{
    System.out.println('\f');
    System.out.println(user.getNome() + " # Inserir Micro-cache\n\n");
    System.out.println("Inserir código da cache:\n");
    codCache = input.lerString();
    System.out.println("Inserir dia:\n");
    diaM = input.lerInt();
    System.out.println("Inserir mes:\n");
    mes = input.lerInt();
    System.out.println("Inserir ano:\n");
    ano = input.lerInt();
    System.out.println("Inserir descrição extra:\n");
    desc_x = input.lerString();
    
    GregorianCalendar data = new GregorianCalendar(ano, mes-1, diaM);
    
    Coordenadas cor = getCoordenadas();
    
    rede.registaMicroCache(codCache, user.getEmail(), data, desc_x, cor);
    
    System.out.println("Micro-cache " + codCache + " criada com sucesso. Prima enter para continuar\n");
    input.lerString();
    }
    catch (Exception ex) 
    {
    ex.printStackTrace();
    System.out.println("Erro na criação da cache. Prima enter para voltar a tentar");
    input.lerString();
    insereCache();
    }
    
   
    }
    
    public static void insereMultiC(){
    
    String codCache = "";
    String desc_x = "";
    int diaM = 0;
    int mes = 0;
    int ano = 0;
    String obj = "";
    
    
    int nCaches = 0;
    int i = 0;
    try{
    System.out.println('\f');
    System.out.println(user.getNome() + " # Inserir Multi-cache\n\n");
    System.out.println("Indique o número de locais que terá a Multi-cache:\n");
    nCaches = input.lerInt();
    Coordenadas coordends = null;
    ArrayList<Coordenadas> listCaches = new ArrayList<>();
    for (i=0;i<nCaches;i++) {
    System.out.println('\f');
    System.out.println("Localização " + (i+1));
    coordends = getCoordenadas();
    listCaches.add(coordends);
    }
    System.out.println('\f');
    System.out.println("Coordendas inseridas. Prima enter para continuar\n");
    input.lerString();
    System.out.println("Insira o código da multi-cache criada:\n");
    codCache = input.lerString();
    System.out.println("Qual o objeto que colocará na cache?\n");
    obj = input.lerString();
    System.out.println("Insira a descrição extra da cache:\n");
    desc_x = input.lerString();
    System.out.println("Dia:\n");
    diaM = input.lerInt();
    System.out.println("Mês:\n");
    mes = input.lerInt();
    System.out.println("Ano:\n");
    ano = input.lerInt();
    
    Coordenadas last = listCaches.get(listCaches.size() -1);
    
    GregorianCalendar data = new GregorianCalendar(ano, mes-1, diaM);
    
    rede.registaMultiCache(codCache, user.getEmail(), data, desc_x, last, obj, listCaches);
    
    System.out.println("Multi-cache " + codCache + " criada com sucesso. Prima enter para continuar\n");
    input.lerString();
    }
    catch(Exception ex) {
    ex.printStackTrace();
    System.out.println("Erro a criar cache. Prima enter para voltar a tentar.\n");
    insereCache();
    }
    }
    
    public static void insereCacheM(){
    
    String codCache = "";
    String desc_x = "";
    int diaM = 0;
    int mes = 0;
    int ano = 0;
    String obj = "";
    String adivinhaP = "";
    String adivinhaR = "";
        
    try {System.out.println('\f');
    System.out.println(user.getNome() + " # Inserir Cache-Mistério\n\n");
    System.out.println("Inserir código de cache:\n");
    codCache = input.lerString();
    System.out.println("Qual o objeto que colocará na cache?\n");
    obj = input.lerString();
    System.out.println("Indique o enigma a colocar na cache:\n");
    adivinhaP = input.lerString();
    System.out.println("Indique a solução para o enigma:\n");
    adivinhaR = input.lerString();
    System.out.println("Indique uma descrição extra para colocar na cache:\n");
    desc_x = input.lerString();
    System.out.println("Dia:\n");
    diaM = input.lerInt();
    System.out.println("Mês:\n");
    mes = input.lerInt();
    System.out.println("Ano:\n");
    ano = input.lerInt();
    
    GregorianCalendar data = new GregorianCalendar(ano, mes-1, diaM);
    
    Coordenadas cor = getCoordenadas();
    
    rede.registaCacheMist(codCache, user.getEmail(), data, desc_x, cor, obj, adivinhaP, adivinhaR);
    
    System.out.println("Cache-mistério " + codCache + " criada com sucesso. Prima ok para continuar.");
    input.lerString();
    insereCache();}
    
    catch(Exception ex){
    ex.printStackTrace();
    System.out.println("Erro no registo de cache. Prima enter para continuar.\n");
    input.lerString();
    insereCache();}
    
    }
    
    public static void consultaCaches() {
        HashMap<String, Caches> caches = new HashMap<>(rede.getCaches());
        Caches c;
        for(String codigo: caches.keySet()) {
        c = caches.get(codigo);
        System.out.println(c.toString());
        }
        System.out.println("Prima enter para retroceder\n");
        input.lerString();
    }
    
    public static void descobreCache(){
        
    System.out.println('\f');
    System.out.println(user.getNome() + " # Descoberta de cache\n\n");
    System.out.println("1 - Micro-cache\n");
    System.out.println("2 - Multi-cache\n");
    System.out.println("3 - Cache-Mistério\n");
    System.out.println("0 - Voltar a menu anterior\n");
    
    int op = input.lerInt();
    
    switch(op){
    case 1 : {descobreMicroC(); break;}
    case 2 : {descobreMultiC(); break;}
    case 3 : {descobreCacheMist(); break;}
    case 0 : {menuAtividades(); break;}
    
    }
    
    
    }
    
  public static void descobreMicroC() {
  
     
  
  String codCache = "";    
  int diaM = 0;
  int mes = 0;
  int ano = 0;
  String desc_x = "";
  
  
  System.out.println('\f');
  System.out.println(user.getNome() + " # Descoberta de Micro-cache\n\n");
  System.out.println("Insira código da cache descoberta:\n");
  codCache = input.lerString();
  if(rede.isMicro(codCache)) {
   System.out.println("Insira o dia de hoje:\n");
   diaM = input.lerInt();
   System.out.println("Insira o mês atual:\n");
   mes = input.lerInt();
   System.out.println("Insira o ano:\n");
   ano = input.lerInt();
   System.out.println("Insira uma descrição adicional:\n");
   desc_x = input.lerString();
   
   GregorianCalendar data = new GregorianCalendar(ano, mes-1, diaM);
   
   rede.descobreMicroCache(codCache, data, user.getEmail(), desc_x);
   System.out.println("Micro-cache " + codCache + " descoberta com sucesso. Prima enter para continuar.\n");
   input.lerString();
   descobreCache();
    } else {
    System.out.println("Código de cache inserido não pertence a uma micro-cache. Prima enter para voltar a tentar.\n");
    input.lerString();
    descobreCache();
    }
  
  }
  
  public static void descobreMultiC(){
    
  String codCache = "";    
  int diaM = 0;
  int mes = 0;
  int ano = 0;
  String desc_x = "";
  
  
  
  System.out.println('\f');
  System.out.println(user.getNome() + " # Descoberta de Multi-cache\n\n");
  System.out.println("Insira código da cache descoberta:\n");
  codCache = input.lerString();
  if(rede.isMulti(codCache)){
   System.out.println("Insira o dia de hoje:\n");
   diaM = input.lerInt();
   System.out.println("Insira o mês atual:\n");
   mes = input.lerInt();
   System.out.println("Insira o ano:\n");
   ano = input.lerInt();
   System.out.println("Insira uma descrição adicional:\n");
   desc_x = input.lerString();
   
   
   GregorianCalendar data = new GregorianCalendar(ano, mes-1, diaM);
   rede.descobreMultiCache(codCache, data, user.getEmail(), desc_x);
   System.out.println("Multi-cache descoberta com sucesso. Prima enter para continuar.\n");
   input.lerString();
   
    } else {
    System.out.println("Cache inserida não é uma Multi-cache. Prima enter para retroceder.\n");
    input.lerString();
    descobreCache();}
  }
    
 
  public static void descobreCacheMist(){
  
  String codCache = "";
  int diaM = 0;
  int mes = 0;
  int ano = 0;
  String desc_x = "";
  
  System.out.println('\f');
  System.out.println(user.getNome() + " # Descoberta de Cache-mistério\n\n");
  System.out.println("Insira código da cache descoberta:\n");
  codCache = input.lerString();
  if(rede.isMistery(codCache)){
   if(rede.adivinhaCache(codCache)){
   System.out.println("Enigma solucionado.\n");   
   System.out.println("Insira o dia de hoje:\n");
   diaM = input.lerInt();
   System.out.println("Insira o mês atual:\n");
   mes = input.lerInt();
   System.out.println("Insira o ano:\n");
   ano = input.lerInt();
   System.out.println("Insira uma descrição adicional:\n");
   desc_x = input.lerString();}
   else {
   System.out.println("Solução errada. Prima enter para voltar a tentar.\n");
   input.lerString();
   descobreCache();}
  }
  
  GregorianCalendar data = new GregorianCalendar(ano, mes-1, diaM);
  rede.descobreMisteryCache(codCache, data, user.getEmail(), desc_x);
  System.out.println("Cache-mistério descoberta com sucesso. Prima enter para continuar.\n");
  input.lerString();
  descobreCache();
  }
  
  
   public static Coordenadas getCoordenadas()
   {
   Coordenadas cor = new Coordenadas();
   
   int minLt = 0;
   int segLt = 0;
   int grauLt = 0;
   String dirLt = "";
   
   System.out.println("Inserir coordenadas latitude\n");
   System.out.println("Inserir grau latitude:\n");
   grauLt = input.lerInt();
   System.out.println("Inserir minuto latitude:\n");
   minLt = input.lerInt();
   System.out.println("Inserir segundo latitude:\n");
   segLt = input.lerInt();
   System.out.println("Inserir direção latitude:\n\n");
   dirLt = input.lerString();
   
   Coord lat = new Coord (grauLt, minLt, segLt, dirLt);
   
   int minLg = 0;
   int segLg = 0;
   int grauLg = 0;
   String dirLg = "";
   
   System.out.println("Inserir coordenadas logitude\n");
   System.out.println("Inserir grau longitude:\n");
   grauLg = input.lerInt();
   System.out.println("Inserir minuto longitude:\n");
   minLg = input.lerInt();
   System.out.println("Inserir segundo longitude:\n");
   segLg = input.lerInt();
   System.out.println("Inserir direção longitude:\n");
   dirLg = input.lerString();
   
   Coord lng = new Coord (grauLg, minLg, segLg, dirLg);
   
   cor.setLat(lat);
   cor.setLongt(lng);
   
   return cor;
  }
    
   /*
    *                                                            MÉTODO DE SALVAGUARDA DA APLICAÇÃO
    */
   
   //Método que permite guardar alterações na aplicação
   
   public static void guardaAPP(){
   System.out.println('\f');
   System.out.println("Pretende guardar as alterações efetuadas? (1 - Sim, 0 - Não)\n");
   int op = input.lerInt();
   
   if (op == 1) {
   gravaFicheiro();}
   
   
   }
    
    
    
    
    }
    
    