package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "config, service, model, console")
@PropertySource("classpath:application.properties")
public class AppConfig {
}
