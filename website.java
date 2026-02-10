import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Website {
    public static void main(String[] args) throws IOException {
        // Berjalan di port 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        
        // Handler untuk file HTML
        server.createContext("/", (exchange) -> {
            byte[] response = Files.readAllBytes(Paths.get("index.html"));
            exchange.sendResponseHeaders(200, response.length);
            OutputStream os = exchange.getResponseBody();
            os.write(response);
            os.close();
        });

        // Handler untuk file CSS
        server.createContext("/style.css", (exchange) -> {
            byte[] response = Files.readAllBytes(Paths.get("style.css"));
            exchange.getResponseHeaders().set("Content-Type", "text/css");
            exchange.sendResponseHeaders(200, response.length);
            OutputStream os = exchange.getResponseBody();
            os.write(response);
            os.close();
        });

        System.out.println("Server jalan di http://localhost:8080");
        server.start();
    }
}