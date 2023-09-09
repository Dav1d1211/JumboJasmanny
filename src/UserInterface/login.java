// package UserInterface;

// import java.security.MessageDigest;
// import java.security.NoSuchAlgorithmException;
// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import javax.swing.JOptionPane;

// public class login {

//     private static final String jjURL = "jdbc:sqlite:C:\\PRG II\\JumboJasmanny\\DataBase\\jjExamenDatabase.db";

//     public static void main(String[] args) {
//         int attempts = 0;
//         boolean authenticated = false;

//         while (attempts < 3 && !authenticated) {
//             String jjUsername = JOptionPane.showInputDialog("Ingrese su nombre de usuario:");
//             String jjPassword = JOptionPane.showInputDialog("Ingrese su pasword:");
            
//             authenticated = jjAuthenticate(jjUsername, jjPassword);

//             if (authenticated) {
//                 JOptionPane.showMessageDialog(null, "Autenticacion exitosa.");
//             } else {
//                 JOptionPane.showMessageDialog(null, "Autenticacion fallida.");
//                 attempts++;
//             }
//         }

//         if (attempts == 3) {
//             JOptionPane.showMessageDialog(null, "Ha alcanzado el numero maximo de intentos.");
//         }
//     }

//     public static boolean jjAuthenticate(String jjUsername, String jjPassword) {
//         String jjQuery = "SELECT password FROM USUARIOS WHERE NombreUser = ?";
        
//         try (Connection jjConnection = DriverManager.getConnection(jjURL);
//              PreparedStatement jjPreparedStatement = jjConnection.prepareStatement(jjQuery)) {
            
//             jjPreparedStatement.setString(1, jjUsername);

//             try (ResultSet jjResultSet = jjPreparedStatement.executeQuery()) {
//                 if (jjResultSet.next()) {
//                     String dbPasswordHash = jjResultSet.getString("password");
//                     String inputPasswordHash = jjHashPassword(jjPassword);
//                     return dbPasswordHash.equals(inputPasswordHash);
//                 }
//             }
//         } catch (SQLException e) {
//             System.err.println("SQL Error: " + e.getMessage());
//         }

//         return false;
//     }

//     public static String jjHashPassword(String jjPassword) {
//         try {
//             MessageDigest jjMessageDigest = MessageDigest.getInstance("MD5");
//             jjMessageDigest.update(jjPassword.getBytes());
//             byte[] jjDigest = jjMessageDigest.digest();
//             StringBuilder jjStringBuilder = new StringBuilder();

//             for (byte jjB : jjDigest) {
//                 jjStringBuilder.append(String.format("%02x", jjB));
//             }

//             return jjStringBuilder.toString();
//         } catch (NoSuchAlgorithmException e) {
//             throw new RuntimeException("Error al obtener la instancia de MessageDigest para MD5", e);
//         }
//     }
// }

package UserInterface;

import javax.swing.JOptionPane;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class login {

    private static final String jjURL = "jdbc:sqlite:C:\\PRG II\\JumboJasmanny\\DataBase\\jjExamenDatabase.db";

    public static void jjLoginProcedure() {
        int attempts = 0;
        boolean authenticated = false;

        while (attempts < 3 && !authenticated) {
            String jjUsername = JOptionPane.showInputDialog("Ingrese su nombre de usuario:");
            String jjPassword = JOptionPane.showInputDialog("Ingrese su pasword:");
            
            authenticated = jjAuthenticate(jjUsername, jjPassword);

            if (authenticated) {
                JOptionPane.showMessageDialog(null, "Autenticacion exitosa.");
            } else {
                JOptionPane.showMessageDialog(null, "Autenticacion fallida.");
                attempts++;
            }
        }

        if (attempts == 3) {
            JOptionPane.showMessageDialog(null, "Ha alcanzado el numero maximo de intentos.");
        }
    }

    public static boolean jjAuthenticate(String jjUsername, String jjPassword) {
        String jjQuery = "SELECT password FROM USUARIOS WHERE NombreUser = ?";
        try (Connection jjConnection = DriverManager.getConnection(jjURL);
             PreparedStatement jjPreparedStatement = jjConnection.prepareStatement(jjQuery)) {
            
            jjPreparedStatement.setString(1, jjUsername);

            try (ResultSet jjResultSet = jjPreparedStatement.executeQuery()) {
                if (jjResultSet.next()) {
                    String dbPasswordHash = jjResultSet.getString("password");
                    String inputPasswordHash = jjHashPassword(jjPassword);
                    return dbPasswordHash.equals(inputPasswordHash);
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }

        return false;
    }

    public static String jjHashPassword(String jjPassword) {
       try {
            MessageDigest jjMessageDigest = MessageDigest.getInstance("MD5");
            jjMessageDigest.update(jjPassword.getBytes());
            byte[] jjDigest = jjMessageDigest.digest();
            StringBuilder jjStringBuilder = new StringBuilder();

            for (byte jjB : jjDigest) {
                jjStringBuilder.append(String.format("%02x", jjB));
            }

            return jjStringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al obtener la instancia de MessageDigest para MD5", e);
        }
    }
}

