package logbook;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.Strings;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Random;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Pies {

    public String enteredDate = ZonedDateTime.now().toString().split("T")[0];;
    public String enteredTime = ZonedDateTime.now().toString().split("T")[1].substring(0,5);
    public Integer productType = 0;
    public Integer reasonType = 0;
    public String temperature = "";
    public List<Product> products;

    public String getRandom(String temp) {

        String[] ranges = temp.split(";");
        Random r = new Random();
        double random = (r.nextInt(21)-10) / 10.0;
        int number = r.nextInt(Integer.parseInt(ranges[1]) - Integer.parseInt(ranges[0])) + Integer.parseInt(ranges[0]);
        System.out.println(ranges[0] + " " + ranges[1] + " " + (random+number) );
        return String.valueOf(random + (double) number);
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

    public int getProductType() {
        return productType;
    }

    public void setProductType(int productType) {
        this.productType = productType;
    }

    public Integer getReasonType() {
        return reasonType;
    }

    public void setReasonType(Integer reasonType) {
        this.reasonType = reasonType;
    }

    public String getTemperature() {
        if(!Strings.isNullOrEmpty(temperature)) {
            if (temperature.contains(";")) {
                return getRandom(temperature);
            }
        }
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


}
