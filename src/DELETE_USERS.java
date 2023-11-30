import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class DELETE_USERS extends JFrame{
    private JTextField txtNAME;
    private JButton DELETEButton;
    private JButton BACKTOMENUButton;
    private JPanel MainPanel;
    private JButton CLEARButton;

    public DELETE_USERS() {
        setContentPane(MainPanel);
        setTitle("DELETE FRIENDS");     //Titulo Ventana
        setSize(370, 150);      //Tamaño ventana
        setLocationRelativeTo(null);        //La ventana se posiciona en el centro
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);    //El boton cerrar cierra la ventana
        setResizable(false);                //La ventana no se puede cambiar de tamaño
        //setVisible(true); Se visualiza la ventana en la parte superior izquierda


        DELETEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    // Get the name of the contact to be updated
                    // from the Command line argument
                    String newName = txtNAME.getText();

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

                    // Checking whether the name of contact exists.
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
                        if (name.equals(newName)){
                            found = true;
                            break;
                        }
                    }

                    // Delete the contact if record exists.
                    if (found == true) {

                        // Creating a temporary file
                        // with file pointer as tmpFile.
                        File tmpFile = new File("C:\\Users\\JAIRO\\IdeaProjects\\POO-Ejercicios Trabajo6\\temp.txt");

                        // Opening this temporary file
                        // in ReadWrite Mode
                        RandomAccessFile tmpraf
                                = new RandomAccessFile(tmpFile, "rw");

                        // Set file pointer to start
                        raf.seek(0);

                        // Traversing the friendsContact.txt file
                        while (raf.getFilePointer()
                                < raf.length()) {

                            // Reading the contact from the file
                            nameNumberString = raf.readLine();

                            index = nameNumberString.indexOf('!');
                            name = nameNumberString.substring(
                                    0, index);

                            // Check if the fetched contact
                            // is the one to be deleted
                            if (name.equals(newName)) {

                                // Skip inserting this contact
                                // into the temporary file
                                continue;
                            }

                            // Add this contact in the temporary
                            // file
                            tmpraf.writeBytes(nameNumberString);

                            // Add the line separator in the
                            // temporary file
                            tmpraf.writeBytes(
                                    System.lineSeparator());
                        }

                        // The contact has been deleted now
                        // So copy the updated content from
                        // the temporary file to original file.

                        // Set both files pointers to start
                        raf.seek(0);
                        tmpraf.seek(0);

                        // Copy the contents from
                        // the temporary file to original file.
                        while (tmpraf.getFilePointer()
                                < tmpraf.length()) {
                            raf.writeBytes(tmpraf.readLine());
                            raf.writeBytes(System.lineSeparator());
                        }

                        // Set the length of the original file
                        // to that of temporary.
                        raf.setLength(tmpraf.length());

                        // Closing the resources.
                        tmpraf.close();
                        raf.close();

                        // Deleting the temporary file
                        tmpFile.delete();

                        JOptionPane.showMessageDialog(null, " FRIEND DELETED ",
                                "CONFIRMATION",
                                JOptionPane.INFORMATION_MESSAGE);

                        System.out.println(" Friend deleted. ");
                    }

                    // The contact to be deleted
                    // could not be found
                    else {

                        // Closing the resources.
                        raf.close();

                        // Print the message
                        JOptionPane.showMessageDialog(null, " THE FRIEND DOES NOT EXIST ",
                                "CONFIRMATION",
                                JOptionPane.ERROR_MESSAGE);

                        txtNAME.setText("");

                        System.out.println(" Input name"
                                + " does not exists. ");
                    }
                }

                catch (IOException ioe) {
                    System.out.println(ioe);
                }
                catch (NumberFormatException nef) {
                    JOptionPane.showMessageDialog(null, " FOR INPUT DATA ",
                            "CONFIRMATION",
                            JOptionPane.ERROR_MESSAGE);

                    System.out.println(nef);
                }

            }
        });

        CLEARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                txtNAME.setText("");

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


