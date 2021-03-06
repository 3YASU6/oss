package com.example.kimhy.open_source_project_naver_price_compare;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;


//NetworkOnMainThreadException AsyncTask
public class Naver_API extends Thread
{
    private final String clientId = "tFZOEVXrE7b672z3YZ5L";//애플리케이션 클라이언트 아이디값";
    private final String clientSecret = "S2m9hxStjR";//애플리케이션 클라이언트 시크릿값";
    private String returnString = null;// naver_API_Call return variable
    String printString = null; //thread print variable
    private String keyword;
    StringBuilder laminatingData;
    private int display = 20;// display 10(기본값), 100(최대)	검색 결과 출력 건수 지정


    //parse variable
    static String[] title;
    static String[] link;
    static String[] image;
    static String[] lprice;
    static String[] hprice;
    static String[] mallName;
    static String[] produceId;
    static String[] productType;

    public Naver_API(final String keyword)//class constructor
    {
        laminatingData = new StringBuilder();
        this.keyword = keyword;
    }

    public void run()
    {
        try
        {

            String text = URLEncoder.encode(keyword, "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/search/shop?query=" + text + "&display=" + display + "&"; // json 결과 "sort=asc&" : 가격 오름차순 정렬
            //String apiURL = "https://openapi.naver.com/v1/search/shop.xml?query="+ text + "&display=" + display + "&"; // xml 결과
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode == 200)
            { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            }
            else
            {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            //StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null)
            {
                laminatingData.append(inputLine + "\n");
            }
            br.close();
            con.disconnect();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        //reference http://wowon.tistory.com/122
        //String parse part start
        String data = laminatingData.toString();
        data = data.replaceAll("<b>", "");//<b> 제거
        data = data.replaceAll("</b>", "");//</b>제거

        System.out.println(data);//테스트 완료 후 삭제
        String[] array;
        array = data.split("\"");


        title = new String[display];
        link = new String[display];
        image = new String[display];
        lprice = new String[display];
        hprice = new String[display];
        mallName = new String[display];
        produceId = new String[display];
        productType = new String[display];
        int k = 0;
        for (int i = 0; i < array.length; i++)
        {
            if (array[i].equals("title"))
            {
                title[k] = array[i + 2];
            }
            if (array[i].equals("link"))
            {
                link[k] = array[i + 2];
            }
            if (array[i].equals("image"))
            {
                image[k] = array[i + 2];
            }
            if (array[i].equals("lprice"))
            {
                lprice[k] = array[i + 2];
            }
            if (array[i].equals("hprice"))
            {
                hprice[k] = array[i + 2];
            }
            if (array[i].equals("mallName"))
            {
                mallName[k] = array[i + 2];
            }
            if (array[i].equals("productId"))
            {
                produceId[k] = array[i + 2];
            }
            if (array[i].equals("productType"))
            {
                productType[k] = array[i + 2];
                k++;
            }
        }
        for (String str:produceId)
        {
            System.out.println(str);
        }
        //String parse part end

        //test

//        for (int i = 0; i < display; i++)
//            {
//            System.out.println((i + 1) + "번째 타이틀: " + title[i] + "\nprice : " + lprice[i]);
//            System.out.println("두번째 타이틀: " + title[1]);
//            }
//
    }

    public String getResult()
    {
        return laminatingData.toString();
    }

    public String[] getTitle()
    {
        return title;
    }

    public String[] getLink()
    {
        return link;
    }

    public String[] getImage()
    {
        return image;
    }

    public String[] getIprice()
    {
        return lprice;
    }

    public String[] getHprice()
    {
        return hprice;
    }

    public String[] getMallName()
    {
        return mallName;
    }

    public String[] getProduceId()
    {
        return produceId;
    }

    public String[] getProductType()
    {
        return productType;
    }


    public String getClientId()
    {
        return clientId;
    }

    public String getClientSecret()
    {
        return clientSecret;
    }
}

