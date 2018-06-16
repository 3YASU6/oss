package com.example.kimhy.open_source_project_naver_price_compare;

import android.content.Context;
import android.graphics.Paint;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

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
        File file = new File(context.getFilesDir(), fileName);//file object Create
        System.out.println("file location " + context.getFilesDir());

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

        if (file.exists())
            {
            System.out.println("I find the Items.txt");
            }
        else
            {
            System.out.println("No, there is not a no file.");
            }

        if (file.exists() & file.canWrite())
            {
            try
                {
                reader = new FileReader(file);
                bufferedReader = new BufferedReader(reader);

                while ((string = bufferedReader.readLine()) != null)
                    {
                    items.add(string+"\n");
                    }
                bufferedReader.close();
                reader.close();

                System.out.println("file read success");//
                }
            catch (Exception e)
                {
                e.printStackTrace();

                }
            System.out.println("success " + items);
            }

        //        int size = 0;
        //        String[] arrayReturn = new String[items.size()];
        //        for (String temp : items)
        //        {
        //
        //            arrayReturn[size++] = temp;
        //
        //        }
        file.delete();
        try
            {
            file.createNewFile();
            }
        catch (Exception e)
            {
            e.printStackTrace();
            }
        return items;
    }

    public void storeItemsToFile(
            ArrayList<String> argumentTitle)
    {

        System.out.println("storeItemsToFile method load success");
        System.out.println(argumentTitle);
        ArrayList<String> items = new ArrayList<>();
        //        for (String temp : argumentTitle)
        //        {
        //
        //            items.add(temp);
        //
        //        }
        File file = new File(context.getFilesDir(), fileName);//file object Create


        try
            {
            writer = new FileWriter(file);
            bufferedWriter = new BufferedWriter(writer);
            System.out.println("data " + argumentTitle);
            for (String str : argumentTitle)//items->argumentTitle
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
