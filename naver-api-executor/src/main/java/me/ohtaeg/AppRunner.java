package me.ohtaeg;

import config.NaverApiProperties;
import me.ohtaeg.config.SomeThing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


@Component
public class AppRunner implements ApplicationRunner, ApplicationListener<ServletWebServerInitializedEvent> {

    @Autowired
    NaverApiProperties naverApiProperties;

    @Autowired
    SomeThing someThing;

    @Override
    public void run(final ApplicationArguments args) {
        System.out.println("clientId : " + naverApiProperties.getClientId());
        System.out.println("test something : " + someThing.getName());
    }

    @Override
    public void onApplicationEvent(final ServletWebServerInitializedEvent event) {
        ServletWebServerApplicationContext applicationContext = event.getApplicationContext();
        System.out.println("port : " + applicationContext.getWebServer().getPort());
    }
}
