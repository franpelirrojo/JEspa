package jespa.util;

import org.w3c.dom.Document;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class Scraper {
    private final String url = "https://en.wiktionary.org/wiki/Wiktionary:Frequency_lists/Spanish/Subtitles10K";
    private Document html;
    public Scraper(){

    }

    public void upgradeWords() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request;
        try {
            request = HttpRequest.newBuilder(
                    new URI(url)).build();
            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenAccept(System.out::println).join();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }


    }
}
