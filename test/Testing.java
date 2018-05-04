import org.junit.Test;


public class Testing {

//  @Test
//   public void testTrie() {
//    Trie dict = new Trie();
//    dict.insert("are");
//    dict.insert("area");
//    dict.insert("base");
//    dict.insert("cat");
//    dict.insert("cater");
//    dict.insert("caterer");
//    dict.insert("basement");
//
//    String input = "caterer";
//    System.out.print(input + ":   ");
//    System.out.println(dict.getLongestMatchingPrefix(input));
//    System.out.print(input + ":   ");
//    System.out.println(dict.getShortestMatchingPrefix(input));
//
//    input = "caterers";
//    System.out.print(input + ":   ");
//    System.out.println(dict.getLongestMatchingPrefix(input));
//
//    input = "basement";
//    System.out.print(input + ":   ");
//    System.out.println(dict.getLongestMatchingPrefix(input));
//
//    input = "are";
//    System.out.print(input + ":   ");
//    System.out.println(dict.getLongestMatchingPrefix(input));
//
//    input = "arex";
//    System.out.print(input + ":   ");
//    System.out.println(dict.getLongestMatchingPrefix(input));
//
//    input = "basemexz";
//    System.out.print(input + ":   ");
//    System.out.println(dict.getLongestMatchingPrefix(input));
//
//    input = "xyz";
//    System.out.print(input + ":   ");
//    System.out.println(dict.getLongestMatchingPrefix(input));
//  }
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

}
