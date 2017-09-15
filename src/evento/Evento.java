/*
//Esta clase define la clase abstracta evento, que hereda a los otros tipos
de eventos
 */
package evento;
import java.time.LocalTime;
import java.util.Scanner;
/**
 *
 * @author Jhovan
 */
public abstract class Evento {
   static Scanner sc = new Scanner(System.in);

   private int dia;
   private String desc,tipo;
   private LocalTime hi,hf;
   private static String sep="-"; //separador
    
    public Evento(){}
    public Evento(int dia,LocalTime hi,LocalTime hf,String desc, String tipo){ //constructor de evento
        this.dia=dia;
        this.hi=hi;
        this.hf=hf;
        this.desc=desc;
        this.tipo=tipo;
    }
    
    public void setDia(int dia){
        this.dia=dia;
    }
    public void setHoraI(LocalTime hi){
        this.hi=hi;
    }
    public void setHoraF(LocalTime hf){
        this.hf=hf;
    }
    public void setDesc(String desc){
        this.desc=desc;
    }

    public void setTipo(String tipo){
        this.tipo=tipo;
    }
    
    public int getDia(){
        return dia;
    }
    public LocalTime getHoraI(){
        return hi;
    }
    public LocalTime getHoraF(){
        return hf;
    }
    public String getDesc(){
        return desc;
    }

    
    public String getTipo(){
        return tipo;
    }
    
    public boolean intersecta(Evento evento){ //verifica cuando se intersectan dos eventos
        if(dia==evento.getDia()){
            LocalTime hie,hfe;
            hie=evento.getHoraI();
            hfe=evento.getHoraF();
            if(hi.isBefore(hie)&&hf.isAfter(hfe)) return true;
            if(hi.isAfter(hie)&&hi.isBefore(hfe)) return true;
            if(hf.isAfter(hie)&&hf.isBefore(hfe)) return true;
            if(hi.equals(hie)||hf.equals(hfe)) return true;
            return false;
        }
        return false;
    }
    
    public boolean isBefore(Evento evento){ //verifica si un evento ocurre antes que otro
        return (dia<evento.getDia()||(hi.isBefore(evento.getHoraI())&&dia==evento.getDia()));
    }
    
    public String toString(){ //regresa una cadena para guardar los archivos
        return (tipo + sep + dia + sep + hi + sep + hf + sep + desc);
    }
    
    public String mostrar(){ //regresa una cadena para mostrar al usuario el evento
        return(" " + dia + "|HoraInicio:"+hi+"|HoraFin:"+hf+"|Tipo:"+tipo+"|Descripcion:"+desc);
    }
    
    public static LocalTime hourFromString(String cadena){ //convierte una cadena formato HH:MM a un objeto de tipo LocalTime
        try{
            String[] parametros=cadena.split(":");
            int intHi,intHf;
            LocalTime hora;
            intHi=Integer.parseInt(parametros[0]);
            intHf=Integer.parseInt(parametros[1]);
            hora= LocalTime.of(intHi, intHf);
            return hora;
        }
        catch(Exception e){
            System.out.println("Hora invalida");
            return LocalTime.now();
        }
    }
    
    public static boolean validarHora(LocalTime hi,LocalTime hf){ //verifica si dos horas son aceptales para el vento
       return (hi.isBefore(hf));
    }
    
    public static String getSep(){
        return sep;
    }
    
    public static int getOp(int num){ //metodo para leer opciones y verificar que sean validas, num es el valor maxio que puede tomar
         boolean flag;
         int op=1;
         do{
            try{
                op=sc.nextInt();
                if(op<1||op>num) throw new Exception();
                flag=true;
            }
            catch(Exception e){
                System.out.println("Escribe un numero valido");
                sc.nextLine();
                flag=false;
            }
        }
        while(!flag);
        sc.nextLine();
        return op;
     
     }    
     
    public void modificar(){ //metodo para modificar atributos especiales del tipo
         System.out.println("No hay modificaciones exclusivas");   
     }
   
}
