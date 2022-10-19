
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Handler {

    final static String API_URL = "http://" + System.getenv("AWS_LAMBDA_RUNTIME_API") + "/2018-06-01";

    public static void main(String[] args) throws IOException {
        for (;;) {
            HttpURLConnection conn = (HttpURLConnection) new URL(API_URL + "/runtime/invocation/next").openConnection();
            conn.connect();
            byte[] request;

            try (InputStream is = conn.getInputStream()) {
                request = is.readAllBytes();
            }

            HttpURLConnection http = (HttpURLConnection) new URL(API_URL + "/runtime/invocation/" + conn.getHeaderField("Lambda-Runtime-Aws-Request-Id") + "/response").openConnection();
            http.setRequestMethod("POST");
            http.setDoOutput(true);
            http.connect();

            try (OutputStream os = http.getOutputStream()) {
                handle(request, os);
            }
            if (http.getResponseCode() >= 300) {
                throw new IllegalStateException("ERROR: statusCode = " + http.getResponseCode());
            }
            http.disconnect();
            conn.disconnect();
        }
    }

    private static void handle(byte[] request, OutputStream os) throws IOException {
        os.write(("{\"hello\": \"world\", \"request\": \"" + URLEncoder.encode(new String(request), "utf-8") + "\"}").getBytes());
    }

}
