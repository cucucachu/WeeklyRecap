import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.filechooser.*;

public class WeeklyRecapGui extends JFrame implements ActionListener {
   private static final String newline = System.lineSeparator();
   private static final String welcomeText = "Welcome to the Weekly Recap Program." + newline + newline
      + "    To create this week's recap, first put all of this week's time sheets into"
      + " a single folder. Next select the folder in the file chooser above and"
      + " verify the correct path has appeared next to 'Folder Name:'. Finally"
      + " click the Create Recap button below. The recap will be saved in the same"
      + " folder that the timesheets are in. Any errors or messages will be displayed"
      + " in this text box." + newline + newline + "    Enjoy : ) " + newline; 

   private JPanel mainPanel;
   private JButton selectButton;
   private JButton runButton;
   private JTextArea log;
   private JFileChooser fileChooser;
   private JScrollPane logScrollPane;
   private WeeklyRecap weeklyRecap;

   public WeeklyRecapGui(WeeklyRecap weeklyRecap) {
      super("Weekly Recap");
      this.weeklyRecap = weeklyRecap;
      guiSetUp();
   }
   
   public WeeklyRecapGui(String title, WeeklyRecap weeklyRecap) {
      super("Weekly Recap");
      this.weeklyRecap = weeklyRecap;
      guiSetUp();
   }
   
   private void guiSetUp() {
      this.setDefaultCloseOperation(EXIT_ON_CLOSE);
      this.setSize(550, 635);
      this.setResizable(false);
      
      mainPanel = new JPanel();
      
      fileChooser = new JFileChooser();
      fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
      
      log = new JTextArea(15, 44);
      log.setMargin(new Insets(5,5,5,5));
      log.setEditable(false);
      log.setLineWrap(true);
      log.setWrapStyleWord(true);
      writeToTextArea(welcomeText);
      
      logScrollPane = new JScrollPane(log);
      logScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      
      runButton = new JButton("Create Recap");
      runButton.addActionListener(this);
            
      mainPanel.add(fileChooser);
      mainPanel.add(logScrollPane);
      mainPanel.add(runButton);
      
      this.add(mainPanel);
      
      this.setVisible(true);
   }
   
   public void actionPerformed(ActionEvent e) {
      weeklyRecap.recapWeek(fileChooser.getSelectedFile().getAbsolutePath());
   }
   
   public void writeToTextArea(String text) {
      log.append(text + newline);
   }
}
