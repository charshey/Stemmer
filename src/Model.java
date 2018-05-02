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
        
    }


    // return list of stems

    // write model to save

}
