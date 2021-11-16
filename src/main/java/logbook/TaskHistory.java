package logbook;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskHistory {

        @JsonProperty("time")
        private String time;

        @JsonProperty("ambient Cabinet Temps")
        private KeyValue[][] ambientCabinetTemp;

        @JsonProperty("chest Freezer Temps")
        private KeyValue[][] chestFreezerTemp;

        @JsonProperty("clean Coffee Machine Station")
        private KeyValue[][] cleanCoffeeMachineStation;

        @JsonProperty("coffee Machine Counter")
        private KeyValue[][] coffeeMachineCounter;

        @JsonProperty("hot cabinet temps")
        private KeyValue[][] hotCabinetTemp;

        @JsonProperty("sandwich unit Temps")
        private KeyValue[][] sandwichUnitTemp;

        @JsonProperty("toilet Cleaning")
        private KeyValue[][] toiletCleaning;

        @JsonProperty("walk-in Temps")
        private KeyValue[][] walkInTemp;

        @JsonProperty("under Bench Freezer/ Refrigerator Temps")
        private KeyValue[][] underBenchFreezer;
}
