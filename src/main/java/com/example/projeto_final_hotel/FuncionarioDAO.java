package com.example.projeto_final_hotel;

import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;

public class FuncionarioDAO {
    public static ObservableList<Funcionario> listarFuncionario() {
        Connection conn = ConexaoBD.openDB();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ObservableList<Funcionario> funcionarios = Settings.getListaFuncionarios();
        String sql = "SELECT * FROM Funcionario;";

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idFun = rs.getInt("idFun");
                String primeiroNome = rs.getString("primeiroNome");
                String segundoNome = rs.getString("segundoNome");
                LocalDate idade = rs.getDate("dataNascimento").toLocalDate();
                String cargo = rs.getString("cargo");
                Funcionario f = new Funcionario(idFun, primeiroNome, segundoNome, idade, cargo);
                funcionarios.add(f);
            }
        }
        catch (SQLException ex){
            System.out.println("Erro ao listar os funcionários" + ex);
        }finally {
            ConexaoBD.closeDB(stmt, rs);
        }
        return funcionarios;
        }
    }