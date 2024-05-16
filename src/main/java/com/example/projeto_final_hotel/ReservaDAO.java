package com.example.projeto_final_hotel;

import javafx.collections.ObservableList;

import java.sql.*;

public class ReservaDAO {
    public static ObservableList<Reserva> listarReserva() {
        Connection conn = ConexaoBD.openDB();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ObservableList<Reserva> reservas = Settings.getListaReserva();
        String sql = "SELECT * FROM reserva;";

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idReserva = rs.getInt("idReserva");
                int idQuarto = rs.getInt("idQuarto");
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                String email = rs.getString("email");
                Date checkIn = rs.getDate("checkIn");
                Date checkOut = rs.getDate("checkOut");
                Reserva r = new Reserva(idReserva, idQuarto, nome, telefone, email, checkIn, checkOut);
                reservas.add(r);
            }
        }
        catch (SQLException ex){
            System.out.println("Erro ao listar as reservas" + ex);
        }finally {
            ConexaoBD.closeDB(stmt, rs);
        }
        return reservas;
    }
}

