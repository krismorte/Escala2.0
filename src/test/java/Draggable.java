/*
 * Draggable.java
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import javax.swing.border.Border;
import javax.swing.event.MouseInputAdapter;
public class Draggable extends JComponent {
    private Point pointPressed;
    private JComponent draggable;
    public Draggable(final JComponent component, final int x, final int y) {
        draggable = component;
//        draggable.setCursor(draggable.getCursor());
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setLocation(x, y);
        setSize(component.getPreferredSize());
        setLayout(new BorderLayout());
        add(component);
        MouseInputAdapter mouseAdapter = new MouseHandler();
        addMouseMotionListener(mouseAdapter);
        addMouseListener(mouseAdapter);
    }
    @Override
    public void setBorder(final Border border) {
        super.setBorder(border);
        if (border != null) {
            Dimension size = draggable.getPreferredSize();
            Insets insets = border.getBorderInsets(this);
            size.width += (insets.left + insets.right + 5);
            size.height += (insets.top + insets.bottom);
            setSize(size);
        }
    }
    private class MouseHandler extends MouseInputAdapter {
        @Override
        public void mouseDragged(final MouseEvent e) {
            Point pointDragged = e.getPoint();
            Point loc = getLocation();
            loc.translate(pointDragged.x - pointPressed.x,
                    pointDragged.y - pointPressed.y);
            setLocation(loc);
        }
        @Override
        public void mousePressed(final MouseEvent e) {
            pointPressed = e.getPoint();
        }
    }
    //for testing only:
    public static void main(final String[] args) {
        Runnable gui = new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(
                            UIManager.getSystemLookAndFeelClassName());
                } catch (Exception ex) {
                }
                JFrame f = new JFrame("Draggable Components");
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setSize(300, 300);
                f.setLocationRelativeTo(null);
                JPanel panel = new JPanel(null);
                JLabel label = new JLabel("Hello World");
                Draggable d1 = new Draggable(label, 50, 50);
                panel.add(d1);
                JTextField textfield = new JTextField("JTextField");
                Draggable d2 = new Draggable(textfield, 150, 150);
                d2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                panel.add(d2);
                JTable table = new JTable(2, 2);
                Draggable d3 = new Draggable(table, 50, 200);
                d3.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                panel.add(d3);
                f.add(panel);
                f.setVisible(true);
            }
        };
        //GUI must start on EventDispatchThread:
        SwingUtilities.invokeLater(gui);
    }
}