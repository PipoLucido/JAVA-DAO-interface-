/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auto;

import fecha.MiCalendario;
import fecha.MiCalendarioException;

/**
 *
 * @author Gabriel
 */
public class Auto extends Motorizado {

    private String marca;
    
    private String modelo;
    
    private String patente;
    
    private MiCalendario fechaPatentamiento;
    
    private Double precio;
    
    private Integer anio;

    public Auto(String vin, MiCalendario fechaFab) 
            throws MotorizadoException {
        super(vin, fechaFab);
    }

    public Auto() {
        super();
    }

    public Auto(String marca, String modelo, String patente, MiCalendario fechaPatentamiento, Double precio, Integer anio, String vin, MiCalendario fechaFab) throws MotorizadoException {
        super(vin, fechaFab);
        this.marca = marca;
        this.modelo = modelo;
        this.patente = patente;
        this.fechaPatentamiento = fechaPatentamiento;
        this.precio = precio;
        this.anio = anio;
    }

    
    
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public MiCalendario getFechaPatentamiento() {
        return fechaPatentamiento;
    }

    public void setFechaPatentamiento(MiCalendario fechaPatentamiento) {
        this.fechaPatentamiento = fechaPatentamiento;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    @Override
    public String toString() {
        return super.toString()+DELIM+
                String.format("%-20s", this.marca) +DELIM+
                String.format("%-10s", this.modelo)+DELIM+
                String.format("%-8s", this.patente);
    }
    
    
    public static Auto str2Auto(String[] campos) 
            throws MotorizadoException, MiCalendarioException {
        Auto auto  = new Auto();
        int contador = 0;
        auto.setVin(campos[contador++]);
        auto.setFechaFab(MiCalendario.str2Fecha(campos[contador++]));
        
        //auto.setFechaPatentamiento(MiCalendario.str2Fecha(null));
        
        return auto;
    }
    
}
