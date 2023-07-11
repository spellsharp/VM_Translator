import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Parser parser = new Parser();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the path of the file to be parsed: ");
        String inputFilePath = sc.nextLine();
        File inputFile = new File(inputFilePath);
        File cleanFile = parser.cleanFile(inputFile);
        parser.parseFile(cleanFile);
    }
}