import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManejoArchivosGUI extends JFrame {
    private JButton btnCREATE;
    private JButton btnADDUSERS;
    private JButton btnREADUSERS;
    private JButton btnUPDATEUSERS;
    private JPanel MainPanel;
    private JButton DELETEFRIENDSButton;
    private JButton EXITButton;

    public ManejoArchivosGUI() {
        setContentPane(MainPanel);
        setTitle("MENU MANAGEMENT");     //Titulo Ventana
        setSize(420, 150);      //Tamaño ventana
        setLocationRelativeTo(null);        //La ventana se posiciona en el centro
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);    //El boton cerrar cierra la ventana
        setResizable(false);                //La ventana no se puede cambiar de tamaño
        //setVisible(true); Se visualiza la ventana en la parte superior izquierda


        btnCREATE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
                CREATE_ADDUSERS form=new CREATE_ADDUSERS();
                form.setVisible(true);

            }
        });
        btnADDUSERS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
                CREATE_ADDUSERS form=new CREATE_ADDUSERS();
                form.setVisible(true);

            }
        });
        btnREADUSERS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
                READ_USERS form=new READ_USERS();
                form.setVisible(true);
            }
        });
        btnUPDATEUSERS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
                UPDATE_USERS form=new UPDATE_USERS();
                form.setVisible(true);
            }
        });
        DELETEFRIENDSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
                DELETE_USERS form=new DELETE_USERS();
                form.setVisible(true);
            }
        });
        EXITButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }
        });
    }

}
