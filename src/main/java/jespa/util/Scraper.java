package jespa.util;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.lang.reflect.Array;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Scraper {
    private final String url = "https://en.wiktionary.org/wiki/Wiktionary:Frequency_lists/Spanish/Subtitles10K";
	HttpResponse<InputStream> response;
	DocumentBuilderFactory dbf;
	DocumentBuilder db;
	Document html;
	public Scraper(){

    }

    public void upgradeWords() throws IOException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request;
        try {
            request = HttpRequest.newBuilder(
                    new URI(url)).build();
			response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
			dbf = DocumentBuilderFactory.newDefaultInstance();
			db = dbf.newDocumentBuilder();
			html = db.parse(response.body());
        } catch (URISyntaxException | IOException | ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
			System.out.println("http request interrupted: " + e);
		} catch (SAXException e) {
			cleanHTML(response.body());
		}
	}

	private InputStream cleanHTML(InputStream input) throws IOException {
		String html = streamAString(input);
		StringReader reader = new StringReader(html);
		List<String> tokens = new ArrayList<>();
		List<Character> token = new ArrayList<>();
		boolean inToken = false;
		int counter = 0;

		while (reader.ready()){
			reader.skip(counter);
			char character = (char)reader.read();
			if (character == '<'){
				inToken = true;
			}

			if (inToken){
				token.add(character);
				if (character == '>'){
					inToken = false;
					tokens.add(token.stream().map(e -> e.toString()).collect(Collectors.joining()));
					token.clear();
				}
			}
			counter++;
			reader.mark(counter);
		}
		return input;
	}

	private String streamAString(InputStream input) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		StringBuilder string  = new StringBuilder();
		while (reader.ready()){
			string.append(reader.readLine()).append("\n");
		}

		return string.toString();
	}
}
