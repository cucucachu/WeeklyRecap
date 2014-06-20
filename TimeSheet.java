import jxl.*;
import java.io.File;
import java.io.IOException;
import jxl.read.biff.BiffException;
import java.util.ArrayList;
import java.util.Iterator;

public class TimeSheet {
  
   private static final String JobNoColumnLabel = "Project No.";
   private static final String HoursColumnLabel = "Total";
   private static final String NameColumnLabel = "Project Name";
   private static final String InitialsColumnLabel = "Initials";

   private String employee;
   private String initials;
   private ArrayList<Job> jobs;
   private Workbook wb;
   private ArrayList<Sheet> timeSheets;
   private String fileName;
   
   public TimeSheet(String filePath) 
      throws IOException, BiffException, TimeSheetFormatException {
      jobs = new ArrayList<Job>();
      timeSheets = new ArrayList<Sheet>();
      fileName = filePath;
      wb = Workbook.getWorkbook(new File(filePath));
      //Need to loop through sheets, not call them specifically

      for (int i = 1; i < wb.getNumberOfSheets(); i++)
         timeSheets.add(wb.getSheet(i));

      setInitials();
      
      setJobs();
   }
   
   private void setInitials() {
      initials = timeSheets.get(2).getCell(3, 6).getContents();
   }
   
   String getInitials() {
      return initials;
   }
   
   private void setJobs() throws TimeSheetFormatException {
      int jobNoCol;
      int hoursCol;
      int initialsCol;
      int nameCol;
      
      int curRow;
      Cell jobCell;
      Cell hoursCell;
      Cell nameCell;
      Cell initialsCell;
      Iterator<Sheet> it = timeSheets.iterator();
      Sheet curSheet;
      
      if (wb.getNumberOfSheets() > 5) 
         throw new TimeSheetFormatException("There are more than 5 sheets in this workbook.");
      
      while (it.hasNext()) {
         curSheet = it.next();
         jobCell = curSheet.findCell(JobNoColumnLabel);
         hoursCell = curSheet.findCell(HoursColumnLabel);
         nameCell = curSheet.findCell(NameColumnLabel);
         initialsCell = curSheet.findCell(InitialsColumnLabel);
         
         if (jobCell == null) {
            throw new TimeSheetFormatException("Could not find Job No. Column.");
         }
         else if (hoursCell == null) {
            throw new TimeSheetFormatException("Could not find the hours column.");
         }
         else if (nameCell == null) {
            throw new TimeSheetFormatException("Could not find the job name column.");
         }
         else if (initialsCell == null) {
            throw new TimeSheetFormatException("Could not find the employee initals column.");
         }
         else {
            jobNoCol = jobCell.getColumn();
            hoursCol = hoursCell.getColumn();
            nameCol = nameCell.getColumn();
            initialsCol = initialsCell.getColumn();
            curRow = jobCell.getRow() + 2;
            jobCell = curSheet.getCell(jobNoCol, curRow);
            hoursCell = curSheet.getCell(hoursCol, curRow);
            nameCell = curSheet.getCell(nameCol, curRow);
            initialsCell = curSheet.getCell(initialsCol, curRow);
            while (jobCell.getContents() != "") {
               // add error checking for full information
               if (Double.parseDouble(hoursCell.getContents()) != 0)
                  jobs.add(new Job(jobCell.getContents(), hoursCell.getContents(),
                     nameCell.getContents(), initialsCell.getContents()));
               curRow++;
               jobCell = curSheet.getCell(jobNoCol, curRow);
               hoursCell = curSheet.getCell(hoursCol, curRow);
               nameCell = curSheet.getCell(nameCol, curRow);
               initialsCell = curSheet.getCell(initialsCol, curRow);
            }
         }
      }
   }
   
   public ArrayList<Job> getJobs() {
      return jobs;
   }
   
   void printJobs() {
      for (Job job : jobs)
         System.out.println(job);
   }
   
   void close() {
      wb.close();
   }
}
