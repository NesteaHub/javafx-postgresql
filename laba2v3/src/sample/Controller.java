package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import javax.swing.*;
import javax.swing.text.TabableView;
import java.awt.*;
import java.sql.*;

public class Controller {
    private ObservableList<zapr1> z1, z2, z3, z4;
    @FXML
    private Pane forma = new Pane();
    @FXML
    private Button but1 = new Button("Запрос 1");
    @FXML
    private TableView<zapr1> tab1;
    @FXML
    private TableColumn<zapr1, String> col1;
    @FXML
    private TableColumn<zapr1, String> col2;
    @FXML
    private TextField tf1, tf2, tf3, tf4, tf5;

    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/laba(2)";
    static final String USER = "postgres";
    static final String PASS = "1";

    private static Connection get_con() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            return connection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    private void zap1() throws SQLException {
        z1 = FXCollections.observableArrayList();
        Connection connection = get_con();
        Statement statement = connection.createStatement();
        String select1 = "SELECT tovary.naim, magaziny.nazvanie FROM tovary, magaziny WHERE magaziny.id = \n" +
                "(SELECT magaz_id FROM otdely WHERE otdely.id = tovary.otdel_id)\n";
        ResultSet rs1 = statement.executeQuery(select1);
        while (rs1.next()) {
            zapr1 zapis = new zapr1();
            zapis.setNaim(rs1.getString("naim"));
            zapis.setNazvanie(rs1.getString("nazvanie"));
            z1.add(zapis);
        }
        col1.setCellValueFactory(new PropertyValueFactory<zapr1, String>("naim"));
        col1.setText("naim");
        col2.setCellValueFactory(new PropertyValueFactory<zapr1, String>("nazvanie"));
        col2.setText("nazvanie");
        tab1.setItems(z1);
    }

    private void zap2() throws SQLException {
        z2 = FXCollections.observableArrayList();
        Connection connection = get_con();
        Statement statement = connection.createStatement();
        String select2 = "SELECT naim, kolvo FROM tovary WHERE otdel_id = 2";
        ResultSet rs2 = statement.executeQuery(select2);
        while (rs2.next()) {
            zapr1 zapis = new zapr1();
            zapis.setNaim(rs2.getString("naim"));
            zapis.setKolvo(rs2.getString("kolvo"));
            z2.add(zapis);
        }
        col1.setCellValueFactory(new PropertyValueFactory<zapr1, String>("naim"));
        col1.setText("naim");
        col2.setCellValueFactory(new PropertyValueFactory<zapr1, String>("kolvo"));
        col2.setText("kolvo");
        tab1.setItems(z2);
    }

    private void zap3() throws SQLException {
        z3 = FXCollections.observableArrayList();
        Connection connection = get_con();
        Statement statement = connection.createStatement();
        String select3 = "SELECT magaziny.nazvanie, otdely.FIO_zaved FROM magaziny, otdely WHERE otdely.magaz_id = magaziny.id";
        ResultSet rs3 = statement.executeQuery(select3);
        while (rs3.next()) {
            zapr1 zapis = new zapr1();
            zapis.setNazvanie(rs3.getString("nazvanie"));
            zapis.setFio_zaved(rs3.getString("fio_zaved"));
            z3.add(zapis);
        }
        col1.setCellValueFactory(new PropertyValueFactory<zapr1, String>("nazvanie"));
        col1.setText("nazvanie");
        col2.setCellValueFactory(new PropertyValueFactory<zapr1, String>("fio_zaved"));
        col2.setText("fio_zaved");
        tab1.setItems(z3);
    }

    private void zap4() throws SQLException {
        z4 = FXCollections.observableArrayList();
        Connection connection = get_con();
        Statement statement = connection.createStatement();
        String select4 = "WITH duplicate AS (SELECT naim, COUNT (*) AS c FROM tovary GROUP BY naim HAVING COUNT(*) > 1)\n" +
                "SELECT otdel_id FROM tovary WHERE naim IN (SELECT naim FROM duplicate)";
        ResultSet rs4 = statement.executeQuery(select4);
        while (rs4.next()) {
            zapr1 zapis = new zapr1();
            zapis.setOtdel_id(rs4.getString("otdel_id"));
            z4.add(zapis);
        }
        col1.setCellValueFactory(new PropertyValueFactory<zapr1, String>("otdel_id"));
        col1.setText("otdel_id");
        col2.setText("");
        tab1.setItems(z4);
    }

    private void addtov() throws SQLException {
        Connection connection = get_con();
        Statement statement = connection.createStatement();
        String s1 = tf1.getText();
        String s2 = tf2.getText();
        String s3 = tf3.getText();
        String s4 = tf4.getText();
        String s5 = tf5.getText();
        String insert = "INSERT INTO tovary (naim, cena, sort, kolvo, otdel_id) VALUES ('"
                + s1 + "', '" + s2 + "', '" + s3 + "', '" + s4 + "', '" + s5 + "');";
        statement.executeUpdate(insert);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Adding");
        alert.setHeaderText(null);
        alert.setContentText("Successful");
        alert.showAndWait();
    }

    @FXML
    public void onClick1(ActionEvent actionEvent) {
        try {
            zap1();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onClick2(ActionEvent actionEvent) {
        try {
            zap2();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onClick3(ActionEvent actionEvent) {
        try {
            zap3();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onClick4(ActionEvent actionEvent) {
        try {
            zap4();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onClick5(ActionEvent actionEvent) {
        try {
            addtov();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
