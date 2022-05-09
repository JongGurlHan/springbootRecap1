package com.jghan.myhome.validator;

import com.jghan.myhome.model.Board;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

@Component
public class BoardValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Board.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Board b = (Board) obj;
        if(StringUtils.isEmpty(b.getContent())){
            errors.rejectValue("content", "key", "내용을 입력하세요");
        }


    }
}


//public class PersonValidator implements Validator {
//
//    /**
//     * This Validator validates *just* Person instances
//     */
//    public boolean supports(Class clazz) {
//        return Person.class.equals(clazz);
//    }
//
//    public void validate(Object obj, Errors e) {
//        ValidationUtils.rejectIfEmpty(e, "name", "name.empty");
//        Person p = (Person) obj;
//        if (p.getAge() < 0) {
//            e.rejectValue("age", "negativevalue");
//        } else if (p.getAge() > 110) {
//            e.rejectValue("age", "too.darn.old");
//        }
//    }
//}