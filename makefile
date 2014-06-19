JCC = javac

#JFLAGS = -classpath ./jexcelapi/jxl.jar

default: TimeSheetFormatException.class Job.class Driver.class TimeSheet.class Recap.class

Job.class: Job.java
	$(JCC) $(JFLAGS) Job.java

Recap.class: Recap.java
	$(JCC) $(JFLAGS) Recap.java

Driver.class: Driver.java
	$(JCC) $(JFLAGS) Driver.java

TimeSheet.class: TimeSheet.java
	$(JCC) $(JFLAGS) TimeSheet.java
	
TimeSheetFormatException.class: TimeSheetFormatException.java
	$(JCC) $(JFLAGS) TimeSheetFormatException.java

clean: 
	$(RM) Job.class Driver.class TimeSheet.class TimeSheetFormatException.class Recap.class
