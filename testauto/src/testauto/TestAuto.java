/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testauto;

import auto.Auto;
import auto.Motorizado;
import auto.MotorizadoException;
import fecha.MiCalendario;
import java.util.GregorianCalendar;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel
 */
public class TestAuto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MotorizadoException {
        Motorizado auto = new Auto();
        try {
            auto.setVin("1234567890A234567");
        } catch (MotorizadoException ex) {
            Logger.getLogger(TestAuto.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
//////////////////////////////////////////

        Auto auto2;
        MiCalendario fechaFab =
                new MiCalendario(44, 5, 2019);
        auto2 = new Auto("1234567890A234567",
                fechaFab);

        System.out.println(auto2.toString());

        System.out.println("Todo OK!!!");
    }
    
}
