package com.example.api.validator;

import com.example.api.util.ValidatorUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @projectName: dubbo_demo
 * @package: com.example.provider.validator
 * @className: IsMobileValidator
 * @author: LiYinjian
 * @date: 2022/7/20 13:58
 * @version: 1.0
 */

public class IsMobileValidator implements ConstraintValidator<IsMobile, String> {
    @Override
    public void initialize(IsMobile constraintAnnotation) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return ValidatorUtil.isMobile(s);
    }
}
