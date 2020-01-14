package me.ohtaeg;

import config.NaverApiProperties;
import me.ohtaeg.config.SomeThing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


@Component
public class AppRunner implements ApplicationRunner {

    @Autowired
    NaverApiProperties naverApiProperties;

    @Autowired
    SomeThing someThing;

    @Override
    public void run(final ApplicationArguments args) throws Exception {
        System.out.println(naverApiProperties.getClientId());
        System.out.println(someThing.getName());
    }
}
