package com.soft.appservice1.controller;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 *
 */
@RestController
@RequestMapping("/app1")
public class AppServiceController {

    @RequestMapping(value = "/getMessage",method = RequestMethod.GET)
    public String shenshou(){
        String reponse = String.format("current : appservice1-%s","getMessage");
        return reponse;
    }

    public static void main(String[]args){
        cexio();
        bitflyer();
        hanguo();
    }

    public static void hanguo(){
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://www.bithumb.com/resources/csv/total_ticker.json?" +
                "csrf_xcoin_name=68e6a6dd7c0851a543574dcda00d361b&" +
                "_=1519469964335");
        CloseableHttpResponse response1 = null;
        try {
            response1 = httpclient.execute(httpGet);
            HttpEntity entity1 = response1.getEntity();
            if(null!=entity1){
                String html = EntityUtils.toString(entity1,"UTF-8");
                System.out.println(html);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
                response1.close();
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    /**
     * 日本：https://bitflyer.jp/en/?top_link
     */
    public static void bitflyer(){
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://bitflyer.jp/en/?top_link");
        CloseableHttpResponse response1 = null;
        try {
            response1 = httpclient.execute(httpGet);
            HttpEntity entity1 = response1.getEntity();
            if(null!=entity1){
                String html = EntityUtils.toString(entity1,"UTF-8");
                Document doc = Jsoup.parse(html);
                Elements titleElements = doc.getElementsByClass("overview_title").get(0).getAllElements();
                Elements priceElements = doc.getElementsByClass("board-small").get(0).getAllElements();
                titleElements.stream().forEach(x->System.out.println(x.text()));
                priceElements.stream().forEach(x->System.out.println(x.text()));

            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
                response1.close();
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    /**
     * 英国：https://cex.io/
     */
    public static void cexio(){
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://cex.io/");
        CloseableHttpResponse response1 = null;
        try {
            response1 = httpclient.execute(httpGet);
            HttpEntity entity1 = response1.getEntity();
           if(null!=entity1){
               String html = EntityUtils.toString(entity1,"UTF-8");
               Document doc = Jsoup.parse(html);
               Elements linksElements = doc.getElementsByClass("row pairs").get(0).getElementsByTag("li");
               for (Element ele:linksElements) {
                   String title = ele.text();
                   System.out.println(title);
               }
           }
        }catch(Exception e){
           e.printStackTrace();
        }finally {
            try {
                response1.close();
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
