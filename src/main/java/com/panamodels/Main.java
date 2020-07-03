package com.panamodels;

import com.panamodels.crawlers.Scrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired
    Scrapper scrapper;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        scrapper.showOneSpecification("https://www.panasonic.com/pl/consumer/kamery-i-aparaty/kamery/kamery-4k/hc-vx870ep-k.specs.html");
    }
}
