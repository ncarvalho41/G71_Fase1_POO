import java.util.*;
import java.io.Serializable;

public class Admin extends User implements Serializable
{
   
        
        private String nome = "admin";
        private String email = "admin";
        private String pw = "admin";
        private boolean admin;
        
        /*
         * Construtores
         */
        
        //Construtor vazio
        public Admin(){
        this.nome="";
        this.email="";
        this.pw="";
        }
        
        //Construtor de parametros
        public Admin(String nm, String mail, String pwd){
        this.nome = nm;
        this.email = mail;
        this.pw = pwd;
        }
        
        //Construtor de cópia
        public Admin(Admin u) {
        this.nome=u.getNome();
        this.email=u.getEmail();
        this.pw=u.getPw();
 
        }
        
        //GETS
        public String getNome() {
        return this.nome;}
        
        public String getEmail() {
        return this.email;}
        
        public String getPw(){
        return this.pw;}
        
        //SETS
        
        public void setNome(String n)
        {this.nome = n;
        }
        
        public void setEmail(String e){
        this.email = e;}
        
        public void setPw(String p){
        this.pw = p;
    }
          
        
        
        
        
        
        
      
}