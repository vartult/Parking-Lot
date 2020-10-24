package com.cellfishpool.utils.filesystem;

import com.cellfishpool.models.Command;
import com.cellfishpool.utils.baseclass.QueryBaseClass;
import com.cellfishpool.utils.constants.Constants;
import com.cellfishpool.utils.factories.QueryFactory;
import com.cellfishpool.utils.output.OutputPrinter;

import java.io.*;

/*
* Utility class for getting file from file system & Passing it for parameter processing
* */

public class FileUtil {
  private String fileName;
  private OutputPrinter outputPrinter;
  private QueryFactory queryFactory;

  public FileUtil(QueryFactory queryFactory, OutputPrinter outputPrinter, String fileName){
    this.fileName = fileName;
    this.outputPrinter = outputPrinter;
    this.queryFactory = queryFactory;
  }

  public void process() throws IOException {
    final File file = new File(Constants.INPUT_FILE);
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