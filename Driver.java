import java.io.IOException;
import jxl.read.biff.BiffException;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class Driver {
   private static final String testRecapPath = "../Data/Round 3/TimeSheets/testRecap.xls";

   public static void main(String[] args) {
      try {
         Recap recap = new Recap(testRecapPath);
         recap.printJobs();
      }
      catch (RecapFormatException ex) {
         System.out.println(ex.getMessage());
      }
   }
}
