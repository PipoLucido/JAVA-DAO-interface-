/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fecha;

import java.sql.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Gabriel
 */
public class MiCalendario extends GregorianCalendar{

    public MiCalendario(Date date) {
        
        setTimeInMillis(date.getTime());
        
    }
    public MiCalendario(int dia, int mes, int anio) 
            throws MiCalendarioException {
        super(anio, mes-1, dia);
        setLenient(false); // apagamos la permisividad
        
        try {
            get(MONTH);
        } catch (Exception e) {
            throw new MiCalendarioException("Error en la fecha");
        }
    }

    public int getMes() {
        
        return get(MONTH)+1;
    }
    @Override
    public String toString() {
        return get(DAY_OF_MONTH)+"/"+
               getMes()+"/"+
               get(YEAR);
    }
    
    public static MiCalendario str2Fecha(String fechaStr) 
            throws MiCalendarioException {
        String fecha[] = fechaStr.split("/");
        int dia = Integer.valueOf(fecha[0]);
        int mes = Integer.valueOf(fecha[1]);
        int anio = Integer.valueOf(fecha[2]);
        
        return new MiCalendario(dia, mes, anio);
    }
    
    public static Date converto2SqlDate(MiCalendario cal) {
        return new Date(cal.getTimeInMillis());
    }
    
    public static MiCalendario convert2MiCalendario(Date sqlDate) {
        return new MiCalendario(sqlDate);
    }
}
