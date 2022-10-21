package org.noframework;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.codec.binary.Base64;

public class Handler {

    public void handle(byte[] request, OutputStream os) throws IOException {
        try {
            // here we demonstrate the use of an external dependency (Commons Codec)
            String computation = Base64.encodeBase64String(request);

            // important: the response must be valid json
            String response = String.format("{\"hello\": \"world\", \"request\": \"%s\"}", computation);
            os.write(response.getBytes());
        }
        catch(Exception e) {
            e.printStackTrace(System.err);
        }
    }
}
