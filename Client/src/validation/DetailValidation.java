package validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DetailValidation {

    public boolean nameDetailValidation(String nameDetail){
        String nameDetailExpression = "([А-Я]|[а-я]|[ ]){2,20}+";
        Pattern pattern=Pattern.compile(nameDetailExpression);
        Matcher matcher=pattern.matcher(nameDetail);
        return matcher.matches();
    }

    public boolean producerDetailValidation(String producerDetail){
        String producerDetailExpression = "([А-Я]|[а-я]|[ ]|[A-Z]|[a-z]|[0-9]){2,25}+";
        Pattern pattern=Pattern.compile(producerDetailExpression);
        Matcher matcher=pattern.matcher(producerDetail);
        return matcher.matches();
    }

    public boolean markDetailValidation(String markDetail){
        String markDetailExpression="([А-Я]|[а-я]|[ ]|[A-Z]|[a-z]|[0-9]){2,25}+";
        Pattern pattern=Pattern.compile(markDetailExpression);
        Matcher matcher=pattern.matcher(markDetail);
        return matcher.matches();
    }

    public boolean modelDetailValidation(String modelDetail){
        String modelDetailExpression="([А-Я]|[а-я]|[ ]|[A-Z]|[a-z]|[0-9]){2,25}+";
        Pattern pattern=Pattern.compile(modelDetailExpression);
        Matcher matcher=pattern.matcher(modelDetail);
        return matcher.matches();
    }

    public boolean typeMotorValidation(String typeMotor){
        String typeMotorExpression = "([А-Я]|[а-я]|[A-Z]|[a-z]){1,25}+";
        Pattern pattern=Pattern.compile(typeMotorExpression);
        Matcher matcher=pattern.matcher(typeMotor);
        return matcher.matches();
    }

    public boolean volumeMotorValidation(String volumeMotor){
        String volumeMotorExpression = "([0-9]|[.]){1,3}+";
        Pattern pattern=Pattern.compile(volumeMotorExpression);
        Matcher matcher=pattern.matcher(volumeMotor);
        return matcher.matches();
    }

    public boolean priceDetailValidation(String priceDetail){
        String priceDetailExpression = "([0-9]{1,6})+";
        Pattern pattern=Pattern.compile(priceDetailExpression);
        Matcher matcher=pattern.matcher(priceDetail);
        return matcher.matches();
    }
}
