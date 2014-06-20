JCC = javac

#JFLAGS = -classpath ./jexcelapi/jxl.jar

default: TimeSheetFormatException.class Job.class WeeklyRecap.class TimeSheet.class\
   Recap.class WeeklyRecapGui.class WeeklyRecapMain.class
   
Job.class: Job.java
	$(JCC) $(JFLAGS) Job.java

Recap.class: Recap.java
	$(JCC) $(JFLAGS) Recap.java

WeeklyRecap.class: WeeklyRecap.java
	$(JCC) $(JFLAGS) WeeklyRecap.java

WeeklyRecapMain.class: WeeklyRecapMain.java
	$(JCC) $(JFLAGS) WeeklyRecapMain.java

TimeSheet.class: TimeSheet.java
	$(JCC) $(JFLAGS) TimeSheet.java

TimeSheetFormatException.class: TimeSheetFormatException.java
	$(JCC) $(JFLAGS) TimeSheetFormatException.java

WeeklyRecapGui.class: WeeklyRecapGui.java
	$(JCC) $(JFLAGS) WeeklyRecapGui.java

clean: 
	$(RM) Job.class WeeklyRecap.class TimeSheet.class TimeSheetFormatException.class\
	   Recap.class WeeklyRecapGui.class WeeklyRecapMain.class
