package br.com.sample.solutionbto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.Locale;
import java.util.TimeZone;

@EnableCaching
@EnableFeignClients
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        setDefaults();
        SpringApplication.run(App.class, args);
    }

    private static void setDefaults() {
        Locale.setDefault(Locale.of("pt", "BR"));
        TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
        System.setProperty("file.encoding", "UTF-8");
    }

}
