

package evento;

import java.time.LocalTime;

/**
 *
 * @author Jhovan
 */
public class Otro extends Evento {
    public Otro(int dia,LocalTime hi,LocalTime hf,String desc){
        super(dia,hi,hf,desc,"otro");
    }
}
