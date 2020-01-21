package com.test.svc;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by caijiang
 * Date:2018/5/13
 * Time:下午9:38
 */
@Component
public class ApplicationContextHelper implements ApplicationContextAware {

    public static ApplicationContext applicationContext;

    public ApplicationContextHelper() {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHelper.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }
}
