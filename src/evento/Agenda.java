/*
 Esta clase se encarga de manejar una lista de eventos, de un respectivo mes y año
 */
package evento;
import java.time.YearMonth;
import java.util.ArrayList;
import java.time.LocalTime;
import java.util.Iterator;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
import java.time.LocalDate;


/**
 *
 * @author Jhovan
 */
public class Agenda {
    
   private YearMonth mes = YearMonth.now(); 
   private ArrayList<Evento> listaEventos = new ArrayList<>();
   private String nombre;
   private Iterator<Evento> iterador = listaEventos.iterator();

   
    public Agenda(){
    }
   
    public Agenda(int ano,int mes,String nombre){ //crea una agenda vacia para un mes dado en el año actual
       this.mes=YearMonth.of(ano, mes);
       this.nombre=nombre;
    }
   
    public Agenda(String nombre){ //crea la agenda desde a partir del nombre de un archivo en el directorio donde se ejecuta
        this.nombre=nombre;
        try {
            String directorioActual = System.getProperty("user.dir");      
            String rutaArchivo = directorioActual + File.separator + nombre + ".txt";

            // Cargamos el buffer con el contenido del archivo
            BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));

            //StringBuffer bs = new StringBuffer();
            String linea,desc,tipo;
            int intMes,dia,ano;
            LocalTime hi,hf;
            String[] parametros;
            Evento evento;
            listaEventos.clear(); //limpia la lista
            
            linea=br.readLine();
            intMes=Integer.parseInt(linea);
            linea=br.readLine();
            ano=Integer.parseInt(linea);
            mes=YearMonth.of(ano, intMes);
            while ((linea = br.readLine()) != null) {
                parametros=linea.split(Evento.getSep());
                tipo=parametros[0];
                dia=Integer.parseInt(parametros[1]);
                hi=Evento.hourFromString(parametros[2]);
                hf=Evento.hourFromString(parametros[3]);
                desc=parametros[4];
                switch(tipo){
                    case "academico":
                        String[] materiales=new String[parametros.length-5];
                        for(int i=0;i<materiales.length;i++){
                            materiales[i]=parametros[i+5];
                        }
                        evento= new Academico(dia, hi,hf,desc,materiales);
                        break;
                    case "medico":
                        String nombreDoc,especialidad,telefono;
                        nombreDoc=parametros[5];
                        especialidad=parametros[6];
                        telefono=parametros[7];
                        evento= new Medico(dia,hi,hf,desc,nombreDoc,especialidad,telefono);
                        break;
                    case "deportivo":
                        String contrincante=parametros[5];
                        evento= new Deportivo(dia,hi,hf,desc,contrincante);
                        break;
                    case "social":
                        String[] personas=new String[parametros.length-5];
                        for(int i=0;i<personas.length;i++){
                            personas[i]=parametros[i+5];
                        }
                        evento= new Social(dia, hi,hf,desc,personas);
                        break;
                    default:
                        evento=new Otro(dia,hi,hf,desc);
                        break;
                }
                listaEventos.add(evento);
            }
            br.close();            
        } 
        catch (IOException e) {
            System.out.println("Archivo no encontrado");
            System.out.println("El error es"+e.toString());
        }    
   }
   
   public boolean agregar(Evento evento){ //metodo para agregar evento a la agenda y colocarlo en el orden correcto
        Evento eventoAux;
        if(validarDia(evento.getDia())&&Evento.validarHora(evento.getHoraI(),evento.getHoraF())){
            for(int i=0;i<listaEventos.size();i++){
                eventoAux=listaEventos.get(i);
                if(eventoAux.intersecta(evento)){
                    System.out.println("El evento se intersecta con otro");
                    return false;
                }
                if(evento.isBefore(eventoAux)){
                    listaEventos.add(i, evento);
                    return true;
                }
            }
            listaEventos.add(evento);
            return true;
        }
        System.out.println("Fecha u hora invalida");
        return false;
   }
   
   public void borrar(int i){ //metodo para borrar eventos de la agenda a partir de su indice
       try{
           listaEventos.remove(i);
       }
       catch(Exception e){
           System.out.println("El evento no existe");
       }
   }
   
   public void setMes(YearMonth mes){
       this.mes=mes;
   }
    
   public YearMonth getMes(){
      return mes;
   }   
   
   public  ArrayList<Evento>  getListaEventos(){
       return listaEventos;
   }
    
   public void setListaEventos(ArrayList<Evento> listaEventos){
       this.listaEventos=listaEventos;
   }
   
   public String getNombre(){
       return nombre;
   }
   
   public void setNombre(String nombre){
       this.nombre=nombre;
   }
   
   public  boolean validarDia(int dia){ //verifica que un dia este en el rango de dias del mes de la agenda
       return (dia>0&&dia<=mes.lengthOfMonth());
   }
   

   public void escribirArchivo(){ //guarda la agenda en un archivo de texto del mismo nombre en el directorio actual
       try {
            String directorioActual = System.getProperty("user.dir");      
            String rutaArchivo = directorioActual + File.separator + nombre + ".txt";
            System.out.println("Ruta: " + rutaArchivo);
            BufferedWriter out = new BufferedWriter(new FileWriter(rutaArchivo));
            out.write(mes.getMonthValue()+"");
            out.newLine();
            out.write(mes.getYear()+"");
            out.newLine();
            iterador = listaEventos.iterator();
            while(iterador.hasNext()){
                out.write(iterador.next().toString());  
                out.newLine();
            }
            out.close();
        } 
        catch (IOException e) {
            System.out.println("Archivo no encontrado");
            System.out.println("El error es"+e.toString());
        }
   }
   
  
   
   public void mostrar(){ //muestra los elementos de la agenda completos
       Evento evento;
       System.out.println(mes.getMonth().toString()+" "+mes.getYear());
       iterador = listaEventos.iterator();
       while(iterador.hasNext()){
           evento=iterador.next(); 
           System.out.println((listaEventos.indexOf(evento)+1) + ".Dia:" + LocalDate.of(mes.getYear(), mes.getMonth(),evento.getDia()).getDayOfWeek() +evento.mostrar());  
       }
   }
   
   public void mostrarTipo(String tipo){ //muestra los elementos de la agenda de cierto tipo
       Evento evento;
       System.out.println(mes.getMonth().toString()+" "+mes.getYear());
       iterador = listaEventos.iterator();
       while(iterador.hasNext()){
            evento=iterador.next();
            if(evento.getTipo().equals(tipo)) System.out.println( "Dia:" +LocalDate.of(mes.getYear(), mes.getMonth(),evento.getDia()).getDayOfWeek() +evento.mostrar()); 
       }
   }
   
   public void mostrarDia(int dia){ //muestra los elemenots de la agenda que ocurren en cierto dia
       Evento evento;
       System.out.println(mes.getMonth().toString()+" "+mes.getYear());
       iterador = listaEventos.iterator();
       while(iterador.hasNext()){
            evento=iterador.next();
            if(evento.getDia()==dia) System.out.println("Dia:" + LocalDate.of(mes.getYear(), mes.getMonth(),evento.getDia()).getDayOfWeek() +evento.mostrar()); 
       }
   }

}