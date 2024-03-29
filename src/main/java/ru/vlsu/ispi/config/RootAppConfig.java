package ru.vlsu.ispi.config;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "ru.vlsu.ispi")
public class RootAppConfig {
    // Здесь можно добавить дополнительные настройки для корневого контекста Spring
}