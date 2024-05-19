package com.example.projeto_final_hotel;

import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;

public class FuncionarioDAO {
    public static ObservableList<Funcionario> listarFuncionario() {
        // Estabelece uma ligação à base de dados
        Connection conn = ConexaoBD.openDB();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        // Obtém a lista de funcionários existente nos Settings
        ObservableList<Funcionario> funcionarios = Settings.getListaFuncionarios();

        // Define a consulta SQL para selecionar todos os funcionários
        String sql = "SELECT * FROM Funcionario;";

        try {
            // Prepara a consulta SQL, executa a consulta SQL e armazena os resultados,
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                // Obtém os valores das colunas do resultado da consulta
                int idFun = rs.getInt("idFun");
                String primeiroNome = rs.getString("primeiroNome");
                String segundoNome = rs.getString("segundoNome");
                LocalDate idade = rs.getDate("dataNascimento").toLocalDate();
                String cargo = rs.getString("cargo");

                // Cria um novo objeto Funcionario com os valores obtidos
                Funcionario f = new Funcionario(idFun, primeiroNome, segundoNome, idade, cargo);

                // Adiciona o objeto Funcionario à lista de funcionários
                funcionarios.add(f);
            }
        }
        catch (SQLException ex){
            // Em caso de exceção, imprime uma mensagem de erro
            System.out.println("Erro ao listar os funcionários" + ex);
        }finally {
            //fecha a conexão com a base de dados
            ConexaoBD.closeDB(stmt, rs);
        }
        return funcionarios;
        }

        public static void removerFuncionario(int idFun){
        // Estabelece uma ligação à base de dados
        Connection conn = ConexaoBD.openDB();
        PreparedStatement stmt = null;

        // Define a consulta SQL para remover um funcionário com base no id do funcionario
        String sql = "DELETE FROM Funcionario WHERE idFun = ?;";

        try{
            // Define a consulta SQL para remover um funcionário com base no seu ID
            stmt = conn.prepareStatement(sql);
            // Define o valor do parâmetro da consulta (idFun)
            stmt.setInt(1, idFun);
            // Define o valor do parâmetro da consulta (idFun)
            stmt.executeUpdate();
            System.out.println("Funcionario removido com sucesso!");
        }catch (Exception ex){
            // Em caso de exceção, imprime uma mensagem de erro
            System.out.println("Erro ao remover o Funcionario" + ex);
        }finally {
            //Fecha a conexão com a base dados
            ConexaoBD.closeDB();
        }
        }

    }
