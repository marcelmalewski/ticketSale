package com.marcel.malewski.ticketsale.backend.ticketbuyer.agerange;

public enum AgeRange {
   CHILD("CHILD"), ADULT("ADULT"), SENIOR("SENIOR");
   private final String code;
   private AgeRange(String code) {
      this.code = code;
   }

   public String getCode() {
      return code;
   }
}
