import java.io.IOException;
import jxl.read.biff.BiffException;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class Driver {
   private static final String timeSheetFolderPath = "../Data/Round 1/TimeSheets/";

   public static void main(String[] args) {
      try {
         TimeSheet curTimeSheet;
         File timeSheetFolder = new File("../Data/Round 1/TimeSheets/");
         File[] allTimeSheets = timeSheetFolder.listFiles();
         ArrayList<String> timeSheetFileNames = new ArrayList<String>();
         ArrayList<String> failedSheets = new ArrayList<String>();
         ArrayList<Job> allJobs = new ArrayList<Job>();
         Recap recap;
         WeeklyRecapGui gui = new WeeklyRecapGui();         

/*
         for (File file : allTimeSheets) {
             if (file.isFile()) {
                 timeSheetFileNames.add(file.getName());
             }
         }
         
         for (String fileName : timeSheetFileNames) {
            try {
               curTimeSheet = new TimeSheet(timeSheetFolderPath + fileName);
               System.out.println(fileName + ":");
               //curTimeSheet.printJobs();
               allJobs.addAll(curTimeSheet.getJobs());
               curTimeSheet.close();
            }
            catch (BiffException ex) {
               System.out.println(fileName + " is not a timesheet or is not in the"
                  + " .xls format. Please remove this file, handle it manually or "
                  + "convert it to .xls format. The data from this file will not be "
                  + "included in the output file.");
               failedSheets.add(fileName);
            }
            catch (IndexOutOfBoundsException ex) {
               System.out.println(fileName + " contains less than 5 sheets in its "
                  + "workbook. The info from this timesheet will not be included in "
                  + "the output file. Reformat " + fileName + " to the standard "
                  + "timesheet format and rerun this program or add its info to the"
                  + " recap manually.");
               failedSheets.add(fileName);
            }
            catch (TimeSheetFormatException ex) {
               System.out.println(fileName + " is incorrectly formatted. "
                  + ex.getMessage());
               failedSheets.add(fileName);
            }
         }
         
         for (Job job : allJobs)
            System.out.println(job);
                     
         System.out.println("Failed to extract info from the following files:");
         for (String fileName : failedSheets) {
            System.out.println(fileName);
         }
         
         recap = new Recap(allJobs, timeSheetFolderPath+"testRecap.xls");
         recap.makeRecap();
         recap.writeRecap();*/
      }
      catch (Exception ex) {
         System.out.println("Caught exception" + ex);
      }
      

   }
}
