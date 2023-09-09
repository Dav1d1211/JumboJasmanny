package BussinesLogic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.io.IOException;
import java.util.List;

public class JJCoordenadas {
    
    private static final String URL = "jdbc:sqlite:C:\\PRG II\\JumboJasmanny\\DataBase\\jjExamenDatabase.db"; // Reemplaza con tu ruta
    
    public static void insert(String geoposicion) {
        String sql = "INSERT INTO JJ_COORDENADAS(Geoposicion) VALUES(?)";
        
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, geoposicion);
            pstmt.executeUpdate();
            System.out.println("Datos de geoposición " + geoposicion + " insertados exitosamente.");
        } catch (Exception e) {
            System.err.println("No se pudo insertar los datos: " + e.getMessage());
        }
    }

    public static void jjcoordenadas() {
        Path pathToFile = Paths.get("C:\\PRG II\\JumboJasmanny\\src\\Arsenall\\JumboJamannyArsenal.csv"); // Reemplaza con la ruta a tu archivo CSV
        try {
            List<String> lines = Files.readAllLines(pathToFile, StandardCharsets.ISO_8859_1);
            for (String line : lines.subList(1, lines.size())) {
                String[] attributes = line.split(";");
    
                // Verifica que la línea tenga al menos 2 elementos antes de intentar acceder al índice 1
                if (attributes.length >= 2) {
                    insert(attributes[1].trim());
                } else {
                    System.err.println("Línea malformada: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }
    
}