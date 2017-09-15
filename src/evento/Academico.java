
package evento;

import java.time.LocalTime;

/**
 *
 * @author Jhovan
 */
public class Academico extends Evento{
    private String[] material;
    
    public Academico(){}
    public Academico(int dia,LocalTime hi,LocalTime hf,String desc,String[] material){
        super(dia,hi,hf,desc,"academico");
        this.material=material;
    }
    
    public void setMaterial(String[] material){
        this.material=material;
    }
    
    public String[] getMaterial(){
        return material;
    }
    
    @Override
    public String toString(){
        String cadena=super.toString();
        for(int i=0;i<material.length;i++){
            cadena+=Evento.getSep() + material[i];
        }
        return cadena;
    }
    
    @Override
    public String mostrar(){
        String cadena=super.mostrar()+"|Material:";
        for(int i=0;i<material.length;i++){
            if(i!=0) cadena+=",";
            cadena+=material[i];
        }
        return cadena;
    }    
    
     public static String[] pedirMaterial(){ //regresa un arreglo de tipo String con el material
        System.out.println("¿Cuantos materiales necesitas?");
        int n=getOp(1000);
        String[] material = new String[n];
        for(int i=0;i<n;i++){
            System.out.println("Escribe el material: " + (i+1));
            material[i]=sc.nextLine();
        }       
        return material;
     }
     
     @Override
     public void modificar(){ //perite modificar atributos especiales del tipo de evento
         int op;
         System.out.println();
         System.out.println("Menu modificaciones exclusivas de Academico");
         System.out.println("Selecciona una opcion:");
         System.out.println("1.Cambiar lista de material");
         System.out.println("2.Regresar al menu anterior");
         op=getOp(2);
         switch(op){
            case 1:
                String[] material=pedirMaterial();
                setMaterial(material);
                break;                             
            default:
                break;  
             }
     }
}
