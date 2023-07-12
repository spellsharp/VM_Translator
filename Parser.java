import java.io.*;
import java.util.*;

public class Parser {
    CodeWriter codeWriter;
    Scanner sc;

    public Parser() throws IOException {
        sc = new Scanner(System.in);
        System.out.println("Parsed output file path/name: ");
        String outputFilePath = sc.nextLine();
        codeWriter = new CodeWriter(outputFilePath);
    }

    public File cleanFile(File inputFile) throws IOException {
        try {
            String outputFileName = "output_files/"
                    + inputFile.getName() + ".txt";
            File outputFile = new File(outputFileName);
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));

            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.equals(""))
                    continue;
                if (!(line.startsWith("//"))) {
                    if (line.contains("//")) {
                        int commentIndex = line.indexOf("//");
                        line = line.substring(0, commentIndex);
                        line = line.trim();
                    }
                    bw.write(line + '\n');
                    // System.out.println(line);
                }
            }

            br.close();
            bw.close();
            return outputFile;
        } catch (IOException e) {
            System.out.println("Error: " + e);
            System.out.println(e.getStackTrace());
            return null;
        }
    }

    public void parseFile(File inputFile) throws IOException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            String line;

            while ((line = br.readLine()) != null) {
                String[] commandTokens = line.split(" ");
                if ("push".equals(commandTokens[0])) {
                    codeWriter.handlePush(commandTokens);
                } else if ("pop".equals(commandTokens[0])) {
                    codeWriter.handlePop(commandTokens);
                } else if (commandTokens.length == 1) {
                    codeWriter.handleArithmetic(commandTokens);
                } else if ("label".equals(commandTokens[0])) {
                    codeWriter.handleLabel(commandTokens);
                } else if ("if-goto".equals(commandTokens[0])) {
                    codeWriter.HandleIfGoto(commandTokens);
                } else if ("goto".equals(commandTokens[0])) {
                    codeWriter.HandleGoto(commandTokens);
                } else {
                    System.out.println(commandTokens[0]);
                }
            }
            br.close();
            codeWriter.close();
        } catch (IOException e) {
            System.out.println("Error: " + e);
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

}