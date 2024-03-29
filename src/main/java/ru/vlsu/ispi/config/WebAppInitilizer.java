package ru.vlsu.ispi.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitilizer extends
        AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {RootAppConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        Class[] configFiles = {MyAppConfig.class};
        return configFiles;
    }

    @Override
    protected String[] getServletMappings() {
        String[] mappings = {"/"};
        return mappings;
    }
}
