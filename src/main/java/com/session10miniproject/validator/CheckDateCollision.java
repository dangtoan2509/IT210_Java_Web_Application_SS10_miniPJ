package com.session10miniproject.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;
@Documented
@Constraint(validatedBy = CheckDateCollisionValidator.class)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckDateCollision {
    String message() default "Thiết bị đã được đặt mượn trong khoảng thời gian này";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

