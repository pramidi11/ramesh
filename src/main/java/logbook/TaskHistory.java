package logbook;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskHistory {

        @JsonProperty("time")
        public String time;

        @JsonProperty("ambient Cabinet Temps")
        public KeyValue[][] ambientCabinetTemp;

        @JsonProperty("chest Freezer Temps")
        public KeyValue[][] chestFreezerTemp;

        @JsonProperty("clean Coffee Machine Station")
        public KeyValue[][] cleanCoffeeMachineStation;

        @JsonProperty("coffee Machine Counter")
        public KeyValue[][] coffeeMachineCounter;

        @JsonProperty("hot cabinet temps")
        public KeyValue[][] hotCabinetTemp;

        @JsonProperty("sandwich unit Temps")
        public KeyValue[][] sandwichUnitTemp;

        @JsonProperty("toilet Cleaning")
        public KeyValue[][] toiletCleaning;

        @JsonProperty("walk-in Temps")
        public KeyValue[][] walkInTemp;

        @JsonProperty("under Bench Freezer/ Refrigerator Temps")
        public KeyValue[][] underBenchFreezer;

        public String getTime() {
                return time;
        }

        public void setTime(String time) {
                this.time = time;
        }

        public KeyValue[][] getAmbientCabinetTemp() {
                return ambientCabinetTemp;
        }

        public void setAmbientCabinetTemp(KeyValue[][] ambientCabinetTemp) {
                this.ambientCabinetTemp = ambientCabinetTemp;
        }

        public KeyValue[][] getChestFreezerTemp() {
                return chestFreezerTemp;
        }

        public void setChestFreezerTemp(KeyValue[][] chestFreezerTemp) {
                this.chestFreezerTemp = chestFreezerTemp;
        }

        public KeyValue[][] getCleanCoffeeMachineStation() {
                return cleanCoffeeMachineStation;
        }

        public void setCleanCoffeeMachineStation(KeyValue[][] cleanCoffeeMachineStation) {
                this.cleanCoffeeMachineStation = cleanCoffeeMachineStation;
        }

        public KeyValue[][] getCoffeeMachineCounter() {
                return coffeeMachineCounter;
        }

        public void setCoffeeMachineCounter(KeyValue[][] coffeeMachineCounter) {
                this.coffeeMachineCounter = coffeeMachineCounter;
        }

        public KeyValue[][] getHotCabinetTemp() {
                return hotCabinetTemp;
        }

        public void setHotCabinetTemp(KeyValue[][] hotCabinetTemp) {
                this.hotCabinetTemp = hotCabinetTemp;
        }

        public KeyValue[][] getSandwichUnitTemp() {
                return sandwichUnitTemp;
        }

        public void setSandwichUnitTemp(KeyValue[][] sandwichUnitTemp) {
                this.sandwichUnitTemp = sandwichUnitTemp;
        }

        public KeyValue[][] getToiletCleaning() {
                return toiletCleaning;
        }

        public void setToiletCleaning(KeyValue[][] toiletCleaning) {
                this.toiletCleaning = toiletCleaning;
        }

        public KeyValue[][] getWalkInTemp() {
                return walkInTemp;
        }

        public void setWalkInTemp(KeyValue[][] walkInTemp) {
                this.walkInTemp = walkInTemp;
        }

        public KeyValue[][] getUnderBenchFreezer() {
                return underBenchFreezer;
        }

        public void setUnderBenchFreezer(KeyValue[][] underBenchFreezer) {
                this.underBenchFreezer = underBenchFreezer;
        }

}
