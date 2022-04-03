package ev.laborai.pirmaslab.services;

import javax.enterprise.inject.Specializes;
import java.util.Random;

@Specializes
public class ShortLatinNicknameSuggester extends ShortNicknameSuggester {

    @Override
    public String suggestNick(String currentNickname) {
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {}
        StringBuilder generatedNickname = new StringBuilder(currentNickname);
        Random random = new Random();
        String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for(int i = 0; i < 10; i++) {
            int chosenIndex = random.nextInt(lexicon.length());
            char chosenLetter = lexicon.charAt(chosenIndex);
            generatedNickname.append(chosenLetter);
        }
        return "Specializes" + generatedNickname.toString();
    }

}
