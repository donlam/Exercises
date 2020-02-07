
**About:** <br>
Program displays how many times a word appears up in a file, or across files.  The
program will be seeded with an input file where each line is another file’s relative path.  Each file in
the path will contain the words needed to be tracked. User should be able to run the program and
see a list of all the words and their respective counts. The program will need to quickly handle
1000’s lines in the input file and display results in a timely manner.

**Usage** <br>
1) run `mvn clean install` to build a jar
2) run `java -jar fullpath_to_jar/Cisco-1.0-SNAPSHOT.jar`
3) provide seed file `seed.txt` that is included in the jar under main/java/resources

**Additional Information** </br>
Duplicate seed files in seed will be ignored
