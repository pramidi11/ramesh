/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package logbook;

public class App {
    private static final Runner runner = new Runner();

    public static void main(String[] args) throws Exception {
        runner.logBookEntries(true, null); // to run today
        runner.onDemandTaskLogEntries(true, "2021-11-16"); // run specific date
    }
}
