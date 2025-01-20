import java.net.HttpURLConnection;
import java.net.URL;

public class TestSOAPConnection {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://localhost:8080/PiadineriaAdvancia/api/soap/piadinas");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Connessione al servizio SOAP riuscita.");
            } else {
                System.out.println("Errore nella connessione. Codice di risposta: " + responseCode);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}