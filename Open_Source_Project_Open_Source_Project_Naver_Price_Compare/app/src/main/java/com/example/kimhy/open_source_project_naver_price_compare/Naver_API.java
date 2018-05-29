package com.example.kimhy.open_source_project_naver_price_compare;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;


//NetworkOnMainThreadException AsyncTask
public class Naver_API extends AsyncTask<Void, Void, Void>
{

    private final String clientId = "VgVlGnfsifjWb55DM4s_";//애플리케이션 클라이언트 아이디값";
    private final String clientSecret = "uw7_JLBNnh";//애플리케이션 클라이언트 시크릿값";
    private String returnString = null;

    public Naver_API()
    {

    }

    @Override
    protected Void doInBackground(Void... params)
    {

        try

            {
            }
        catch (Exception e)
            {

            }
        return null;

    }

    public String naver_API_Call(String keyword)
    {
        try
            {
            String text = URLEncoder.encode(keyword, "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/search/shop?query=" + text; // json 결과
            //String apiURL = "https://openapi.naver.com/v1/search/shop.xml?query="+ text; // xml 결과
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
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null)
                {
                response.append(inputLine);
                }
            br.close();

            returnString = response.toString();
            //System.out.println(response.toString());
            }
        catch (Exception e)
            {
            System.out.println(e);
            }
        return returnString;

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
//var clientId = "VgVlGnfsifjWb55DM4s_"//애플리케이션 클라이언트 아이디값";
//        var clientSecret = "uw7_JLBNnh"//애플리케이션 클라이언트 시크릿값";
/*
public class Naver_API
{


    public static StringBuilder sb;//

    static String getString(String input, String data) // API에서 필요한 문자 자르기. 파싱
    {
        String[] dataSplit = data.split("{" + input + "}");
        String[] dataSplit2 = dataSplit[1].split("\"" + input + "\"");
        return dataSplit2[0];
    }

    public static StringBuilder Naver_Search_API(String search_keyword)
    {

        // TODO Auto-generated method stub
        String clientId = "VgVlGnfsifjWb55DM4s_";
        String clientSecret = "uw7_JLBNnh";
        int display = 2;

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter an search keyword : ");//consol input keyword
        String search = sc.next();

        try
            {
            String text = URLEncoder.encode(search_keyword, "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/search/shop?query=" + text + "&display=" + display + "&";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode == 200)
                {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                }
            else
                {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                }
            sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null)
                {
                sb.append(line + "\n");
                }

            br.close();
            con.disconnect();
            System.out.println(sb);
            String data = sb.toString();
            String[] array;
            array = data.split("\"");
            String[] title = new String[display];
            String[] link = new String[display];
            String[] category = new String[display];
            String[] description = new String[display];
            String[] telephone = new String[display];
            String[] address = new String[display];
            String[] mapx = new String[display];
            String[] mapy = new String[display];
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
                if (array[i].equals("category"))
                    {
                    category[k] = array[i + 2];
                    }
                if (array[i].equals("description"))
                    {
                    description[k] = array[i + 2];
                    }
                if (array[i].equals("telephone"))
                    {
                    telephone[k] = array[i + 2];
                    }
                if (array[i].equals("address"))
                    {
                    address[k] = array[i + 2];
                    }
                if (array[i].equals("mapx"))
                    {
                    mapx[k] = array[i + 2];
                    }
                if (array[i].equals("mapy"))
                    {
                    mapy[k] = array[i + 2];
                    k++;
                    }
                }
            System.out.println(sb);
            System.out.println("----------------------------");
            System.out.println("first title : " + title[0]);
            System.out.println("second title : " + title[1]);
            } catch (Exception e)
            {
            System.out.println(e);
            }
        return sb;//test 후 삭제 혹은 수정할 것
    }

}
*/