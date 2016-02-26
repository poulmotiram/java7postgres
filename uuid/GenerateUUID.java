package com.mycompany.app;

import java.util.UUID;

public class GenerateUUID {
  
  public static final void main(String... aArgs){
    //generate random UUIDs
    UUID idOne = UUID.randomUUID();
    UUID idTwo = UUID.randomUUID();
    System.out.println( "\nThe UUID generated are ....\n" );
    log("UUID One: " + idOne);
    log("UUID Two: " + idTwo);
    
    // creating UUID      
    UUID uid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");    
    // checking the value of random UUID
    System.out.println("\nRandom UUID value: "+uid.randomUUID());
  }
  
  
  
  private static void log(Object aObject){
    System.out.println( String.valueOf(aObject) );
  }
} 

