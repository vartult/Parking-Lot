package com.cellfishpool.models;

import com.cellfishpool.utils.enums.QueryEnum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
/**
* Data & utils methods for getting query and
* */
public class Command {

  private static final String SPACE = " ";
  private QueryEnum queryName;
  private List<String> params;

  public QueryEnum getQueryName() {
    return queryName;
  }

  public List<String> getParams() {
    return params;
  }

  public Command(final String inputLine) {
    final List<String> tokensList = Arrays.stream(inputLine.trim().split(SPACE))
        .map(String::trim)
        .filter(token -> (token.length() > 0)).collect(Collectors.toList());

    if (tokensList.size() == 0) {
      throw new RuntimeException();
    }

    queryName = QueryEnum.valueOf(tokensList.get(0).toLowerCase());
    tokensList.remove(0);
    params = tokensList;
  }

}