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
      return this.jobNoStr.compareTo(other.jobNoStr);
   }
   
}
