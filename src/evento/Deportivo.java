
package evento;

import java.time.LocalTime;

/**
 *
 * @author Jhovan
 */
public class Deportivo extends Evento{
    private String contrincante;
    public Deportivo(int dia,LocalTime hi,LocalTime hf,String desc,String contrincante){
        super(dia,hi,hf,desc,"deportivo");
        this.contrincante=contrincante;
    }
    
    public void setContrincante(String contrincante){
        this.contrincante=contrincante;
    }
    
    public String getContrincante(){
        return contrincante;
    }
    
    @Override
    public String toString(){
        String cadena=super.toString()+Evento.getSep()+contrincante;
        return cadena;
    }
    
    @Override
    public String mostrar(){
        String cadena=super.mostrar()+"|Contrincante:"+contrincante;
        return cadena;
    }

    @Override
     public void modificar(){ //permite modificar atributos especiales del tipo de evento
         int op;
         System.out.println();
         System.out.println("Menu modificaciones exclusivas de Deportivo");
         System.out.println("Selecciona una opcion:");
         System.out.println("1.Cambiar el nombre del contrincante");
         System.out.println("2.Regresar al menu anterior");
         op=Evento.getOp(2);
         switch(op){
            case 1:
                 String contrincante;
                 System.out.println("Escribe el nombre del contrincante");
                 contrincante=sc.nextLine();
                 setContrincante(contrincante);
                break;                             
            default:
                break;  
             }
     }
}
