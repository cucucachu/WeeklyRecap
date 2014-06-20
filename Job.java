public class Job implements Comparable<Job> {
   String jobNoStr;
   String jobName;
   String initials;
   double hours;
   String miles;
   String fdt;
   String other;
   
   public Job(String jobNoStr, String hours, String jobName, String initials) {
      this.jobNoStr = jobNoStr;
      this.hours = Double.parseDouble(hours);
      this.jobName = jobName;
      this.initials = initials;
      this.miles = null;
      this.fdt = null;
      this.other = null;
   }   
   
   public Job(String jobNoStr, String hours, String jobName, 
      String initials, String miles) {
      this.jobNoStr = jobNoStr;
      this.hours = Double.parseDouble(hours);
      this.jobName = jobName;
      this.initials = initials;
      this.miles = miles;
      this.fdt = null;
      this.other = null;
   }     
   
   public Job(String jobNoStr, String hours, String jobName, 
      String initials, String miles, String fdt) {
      this.jobNoStr = jobNoStr;
      this.hours = Double.parseDouble(hours);
      this.jobName = jobName;
      this.initials = initials;
      this.miles = miles;
      this.fdt = fdt;
      this.other = null;
   }
   
   public Job(String jobNoStr, String hours, String jobName, 
      String initials, String miles, String fdt, String other) {
      this.jobNoStr = jobNoStr;
      this.hours = Double.parseDouble(hours);
      this.jobName = jobName;
      this.initials = initials;
      this.miles = miles;
      this.fdt = fdt;
      this.other = other;
      
      if (this.fdt == "")
         this.fdt = null;
   } 
   
   public String toString() {
      if (miles == null)
         return "Job: " + jobName + " " + jobNoStr + " hours: " + hours + " Employee: "
            + initials;
      else if (fdt == null) 
         return "Job: " + jobName + " " + jobNoStr + " hours: " + hours + " miles: "
            + miles + " Employee: " + initials;
      else if (other == null)
         return "Job: " + jobName + " " + jobNoStr + " hours: " + hours + " miles: "
            + miles + " FDT: " + fdt + "Employee: " + initials;
      else
         return "Job: " + jobName + " " + jobNoStr + " hours: " + hours + " miles: "
            + miles + " FDT: " + fdt + " Other: " + other + "Employee: " + initials;
   }
   
   public int compareTo(Job other) {
      double jobNoThis;
      double jobNoOther;
      
      try {
         jobNoThis = Double.parseDouble(this.jobNoStr);
         jobNoOther = Double.parseDouble(other.jobNoStr);
         
         if (jobNoThis > jobNoOther)
            return 10;
         else if (jobNoThis < jobNoOther)
            return -10;
         else 
            return this.initials.compareTo(other.initials);
      }
      catch (NumberFormatException ex) {
         return this.jobNoStr.compareTo(other.jobNoStr);
      }
   }
}
