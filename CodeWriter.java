import java.io.*;

public class CodeWriter {
    // File outputFile = new File("output.asm");
    private BufferedWriter bw;

    CodeWriter(String filename) throws IOException {
        FileWriter fw = new FileWriter(filename, true); // Append to the existing file
        bw = new BufferedWriter(fw);
    }

    public void close() throws IOException {
        bw.close();
    }

    public void handleArithmetic(String[] command) throws IOException {

        try {
            switch (command[0]) {
                case "add":
                    // add
                    bw.write("@SP\n");
                    bw.write("M=M-1\n");
                    bw.write("A=M\n");
                    bw.write("D=M\n");
                    bw.write("@SP\n");
                    bw.write("M=M-1\n");
                    bw.write("A=M\n");
                    bw.write("M=D+M\n");
                    bw.write("@SP\n");
                    bw.write("M=M+1\n");
                    break;
                case "sub":
                    // sub
                    bw.write("@SP\n");
                    bw.write("M=M-1\n");
                    bw.write("A=M\n");
                    bw.write("D=M\n");
                    bw.write("@SP\n");
                    bw.write("M=M-1\n");
                    bw.write("A=M\n");
                    bw.write("M=M-D\n");
                    bw.write("@SP\n");
                    bw.write("M=M+1\n");
                    break;
                case "neg":
                    // neg
                    bw.write("@SP\n");
                    bw.write("M=M-1\n");
                    bw.write("A=M\n");
                    bw.write("M=-M\n");
                    bw.write("@SP\n");
                    bw.write("M=M+1\n");
                    break;
                case "eq":
                    // eq
                    bw.write("@SP\n");
                    bw.write("M=M-1\n");
                    bw.write("A=M\n");
                    bw.write("D=M\n");
                    bw.write("@SP\n");
                    bw.write("M=M-1\n");
                    bw.write("A=M\n");
                    bw.write("D=M-D\n");
                    bw.write("@TRUE\n");
                    bw.write("D;JEQ\n");
                    bw.write("@SP\n");
                    bw.write("A=M\n");
                    bw.write("M=0\n");
                    bw.write("@CONTINUE\n");
                    bw.write("0;JMP\n");
                    bw.write("(TRUE)\n");
                    bw.write("@SP\n");
                    bw.write("A=M\n");
                    bw.write("M=-1\n");
                    bw.write("(CONTINUE)\n");
                    bw.write("@SP\n");
                    bw.write("M=M+1\n");
                    break;
                case "gt":
                    bw.write("@SP\n");
                    bw.write("M=M-1\n");
                    bw.write("A=M\n");
                    bw.write("D=M\n");
                    bw.write("@SP\n");
                    bw.write("M=M-1\n");
                    bw.write("A=M\n");
                    bw.write("D=M-D\n");
                    bw.write("@TRUE\n");
                    bw.write("D;JGT\n");
                    bw.write("@SP\n");
                    bw.write("A=M\n");
                    bw.write("M=0\n");
                    bw.write("@CONTINUE\n");
                    bw.write("0;JMP\n");
                    bw.write("(TRUE)\n");
                    bw.write("@SP\n");
                    bw.write("A=M\n");
                    bw.write("M=-1\n");
                    bw.write("(CONTINUE)\n");
                    bw.write("@SP\n");
                    bw.write("M=M+1\n");
                    // gt
                    break;
                case "lt":
                    bw.write("@SP\n");
                    bw.write("M=M-1\n");
                    bw.write("A=M\n");
                    bw.write("D=M\n");
                    bw.write("@SP\n");
                    bw.write("M=M-1\n");
                    bw.write("A=M\n");
                    bw.write("D=M-D\n");
                    bw.write("@TRUE\n");
                    bw.write("D;JLT\n");
                    bw.write("@SP\n");
                    bw.write("A=M\n");
                    bw.write("M=0\n");
                    bw.write("@CONTINUE\n");
                    bw.write("0;JMP\n");
                    bw.write("(TRUE)\n");
                    bw.write("@SP\n");
                    bw.write("A=M\n");
                    bw.write("M=-1\n");
                    bw.write("(CONTINUE)\n");
                    bw.write("@SP\n");
                    bw.write("M=M+1\n");
                    break;
                // lt
                case "and":

                    // and
                    break;
                case "or":
                    // or
                    break;
                case "not":
                    // not
                    break;
            }
        } catch (IOException e) {
            System.out.println("Error: " + e);
            System.out.println(e.getStackTrace());
        }
    }

    public void handlePush(String[] command) throws IOException {

        try {
            switch (command[1]) {
                case "constant":
                    bw.write("@" + command[2] + "\n");
                    bw.write("D=A\n");
                    bw.write("@SP\n");
                    bw.write("A=M\n");
                    bw.write("M=D\n");
                    bw.write("@SP\n");
                    bw.write("M=M+1\n");
                    break;
                case "local":
                    bw.write("@" + command[2] + "\n");
                    bw.write("D=A\n");
                    bw.write("@LCL\n");
                    bw.write("A=M+D\n");
                    bw.write("D=M\n");
                    bw.write("@SP\n");
                    bw.write("A=M\n");
                    bw.write("M=D\n");
                    bw.write("@SP\n");
                    bw.write("M=M+1\n");
                    break;
                case "argument":
                    bw.write("@" + command[2] + "\n");
                    bw.write("D=A\n");
                    bw.write("@ARG\n");
                    bw.write("A=M+D\n");
                    bw.write("D=M\n");
                    bw.write("@SP\n");
                    bw.write("A=M\n");
                    bw.write("M=D\n");
                    bw.write("@SP\n");
                    bw.write("M=M+1\n");
                    break;
                case "this":
                    bw.write("@" + command[2] + "\n");
                    bw.write("D=A\n");
                    bw.write("@THIS\n");
                    bw.write("A=M+D\n");
                    bw.write("D=M\n");
                    bw.write("@SP\n");
                    bw.write("A=M\n");
                    bw.write("M=D\n");
                    bw.write("@SP\n");
                    bw.write("M=M+1\n");
                    break;
                case "that":
                    bw.write("@" + command[2] + "\n");
                    bw.write("D=A\n");
                    bw.write("@THAT\n");
                    bw.write("A=M+D\n");
                    bw.write("D=M\n");
                    bw.write("@SP\n");
                    bw.write("A=M\n");
                    bw.write("M=D\n");
                    bw.write("@SP\n");
                    bw.write("M=M+1\n");
                    break;
                case "temp":
                    bw.write("@" + command[2] + "\n");
                    bw.write("D=A\n");
                    bw.write("@5\n");
                    bw.write("A=A+D\n");
                    bw.write("D=M\n");
                    bw.write("@SP\n");
                    bw.write("A=M\n");
                    bw.write("M=D\n");
                    bw.write("@SP\n");
                    bw.write("M=M+1\n");
                    break;
                case "pointer":
                    bw.write("@" + command[2] + "\n");
                    bw.write("D=A\n");
                    bw.write("@3\n");
                    bw.write("A=A+D\n");
                    bw.write("D=M\n");
                    bw.write("@SP\n");
                    bw.write("A=M\n");
                    bw.write("M=D\n");
                    bw.write("@SP\n");
                    bw.write("M=M+1\n");
                    break;
                case "static":
                    bw.write("@" + command[2] + "\n");
                    bw.write("D=A\n");
                    bw.write("@16\n");
                    bw.write("A=A+D\n");
                    bw.write("D=M\n");
                    bw.write("@SP\n");
                    bw.write("A=M\n");
                    bw.write("M=D\n");
                    bw.write("@SP\n");
                    bw.write("M=M+1\n");
                    break;
                default:
                    System.out.println("Error: " + command + " is not a valid command");
                    break;
            }
        } catch (IOException e) {
            System.out.println("Error: " + e);
            System.out.println(e.getStackTrace());
        }

    }

    public void handlePop(String[] command) throws IOException {

        try {
            bw.write("@SP\n");
            bw.write("M=M-1\n");
            bw.write("A=M\n");
            bw.write("D=M\n");
            bw.write("@SP\n");
            bw.write("M=M-1\n");
            bw.write("A=M\n");
            bw.write("M=D\n");
        } catch (IOException e) {
            System.out.println("Error: " + e);
            System.out.println(e.getStackTrace());
        }
    }

    public void handleLabel(String[] command) throws IOException {

        try {
            bw.write("(" + command[1] + ")\n");
        } catch (IOException e) {
            System.out.println("Error: " + e);
            System.out.println(e.getStackTrace());
        }

    }

    public void HandleGoto(String[] command) throws IOException {
        try {
            bw.write("@{label}\n".replace("{label}", command[1]) + "0;JMP\n");
        } catch (IOException e) {
            System.out.println("Error: " + e);
            System.out.println(e.getStackTrace());
        }
    }

    public void HandleIfGoto(String[] command) throws IOException {
        try {
            bw.write("@SP\n" +
                    "M=M-1\n" +
                    "A=M\n" +
                    "D=M\n" +
                    "@{label}\n".replace("{label}", command[1]) +
                    "D;JNE\n");
        } catch (IOException e) {
            System.out.println("Error: " + e);
            System.out.println(e.getStackTrace());
        }
    }
}
