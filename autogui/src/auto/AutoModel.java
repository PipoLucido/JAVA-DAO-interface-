/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auto;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Gabriel
 */
public class AutoModel extends AbstractTableModel{

    List<Auto> autos;
    
    String[] encabezados = {"VIN", "Fecha Fab.", "Marca"};
    
    public void setAutos(List<Auto> autos) {
        this.autos = autos;
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return autos.size();
    }

    @Override
    public int getColumnCount() {
        
        return encabezados.length;
    }

    @Override
    public String getColumnName(int column) {
        return encabezados[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Auto auto = autos.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return auto.getVin();
            case 1:
                return auto.getFechaFab();
            case 2:
                return auto.getMarca();
        }
        
        return null;
    }
    
}
