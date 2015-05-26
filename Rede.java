
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

public class Rede
{
   private HashMap<String, User> users;
   
   

/*
 * Construtores
 */   

/*
 * 
 */

   //Método que procura utiizador

   public boolean existeUser(String email)
   {
       return users.containsKey(email);
    }

   //Método que devolve utilizador

   public User getUser(String email)
   {
       return users.get(email).clone();
    }
   
   //Método que valida login do utilizador
   
   public boolean validaLogin(String usr, String pwd){
   return(this.users.containsKey(usr) && this.users.get(usr).getPw().equals(pwd));
   }
}
