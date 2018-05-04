import java.util.Iterator;

public class Trie {

    class Node{
        private char value;
        private String word;
        private HashMap<Character,Node> children;
        private boolean bIsEnd;
        private int freq = 0;

        public Node(char c, int freq, String word){
            value = c;
            children = new HashMap<>();
            bIsEnd = false;
            this.freq = freq;
            this.word = word;
        }

        public Node(char c){
            value = c;
            children = new HashMap<>();
            bIsEnd = false;
        }

        public HashMap<Character,Node> getChildren(){
            return children;
        }

        public char getValue(){
            return value;
        }

        public void setIsEnd(boolean val){
            bIsEnd = val;
        }

        public boolean isEnd(){
            return bIsEnd;
        }

        public int freq(){
            return freq;
        }

    }

    private Node root;
    private int n = 0;

    public Trie(){
        root = new Node((char)0);
    }

    public void insert(String word,int freq){
        word = word.replaceAll("[\uFEFF-\uFFFF]", "");
        int length = word.length();
        Node crawl = root;

        for(int level = 0; level < length; level++){
            HashMap<Character,Node> child = crawl.getChildren();
            char c = word.charAt(level);
            if(c == '\uFEFF')
                continue;

            if(child.get(c) != null) {
                crawl = child.get(c);
            }
            else{
                Node temp;
                if(level == length - 1)
                    temp = new Node(c,freq,word);
                else
                    temp = new Node(c);
                child.put(c,temp);
                crawl = temp;
            }
        }

        crawl.setIsEnd(true);
        n++;
    }

    public String getLongestMatchingPrefix(String input, int freq){
        String result = "";
        int length = input.length();

        // initialize reference to traverse structure
        Node crawl = root;

        // go through all chars of input and walk down structure
        int level, prevMatch = 0;
        for(level = 0; level < length-1; level++){
            char c = input.charAt(level);
            HashMap<Character,Node> child = crawl.getChildren();
            // see if there is edge for current char
            if(child.get(c) != null){
                result += c; // update result
                crawl = child.get(c); // set crawl to keep going next round
                if(crawl.isEnd() && crawl.freq > freq * Constants.c_i) // if we're already at the end of a word update prev match
                    prevMatch = level + 1;
            }
            else break;
        }
        if(prevMatch == 0)
            return input;
        // if the last processed char didn't match the end of a word return the previous match
        if(!crawl.isEnd())
            return result.substring(0,prevMatch);
        else return result;
    }

    public String getShortestMatchingPrefix(String input,int freq){
        String result = "";
        int length = input.length();

        Node crawl = root;

        int level, match = 0;
        for(level = 0; level < length; level++){
            char c = input.charAt(level);
            HashMap<Character,Node> child = crawl.getChildren();
            if(child.get(c) != null) {
                result += c;
                crawl = child.get(c);
                if (crawl.isEnd()) {
                    match = level;
                    break;
                }
            }
            else break;
        }
        if(!crawl.isEnd())
            return result.substring(0,match);
        else return result;
    }

    public boolean isEmpty(){
        return n == 0;
    }

}
