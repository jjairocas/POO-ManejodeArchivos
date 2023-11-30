import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class READ_USERS extends JFrame {
    private JButton btnREADUSERS;
    private JButton btnCLEAR;
    private JButton btnBACKTOMENU;
    private JPanel MainPanel;
    private JList DisplayUsers;
    private DefaultListModel listadespliegaUI = new DefaultListModel<>();

    public READ_USERS() {
        setContentPane(MainPanel);
        setTitle("READ FRIENDS");     //Titulo Ventana
        setSize(450, 350);      //Tamaño ventana
        setLocationRelativeTo(null);        //La ventana se posiciona en el centro
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);    //El boton cerrar cierra la ventana
        setResizable(false);                //La ventana no se puede cambiar de tamaño
        //setVisible(true); Se visualiza la ventana en la parte superior izquierda


        btnREADUSERS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    String nameNumberString;
                    String name;
                    long number;
                    int index;

                    // Using file pointer creating the file.
                    File file = new File("C:\\Users\\JAIRO\\IdeaProjects\\POO-Ejercicios Trabajo6\\friendsContact.txt");

                    if (!file.exists()) {

                        // Create a new file if not exists.
                        file.createNewFile();
                    }

                    // Opening file in reading and write mode.

                    RandomAccessFile raf
                            = new RandomAccessFile(file, "rw");
                    boolean found = false;

                    // Traversing the file
                    // getFilePointer() give the current offset
                    // value from start of the file.
                    while (raf.getFilePointer() < raf.length()) {

                        // reading line from the file.
                        nameNumberString = raf.readLine();

                        // splitting the string to get name and
                        // number
                        String[] lineSplit
                                = nameNumberString.split("!");

                        // separating name and number.
                        name = lineSplit[0];
                        number = Long.parseLong(lineSplit[1]);

                        String leyenda="FRIEND NAME: "+name+" CONTACT NUMBER: "+number;
                        listadespliegaUI.addElement(leyenda);
                        DisplayUsers.setModel(listadespliegaUI);

                        // Print the contact data
                        System.out.println(
                                "Friend Name: " + name + "\n"
                                        + "Contact Number: " + number + "\n");
                    }


                }
                catch(IOException ioe)
                {

                    System.out.println(ioe);
                }
                catch(NumberFormatException nef)
                {

                    System.out.println(nef);
                }

            }
        });
        btnCLEAR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                listadespliegaUI.clear();

            }
        });
        btnBACKTOMENU.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
                ManejoArchivosGUI form=new ManejoArchivosGUI();
                form.setVisible(true);
            }
        });
    }

}
