/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.escala2.util;

import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author krismorte
 */
public class MyJTable extends JTable {

    private BeanTableModel model;

    public MyJTable(BeanTableModel model) {
        super(model);
        this.model = model;
        //this.setModel(model);
    }

    public List getRows() {
        return model.getRows();
    }

    public void addRow(Object o) {
        model.addRow(o);
    }

    public Object getColumnValue(int column) {
        return this.getValueAt(this.getSelectedRow(), column);
    }

   /* @Override
    public boolean isCellEditable(int row, int column) {
        return model.isCellEditable(row, column); //To change body of generated methods, choose Tools | Templates.
    }*/

}
