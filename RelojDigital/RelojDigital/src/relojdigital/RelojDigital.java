/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relojdigital;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;

/**
 *
 * @author 34605
 * 
 */
public class RelojDigital extends JLabel implements Serializable
{
    /**
     * Nuestras dos propiedades
     */
    private boolean formato24;
    private Alarma alarma;
    
    /**
     * Esto son atributos (sin getters ni setters, solo para uso interno.
     */
    private final SimpleDateFormat sdf24h = new SimpleDateFormat("HH:mm:ss");
    private final SimpleDateFormat sdf12h = new SimpleDateFormat("hh:mm:ss a");
    
    /**
     * Listener de nuestro componente
     */
    private AlarmaListener alarmaListener;
    
    public RelojDigital()
    {
        //Aquí  metemos el método clave. Un timer que se ejecuta cada segundo para
        //actualizar la hora.
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Date horaActual = new Date();

                //Decidimos con que formato mostramos la hora
                if (formato24)
                    setText(sdf24h.format(horaActual));
                else
                    setText(sdf12h.format(horaActual));
                
                if (alarma!=null)
                {
                    //Si la condición de la alarma se activa, llamamos al listener
                    if (alarma.isActiva() && horasCoinciden(horaActual,alarma.getHoraAlarma()))
                    {
                        if (alarmaListener!=null)
                        {
                            alarmaListener.suenaAlarma();
                        }
                    }
                }
            }
        }, 0, 1000);
    }

    public boolean isFormato24() {
        return formato24;
    }
    
    public void setFormato24 (boolean formato24){
        this.formato24 = formato24;
    }

    public Alarma getAlarma() {
        return alarma;
    }

    public void setAlarma(Alarma alarma) {
        this.alarma = alarma;
    }
       
    public void addAlarmaListener(AlarmaListener alarmaListener)
    {
        this.alarmaListener = alarmaListener;
    }
    
    /**
     * Método para comprobar si dos horas coinciden
     * @param horaActual 
     * @param horaAlarma
     * @return Si la diferencia entre las dos horas es menos de un segundo, devolvemos
     * true
     */
    private boolean horasCoinciden(Date horaActual, Date horaAlarma)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(horaActual);
        int horasActual, minActual, segActual, horasAlarma, minAlarma, segAlarma;
        horasActual = calendar.get(Calendar.HOUR_OF_DAY);
        minActual = calendar.get(Calendar.MINUTE);
        segActual = calendar.get(Calendar.SECOND);
        calendar.setTime(horaAlarma);
        horasAlarma = calendar.get(Calendar.HOUR_OF_DAY);
        minAlarma = calendar.get(Calendar.MINUTE);
        segAlarma = calendar.get(Calendar.SECOND);
        if (horasActual == horasAlarma && minActual == minAlarma && segActual == segAlarma)
            return true;
        else
            return false;
    }
}
