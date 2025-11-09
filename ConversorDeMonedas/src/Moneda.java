import java.util.Map;

// Este record ahora refleja la respuesta del endpoint /pair/...
public record Moneda(
        String result,
        String base_code,
        String target_code,
        double conversion_rate,
        double conversion_result
) {
}