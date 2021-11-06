package logbook;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder( { "day", "nineam", "onepm", "ninepm" })
public class Worker {
    public int day;
    public String nineam;
    public String onepm;
    public String ninepm;
}
