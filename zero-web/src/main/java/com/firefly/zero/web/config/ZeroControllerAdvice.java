/**
 * File: ZeroControllerAdvice
 * Author: DorSey Q F TANG
 * Created: 2020/2/18 18:50
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.config;

import com.firefly.zero.web.model.Constants;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@ControllerAdvice
public class ZeroControllerAdvice {

    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                try {
                    setValue(new SimpleDateFormat(Constants.DEFAULT_DATE_PATTERN).parse(text));
                } catch (ParseException pe) {
                    setValue(null);
                }
            }

            @Override
            public String getAsText() {
                return new SimpleDateFormat(Constants.DEFAULT_DATE_PATTERN).format((Date) getValue());
            }
        });
    }
}
