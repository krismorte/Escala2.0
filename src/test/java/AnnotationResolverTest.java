
import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author c007329
 */
public class AnnotationResolverTest {

    public void testAnnotationResolverInit() {
        AnnotationResolver resolver = new AnnotationResolver(Person.class);
        ObjectTableModel<Person> tableModel = new ObjectTableModel<Person>(
                resolver,
                "name,age,parent.name:Parent,parent.age:Parent age");
        tableModel.setData(getData());
        tableModel.setEditableDefault(true);

        JTable table = new JTable(tableModel);
        JFrame frame = new JFrame("ObjectTableModel");
        JScrollPane pane = new JScrollPane();
        pane.setViewportView(table);
        pane.setPreferredSize(new Dimension(400, 200));
        frame.add(pane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private List<Person> getData() {
        List<Person> list = new ArrayList<Person>();
        list.add(new Person("Marky", 17, new Person("Marcos", 40)));
        list.add(new Person("Jhonny", 21, new Person("", 0)));
        list.add(new Person("Douglas", 50, new Person("Adams", 20)));
        return list;
    }

    public static void main(String[] args) {
        new AnnotationResolverTest().testAnnotationResolverInit();
    }

}
