import jxl.*;
import jxl.write.*;
import java.io.File;
import java.io.IOException;
import jxl.read.biff.BiffException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;

public class Recap {
   public static final int nameCol = 0;
   public static final int jobNoCol = 2;
   public static final int initialsCol = 3;
   public static final int hoursCol = 4;

   ArrayList<Job> allJobs;
   WritableWorkbook recapWorkbook;
   WritableSheet sheet;
   
   public Recap(ArrayList<Job> allJobs, String outFilePath) {
      this.allJobs = allJobs; 
      
      try {
         recapWorkbook = Workbook.createWorkbook(new File(outFilePath));
         sheet = recapWorkbook.createSheet("Recap", 0);
      }
      catch (IOException ex) {
         System.out.println("Caught " + ex + " when initializing recap");
      }
   }
   
   public void makeRecap() {
      Label nameLabel = new Label(nameCol, 0, "Project Name");
      Label jobNoLabel = new Label(jobNoCol, 0, "Job Number");
      Label initialsLabel = new Label(initialsCol, 0, "Employee Initials");
      Label hoursLabel = new Label(hoursCol, 0, "Hours");
      jxl.write.Number hours;
      int curRow = 1;
      
      try {   
         sheet.addCell(nameLabel);
         sheet.addCell(jobNoLabel);
         sheet.addCell(initialsLabel);
         sheet.addCell(hoursLabel);
         
         Collections.sort(allJobs);
         
         for (Job job : allJobs) {
            nameLabel = new Label(nameCol, curRow, job.jobName);
            jobNoLabel = new Label(jobNoCol, curRow, job.jobNoStr);
            initialsLabel = new Label(initialsCol, curRow, job.initials);
            hours = new jxl.write.Number(hoursCol, curRow, job.hours);
            
            sheet.addCell(nameLabel);
            sheet.addCell(jobNoLabel);
            sheet.addCell(initialsLabel);
            sheet.addCell(hours);
            
            curRow++;
         }
      }
      catch (WriteException ex) {
         System.out.println("Caught " + ex + "when making recap.");
      }
   }
   
   public void writeRecap() {
      try {
         recapWorkbook.write();
         recapWorkbook.close();
      }
      catch (IOException|WriteException ex) {
         System.out.println("Caught " + ex + "when writing recap.");
      }
   }
}