/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package logbook;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.List;

@RunWith(JUnit4.class)
public class AppTest {

    private WebDriver webDriver;

//    @Before
//    public void setup() {
//        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver");
//        webDriver = new ChromeDriver();
//        webDriver.get("http://logbook.pieface.com.au/");
//        webDriver.findElement(By.xpath("//*[@id=\"navbarCollapse\"]/div[2]/a")).click();
//        webDriver.findElement(By.id("Input_ExternalId")).sendKeys("3389");
//        webDriver.findElement(By.id("Input_Password")).sendKeys("3389");
//        webDriver.findElement(By.xpath("//*[@id=\"account\"]/div[5]/button")).click();
//    }

     public boolean openPieFace(String payload) throws IOException {
        OkHttpClient client = Client.getUnsafeOkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, payload);
//        Request request = new Request.Builder()
//            .url("https://logbook.pieface.com.au/api/task/record?timeZone=Australia/Melbourne")
//            .method("POST", body)
//            .addHeader("Content-Type", "application/json")
//            .addHeader("Cookie", webDriver.manage().getCookies().toString())
//            .build();
//        Response response = client.newCall(request).execute();
//        return response.isSuccessful();
        return false;
//        System.out.println(response.isSuccessful());
//        System.out.println(response.body().string());
    }

    @Test
    public void readListTest() throws Exception {
        Calendar now =  Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        String file;
        if((hour > 9) && (hour < 12)) {
            file = "nineam";
        } else if ((hour > 13) && (hour < 21)) {
            file = "ninepm";
        } else if((hour > 21) && (hour < 24)) {
            file = "onepm";
        } else {
            throw new Exception("Batch is out of time range");
        }
        System.out.println("running file " + file);
        ReadCsv readCsv = new ReadCsv();
        List<Task> taskList = readCsv.readTasks(file+".csv");
        List<Worker> workers = readCsv.readWorkers("employees.csv");;
        int day = now.get(Calendar.DAY_OF_WEEK);
        Worker entry = workers.get(day - 1 );
        Field f = entry.getClass().getField(file);
        String value = (String) f.get(entry);
        System.out.println("logging for working day" + day + "- for " + value + "with file " + file);
        taskList.forEach(task -> {
            task.setPerformedBy(value);
        });
        taskList.forEach(task -> {
            try {
                task.setSuccess(openPieFace(new ObjectMapper().writeValueAsString(task)));
                System.out.format("%1s%9s%6s%6s%6s \n--------------------------------------------------\n", task.getTaskId(), task.getPerformedBy(), task.getEnteredDate(), task.getEnteredTime(), task.isSuccess());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

}
