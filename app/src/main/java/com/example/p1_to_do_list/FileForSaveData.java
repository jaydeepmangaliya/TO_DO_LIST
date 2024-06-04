package com.example.p1_to_do_list;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileForSaveData {

    // this class use to save data in memory
    // write and read

    // file name c

    public  static  final String filename  ="savedata";


    // for write datav
    public  static  void  writedata(ArrayList<String> item , Context context){

          // fileoutputstrime use to write data
        try {
            FileOutputStream fos = context.openFileOutput(filename , Context.MODE_PRIVATE);
            ObjectOutputStream oas = new ObjectOutputStream(fos);
            oas.writeObject(item);
            oas.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public static  ArrayList<String> readdata(Context context){
        ArrayList<String> list = null;


        try {
            FileInputStream fis = context.openFileInput(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            list = (ArrayList<String>) ois.readObject();
        } catch (FileNotFoundException e) {

            list = new ArrayList<>();
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
 return  list;

    }

}
