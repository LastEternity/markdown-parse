// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {

    // Loop with a stack until finding the corresponding closeParen
    static int findCloseParen(String markdown, int openParen) {
        int closeParen = openParen + 1;
        int openParenCount = 1;
        
        if(markdown.indexOf(")") == -1){
            return -1;
        }

        boolean checkIfOpenBracket = false;

        while (openParenCount > 0 && closeParen < markdown.length()) {
            
            if(markdown.charAt(closeParen) == '['){
                checkIfOpenBracket = true;
            }
        
            if(checkIfOpenBracket && markdown.charAt(closeParen) == ']'){
                return -2;
            }
            
            
            if (markdown.charAt(closeParen) == '(') { 
                openParenCount++;
            } else if (markdown.charAt(closeParen) == ')') {
                openParenCount--;
            }
            closeParen++;
        }
        return closeParen - 1;

    }

    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();

        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            int openParen = markdown.indexOf("](", nextOpenBracket) + 1;

            // The close paren we need may not be the next one in the file
            int closeParen = findCloseParen(markdown, openParen);
            
            if(nextOpenBracket == -1 || closeParen == -1 || openParen == -1) {
                return toReturn;
            }
            if(closeParen == -2){
                currentIndex = currentIndex + 1;
                continue;
            }

            String potentialLink = markdown.substring(openParen + 1, closeParen).trim();
            if(potentialLink.indexOf(" ") == -1 && potentialLink.indexOf("\n") == -1) {
                toReturn.add(potentialLink);
                currentIndex = closeParen + 1;
            }
            else {
                currentIndex = currentIndex + 1;
            }
        }
        return toReturn;
    }

    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}