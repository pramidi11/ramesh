/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package logbook;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Runner {

    private WebDriver webDriver;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

    public void setup() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("----disable-dev-shm-usage");
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/chromedriver");
        webDriver = new ChromeDriver(chromeOptions);
        webDriver.get("http://logbook.pieface.com.au/");
        webDriver.findElement(By.xpath("//*[@id=\"navbarCollapse\"]/div[2]/a")).click();
        webDriver.findElement(By.id("Input_ExternalId")).sendKeys("3389");
        webDriver.findElement(By.id("Input_Password")).sendKeys("3389");
        webDriver.findElement(By.xpath("//*[@id=\"account\"]/div[5]/button")).click();
    }

    public List<TaskHistory> getHistory(String historyDate) throws IOException {
        OkHttpClient client = Client.getUnsafeOkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        Request request = new Request.Builder()
            .url("https://logbook.pieface.com.au/api/task/record?date="+historyDate)
            .addHeader("Content-Type", "application/json")
            .addHeader("Cookie", webDriver.manage().getCookies().toString())
            .build();
            Response response = client.newCall(request).execute();
        assert response.body() != null;
        String out = response.body().string();
        System.out.println(out);
        return objectMapper.readValue(out, new TypeReference<List<TaskHistory>>() {});
    }

     public boolean submitTask(String payload, boolean submit) throws IOException {
        OkHttpClient client = Client.getUnsafeOkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, payload);
        Request request = new Request.Builder()
            .url("https://logbook.pieface.com.au/api/task/record?timeZone=Australia/Melbourne")
            .method("POST", body)
            .addHeader("Content-Type", "application/json")
            .addHeader("Cookie", webDriver.manage().getCookies().toString())
            .build();
        if(submit) {
            Response response = client.newCall(request).execute();
            return response.isSuccessful();
        }
        return false;
    }

    public void onDemandTaskLogEntries(boolean fullDay, String historyDate) throws Exception {
        try {
            AtomicBoolean found = new AtomicBoolean(false);
            setup();
            List<TaskHistory> taskHistories = getHistory(historyDate);
            taskHistories.forEach(taskHistory -> {
                Arrays.stream(taskHistory.getClass().getFields()).forEach(field -> {
                    try {
                        if(field.get(taskHistory) != null || field.get(taskHistory) != "00:00")
                            found.set(true);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
            });
            if(!found.get()) {
                Calendar c = Calendar.getInstance();
                c.setTime(format1.parse(historyDate));
                logBookEntries(fullDay, c);
            } else  {
                System.out.println("Skipping as entries are logged already");
            }
            System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(taskHistories));

            webDriver.quit();
        } catch (Exception e) {
            webDriver.quit();
            throw e;
        }
    }

    private void util(String file, List<Task> taskList, List<Worker> workers, Calendar c) throws NoSuchFieldException, IllegalAccessException, ParseException {
        int day =  c.get(Calendar.DAY_OF_WEEK);
        Worker entry = workers.get(day - 1);
        Field f = entry.getClass().getField(file);
        String value = (String) f.get(entry);
        System.out.println("logging for working day" + day + "- for " + value + "with file " + file);
        taskList.forEach(task -> {
            task.setPerformedBy(value);
            task.setEnteredDate(c.toString());
        });
        taskList.forEach(task -> {
            try {
                task.setSuccess(submitTask(new ObjectMapper().writeValueAsString(task), false));
                System.out.format("%1s%9s%6s%6s%6s \n--------------------------------------------------\n", task.getTaskId(), task.getPerformedBy(), task.getEnteredDate(), task.getEnteredTime(), task.isSuccess());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void logBookEntries(boolean fullDay, Calendar cal) throws Exception {
        try {
            ReadCsv readCsv = new ReadCsv();
            List<Worker> workers = readCsv.readWorkers("employees.csv");
            setup();
            int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
            if(!fullDay) {
                String file;
                if ((hour > 9) && (hour < 12)) {
                    file = "nineam";
                } else if ((hour > 13) && (hour < 21)) {
                    file = "onepm";
                } else if (hour > 21) {
                    file = "ninepm";
                } else {
                    throw new Exception("Batch is out of time range");
                }
                System.out.println("running file " + file);
                List<Task> taskList = readCsv.readTasks(file + ".csv");
                util(file, taskList, workers, cal);
            } else {
                ArrayList<String> list = new ArrayList<>();
                list.add("nineam");
                list.add("onepm");
                list.add("ninepm");
                list.forEach(entryFile -> {
                    System.out.println("running file " + entryFile);
                    try {
                        List<Task> taskList = readCsv.readTasks(entryFile + ".csv");
                        if(cal != null)
                            util(entryFile, taskList, workers, cal);
                        else
                            util(entryFile, taskList, workers, Calendar.getInstance());
                    } catch (IOException | IllegalAccessException | URISyntaxException | NoSuchFieldException | ParseException e) {
                        e.printStackTrace();
                    }
                });
            }
            webDriver.quit();
        } catch (Exception e) {
            webDriver.quit();
            throw e;
        }
    }

}
