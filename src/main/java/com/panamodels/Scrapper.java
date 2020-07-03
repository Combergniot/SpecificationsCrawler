package com.panamodels;


import java.io.IOException;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class Scrapper extends ScrapperConfig {

    public void showOneSpecification(String link) throws IOException {
        Document document = connectWith(link);
        Elements elements = document.select("tbody.body-child>tr");
        for (Element element : elements) {
            String first = element.select("th[scope = row]").text();
            String second = element.select("td.tb-blk").text();
            System.out.println(first + ": " + second);
        }

    }
}
