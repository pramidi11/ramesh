package logbook;

import java.time.ZonedDateTime;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "taskId", "enteredTime", "addProperty1", "addProperty2", "addProperty3" })
public class Task {

    public String taskId ="";
    public String performedBy="";
    public String enteredDate= ZonedDateTime.now().toString().split("T")[0];
    public String enteredTime="";
    public String addProperty1="";
    public String addProperty2="";
    public String addProperty3="";
    public boolean success=false;

    public String getRandom(String temp) {

        String[] ranges = temp.split(";");
        Random r = new Random();
        double random = (r.nextInt(21)-10) / 10.0;

        int number = r.nextInt(Integer.parseInt(ranges[1]) - Integer.parseInt(ranges[0])) + Integer.parseInt(ranges[0]);
        return String.valueOf(random+ (double) number);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getPerformedBy() {
        return performedBy;
    }

    public void setPerformedBy(String performedBy) {
        this.performedBy = performedBy;
    }

    public String getEnteredDate() {
        return enteredDate;
    }

    public void setEnteredDate(String enteredDate) {
        this.enteredDate = enteredDate;
    }

    public String getEnteredTime() {
        return enteredTime;
    }

    public void setEnteredTime(String enteredTime) {
        this.enteredTime = enteredTime;
    }

    public String getAddProperty1() {
        if(addProperty1.contains(";")) {
            return getRandom(addProperty1);
        }
        return addProperty1;
    }

    public void setAddProperty1(String addProperty1) {
        this.addProperty1 = addProperty1;
    }

    public String getAddProperty2() {
        return addProperty2;
    }

    public void setAddProperty2(String addProperty2) {
        this.addProperty2 = addProperty2;
    }

    public String getAddProperty3() {
        return addProperty3;
    }

    public void setAddProperty3(String addProperty3) {
        this.addProperty3 = addProperty3;
    }
}
