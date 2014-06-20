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
   private static final String InitialVColumnLabel = "Initial-V";
   private static final String FDTColumnLabel = "FDT";
   private static final String OtherColumnLabel = "OTHER";
   private static final String VehicleStartLabel = "OTHER CHARGES - PLEASE INDICATE TYPE"
      + " -Inclinometer I/2 or Full Day (Incl 1/2 or F), Water Level (WL), Laser Level(LL),"
      + " Sampler 1/2 or Full Day (S 1/2 or F)";
      
   private static final String TerminatorOne = "Total Chargeable Hours";
   private static final String TerminatorTwo = "Total for Expense Report";

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
      int milesCol;
      int fdtCol;
      int otherCol;
      int curRow;
      
      Cell jobCell;
      Cell hoursCell;
      Cell nameCell;
      Cell initialsCell;
      Cell milesCell;
      Cell fdtCell;
      Cell otherCell;
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
         curRow = 0;//newzxz
         
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
            
            while (curSheet.getCell(0, curRow).getContents().compareTo(TerminatorOne) != 0) {
               if (hoursCell.getType().equals(CellType.NUMBER_FORMULA) == false)
                  throw new TimeSheetFormatException("Missing formula in hours cell");
               else {
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
         
         try {
            curRow = curSheet.findCell(VehicleStartLabel).getRow();
         }
         catch (Exception ex) {
            throw new TimeSheetFormatException("Could not find the start of vehicle info.");
         }
         
         jobCell = curSheet.findCell(JobNoColumnLabel, 0, curRow, 10,
             curRow + 10, false);
         hoursCell = curSheet.findCell("H", 0, curRow, 10,
             curRow + 10, false);
         nameCell = curSheet.findCell(NameColumnLabel, 0, curRow, 10,
             curRow + 10, false);
         initialsCell = curSheet.findCell(InitialVColumnLabel, 0, curRow, 10,
             curRow + 10, false);
         milesCell = curSheet.findCell("M", 0, curRow, 10, curRow + 10, false);
         fdtCell = curSheet.findCell(FDTColumnLabel, 0, curRow, 25,
             curRow + 10, false);
         otherCell = curSheet.findCell(OtherColumnLabel, 0, curRow, 25,
             curRow + 10, false);
         
         if (jobCell == null) {
            throw new TimeSheetFormatException("Could not find vehicle Job No. Column.");
         }
         else if (hoursCell == null) {
            throw new TimeSheetFormatException("Could not find the vehicle hours column.");
         }
         else if (nameCell == null) {
            throw new TimeSheetFormatException("Could not find the vehicle job name column.");
         }
         else if (initialsCell == null) {
            throw new TimeSheetFormatException("Could not find the vehicle employee initals column.");
         }
         else if (milesCell == null) {
            throw new TimeSheetFormatException("Could not find the vehicle miles column.");
         }
         else if (fdtCell == null) {
            throw new TimeSheetFormatException("Could not find the vehicle fdt column.");
         }
         else if (otherCell == null) {
            throw new TimeSheetFormatException("Could not find the vehicle OTHER column.");
         }
         else {
            jobNoCol = jobCell.getColumn();
            hoursCol = hoursCell.getColumn();
            nameCol = nameCell.getColumn();
            initialsCol = initialsCell.getColumn();
            milesCol = milesCell.getColumn();
            fdtCol = fdtCell.getColumn();
            otherCol = otherCell.getColumn();
            curRow = jobCell.getRow() + 1;
            
            jobCell = curSheet.getCell(jobNoCol, curRow);
            hoursCell = curSheet.getCell(hoursCol, curRow);
            nameCell = curSheet.getCell(nameCol, curRow);
            initialsCell = curSheet.getCell(initialsCol, curRow);
            milesCell = curSheet.getCell(milesCol, curRow);
            fdtCell = curSheet.getCell(fdtCol, curRow);
            otherCell = curSheet.getCell(otherCol, curRow);
            
            while (curSheet.getCell(0, curRow).getContents().compareTo(TerminatorTwo) != 0) {
               
               if (hoursCell.getType().equals(CellType.NUMBER_FORMULA) == false)
                  throw new TimeSheetFormatException("Missing formula in vehicle hours cell");
               else if (milesCell.getType().equals(CellType.NUMBER_FORMULA) == false)
                  throw new TimeSheetFormatException("Missing formula in vehicle miles cell");
               else {
                  if (Double.parseDouble(hoursCell.getContents()) != 0
                     || Double.parseDouble(milesCell.getContents()) != 0) {
                     if (fdtCell.getType().equals(CellType.EMPTY) 
                        && otherCell.getType().equals(CellType.EMPTY))
                        jobs.add(new Job(jobCell.getContents(), hoursCell.getContents(),
                           nameCell.getContents(), initialsCell.getContents(),
                           milesCell.getContents()));
                     else if (fdtCell.getType().equals(CellType.EMPTY) == false
                        && otherCell.getType().equals(CellType.EMPTY))
                        jobs.add(new Job(jobCell.getContents(), hoursCell.getContents(),
                           nameCell.getContents(), initialsCell.getContents(),
                           milesCell.getContents(), fdtCell.getContents()));
                     else if (fdtCell.getType().equals(CellType.EMPTY) 
                        && otherCell.getType().equals(CellType.EMPTY) == false) {
                        jobs.add(new Job(jobCell.getContents(), hoursCell.getContents(),
                           nameCell.getContents(), initialsCell.getContents(),
                           milesCell.getContents(), "", otherCell.getContents()));
                     }
                     else {
                        jobs.add(new Job(jobCell.getContents(), hoursCell.getContents(),
                           nameCell.getContents(), initialsCell.getContents(),
                           milesCell.getContents(), fdtCell.getContents(),
                           otherCell.getContents()));
                     }
                  }
                  curRow++;
                  jobCell = curSheet.getCell(jobNoCol, curRow);
                  hoursCell = curSheet.getCell(hoursCol, curRow);
                  nameCell = curSheet.getCell(nameCol, curRow);
                  initialsCell = curSheet.getCell(initialsCol, curRow);
                  milesCell = curSheet.getCell(milesCol, curRow);
                  fdtCell = curSheet.getCell(fdtCol, curRow);
                  otherCell = curSheet.getCell(otherCol, curRow);
               }
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
