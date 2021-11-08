package logbook;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskHistory {

        @JsonProperty("time")
        private String time;

        @JsonProperty("ambient Cabinet Temps")
        private String ambientCabinetTemp;

        @JsonProperty("chest Freezer Temps")
        private String chestFreezerTemp;

        @JsonProperty("clean Coffee Machine Station")
        private String cleanCoffeeMachineStation;

        @JsonProperty("coffee Machine Counter")
        private String coffeeMachineCounter;

        @JsonProperty("hot cabinet temps")
        private String hotCabinetTemp;

        @JsonProperty("sandwich unit Temps")
        private String sandwichUnitTemp;

        @JsonProperty("toilet Cleaning")
        private String toiletCleaning;

        @JsonProperty("walk-in Temps")
        private String walkInTemp;
}
