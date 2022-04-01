package ev.laborai.pirmaslab.services;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.Random;

@ApplicationScoped
public class NicknameSuggester implements Serializable {

    private static final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";
    private static final int size = 10;

    public String suggestNickname(String currentNickname) {
        try {
            Thread.sleep(5000);
        } catch(InterruptedException e) {}
        StringBuilder generatedNickname = new StringBuilder(currentNickname);
        Random random = new Random();
        for(int i = 0; i < size; i++) {
            int chosenIndex = random.nextInt(lexicon.length());
            char chosenLetter = lexicon.charAt(chosenIndex);
            generatedNickname.append(chosenLetter);
        }
        return generatedNickname.toString();
    }

}
