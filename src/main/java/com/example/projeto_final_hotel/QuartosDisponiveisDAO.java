package com.example.projeto_final_hotel;

import javafx.collections.ObservableList;

import java.sql.*;

public class QuartosDisponiveisDAO {
    public static ObservableList<QuartosDisponiveis> listarQuartos() {
        Connection conn = ConexaoBD.openDB();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ObservableList<QuartosDisponiveis> quartosdisponiveis = Settings.getListaQuartos();
        String sql = "SELECT * FROM QuartosDisponiveis;";

    try{
        stmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery();
        while (rs.next()) {
            int idQuarto = rs.getInt("idQuarto");
            int numQuarto = rs.getInt("numQuarto");
            String tipoDeQuarto = rs.getString("tipo");
            String status = rs.getString("status");
            double preco = rs.getDouble("preco");
            QuartosDisponiveis q = new QuartosDisponiveis(idQuarto, numQuarto, tipoDeQuarto, status, preco);
            quartosdisponiveis.add(q);

        }
    }
    catch(SQLException ex){
            System.out.println("Erro ao listar os quartos" + ex);
        }finally{
            ConexaoBD.closeDB(stmt, rs);
        }
        return quartosdisponiveis;
    }

    public static void editarQuarto(QuartosDisponiveis q){
        Connection conn = ConexaoBD.openDB();
        PreparedStatement stmt = null;
        String sql = "UPDATE QuartosDisponiveis SET numQuarto = ?, tipo = ?, status = ?, preco = ?";

        try{
            stmt = conn.prepareStatement(sql);
                stmt.setInt(1, q.getNumQuarto());
                stmt.setString(2, q.getTipo());
                stmt.setString(3, q.getStatus());
                stmt.setDouble(4, q.getPreco());
                stmt.executeUpdate();
                System.out.println("Quarto atualizado com sucesso!");


        }catch (Exception e){
            System.out.println("Erro ao atualizar o Quarto");
        }
        finally {
            ConexaoBD.closeDB(stmt);
        }
    }

    public static void removerQuarto(int idQuarto){
        Connection conn = ConexaoBD.openDB();
        PreparedStatement stmt = null;
        String sql = "DELETE FROM QuartosDisponiveis WHERE idQuarto =?;";

        try{
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idQuarto);
            stmt.executeUpdate();
            System.out.println("Quarto removido com sucesso!");
        }
        catch(SQLException ex){
            System.out.println("Erro ao remover o quarto" + ex);
        }finally{
            ConexaoBD.closeDB();
        }
    }
}