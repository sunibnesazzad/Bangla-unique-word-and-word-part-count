/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bangla.word.pattern;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sazzad
 */
public class BanglaWordPattern {
    
    public static ArrayList<wordPart> wp =new ArrayList<>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
       ArrayList<String> words = read(new File("G:\\java fx practice\\Bangla word pattern\\last8.txt"));
        System.out.println("finished");
       for(int i=0; i<words.size(); i++)
       {
           String[] segs = segment(words.get(i));
               int length = segs.length;
           for(int j=2; j<3; j++)
           {
               String sp = "";
               if(j>length) continue;
               for(int k=0; k<j; k++) sp +=segs[k];
                if(!contains(sp)) wp.add(new wordPart(sp));
//               System.out.println(length + " " +j);
//               String sp = words.get(i).substring(0, j);
//               if(!contains(sp)) wp.add(new wordPart(sp));
//               String ep = words.get(i).substring( length-j, length-1);
//               if(!contains(ep)) wp.add(new wordPart(ep));
           }
       }
       System.out.println("finished");
        for(int i=0; i<words.size(); i++)
        {
            for(int j=0; j<wp.size(); j++)
            {
                if(words.get(i).contains(wp.get(j).getPart())) wp.get(j).got();
            }
        }
        System.out.println("finished");
//        for(int j=0; j<wp.size(); j++)
//        print(wp.get(j).getPart(), wp.get(j).getCount());
        print(new File("output.txt"));
        System.out.println("finished");
    }

    private static void print(String s, int count) {
        
    }
    
    
    
    private static void print(File file){
        FileWriter fw = null;
        try {
            fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
             for(int j=0; j<wp.size(); j++) {
                 fw.append(wp.get(j).getPart()+" ");
                 fw.append(wp.get(j).count+"\n");
                         }
        } catch (IOException ex) {
            Logger.getLogger(BanglaWordPattern.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(BanglaWordPattern.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static ArrayList<String> read(File file)
    {
        ArrayList<String> words = new ArrayList<>();
        Scanner sc =null;
        try {
            sc = new Scanner(file, "UTF-8");
            while(sc.hasNext()) words.add(sc.next());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BanglaWordPattern.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
         sc.close();
         return words;
        }
        
    }
    
    
    private static boolean contains(String s){
        for(wordPart w : wp)
        {
            if(w.part.equals(s)) 
                return true;
        }
        return false;
    }

    private static String[] segment(String s) {
         ArrayList<String> parts = new ArrayList<>();
        char[] arr = s.toCharArray();
        for(int i=0; i<arr.length; i++) 
        {
            if((arr[i] >= '\u0985' && arr[i] <= '\u0994') || (arr[i] >= '\u0981' && arr[i] <= '\u0983') || arr[i]=='\u09CE') //For vowel Bangla 
            {
                parts.add(arr[i]+"");
            }
            else if(arr[i] >= '\u0995' && arr[i] <='\u09B9')//For Consonent bangla
            {
               int begin = i++;
               if(i >= arr.length) 
               {
                   parts.add(arr[begin]+"");
                   break;
               }
                if(arr[i] >= '\u0995' && arr[i] <='\u09B9') // next consonent
                {
                    parts.add(arr[--i]+"");
                }
                else if(arr[i]>= '\u09BE' && arr[i] <='\u09CC')// kar
                {
                    parts.add(s.substring(begin, i+1));
                    
                }
                else if(arr[i]== '\u09CD')// hosonto
                {
                    i+=2;
                    if(i >= arr.length) 
                    {
                        parts.add(s.substring(begin, arr.length));
                        break;
                    }
                    if(arr[i] >= '\u0995' && arr[i] <='\u09B9')//For Consonent bangla
                    {
                        parts.add(s.substring(begin, i));
                        i--;
                    }
                    else if(arr[i]>= '\u09BE' && arr[i] <='\u09CC')// kar
                    {
                        parts.add(s.substring(begin, i));
                    }
                }
                
            }
            
            
                
        }
        String[] all = new String[parts.size()];
        for(int i = 0; i<parts.size(); i++) all[i] = parts.get(i);
        
        return all;
    }
    
}
