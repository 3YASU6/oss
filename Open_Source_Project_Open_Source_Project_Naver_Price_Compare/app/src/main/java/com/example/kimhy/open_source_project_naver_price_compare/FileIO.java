package com.example.kimhy.open_source_project_naver_price_compare;

import android.content.Context;
import android.graphics.Paint;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileIO
{
    private final String fileName = "Items.txt";
    FileReader reader = null;
    BufferedReader bufferedReader = null;
    FileWriter writer = null;
    BufferedWriter bufferedWriter = null;
    String string;
    Context context;

    public FileIO(Context context)
    {
        this.context = context;
        System.out.println("context const success");
    }


    public ArrayList<String> loadItemsFromFile()
    {
        ArrayList<String> items = new ArrayList<>();
        // file object Create
        File file = new File(context.getFilesDir(), fileName);

        // 파일이 존재하지 않으면 새로은 파일을 생성. ファイルが存在しない時、新たなファイルを生成
        if (!file.exists())
        {
            try
            {
                file.createNewFile();
            }
            catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        else{  // 파일이 존재할때. ファイルが存在するとき
            System.out.println("I find the Items.txt");
        }

        // 파일이 존재하고 또 입력 가능, ファイルが存在し、かつ書き込み可能
        if (file.exists() & file.canWrite()) {
            try {
                reader = new FileReader(file);
                bufferedReader = new BufferedReader(reader);

                while ((string = bufferedReader.readLine()) != null) {
                    items.add(string);
                }
                bufferedReader.close();
                reader.close();

                System.out.println("file read success");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return items;
    }




    public String addNewItem(String newItemInfo) {
        try {
            // file object Create
            File file_name = new File(context.getFilesDir(), fileName);
            FileWriter file_object = new FileWriter(file_name, true);
            PrintWriter pw = new PrintWriter(new BufferedWriter(file_object));

            pw.println(newItemInfo);

            pw.close();

            return newItemInfo + " : Successfully Add New Item";
        } catch (IOException e) {
            e.printStackTrace();
            return "ERROR : " + e.toString();
        }
    }



    public void storeItemsToFile(ArrayList<String> argumentTitle)
    {
        System.out.println("storeItemsToFile method load success");
        System.out.println(argumentTitle);
        File file = new File(context.getFilesDir(), fileName); //file object Create
        try
        {
            writer = new FileWriter(file);
            bufferedWriter = new BufferedWriter(writer);
            System.out.println("data " + argumentTitle);
            for (String str : argumentTitle) //items->argumentTitle
            {
                bufferedWriter.write(str);
                bufferedWriter.newLine();
            }
            //file write
            bufferedWriter.flush();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        try
        {
            if (bufferedWriter != null)
            {
                bufferedWriter.close();
            }
            if (writer != null)
            {
                writer.close();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
