package BussinesLogic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;

public class JJHorarios {

    private static final String URL = "jdbc:sqlite:C:\\PRG II\\JumboJasmanny\\DataBase\\jjExamenDatabase.db"; // Reemplaza con tu ruta

    public static void insert(String dia, String hora) {
        String sql = "INSERT INTO JJ_HORARIOS(Dia, Hora) VALUES(?, ?)";
        
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, dia);
            pstmt.setString(2, hora);
            pstmt.executeUpdate();
            System.out.println("Datos insertados correctamente en JJ_HORARIOS.");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al insertar datos en JJ_HORARIOS.");
        }
    }

    public static void jjhorarios() {
        Path pathToFile = Paths.get("C:\\PRG II\\JumboJasmanny\\src\\Arsenall\\JumboJamannyArsenal.csv"); // Reemplaza con la ruta a tu archivo CSV
        try {
            List<String> lines = Files.readAllLines(pathToFile, StandardCharsets.ISO_8859_1);
            for (String line : lines.subList(1, lines.size())) {
                String[] attributes = line.split(";");
                
                // Aquí deberás añadir lógica adicional para determinar el día y la hora basado en tus necesidades.
                if (attributes.length >= 8) {
                    insert("Lunes", attributes[2].trim()); 
                    //... haz esto para cada día de la semana.
                } else {
                    System.err.println("Línea malformada: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
