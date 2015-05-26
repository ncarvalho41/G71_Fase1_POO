import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Calendar;

public class Coord
{
private int grau;
private int minuto;
private int segundo;
private String direcao;

/*
 * Construtores
 */

//Construtor vazio

public Coord(){
this.grau=0;
this.minuto=0;
this.segundo=0;
this.direcao="";
}
//Construtor parametro

public Coord(int g, int min, int seg, String dir) 
{this.grau = g;
 this.minuto = min;
 this.segundo = seg;
 this.direcao = dir;
}

//Construtor copia

public Coord(Coord c)
{
this.grau = c.getGrau();
this.minuto = c.getMin();
this.segundo = c.getSeg();
this.direcao = c.getDir();
}

//GETS

public int getGrau()
{return this.grau;}

public int getMin()
{return this.minuto;}

public int getSeg()
{return this.segundo;}

public String getDir()
{return this.direcao;}

//SETS

public void setGrau(int g)
{this.grau = g;}

public void setMin(int min)
{this.minuto = min;}

public void setSeg(int seg)
{this.segundo = seg;}

public void setDir(String dir)
{this.direcao = dir;}










}
