import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultarCambio {

    private static final String API_KEY = "5c7f86dda6d0c605cb584753";


    public Moneda consultarConversion(String base_code, String target_code, double amount){

        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/"
                + base_code + "/" + target_code + "/" + amount);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new IOException("La API devolvió un error (código: " + response.statusCode() + "). Respuesta: " + response.body());
            }

            return new Gson().fromJson(response.body(), Moneda.class);

        } catch (IOException | InterruptedException | JsonSyntaxException e) {

            System.err.println("Error interno al consultar la API: " + e.getMessage());
            throw new RuntimeException("No se pudo completar la consulta. Verifique la conexión o el formato de la respuesta.", e);
        }
    }
}