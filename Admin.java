import java.util.*;

public class Admin extends User
{
   
        
        private String nome;
        private String email;
        private String pw;
        private boolean admin;
        
        public Admin(){
        this.nome="";
        this.email="";
        this.pw="";
        }
        
        public Admin(Admin u) {
        this.nome=u.getNome();
        this.email=u.getEmail();
        this.pw=u.getPw();
 
        }
        
        
        
        
      
}