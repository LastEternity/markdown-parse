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
    }

    @Test
    public void testFile1() throws Exception {
        assertEquals(fileMap.get("test-file1.md"), MarkdownParse.getLinks(Files.readString(Path.of("C:/Users/adria/Desktop/CSE15L/markdown-parse/test-file1.md"))));
    }

    @Test
    public void testFile2() throws Exception {
        assertEquals(fileMap.get("test-file2.md"), MarkdownParse.getLinks(Files.readString(Path.of("C:/Users/adria/Desktop/CSE15L/markdown-parse/test-file2.md"))));
    }

    @Test
    public void testFile3() throws Exception {
        assertEquals(fileMap.get("test-file3.md"), MarkdownParse.getLinks(Files.readString(Path.of("C:/Users/adria/Desktop/CSE15L/markdown-parse/test-file3.md"))));
    }
}