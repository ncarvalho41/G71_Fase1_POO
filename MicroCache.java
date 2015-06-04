import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Calendar;


public class MicroCache extends Caches
{
    /*
     * Construtores
     */
    
    //Construtor vazio
    public MicroCache() 
{
    super();
}
    //Construtor parametros
    public MicroCache(String codigo, String criador, GregorianCalendar data, String descricao_extra, Coordenadas coordenadas)
    {
    super(codigo, criador, data, descricao_extra, coordenadas);
    }
    //Construtor copia
    public MicroCache(MicroCache mc)
    {
    super(mc.getCodigo(), mc.getCriador(), mc.getData(), mc.getDesc(), mc.getCoord());
    
}

    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("\nNome: " + this.getCodigo());
        s.append("\nDescrição: " + this.getDesc());
        return s.toString();
    }
}

