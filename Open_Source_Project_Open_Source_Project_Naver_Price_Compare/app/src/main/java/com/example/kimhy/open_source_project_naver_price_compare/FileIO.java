package com.example.kimhy.open_source_project_naver_price_compare;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class FileIO
{
    private final String fileName = "Items.list";
    FileReader reader = null;
    BufferedReader bufferedReader = null;
    FileWriter writer = null;
    BufferedWriter bufferedWriter = null;
    String string;
    ArrayList<String> items;

    public FileIO(ArrayList<String> items)
    {
        this.items = new ArrayList<String>();
        this.items = items;


    }

    public void loadItemsFromFile(Context context)
    {
        File file = new File(context.getFilesDir(), fileName);//file object Create
        if (file.exists())
        {
            try
            {
                reader = new FileReader(file);
                bufferedReader = new BufferedReader(reader);

                while ((string = bufferedReader.readLine()) != null)
                {
                    items.add(string);
                }
                bufferedReader.close();
                reader.close();


            }
            catch (Exception e)
            {
                e.printStackTrace();

            }
        }

    }

    public void storeItemsToFile(Context context)
    {

        File file = new File(context.getFilesDir(), fileName);//file object Create
        try
        {
            writer = new FileWriter(file);
            bufferedWriter = new BufferedWriter(writer);
            for (String str : items)
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
