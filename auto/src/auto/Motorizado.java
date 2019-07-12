/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auto;

import fecha.MiCalendario;

/**
 *
 * @author Gabriel
 */
public class Motorizado {
    public final static Short VIN_LONG = 17;
    public final static String DELIM = "\t";
    
    public Motorizado() {
    }

    public Motorizado(String vin, MiCalendario fechaFab) throws MotorizadoException {
        setVin(vin);
        this.fechaFab = fechaFab;
    }

    
    private String vin;
    
    private MiCalendario fechaFab;

    public String getVin() {
        
        return vin;
    }

    public final void setVin(String vin) throws MotorizadoException {
        if (vin==null || vin.trim().isEmpty())
            throw new MotorizadoException("El VIN no puede ser vacio");
        
        if(vin.length()!=VIN_LONG)
            throw new MotorizadoException(
                "El VIN debe contener "+VIN_LONG+" caracteres");
        
        this.vin = vin;
    }

    public MiCalendario getFechaFab() {
        return fechaFab;
    }

    public void setFechaFab(MiCalendario fechaFab) {
        this.fechaFab = fechaFab;
    }

    @Override
    public String toString() {
        return vin+DELIM+fechaFab;
    }
    
    
}
