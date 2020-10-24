package com.cellfishpool.utils.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class VehicleRegistrationNumberValidator {
    public static boolean isValidCarNumber(final String carNumber){
        String regex = "^[A-Z]{2}[-][0-9]{1,2}(?:[A-Z])?(?:[A-Z]*)?[0-9]{4}$";
        try {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(carNumber);
            return true;
        } catch (PatternSyntaxException exception){
            return false;
        }
    }
}

