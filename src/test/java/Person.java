
import com.towel.bean.Formatter;
import com.towel.el.annotation.Resolvable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author c007329
 */
public class Person {

    @Resolvable(colName = "Name")
    private String name;
    @Resolvable(colName = "Age", formatter = IntFormatter.class)
    private int age;
    private Person parent;

    public Person(String name, int age, Person parent) {
        this.name = name;
        this.age = age;
        this.parent = parent;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static class IntFormatter implements Formatter {

        @Override
        public String format(Object obj) {
            return Integer.toString((Integer) obj);
        }

        @Override
        public String getName() {
            return "int";
        }

        @Override
        public Object parse(Object s) {
            return Integer.parseInt(s.toString());
        }
    }
}
