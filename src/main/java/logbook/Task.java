package logbook;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
        double random = (r.nextInt(9)) / 10.0;
        int number = r.nextInt(Integer.parseInt(ranges[1]) - Integer.parseInt(ranges[0])) + Integer.parseInt(ranges[0]);
        System.out.println(ranges[0] + "AND " + ranges[1] + "  = " + (random+number));
        return String.valueOf(random + (double) number);
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
        if(addProperty1.contains("/")) {
            return getMilkTemp(addProperty1);
        } else if (addProperty1.contains(";")) {
            return getRandom(addProperty1);
        }
        return addProperty1;
    }

    public void setAddProperty1(String addProperty1) {
        this.addProperty1 = addProperty1;
    }

    public String getAddProperty2() {
        if(addProperty2.contains("/")) {
            return getMilkTemp(addProperty2);
        } else if (addProperty2.contains(";")) {
            return getRandom(addProperty2);
        }
        return addProperty2;
    }

    public void setAddProperty2(String addProperty2) {
        this.addProperty2 = addProperty2;
    }

    public String getAddProperty3() {
        if(addProperty3.contains(";")) {
            return getRandom(addProperty3);
        }
        return addProperty3;
    }

    public void setAddProperty3(String addProperty3) {
        this.addProperty3 = addProperty3;
    }

    private String getMilkTemp(String text) {
        List<String> presets = new ArrayList<>();
        String[] values = text.split("/");
        Arrays.stream(values).forEach(v -> {
            presets.add(getRandom(v));
        });
        System.out.println(String.join("/", presets));
        return String.join("/", presets);
    }
}
