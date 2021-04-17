package com.engeto.examples;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    // Postup:
    // 1.  otevreni txt souboru, tokenizace obsahu -> ulozeni do HashMapy, kde key bude poradi a value String
    // 2.  postupne prochazeni HashMapy podle poradi a zapis do noveho souboru

    public static void main(String[] args)throws IOException {

      HashMap<Integer,String> textMap = new HashMap<>();

      textMap = openAndParse("homeworkInputFile.txt");
      if(mapToFile(textMap, "homeworkOutputFile.txt")) System.out.println("Soubor byl úspěšně vytvořen.");
      else System.out.println("Při vytváření souboru došlo k chybě.");
    }

    public static HashMap openAndParse(String fileName)
    {   HashMap<Integer,String> textMap = new HashMap<>();
        String token = new String();
        Integer orderNo = null;
        token = "";

        try(Scanner strings = new Scanner(new BufferedReader(new FileReader(fileName)));) {
            while (strings.hasNext()) {
                if(strings.hasNextInt()) {
                    if(orderNo == null) orderNo = strings.nextInt();
                    else {  textMap.put(orderNo,token);
                            orderNo = strings.nextInt();
                            token = "";
                         }
                }
                else {  //System.out.println(strings.next());
                    token = token + " " + strings.next();
                }
            }
            if(orderNo != null) textMap.put(orderNo,token);
        } catch (IOException error){
            error.printStackTrace();
        }
       return textMap;
    }

    public static boolean mapToFile(HashMap <Integer,String> textMap,String fileName)
    {
        try( BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));
        ) {  for(int i=1; i<= textMap.size();i++)
             {   bufferedWriter.write(textMap.get(i));
             }
             return true;
          }catch (IOException error){
            error.printStackTrace();
            return false;
        }
    }
}
