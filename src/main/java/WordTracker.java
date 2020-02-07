import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/*
Program displays how many times a word appears up in a file, or across files.  The
program will be seeded with an input file where each line is another file’s relative path.  Each file in
the path will contain the words needed to be tracked. User should be able to run the program and
see a list of all the words and their respective counts. The program will need to quickly handle
1000’s lines in the input file and display results in a timely manner.
 */
public class WordTracker {

    private boolean continueProgram = true;
    //public List<String> wordFiles = new ArrayList<>();
    public Set <String> wordFilesSet = new HashSet<>();
    public Set <String> wordsSet = new HashSet<>();


    //main loop menu to continue seeding the program with new files
    public void start() throws Exception {

        while (continueProgram) {
            System.out.println("Seed files can be found in resources directory");
            System.out.println("Provide the seed file or type in Q to quit - example input: 'seed.txt'");
            Scanner input = new Scanner(System.in);
            String seedFile = input.nextLine();
            if(seedFile.equalsIgnoreCase("Q")){
                continueProgram = false;
            } else {
                parseSeedFile(seedFile);
                //fileStat returns a hash map for each word
                HashMap fileStats = buildWordStatsFromWordFiles();
                Iterator ws = wordsSet.iterator();

                StringBuilder detailStats = new StringBuilder();
                //iterate through the word sets
                while(ws!=null && ws.hasNext()){
                    String wsWord = ws.next().toString();
                    //get the word statistics from each file
                    //iterate through the file check the word
                    int totalCount = 0;

                    Iterator <String> fileIterator = wordFilesSet.iterator();
                    while(fileIterator.hasNext()){
                        String fileName = fileIterator.next();
                        //get the statistics for each file
                        HashMap wordInfo = (HashMap) fileStats.get(fileName);
                        //only give info if there are word in that file
                        if(wordInfo.get(wsWord)!=null){
                            //System.out.println("count for "+wsWord+" "+wordInfo.get(wsWord)+" in file: "+wordFiles.get(i));
                            detailStats.append(wsWord+"("+wordInfo.get(wsWord)+")"+" in File "+fileName+"\n");
                            totalCount+= (Integer) wordInfo.get(wsWord);
                        }
                    }
                    System.out.printf("Found: %s, Total Occurrence: %d\n", wsWord,totalCount);
                }
                System.out.println("Get Detailed Breakdown? y/n");
                Scanner detail = new Scanner(System.in);
                if(detail.nextLine().equalsIgnoreCase("y")){
                    System.out.println(detailStats);
                }
            }
        }
    }


    //reads the main seed file and gather list of other files
    //@param fileName - the name of the seed file
    public void parseSeedFile(String fileName) throws Exception{
        InputStream in = getClass().getResourceAsStream(fileName);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            String line;
            while ((line = br.readLine()) != null) {
                wordFilesSet.add(line);
            }
        } catch (Exception e) {
            System.out.printf("Could not read seed file %s due to %s ",fileName,e.getCause());
        }
    }

    /*
    build word statistics for each file
    The key will be fileName, and the value will be another map to the word and its count
     */
    public HashMap<String, HashMap> buildWordStatsFromWordFiles(){
        //map containing the file and another map to word stats
        HashMap <String, HashMap> fileStat = new HashMap<>();

        Iterator <String> fileIterator = wordFilesSet.iterator();
        while(fileIterator.hasNext()){
            String fileName = fileIterator.next();
            fileStat.put(fileName,collectWordStats(fileName));
        }

        return fileStat;
    }


    //for a given fileName - return a hashmap of the word and its count
    //@param fileName - name of individual files listed in seed file
    public HashMap<String, Integer> collectWordStats(String fileName){
        HashMap <String, Integer> wordStat = new HashMap<>();
        InputStream in = getClass().getResourceAsStream(fileName);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(wordStat.get(line)==null){
                    wordStat.put(line,1);
                } else {
                    wordStat.put(line,wordStat.get(line)+1);
                }
                wordsSet.add(line);
            }
        } catch (Exception e) {
            System.out.printf("Could not read seed file %s due to %s \n",fileName,e.getCause());
        }

        return wordStat;
    }

    public static void main(String args[]) throws Exception{
        WordTracker wp = new WordTracker();
        wp.start();
    }
}

/*
Requirements for the program:
o    User must be able to interact with it.
o    The program/executable must be standalone.
o    A set of easy to follow instructions on how to run and execute the program must be provided.
Assume the system used for running the program has just a base OS installed.
o    To be submitted:

· All of the source code and the corresponding executable. Sharing via cloud is
acceptable.
· Unit tests for your application.
· Input data used to test your program.

 
For bonus points:
User should be able to select a word (from the list of words) and display which files the word came
from, display how many times the word showed up in each file and display how many times the word
showed up in total.
 */
