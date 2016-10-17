package stupid.stuff;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import com.michaelbaranov.microba.calendar.DatePicker;

public class AlertWindow extends JFrame {
    JPanel panel;
    JButton button;
    JPopupMenu popupMenu;
    DatePicker datePicker;

    public AlertWindow() {
        panel = new JPanel();
        button = new JButton();

        add(panel);
        panel.add(button);
        panel.add(datePicker);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                datePicker = new DatePicker();
            }
        });

    }


    public static void main(String[] args) {
        AlertWindow alertWindow = new AlertWindow();
        alertWindow.setVisible(true);
    }

}