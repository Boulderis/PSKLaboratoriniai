package ev.laborai.pirmaslab.services;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class NicknameSuggestingComponent implements Serializable {

    private static final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";
    private static final int size = 10;

    private CompletableFuture<String> suggesterTask;

    public String suggestNickname(String currentNickname) throws ExecutionException, InterruptedException {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        suggesterTask = CompletableFuture.supplyAsync(() -> suggestNick(currentNickname));
        return "/rider?faces-redirect=true&riderId=" + requestParameters.get("riderId");
    }

    public boolean isTaskRunning() {
        return suggesterTask != null && !suggesterTask.isDone();
    }

    public String getTaskStatus() throws ExecutionException, InterruptedException {
        if (suggesterTask == null) {
            return null;
        } else if (isTaskRunning()) {
            return "Nickname is being generated.";
        }
        return "Suggested nickname: " + suggesterTask.get();
    }

    public String suggestNick(String currentNickname) {
        try {
            Thread.sleep(1000);
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
