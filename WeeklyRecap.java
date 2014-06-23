import java.io.IOException;
import jxl.read.biff.BiffException;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class WeeklyRecap {

   private static final String newline = System.lineSeparator();
   private WeeklyRecapGui gui;
   
   public WeeklyRecap() {    
      gui = new WeeklyRecapGui(this);
   }
   
   public void recapWeek(String timeSheetFolderStr) {
      try {
         TimeSheet curTimeSheet;
         String timeSheetFolderPath = timeSheetFolderStr + "/";
         File timeSheetFolder = new File(timeSheetFolderPath);
         File[] allTimeSheets = timeSheetFolder.listFiles();
         ArrayList<String> timeSheetFileNames = new ArrayList<String>();
         ArrayList<String> failedSheets = new ArrayList<String>();
         ArrayList<Job> allJobs = new ArrayList<Job>();
         Recap recap;
         
         for (File file : allTimeSheets) {
             if (file.isFile()) {
                 timeSheetFileNames.add(file.getName());
             }
         }
         
         for (String fileName : timeSheetFileNames) {
            try {
               curTimeSheet = new TimeSheet(timeSheetFolderPath + fileName);
               allJobs.addAll(curTimeSheet.getJobs());
               curTimeSheet.close();
               gui.writeToTextArea(fileName + " read succesfully.");
            }
            catch (BiffException ex) {
               gui.writeToTextArea(fileName + " is not a timesheet or is not in the"
                  + " .xls format. Please remove this file, handle it manually or "
                  + "convert it to .xls format. The data from this file will not be "
                  + "included in the output file.");
               failedSheets.add(fileName);
            }
            catch (IndexOutOfBoundsException ex) {
               gui.writeToTextArea(fileName + " contains less than 5 sheets in its "
                  + "workbook. The info from this timesheet will not be included in "
                  + "the output file. Reformat " + fileName + " to the standard "
                  + "timesheet format and rerun this program or add its info to the"
                  + " recap manually.");
               failedSheets.add(fileName);
            }
            catch (TimeSheetFormatException ex) {
               gui.writeToTextArea(fileName + " is incorrectly formatted. "
                  + ex.getMessage());
               failedSheets.add(fileName);
            }
         }
                     
         gui.writeToTextArea(newline + "Failed to extract info from the following files:");
         for (String fileName : failedSheets) {
            gui.writeToTextArea(fileName);
         }
         
         recap = new Recap(allJobs, timeSheetFolderPath+"testRecap.xls");
         recap.makeRecap();
         recap.writeRecap();
         
         gui.writeToTextArea(newline + "Recap Succesful!");
      }
      catch (Exception ex) {
         gui.writeToTextArea("Caught exception" + ex);
      }
   }
}
