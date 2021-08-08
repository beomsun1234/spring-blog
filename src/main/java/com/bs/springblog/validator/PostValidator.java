package com.bs.springblog.validator;

import com.bs.springblog.controller.dto.PostForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import java.io.IOException;


@Component
public class PostValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return PostForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PostForm p = (PostForm) target;

        if(StringUtils.isEmpty(p.getContent())){
            errors.rejectValue("content", "key","내용을 입력하세요");
        }


    }
}
