package ev.laborai.pirmaslab.services;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class NicknameSuggestingComponent implements Serializable {

    @Inject
    private NicknameSuggester nicknameSuggester;

    private CompletableFuture<String> suggesterTask;

    public String suggestNickname(String currentNickname) throws ExecutionException, InterruptedException {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        suggesterTask = CompletableFuture.supplyAsync(() -> nicknameSuggester.suggestNick(currentNickname));
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

}
