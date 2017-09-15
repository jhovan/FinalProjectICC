
package evento;

import java.time.LocalTime;

/**
 *
 * @author Jhovan
 */
public class Social extends Evento {
    private String[] personas;
    public Social(int dia,LocalTime hi,LocalTime hf,String desc,String[] personas){
        super(dia,hi,hf,desc,"social");
        this.personas=personas;
    }
    
    public void setPersonas(String[] personas){
        this.personas=personas;
    }
    
    public String[] getPersonas(){
        return personas;
    }
    
    @Override
    public String toString(){
        String cadena=super.toString();
        for(int i=0;i<personas.length;i++){
            cadena+=Evento.getSep() + personas[i];
        }
        return cadena;
    }

    @Override
    public String mostrar(){
        String cadena=super.mostrar()+"|Personas:";
        for(int i=0;i<personas.length;i++){
            if(i!=0) cadena+=",";
            cadena+=personas[i];
        }
        return cadena;
    }      
    
     public static String[] pedirPersonas(){ //regresa un arreglo de tipo String que contiene a los invitados
        System.out.println("¿Cuantos personas necesitas?");
        int n=Evento.getOp(1000);
        String[] persona = new String[n];
        for(int i=0;i<n;i++){
            System.out.println("Escribe la persona: " + (i+1));
            persona[i]=sc.nextLine();
        }       
        return persona;
     }
     
     @Override
     public void modificar(){ //permite modificar atributos especiales del tipo de evento
         int op;
         System.out.println();
         System.out.println("Menu modificaciones exclusivas de Social");
         System.out.println("Selecciona una opcion:");
         System.out.println("1.Cambiar lista de personas");
         System.out.println("2.Regresar al menu anterior");
         op=Evento.getOp(2);
         switch(op){
            case 1:
                String[] personas=pedirPersonas();
                setPersonas(personas);
                break;                             
            default:
                break;  
         }
    }      
}
