package gui;
import converter.Converter;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Gui {

    public static void main(String[] args) {
        converterWindow();

        }


        public static void converterWindow() {

            JFrame frame = new JFrame("JPEG to PNG Converter");
            JTextField field = new JTextField("");
            JButton convertButton = new JButton("Convert");
            JButton directoryButton = new JButton("...");
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            convertButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    String inputPath = field.getText();
                    List<String> patekiDoSiteFajlovi = null;

                    try {
                        try (Stream<Path> walk = Files.walk(Paths.get(inputPath))) {
                            patekiDoSiteFajlovi = walk.map(file -> file.toString())
                                                      .filter(file -> file.endsWith(".jpeg"))
                                                      .collect(Collectors.toList());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


//                        for (int a = 0; a < patekiDoSiteFajlovi.size(); a++) {
 //                           String patekaDoSlikata = patekiDoSiteFajlovi.get(a);
 //                           Converter.convertImage(patekaDoSlikata);
  //                      }

 //                       for (String patekaDoSlika : patekiDoSiteFajlovi) {
   //                         Converter.convertImage(patekaDoSlika);
     //                   }

                        patekiDoSiteFajlovi.forEach(patekaDoSlika -> Converter.convertImage(patekaDoSlika));

                        JOptionPane.showMessageDialog(null, "Files has been converted.");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "An error hAs occured during conVertIng.");
                    }


                    field.setText("");
                }
            });

            directoryButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    int returnVal = fileChooser.showOpenDialog(frame);

                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        File directory = fileChooser.getSelectedFile();
                        field.setText(directory.getAbsolutePath());
                    }
                }
            });

            convertButton.setBounds(100,100,100,40);
            field.setBounds(5,50,250 ,30);
            directoryButton.setBounds(260,53,30,20);
            frame.add(convertButton);
            frame.add(field);
            frame.add(directoryButton);
            frame.setSize(300,300 );
            frame.setLayout(null);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        }

    }