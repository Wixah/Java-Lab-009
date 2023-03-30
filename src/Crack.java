/**
 * @author Trevor Hartman
 * @author Rachelle Iloff
 * created 3/29/2023
 * @since version 1.0
 */
import org.apache.commons.codec.digest.Crypt;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.stream.Stream;



public class Crack {
    private final User[] users;
    public String dictionary;

    public Crack(String shadowFile, String dictionary) throws FileNotFoundException {
        this.dictionary = dictionary;
        this.users = Crack.parseShadow(shadowFile);
    }
    //## PART 3 - Implement method crack
    public void crack() throws FileNotFoundException {
        FileInputStream in = new FileInputStream(this.dictionary);
        Scanner dictFile = new Scanner(in);
        String[] words = new String[getLineCount(this.dictionary)];

        for (int i = 0; i < getLineCount(this.dictionary); i++) {
            words[i] = dictFile.nextLine();
            for (User user : this.users) {
                if (user.getPassHash().contains("$")) {
                    String hash = Crypt.crypt(words[i], user.getPassHash());
                    if (user.getPassHash().equals(hash)) {
                        System.out.printf("Found %s for user %s.\n", words[i], user.getUsername());
                    }
                }
            }
        }
    }
    public static int getLineCount(String path) {
        int lineCount = 0;
        try (Stream<String> stream = Files.lines(Path.of(path), StandardCharsets.UTF_8)) {
            lineCount = (int)stream.count();
        } catch(IOException ignored) {}
        return lineCount;
    }

    //## PART 2 - Implement method parseShadow
    public static User[] parseShadow(String shadowFile) throws FileNotFoundException {
        FileInputStream in = new FileInputStream(shadowFile);
        Scanner shadFile = new Scanner(in);
        User[] users = new User[getLineCount(shadowFile)];

        int i = 0;
        while (shadFile.hasNextLine()) {
            String[] temp = shadFile.nextLine().split(":", 3);
            User newUser = new User(temp[0], temp[1]);
            users[i] = newUser;
            i++;
        }
        return users;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Type the path to your shadow file: ");
        String shadowPath = sc.nextLine();
        System.out.print("Type the path to your dictionary file: ");
        String dictPath = sc.nextLine();

        Crack c = new Crack(shadowPath, dictPath);
        c.crack();
    }
}
