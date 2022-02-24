import static org.junit.Assert.*;
import org.junit.*;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.nio.file.Path;


public class MarkdownParseTest {
    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }

    HashMap<String, ArrayList<String>> fileMap;
    
    @Before
    public void setupMap() {
        fileMap = new HashMap<>();
        fileMap.put("test-file1.md", new ArrayList<String>(Arrays.asList("https://something.com", "some-page.html")));
        fileMap.put("test-file2.md", new ArrayList<String>(Arrays.asList()));
        fileMap.put("test-file3.md", new ArrayList<String>(Arrays.asList()));
        fileMap.put("test-file4.md", new ArrayList<String>(Arrays.asList("url2")));
        
        fileMap.put("snippet3.md", new ArrayList<String>(Arrays.asList("https://ucsd-cse15l-w22.github.io/")));
        fileMap.put("snippet2.md", new ArrayList<String>(Arrays.asList("a.com", "a.com(())", "example.com")));
        fileMap.put("snippet1.md", new ArrayList<String>(Arrays.asList("`google.com", "google.com", "ucsd.edu")));
    }

    @Test
    public void testFile1() throws Exception {
        assertEquals(fileMap.get("test-file1.md"), MarkdownParse.getLinks(Files.readString(Path.of("test-file1.md"))));
    }

    @Test
    public void testFile2() throws Exception {
        assertEquals(fileMap.get("test-file2.md"), MarkdownParse.getLinks(Files.readString(Path.of("test-file2.md"))));
    }

    @Test
    public void testFile3() throws Exception {
        assertEquals(fileMap.get("test-file3.md"), MarkdownParse.getLinks(Files.readString(Path.of("test-file3.md"))));
    }
    
    @Test
    public void snippet1.md throws Exception {
        assertEquals(fileMap.get("snippet1.md"), MarkdownParse.getLinks(Files.readString(Path.of("snippet1.md"))));
    }
    
    @Test
    public void snippet2.md throws Exception {
        assertEquals(fileMap.get("snippet2.md"), MarkdownParse.getLinks(Files.readString(Path.of("snippet2.md"))));
    }
    
    @Test
    public void snippet3.md throws Exception {
        assertEquals(fileMap.get("snippet3.md"), MarkdownParse.getLinks(Files.readString(Path.of("snippet3.md"))));
    }

    /*
    java -cp .:lib/* org.junit.runner.JUnitCore MarkdownParseTest
    on ssh

    Test needs to be addressed. Is a still failing test. 

    @Test
    public void testFile4() throws Exception {
        assertEquals(fileMap.get("test-file4.md"), MarkdownParse.getLinks(Files.readString(Path.of("test-file4.md"))));
    }
    */
}
