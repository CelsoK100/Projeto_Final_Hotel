package com.example.projeto_final_hotel;

import javafx.collections.ObservableList;

import java.sql.*;

public class QuartosDisponiveisDAO {
    // Abre uma conexão com a base de dados
    public static ObservableList<QuartosDisponiveis> listarQuartos() {
        Connection conn = ConexaoBD.openDB();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        // Obtém a lista dos Quartos a partir dos Settings
        ObservableList<QuartosDisponiveis> quartosdisponiveis = Settings.getListaQuartos();
        // SQL para selecionar todos os quartos disponíveis
        String sql = "SELECT * FROM QuartosDisponiveis;";


    try{
        // Prepara a declaração SQL
        stmt = conn.prepareStatement(sql);

        // Executa a consulta e obtém o resultado
        rs = stmt.executeQuery();

        while (rs.next()) {
            // Extrai os dados do resultado
            int idQuarto = rs.getInt("idQuarto");
            int numQuarto = rs.getInt("numQuarto");
            String tipoDeQuarto = rs.getString("tipo");
            String status = rs.getString("status");
            double preco = rs.getDouble("preco");

            // Cria um novo objeto QuartosDisponiveis com os dados extraídos
            QuartosDisponiveis q = new QuartosDisponiveis(idQuarto, numQuarto, tipoDeQuarto, status, preco);
            // Adiciona o objeto na lista dos quartos disponíveis
            quartosdisponiveis.add(q);

        }
    }
    catch(SQLException ex){
        // Em caso de erro na consulta, imprime uma mensagem de erro
            System.out.println("Erro ao listar os quartos" + ex);
        }finally{
            //Fecha a conexão com a base de dados
            ConexaoBD.closeDB(stmt, rs);
        }
        //Retorna a lista dos quartos
        return quartosdisponiveis;
    }

    public static void removerQuarto(int idQuarto){
        // Abre uma conexão com a base de dados
        Connection conn = ConexaoBD.openDB();
        PreparedStatement stmt = null;

        // SQL para eliminar um quarto em específico
        String sql = "DELETE FROM QuartosDisponiveis WHERE idQuarto =?;";

        try{
            // Prepara a declaração SQL
            stmt = conn.prepareStatement(sql);

            // Define o parâmetro da declaração SQL
            stmt.setInt(1, idQuarto);

            // Executa a eliminação
            stmt.executeUpdate();
            System.out.println("Quarto removido com sucesso!");
        }
        catch(SQLException ex){
            // Em caso de erro na eliminação, imprime uma mensagem de erro
            System.out.println("Erro ao remover o quarto" + ex);
        }finally{
            //Fecha a conexão com a base de dados
            ConexaoBD.closeDB();
        }
    }

    public static boolean quartoAssociado(int numQuarto){
        // Estabelece uma ligação à base de dados
        Connection conn = ConexaoBD.openDB();
        PreparedStatement stmt =null;
        ResultSet rs = null;

        try{
            //Codico SQL para verificar se o quarto esta associado a uma reserva
            String sql = "SELECT COUNT(*) FROM reserva WHERE numQuarto=?;";
            stmt = conn.prepareStatement(sql);

            // Define o parâmetro numQuarto na consulta SQL
            stmt.setInt(1,numQuarto);

            // Executa a consulta
            rs = stmt.executeQuery();

            // Verifica se a consulta retornou algum resultado
            if (rs.next()){
                // Obtém o número de reservas associadas ao quarto
                int conta = rs.getInt(1);

                // Retorna true se o número de reservas for maior que 0, indicando que o quarto está associado a uma reserva
                return conta > 0;
            }
        } catch (Exception e){
            // Imprime os erros em caso de exceção
            e.printStackTrace();
        }finally {
            // Fecha o PreparedStatement se não for nulo
            if(stmt != null){
                try{
                    stmt.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            // Fecha o ResultSet se não for nulo
            if(rs != null){
                try{
                    // Imprime erros em caso de exceção ao fechar o ResultSet
                    rs.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        // Retorna false se nenhuma associação for encontrada
        return false;
    }
}
