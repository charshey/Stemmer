import java.io.*;

public class Model {

    String filename;
    Trie dict;

//    public Model(File file){
//        try {
//            FileReader reader = new FileReader(file);
//            BufferedReader bufferedReader = new BufferedReader(reader);
//
//
//        }
//        catch(FileNotFoundException e){
//            System.out.println("The input file, " + file.getName() + ", is not found!\n");
//        }
//    }

    // read freq profile
    public void load(String filename){
        this.filename = filename;
        BufferedReader br = null;
        String line;
        dict = new Trie();

        try{
            br = new BufferedReader(new FileReader(filename + ".csv"));
            while((line = br.readLine() )!= null){
                String[] split = line.split(",");
                String word = split[0].toLowerCase();
                int freq = Integer.parseInt(split[2].replaceAll("[\uFEFF-\uFFFF]",""));
                dict.insert(word,freq);
            }
        }
        catch(FileNotFoundException e){
            System.out.println("The input file is not found\n");
        }
        catch(IOException e){
            e.printStackTrace(System.out);
        }
    }


//    // return list of stems
//    public DoublyLinkedList<String> getStems(){
//        return dict.listStems();
//    }


    // test the model against gold standard
    public void test(){
        BufferedReader br = null;
        BufferedWriter bw = null;
        String line;
        int correct = 0;
        int count = 0;

        try{
            br = new BufferedReader(new FileReader(filename + ".csv"));
            bw = new BufferedWriter(new FileWriter(new File(this.filename + "_results.txt")));
            bw.write("word" + ", " + "real stem" + ", "+ "stem guess" + ", " + "correct?" + "\n");
            while((line = br.readLine() )!= null){
                String[] split = line.split(",");
                String word = split[0].replaceAll("[\uFEFF-\uFFFF]", "").toLowerCase();
                String stem = split[1].replaceAll("[\uFEFF-\uFFFF]", "").toLowerCase();
                int freq = Integer.parseInt(split[2].replaceAll("[\uFEFF-\uFFFF]",""));
                String guess = dict.getLongestMatchingPrefix(word,freq);
                if(guess.equals(stem))
                    if(!word.equals(stem))
                        correct++;
               if(!word.equals(stem))
                    count++;
//               if(!word.equals(stem))
                   bw.write(word + ", " + stem + ", "+ guess + ", " + guess.equals(stem) + "\n");
            }
            double percentage = 100 * (double) correct / (double) count;
            System.out.println("Test results: " + percentage + "% " + "(" + correct + " out of " + count + " correct)");
        }
        catch(FileNotFoundException e){
            System.out.println("The input file is not found\n");
        }
        catch(IOException e){
            e.printStackTrace(System.out);
        }
    }



}
