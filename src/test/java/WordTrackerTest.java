import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;


public class WordTrackerTest {


    @Test
    public void emptySeedFile()throws Exception{

        WordTracker wordTracker = new WordTracker();
        wordTracker.parseSeedFile("empty_seed.txt");
        Assertions.assertEquals(0,wordTracker.wordFilesSet.size());
    }

    @Test
    void nonEmptySeedFile() throws Exception{
        WordTracker wordTracker = new WordTracker();
        wordTracker.parseSeedFile("seed.txt");
        Assertions.assertEquals(3,wordTracker.wordFilesSet.size());
    }

    @Test
    public void uniqueWordCounts_WorksAcrossFiles() throws Exception{
        WordTracker wordTracker = new WordTracker();
        wordTracker.parseSeedFile("seed.txt");
        HashMap fileStats = wordTracker.buildWordStatsFromWordFiles();
        Assertions.assertEquals(5, wordTracker.wordsSet.size());
    }

}
