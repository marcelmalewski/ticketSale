package com.marcel.malewski.ticketsale.ticketbuyer.agerange;

public enum AgeRange {
   CHILD("CHILD"), ADULT("ADULT"), SENIOR("SENIOR");
   private final String value;
   private AgeRange(String value) {
      this.value = value;
   }

   public String getValue() {
      return this.value;
   }
}
