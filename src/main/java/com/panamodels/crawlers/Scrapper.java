package com.panamodels.crawlers;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class Scrapper extends ScrapperConfig {

    public List<String> showOneSpecification(String link) throws IOException {
        List<String> list = new ArrayList<>();
        Document document = connectWith(link);
        Elements elements = document.select("tbody.body-child>tr");
        for (Element element : elements) {
            String first = element.select("th[scope = row]").text();
            String second = element.select("td.tb-blk").text();
            list.add(first + ": " + second);
        }
        return list;
    }
}
