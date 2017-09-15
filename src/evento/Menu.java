/*
Menu para realizar diversas operaciones con agendas, posee una varible estatica de tipo agenda durante toda su ejecucion
(se centra en un agenda particular)
 */
package evento;


import java.util.Scanner;
import java.time.LocalTime;
import java.time.Month;



/**
 *
 * @author Jhovan
 */
public class Menu {
     static Scanner sc = new Scanner(System.in);
     static Agenda agenda; 
     public static void main(String[] args) {
         menuPrincipal();
     }

     public static void menuPrincipal(){ //es el menu principal
         boolean s=true;
         String nombre;
         int op,n;
         while(s){
            System.out.println();
            System.out.println("Programa Agenda Mensual");
            System.out.println("Selecciona una opcion:");
            System.out.println("1.Crear agenda nueva");
            System.out.println("2.Abrir agenda desde un archivo");
            System.out.println("3.Crear evento para agendas multiples");
            System.out.println("4.Salir del programa");
            op=Evento.getOp(4);
            switch(op){
                case 1:
                    int mes,ano;
                    System.out.println("Escribe el numero del mes que quieres asignarle a tu agenda");
                    mes=Evento.getOp(12);
                    System.out.println("Seleccionaste el mes: " + Month.of(mes));
                    System.out.println("Escribe el año");
                    ano=Evento.getOp(3000);                    
                    System.out.println("Escribe el nombre que quieres asignarle a tu agenda (no incluyas la extension)");
                    nombre=sc.nextLine();
                    agenda=new Agenda(ano,mes,nombre);
                    subMenu();
                    break;
                case 2: 
                    agenda=abrirAgenda();
                    if(!agenda.getListaEventos().isEmpty()){
                        System.out.println("Archivo abierto");
                        subMenu();
                    }
                    break;
                case 3:
                    System.out.println("Recuerda que las agendas deben ser del mismo mes");
                    System.out.println("¿Cuantas agendas vas a consultar?");
                    n=Evento.getOp(1000);
                    Agenda[] listaAgendas= new Agenda[n]; 
                    for(int i=0;i<n;i++){
                        listaAgendas[i]=abrirAgenda();
                        if(i==0) agenda=listaAgendas[i];
                        if(listaAgendas[i].getListaEventos().isEmpty()) i--;
                    }
                    AgendaMultiple agendaMultiple=new AgendaMultiple(listaAgendas,agenda.getMes());
                    Evento evento=crearEvento();
                    while(!agendaMultiple.agregarEvento(evento)){
                        evento.setDia(pedirDia());
                        evento.setHoraI(pedirHoraI());
                        evento.setHoraF(pedirHoraF());
                    }
                    break;
                case 4:
                    System.out.println("Saliendo");
                    s=false;
                    break;
            }
         }
     }
     
     public static Agenda abrirAgenda(){ //regresa una agenda despues de pedir el nombre del archivo
        System.out.println("Escribe el nombre del archivo (debe estar en este direcctorio, no incluyas la extencion)");
        String nombre=sc.nextLine();
        Agenda agendaN=new Agenda(nombre);
        return agendaN;
     }
     
     public static void subMenu(){ //menu para consultar o modificar la agenda
         boolean s=true;
         int op;
         while(s){
            System.out.println();
            System.out.println("Selecciona una opcion:");
            System.out.println("1.Consultar agenda");
            System.out.println("2.Modificar agenda");
            System.out.println("3.Regresar al menu anterior");
            op=Evento.getOp(3);
            switch(op){
                case 1:
                    menuConsultar();
                    break;
                case 2:
                    menuModificarAgenda();
                    break;
                case 3:
                    s=false;
                    break;
            }
         }
     }
     
     public static void menuConsultar(){ //menu para los distintos tipos de consultas
         boolean s=true;
         int op;
         while(s){
            System.out.println();
            System.out.println("Menu consultar");
            System.out.println("Selecciona una opcion:");
            System.out.println("1.Por mes");
            System.out.println("2.Por dia");
            System.out.println("3.Por eventos academicos");
            System.out.println("4.Por eventos medicos");
            System.out.println("5.Por eventos deportivos");
            System.out.println("6.Por eventos sociales");
            System.out.println("7.Por otros eventos");
            System.out.println("8.Regresar al menu anterior");
            op=Evento.getOp(8);
            switch(op){
                case 1:
                    agenda.mostrar();
                    break;
                case 2:
                    System.out.println("Escribe el dia");
                    int dia=Evento.getOp(agenda.getMes().lengthOfMonth());
                    agenda.mostrarDia(dia);
                    break;
                case 3:
                    agenda.mostrarTipo("academico");
                    break;
                case 4:
                    agenda.mostrarTipo("medico");
                    break;
                case 5:
                    agenda.mostrarTipo("deportivo");
                    break;
                case 6:
                    agenda.mostrarTipo("social");
                    break;
                case 7:
                    agenda.mostrarTipo("otro");
                    break;
                case 8:
                    s=false;
                    break;
            }
         }
     }
     
     public static void menuModificarAgenda(){ //menu para los distintos tipos de modificaciones para la agenda
         boolean s=true;
         String nombre;
         int op,n;
         while(s){
            System.out.println();
            System.out.println("Menu modificar agenda");
            System.out.println("Selecciona una opcion:");
            System.out.println("1.Agregar evento");
            System.out.println("2.Borrar evento");
            System.out.println("3.Modificar evento");
            System.out.println("4.Guardar cambios");
            System.out.println("5.Guardar cambios en un archivo nuevo");
            System.out.println("6.Regresar al menu anterior");
            op=Evento.getOp(6);
             switch(op){
                case 1:
                    agregarEvento();
                    break;
                case 2:
                    System.out.println("Selecciona el evento a borrar (con su numero)");
                    agenda.mostrar();
                    n=Evento.getOp(agenda.getListaEventos().size())-1;
                    agenda.borrar(n);
                    break;
                case 3:
                    menuModificarEvento();
                    break;
                case 5: //falling down
                    System.out.println("Escribe el nombre que le quieres asignar a la agenda (no incluyas la extension)");
                    nombre=sc.nextLine();
                    agenda.setNombre(nombre);
                 
                case 4:
                    agenda.escribirArchivo();
                    break;
                case 6:
                    s=false;
                    break;
             }
         }
     }
     
     public static void menuModificarEvento(){ //menu para las modificaciones basicas de un evento
         boolean s=true;
         int op,n;
    
        System.out.println();
        System.out.println("Menu modificar evento");
        System.out.println("Selecciona un evento (por su indice)");
        agenda.mostrar();
        n=Evento.getOp(agenda.getListaEventos().size())-1;
        while(s){
            System.out.println("Selecciona una opcion:");
            System.out.println("1.Cambiar dia");
            System.out.println("2.Cambiar hora");
            System.out.println("3.Cambiar descripcion");
            System.out.println("4.Modificaciones exclusivas del tipo");
            System.out.println("5.Regresar al menu anterior");
            op=Evento.getOp(5);
            switch(op){
               case 1:
                   int dia=pedirDia();
                   while(!agenda.validarDia(dia)){
                        System.out.println("Dia invalido, intenta de nuevo");
                        dia=pedirDia();
                   }
                   agenda.getListaEventos().get(n).setDia(dia);
                   break;
               case 2:
                   LocalTime hi=pedirHoraI(),hf=pedirHoraF();
                   while(!Evento.validarHora(hi, hf)){
                       System.out.println("Hora incorrecta, intenta de nuevo");
                       hi=pedirHoraI();
                       hf=pedirHoraF();
                   }
                   agenda.getListaEventos().get(n).setHoraI(hi);
                   agenda.getListaEventos().get(n).setHoraI(hf);
                   break;
               case 3:
                   System.out.println("Escrbe la nueva descripcion del evento");
                   String desc=sc.nextLine();
                   agenda.getListaEventos().get(n).setDesc(desc);
                   break;
               case 4:
                   agenda.getListaEventos().get(n).modificar();
                   break;     
               case 5:
                   s=false;
                   break;  
             }
         }
     }
     
     
     
     public static void agregarEvento(){ //metodo que permite agregar un evento a la agenda
         Evento evento=crearEvento();
         while(!agenda.agregar(evento)){
             evento.setDia(pedirDia());
             evento.setHoraI(pedirHoraI());
             evento.setHoraF(pedirHoraF());
         }
         System.out.println("Evento agregado");
     }
     
     
     public static Evento crearEvento(){ //metodo que permite regresa un evento despues de pedir los datos necesarios
         int op,dia;
         LocalTime hi,hf;
         String desc;
         Evento evento;
         dia=pedirDia();
         hi=pedirHoraI();
         hf=pedirHoraF();
         System.out.println("Escribe la descripcion del evento");
         desc=sc.nextLine();
         System.out.println("Selecciona un tipo de evento:");
         System.out.println("1.Academico");
         System.out.println("2.Medico");
         System.out.println("3.Deportivo");
         System.out.println("4.Social");
         System.out.println("5.Otro");
         op=Evento.getOp(5);
         switch(op){
             case 1:
                 String[] material=Academico.pedirMaterial();
                 evento=new Academico(dia,hi,hf,desc,material);
                 break;
             case 2:
                 String nombreDoc,especialidad,telefono;
                 System.out.println("Escribe el nombre del medico");
                 nombreDoc=sc.nextLine();
                 System.out.println("Escribe la especialidad del medico");
                 especialidad=sc.nextLine();       
                 System.out.println("Escribe el telefono del medico");
                 telefono=sc.nextLine();
                 evento=new Medico(dia,hi,hf,desc,nombreDoc,especialidad,telefono);
                 break;
             case 3:
                 String contrincante;
                 System.out.println("Escribe el nombre del contrincante");
                 contrincante=sc.nextLine();
                 evento=new Deportivo(dia,hi,hf,desc,contrincante);
                 break;
             case 4:
                 String[] personas = Social.pedirPersonas();              
                 evento=new Social(dia,hi,hf,desc,personas);
                 break;
             default:
                 evento=new Otro(dia,hi,hf,desc);
                 break;
         }
         return evento;
     }
     
     public static int pedirDia(){ //metodos que solo sirven para pedir informacion de forma rapida
         System.out.println("Escribe el dia del evento");
         int dia=Evento.getOp(agenda.getMes().lengthOfMonth());
         return dia;
     }
     
     public static LocalTime pedirHoraI(){
         System.out.println("Escribe la hora de inicio del evento con este formato HH:MM");
         LocalTime hi=Evento.hourFromString(sc.nextLine());
         return hi;
     }
     
     public static LocalTime pedirHoraF(){
         System.out.println("Escribe la hora de termino del evento con este formato HH:MM");
         LocalTime hf=Evento.hourFromString(sc.nextLine());
         return hf;
     }
     
}
