import java.util.HashMap;

public class Trie {

    class Node{
        private char value;
        private HashMap<Character,Node> children;
        private boolean bIsEnd;

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
    }

    private Node root;

    public Trie(){
        root = new Node((char)0);
    }

    public void insert(String word){
        int length = word.length();
        Node crawl = root;

        for(int level = 0; level < length; level++){
            HashMap<Character,Node> child = crawl.getChildren();
            char c = word.charAt(level);

            if(child.containsKey(c))
                crawl = child.get(c);
            else{
                Node temp = new Node(c);
                child.put(c,temp);
                crawl = temp;
            }
        }

        crawl.setIsEnd(true);
    }

    public String getMatchingPrefix(String input){
        String result = "";
        int length = input.length();

        Node crawl = root;

        int level, prevMatch = 0;
        for(level = 0; level < length; level++){
            char c = input.charAt(level);
            HashMap<Character,Node> child = crawl.getChildren();
            if(child.containsKey(c)){
                result += c;
                crawl = child.get(c);
                if(crawl.isEnd())
                    prevMatch = level + 1;
            }
            else break;
        }
        if(!crawl.isEnd())
            return result.substring(0,prevMatch);
        else return result;
    }


}
