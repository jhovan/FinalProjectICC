/*
 Esta clase se trata de un conjunto de agendas, y permite crear eventos a los que todos puedan asistir
 */
package evento;

import java.time.YearMonth;
import java.util.ArrayList;


/**
 *
 * @author Jhovan
 */
public class AgendaMultiple {
    private Agenda[] listaAgendas;
    private YearMonth mes = YearMonth.now(); 
    
    public AgendaMultiple(){    
    }
    
    public AgendaMultiple(Agenda[] listaAgendas,YearMonth mes){   //crea el conjunto de agendas a partir de un arreglo de agendas, un mes y un año
        this.listaAgendas=listaAgendas;
        this.mes=mes;
    }
    
    public boolean agregarEvento(Evento evento){
        Evento eventoAux;
        Agenda agenda=listaAgendas[0];
        ArrayList<Evento> listaEventos;
        if(agenda.validarDia(evento.getDia())&&Evento.validarHora(evento.getHoraI(),evento.getHoraF())){
            for(Agenda elemento:listaAgendas){
                listaEventos=elemento.getListaEventos();
                for(int j=0;j<listaEventos.size();j++){
                    eventoAux=listaEventos.get(j);
                    if(eventoAux.intersecta(evento)){
                        System.out.println("El evento se intersecta en alguna agenda");
                        return false;
                    }
                }
            }
            for(Agenda elemento:listaAgendas){
                elemento.agregar(evento);
                elemento.escribirArchivo();
            }
            return true;
        }
        System.out.println("Fecha u hora invalida");
        return false;

    }
    
    public void setMes(YearMonth mes){
        this.mes=mes;
    }
    
    public void setListaEventos(Agenda[] listaAgendas){
        this.listaAgendas=listaAgendas;
    }
    
    public YearMonth getMes(){
        return mes;
    }
    
    public Agenda[] getlistaAgenda(){
        return listaAgendas;
    }
}
