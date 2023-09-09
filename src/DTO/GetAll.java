package DTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GetAll {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TableExample().setVisible(true);
        });
    }
}

interface jjDataProvider {
    Object[][] getAll();
}

class MockDataProvider implements jjDataProvider {
    @Override
    public Object[][] getAll() {
        return new Object[][]{
                {"profe", "Aereo", "01", "Avion", "lunes", "01-02"},
                {"profe", "Aereo, Martimo, Terrestre", "07", "Avion, Barco, Convoy, Dron", "Miercoles", "03-06"},
                {"profe", "Aereo, Maritimo", "02", "Avion, Barco", "Martes", "02-04"},
                {"profe", "Aereo, Martimo, Terrestre", "05", "Avion, Barco, Convoy, Dron, Tanque", "Viernes", "05-07"},
                {"profe", "Aereo, Martimo, Terrestre", "04", "Avion, Barco, Convoy, Dron, Tanque, Avion, Barco, Convoy, Dron, Tanque, ", "Jueves", "04-08"},
                {"profe", "Aereo, Martimo, Terrestre", "07", "Avion, Barco, Convoy, Dron", "Miercoles", "03-06"},
                {"profe", "Aereo, Martimo, Terrestre", "04", "Avion, Barco, Convoy, Dron, Tanque, Avion, Barco, Convoy, Dron, Tanque, ", "Jueves", "04-08"},
                {"profe", "Aereo, Martimo, Terrestre", "04", "Avion, Barco, Convoy, Dron, Tanque, Avion, Barco, Convoy, Dron, Tanque, ", "Jueves", "04-08"},
                {"profe", "Aereo, Martimo, Terrestre", "04", "Avion, Barco, Convoy, Dron, Tanque, Avion, Barco, Convoy, Dron, Tanque, ", "Jueves", "04-08"},
                {"profe", "Aereo, Martimo, Terrestre", "07", "Avion, Barco, Convoy, Dron", "Miercoles", "03-06"},
        };
    }
}

class TableExample extends JFrame {
    private JTable table;
    private jjDataProvider dataProvider;

    public TableExample() {
        dataProvider = new MockDataProvider();

        setTitle("Tabla de Datos");
        setSize(600, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        table = new JTable();
        table.setModel(new DefaultTableModel(
                dataProvider.getAll(),
                new String[]{"Usuario", "Tipo Arsenal", "Coord. Arsenal", "Dia", "Hora"}
        ));

        add(new JScrollPane(table), BorderLayout.CENTER);
    }
}
