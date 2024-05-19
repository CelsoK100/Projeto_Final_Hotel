package com.example.projeto_final_hotel;

import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;

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
                int idQuarto = rs.getInt("numQuarto");
                String tipo = rs.getString("tipo");
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                String email = rs.getString("email");
                LocalDate checkIn = rs.getDate("dataEntrada").toLocalDate();
                LocalDate checkOut = rs.getDate("dataSaida").toLocalDate();
                Reserva r = new Reserva(idReserva, idQuarto,tipo,nome, telefone, email, checkIn, checkOut);
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

    public static void removerReserva(int idReserva){
    Connection conn = ConexaoBD.openDB();
    PreparedStatement stmt = null;

    String sql = "DELETE FROM reserva WHERE idReserva =?;";
    try{
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1,idReserva);
        stmt.executeUpdate();
        System.out.println("Reserva removida com sucesso!");
    }catch (Exception e){
     System.out.println("ERRO ao remover quarto");
    }finally {
        ConexaoBD.closeDB();
    }

    }
}

