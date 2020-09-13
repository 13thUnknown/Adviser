package edu.suai.recommendations.parsing;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import edu.suai.recommendations.exception.PageNotFound;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.SneakyThrows;

public class Parsing {
@SneakyThrows
  public static void main(String[] args) {
    String searchQuery = "Смартфон APPLE iPhone 7 32Gb, MN8Y2RU/A, серебристый" ;//iPhone 7 32Gb
    String baseUrl = "https://www.citilink.ru/" ;
    String search="search/?text=";
    String xpathPage = "//div[@class='main_content']";
    String xpathPrice = ".//div/aside/section/div/div/div/div/ins[@class='num']";
    try(WebClient client = new WebClient()) {
      client.getOptions().setCssEnabled(false);
      client.getOptions().setJavaScriptEnabled(false);
      String searchUrl = baseUrl + search + URLEncoder.encode(searchQuery, StandardCharsets.UTF_8);
      HtmlPage page = client.getPage(searchUrl);
      List<HtmlElement> items = (List<HtmlElement>) page.getByXPath(xpathPage);
      if (items.isEmpty()) {
        throw new PageNotFound("Page with url " + searchUrl + " and with xpath + " + xpathPage + " not found!");
      } else {
        for (HtmlElement htmlItem : items) {
          HtmlElement spanPrice = htmlItem.getFirstByXPath(xpathPrice);
          String itemPrice = spanPrice == null ? "0.0" : spanPrice.asText();
          Item item = new Item();
          item.setPrice(new BigDecimal(itemPrice.replace(" ", "")));
          ObjectMapper mapper = new ObjectMapper();
          String jsonString = mapper.writeValueAsString(item);
          System.out.println(jsonString);
        }
      }
    }
  }
}
