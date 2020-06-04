package validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidation {
    private String loginPasswordExpression="([A-Z]|[a-z]|[0-9]|[_]|[.]|[-]|[@]){2,20}+";
    private String nameExpression="([А-Я]|[а-я]){2,20}+";

    public boolean loginValidation(String login){
        Pattern pattern=Pattern.compile(loginPasswordExpression);
        Matcher matcher=pattern.matcher(login);
        return matcher.matches();
    }
    public boolean passwordValidation(String password){
        Pattern pattern=Pattern.compile(loginPasswordExpression);
        Matcher matcher=pattern.matcher(password);
        return matcher.matches();
    }
    public boolean firstNameValidation(String firstName){
        Pattern pattern=Pattern.compile(nameExpression);
        Matcher matcher=pattern.matcher(firstName);
        return matcher.matches();
    }
    public boolean lastNameValidation(String lastName){
        Pattern pattern=Pattern.compile(nameExpression);
        Matcher matcher=pattern.matcher(lastName);
        return matcher.matches();
    }
    public boolean countryValidation(String country){
        String countryExpression = "([А-Я]|[а-я]|[ ]){2,25}+";
        Pattern pattern=Pattern.compile(countryExpression);
        Matcher matcher=pattern.matcher(country);
        return matcher.matches();
    }
    public boolean ageValidation(String age){
        String ageExpression = "([0-9]){1,3}+";
        Pattern pattern=Pattern.compile(ageExpression);
        Matcher matcher=pattern.matcher(age);
        return matcher.matches();
    }
}
