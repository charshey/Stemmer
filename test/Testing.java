import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


public class Testing {

  @Test
   public void testTrie() {
    Trie dict = new Trie();
    dict.insert("are",0);
    dict.insert("area",0);
    dict.insert("base",0);
    dict.insert("cat",0);
    dict.insert("cater",0);
    dict.insert("caterer",0);
    dict.insert("basement",0);

    String input = "caterer";
    System.out.print(input + ":   ");
    System.out.println(dict.getLongestMatchingPrefix(input,0));
    System.out.print(input + ":   ");
    System.out.println(dict.getShortestMatchingPrefix(input,0));

    input = "caterers";
    System.out.print(input + ":   ");
    System.out.println(dict.getLongestMatchingPrefix(input,0));

    input = "basement";
    System.out.print(input + ":   ");
    System.out.println(dict.getLongestMatchingPrefix(input,0));

    input = "are";
    System.out.print(input + ":   ");
    System.out.println(dict.getLongestMatchingPrefix(input,0));

    input = "arex";
    System.out.print(input + ":   ");
    System.out.println(dict.getLongestMatchingPrefix(input,0));

    input = "basemexz";
    System.out.print(input + ":   ");
    System.out.println(dict.getLongestMatchingPrefix(input,0));

    input = "xyz";
    System.out.print(input + ":   ");
    System.out.println(dict.getLongestMatchingPrefix(input,0));
  }
//
//  @Test
//  public void testTrieMongolian(){
//    Trie dict = new Trie();
//    dict.insert("өвөлж");
//    dict.insert("яв");
//    dict.insert("ид");
//    dict.insert("унш");
//    dict.insert("бич");
//
//    String input = "өвөлжих";
//    System.out.print(input + ":   ");
//    System.out.println(dict.getShortestMatchingPrefix(input));
//
//    input = "явах";
//    System.out.print(input + ":   ");
//    System.out.println(dict.getShortestMatchingPrefix(input));
//
//    input = "идэх";
//    System.out.print(input + ":   ");
//    System.out.println(dict.getShortestMatchingPrefix(input));
//
//    input = "унших";
//    System.out.print(input + ":   ");
//    System.out.println(dict.getShortestMatchingPrefix(input));
//
//    input = "бичих";
//    System.out.print(input + ":   ");
//    System.out.println(dict.getShortestMatchingPrefix(input));
//
//  }

  @Test
  public void testModelTurkish(){
    Model turk = new Model();
    turk.load("data/turkish.txt");
    assert(!turk.dict.isEmpty());
  }

  @Test
  public void testModel(){
    Model mongol = new Model();
    mongol.load("data/mongolian.csv");
    assert(!mongol.dict.isEmpty());
  }

  @Test
  public void testTest(){
    Model turk = new Model();
    turk.load("data/turkish");
    turk.test();

    Model mag = new Model();
    mag.load("data/hungarian");
    mag.test();
  }

//  @Test
//  public void testStems() {
//    Model turk = new Model();
//    turk.load("data/turkish");
//
//    try {
//      BufferedWriter bw = new BufferedWriter(new FileWriter("data/turkish_stems.txt"));
//      for(String stem : turk.getStems())
//        bw.write(stem + "\n");
//    }
//    catch(FileNotFoundException e){
//      System.out.println("The input file is not found\n");
//    }
//    catch(IOException e){
//      e.printStackTrace(System.out);
//    }
//  }
}
