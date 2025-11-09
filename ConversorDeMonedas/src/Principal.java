import java.util.ArrayList;
import java.util.Scanner;
import java.util.Locale;
import java.text.DecimalFormat;

public class Principal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConsultarCambio api = new ConsultarCambio();
        ArrayList<String> historial = new ArrayList<>();

        DecimalFormat df = new DecimalFormat("#,##0.00");

        int opcion = 0;

        while (opcion != 7) {
            System.out.println("\n*****   Conversor de Monedas   *****");
            System.out.println("===============================================");
            System.out.println("1. Cambio de USD a PYG");
            System.out.println("2. Cambio de PYG a USD");
            System.out.println("3. Cambio de ARS a USD");
            System.out.println("4. Cambio de USD a ARS");
            System.out.println("5. Cambio de USD a EUR");
            System.out.println("6. Cambio de EUR a USD");
            System.out.println("7. Salir");
            System.out.println("8. Historial de conversiones");
            System.out.println("===============================================\n");
            System.out.print("Ingrese una opción: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
                scanner.next();
                continue;
            }
            opcion = scanner.nextInt();

            if (opcion >= 1 && opcion <= 6) {
                System.out.print("Ingrese el monto a convertir: ");

                if (!scanner.hasNextDouble()) {
                    System.out.println("Monto no válido. Intente de nuevo.");
                    scanner.next();
                    continue;
                }

                double amountToConvert = scanner.nextDouble();

                String base_code = "";
                String target_code = "";

                switch (opcion) {
                    case 1 -> { base_code = "USD"; target_code = "PYG"; }
                    case 2 -> { base_code = "PYG"; target_code = "USD"; }
                    case 3 -> { base_code = "ARS"; target_code = "USD"; }
                    case 4 -> { base_code = "USD"; target_code = "ARS"; }
                    case 5 -> { base_code = "USD"; target_code = "EUR"; }
                    case 6 -> { base_code = "EUR"; target_code = "USD"; }
                }

                try {
                    Moneda resultado = api.consultarConversion(base_code, target_code, amountToConvert);

                    if (resultado != null && resultado.result().equalsIgnoreCase("success")) {

                        double finalResult = resultado.conversion_result();

                        String resultadoTexto = df.format(amountToConvert) + " " + base_code +
                                " equivalen a " + df.format(finalResult) + " " + target_code;

                        System.out.println("-----------------------------------------------");
                        System.out.println("Tasa de conversión: 1 " + base_code + " = " + df.format(resultado.conversion_rate()) + " " + target_code);

                        System.out.println("El cambio es de: " + df.format(finalResult) + " " + target_code);

                        System.out.println(resultadoTexto);
                        System.out.println("-----------------------------------------------");

                        historial.add(resultadoTexto);
                    } else {
                        System.err.println("Error: No se pudo completar la conversión. Revise los códigos de moneda.");
                    }

                } catch (RuntimeException e) {
                    System.err.println("Error al realizar la conversión: " + e.getMessage());
                }

            } else if (opcion == 8) {
                System.out.println("===============================================");
                System.out.println("********** Historial de Conversiones **********");

                if (historial.isEmpty()) {
                    System.out.println("Aún no se han realizado conversiones.");
                } else {
                    for (String r: historial) {
                        System.out.println("- " + r);
                    }
                }
                System.out.println("===============================================");

            } else if (opcion != 7) {
                System.out.println("Opción inválida. Intente nuevamente.");
            }
        }

        System.out.println("Gracias por usar el conversor. ¡Hasta luego!");
        scanner.close();
    }
}