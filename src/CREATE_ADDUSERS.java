import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class CREATE_ADDUSERS extends JFrame {
    private JTextField txtNAME;
    private JTextField txtPHONE;
    private JButton ADDUSERButton;
    private JButton CLEARButton;
    private JButton BACKTOMENUButton;
    private JPanel MainPanel;

    public CREATE_ADDUSERS() {
        setContentPane(MainPanel);
        setTitle("ADD USERS");     //Titulo Ventana
        setSize(400, 150);      //Tamaño ventana
        setLocationRelativeTo(null);        //La ventana se posiciona en el centro
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);    //El boton cerrar cierra la ventana
        setResizable(false);                //La ventana no se puede cambiar de tamaño
        //setVisible(true); Se visualiza la ventana en la parte superior izquierda


        ADDUSERButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    // Get the name of the contact to be updated
                    // from the Command line argument
                    String newName;
                    newName=txtNAME.getText();

                    // Get the number to be updated
                    // from the Command line argument
                    long newNumber;
                    newNumber = Long.parseLong(txtPHONE.getText());

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

                    // Checking whether the name
                    // of contact already exists.
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

                        // if condition to find existence of record.
                        if (name == newName
                                || number == newNumber) {
                            found = true;
                            break;
                        }
                    }

                    if (found == false) {

                        // Enter the if block when a record
                        // is not already present in the file.
                        nameNumberString
                                = newName + "!"
                                + String.valueOf(newNumber);

                        // writeBytes function to write a string
                        // as a sequence of bytes.
                        raf.writeBytes(nameNumberString);

                        // To insert the next record in new line.
                        raf.writeBytes(System.lineSeparator());

                        // Print the message
                        JOptionPane.showMessageDialog(null, "FRIEND ADDED ",
                                         "USER CONFIRMATION",
                                JOptionPane.INFORMATION_MESSAGE);
                        System.out.println(" Friend added. ");

                        // Closing the resources.
                        raf.close();
                    }
                    // The contact to be updated
                    // could not be found
                    else {

                        // Closing the resources.
                        raf.close();

                        // Print the message
                        JOptionPane.showMessageDialog(null, "FRIEND ALREADY EXISTS ",
                                "USER CONFIRMATION",
                                JOptionPane.ERROR_MESSAGE);

                        System.out.println(" Input name"
                                + " does not exists. ");
                    }
                }

                catch (IOException ioe) {

                    System.out.println(ioe);
                }
                catch (NumberFormatException nef) {

                    System.out.println(nef);
                }


            }
        });
        CLEARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                txtNAME.setText("");
                txtPHONE.setText("");

            }
        });
        BACKTOMENUButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
                ManejoArchivosGUI form=new ManejoArchivosGUI();
                form.setVisible(true);
            }
        });
    }

}
