import org.junit.Test;


public class Testing {

  @Test
  public void testTrie() {
    Trie dict = new Trie();
    dict.insert("are");
    dict.insert("area");
    dict.insert("base");
    dict.insert("cat");
    dict.insert("cater");
    dict.insert("basement");

    String input = "caterer";
    System.out.print(input + ":   ");
    System.out.println(dict.getMatchingPrefix(input));

    input = "basement";
    System.out.print(input + ":   ");
    System.out.println(dict.getMatchingPrefix(input));

    input = "are";
    System.out.print(input + ":   ");
    System.out.println(dict.getMatchingPrefix(input));

    input = "arex";
    System.out.print(input + ":   ");
    System.out.println(dict.getMatchingPrefix(input));

    input = "basemexz";
    System.out.print(input + ":   ");
    System.out.println(dict.getMatchingPrefix(input));

    input = "xyz";
    System.out.print(input + ":   ");
    System.out.println(dict.getMatchingPrefix(input));
  }

  @Test
  public void testTrieMongolian(){
    Trie dict = new Trie();
    dict.insert("өвөлж");
    dict.insert("яв");
    dict.insert("ид");
    dict.insert("унш");
    dict.insert("бич");

    String input = "өвөлжих";
    System.out.print(input + ":   ");
    System.out.println(dict.getMatchingPrefix(input));

    input = "явах";
    System.out.print(input + ":   ");
    System.out.println(dict.getMatchingPrefix(input));

    input = "идэх";
    System.out.print(input + ":   ");
    System.out.println(dict.getMatchingPrefix(input));

    input = "унших";
    System.out.print(input + ":   ");
    System.out.println(dict.getMatchingPrefix(input));

    input = "бичих";
    System.out.print(input + ":   ");
    System.out.println(dict.getMatchingPrefix(input));


  }

}
