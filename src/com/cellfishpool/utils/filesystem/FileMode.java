package com.cellfishpool.utils.filesystem;

import com.cellfishpool.models.Command;
import com.cellfishpool.utils.baseclass.QueryBaseClass;
import com.cellfishpool.utils.factories.QueryFactory;
import com.cellfishpool.utils.output.OutputPrinter;

import java.io.*;

public class FileMode  {
  private String fileName;
  private OutputPrinter outputPrinter;
  private QueryFactory queryFactory;

  public FileMode(QueryFactory queryFactory, OutputPrinter outputPrinter, String fileName){
    this.fileName = fileName;
    this.outputPrinter = outputPrinter;
    this.queryFactory = queryFactory;
  }

  public void process() throws IOException {
    final File file = new File("/Users/vartultripathi/Desktop/SquadStack-Parking-Lot/bin/input.txt");
    final BufferedReader reader;
    try {
      reader = new BufferedReader(new FileReader(file));
    } catch (FileNotFoundException e) {
      outputPrinter.invalidFile();
      return;
    }

    String input = reader.readLine();
    while (input != null) {
      final Command command = new Command(input);
      processCommand(command);
      input = reader.readLine();
    }
  }

  protected void processCommand(final Command command) {
    final QueryBaseClass queryBaseClass = queryFactory.getCommandExecutor(command);
    if (queryBaseClass.checkValidQuery(command)) {
      queryBaseClass.computeQuery(command);
    } else {
      throw new RuntimeException();
    }
  }
}