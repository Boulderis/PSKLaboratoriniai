package ev.laborai.pirmaslab.services;

import java.io.Serializable;
import java.util.Random;

public class ShortNicknameSuggester implements NicknameSuggester, Serializable {

    @Override
    public String suggestNick(String currentNickname) {
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {}
        StringBuilder generatedNickname = new StringBuilder(currentNickname);
        Random random = new Random();
        String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";
        for(int i = 0; i < 10; i++) {
            int chosenIndex = random.nextInt(lexicon.length());
            char chosenLetter = lexicon.charAt(chosenIndex);
            generatedNickname.append(chosenLetter);
        }
        return generatedNickname.toString();
    }

}
