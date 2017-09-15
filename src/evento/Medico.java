
package evento;

import java.time.LocalTime;

/**
 *
 * @author Jhovan
 */
public class Medico extends Evento {
    private String nombre,especialidad,telefono;
    public Medico(int dia,LocalTime hi,LocalTime hf,String desc,String nombre, String especialidad, String telefono){
        super(dia,hi,hf,desc,"medico");
        this.nombre=nombre;
        this.especialidad=especialidad;
        this.telefono=telefono;
    }
    
    public void setNombre(String nombre){
       this.nombre=nombre;
    }
    
    public void setEspecialidad(String especialidad){
        this.especialidad=especialidad;
    }
    
    public void setTelefono(String telefono){
        this.telefono=telefono;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public String getEspecialidad(){
        return especialidad;
    }
    
    public String getTelefono(){
        return telefono;
    }
    
    @Override
    public String toString(){
        String cadena=super.toString()+Evento.getSep()+nombre+Evento.getSep()+especialidad+Evento.getSep()+telefono;
        return cadena;
    }
    
    @Override
    public String mostrar(){
        String cadena=super.mostrar()+"|Nombre:"+nombre+"|Especialidad:"+especialidad+"|Telefono:"+telefono;
        return cadena;        
    }
    
    @Override
     public void modificar(){ //permite modificar atributos especiales del tipo de evento
         boolean s=true;
         String nombreDoc,especialidad,telefono;
         int op;
         while(s){
            System.out.println();
            System.out.println("Menu modificaciones exclusivas de Medico");
            System.out.println("Selecciona una opcion:");
            System.out.println("1.Cambiar nombre del medico");
            System.out.println("2.Cambiar especialidad del medico");
            System.out.println("3.Cambiar telefono del medico");
            System.out.println("4.Regresar al menu anterior");
            op=Evento.getOp(4);
            switch(op){
                case 1:
                    System.out.println("Escribe el nombre del medico");
                    nombreDoc=sc.nextLine();
                    setNombre(nombreDoc);
                    break;
                case 2:
                    System.out.println("Escribe la especialidad del medico");
                    especialidad=sc.nextLine();  
                    setEspecialidad(especialidad);
                    break;
                case 3:                         
                    System.out.println("Escribe el telefono del medico");
                    telefono=sc.nextLine();
                    setTelefono(telefono);
                    break;
                case 4:
                    s=false;
                    break;
            }
         }     
     }
}
