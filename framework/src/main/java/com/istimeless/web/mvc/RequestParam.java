package com.istimeless.web.mvc;

import java.lang.annotation.*;

/**
 * @author lijiayin
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface RequestParam {
    String value();
}
