import java.io.File;
import java.util.Scanner;

public class FileReader {

    private static String[] str;
    private static int lineCount = 0;

    public static String[] readFile (String fileName) {

        try {

            File file = new File(fileName);
            Scanner input = new Scanner(file);

            while (input.hasNextLine()) {
                lineCount++;
                input.nextLine(); //IF THIS LINE IS OMITTED, INFINITE LOOP HAPPENS, BECAUSE SCANNER DOESNT MOVE TO NEXT LINE
            }

            input.close();

            str = new String[lineCount];

            Scanner input2 = new Scanner(file);

            for (int i = 0; i < str.length; i++)
                str[i] = input2.nextLine();

            input2.close();
            return str;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return str;
    }

    }

