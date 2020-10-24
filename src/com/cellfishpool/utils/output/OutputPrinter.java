package com.cellfishpool.utils.output;

public class OutputPrinter {

  public void welcome() {
    printWithNewLine("Welcome to Automatic Parking lot.");
  }

  public void end() {
    printWithNewLine("Thanks for using Automatic Parking lot.");
  }

  public void notFound() {
    printWithNewLine("Not found");
  }

  public void statusHeader() {
    printWithNewLine("Slot No.    Registration No    Colour");
  }

  public void parkingLotFull() {
    printWithNewLine("Sorry, parking lot is full");
  }

  public void parkingLotEmpty() {
    printWithNewLine("Parking lot is empty");
  }

  public void invalidFile() {
    printWithNewLine("Invalid file given.");
  }

  public void printWithNewLine(final String msg) {
    System.out.println(msg);
  }
}