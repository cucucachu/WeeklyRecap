public class Job implements Comparable<Job> {
   String jobNoStr;
   String jobName;
   String initials;
   double hours;
   
   public Job(String jobNoStr, String hours, String jobName, String initials) {
      this.jobNoStr = jobNoStr;
      this.hours = Double.parseDouble(hours);
      this.jobName = jobName;
      this.initials = initials;
   }
   
   public String toString() {
      return "Job: " + jobName + " " + jobNoStr + " hours: " + hours + " Employee: "
         + initials;
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
            return 0;
      }
      catch (NumberFormatException ex){
         return this.jobNoStr.compareTo(other.jobNoStr);
      }
   }
}
