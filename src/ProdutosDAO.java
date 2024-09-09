
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;


public class ProdutosDAO {

    public static void cadastrarProduto(ProdutosDTO produto) {
        try{
            //conexao com BD
            conectaDAO conecta = new conectaDAO();
            conecta.conectarDB();
            
            //instrução SQL que será executada
            String sql = "INSERT INTO produtos(nome, valor, status) VALUES(?,?,?);";
            
            //usar String e transformar em SQL com PreparedStatement
            PreparedStatement query = conecta.conectDB().prepareStatement(sql);
            query.setString(1, produto.getNome());
            query.setString(2, Integer.toString(produto.getValor()));
            query.setString(3, produto.getStatus());

            //executar a instrução SQL
            query.execute();
            
            //desconectar BD
            conecta.desconectarDB();
        } catch(SQLException sqle){
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar registro no banco de dados");
        }    
    }

    public static ArrayList<ProdutosDTO> listarProdutos() {
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();
        
        try{
            //conectar ao BD
            conectaDAO conecta = new conectaDAO();
            conecta.conectarDB();

            //Instrução SQL
            String sql = "SELECT * FROM produtos";
            PreparedStatement busca = conecta.conectDB().prepareStatement(sql);

            //Executar a instrução SQL e pegar os resultados
            //ResultSet -> Classe do Java que armazena os resultados de uma QUERY (busca) feita em SQL
            ResultSet resposta = busca.executeQuery(sql);

            while (resposta.next()) {
                ProdutosDTO produto = new ProdutosDTO();

                produto.setId(resposta.getInt("id"));
                produto.setNome(resposta.getString("nome"));
                produto.setValor(resposta.getInt("valor"));
                produto.setStatus(resposta.getString("status"));
                listagem.add(produto);
            }
            //Desconectar do banco
            conecta.desconectarDB();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar os registros do banco de dados!");            
        }
        return listagem;    
    }
    
        public static ProdutosDTO buscarId(int id) {
        ProdutosDTO produto = new ProdutosDTO();

        try {
            conectaDAO conecta = new conectaDAO();
            conecta.conectarDB();

            //Instrução SQL
            String sql = "SELECT * FROM produtos WHERE id=?";
            PreparedStatement busca = conecta.conectDB().prepareStatement(sql);
            busca.setInt(1, id);
            
            //Executar a instrução SQL
            ResultSet resposta = busca.executeQuery(sql);
            
            if (resposta.next()) {
                produto.setId(resposta.getInt("id"));
                produto.setNome(resposta.getString("nome"));
                produto.setValor(resposta.getInt("valor"));
                produto.setStatus(resposta.getString("status"));
            }
            
            //Desconectar do banco
            conecta.desconectarDB();
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar o registro " + id + " no banco de dados");            
        }
        return produto;
    }
    
    public static boolean venderProduto(ProdutosDTO produto) {
        try{
            //conexao com BD
            conectaDAO conecta = new conectaDAO();
            conecta.conectarDB();
            
            //instrução SQL que será executada
            String sql = "UPDATE produtos SET status=? WHERE id=?;";
            
            //usar String e transformar em SQL com PreparedStatement
            PreparedStatement busca = conecta.conectDB().prepareStatement(sql);
            busca.setString(1, produto.getStatus());

            //executar a instrução SQL
            busca.execute();
            
            //desconectar BD
            conecta.desconectarDB();
            return true;
        } catch(SQLException sqle){
            JOptionPane.showMessageDialog(null, "Erro ao registrar a venda no banco de dados");
            return false;
        }    
    }
    
    public static void buscarStatus(ProdutosDTO produto) {
        try {
            conectaDAO conecta = new conectaDAO();
            conecta.conectarDB();

            //Instrução SQL
            String sql = "SELECT * FROM produto WHERE status='vendido'";
            PreparedStatement busca = conecta.conectDB().prepareStatement(sql);            
            busca.setString(1, "vendido");
            
            //Executar a instrução SQL
            ResultSet resposta = busca.executeQuery(sql);
            
            if (resposta.next()) {
                produto.setId(resposta.getInt("id"));
                produto.setNome(resposta.getString("nome"));
                produto.setValor(resposta.getInt("valor"));
                produto.setStatus(resposta.getString("status"));
            }
            
            //Desconectar do banco
            conecta.desconectarDB();
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar o registro no banco de dados");            
        }
    }

    public static ArrayList<ProdutosDTO> listarProdutosVendidos() {
        ArrayList<ProdutosDTO> listagemVendido = new ArrayList<>();
        
        try{
            //conectar ao BD
            conectaDAO conecta = new conectaDAO();
            conecta.conectarDB();

            //Instrução SQL
            String sql = "SELECT * FROM produtos where status='vendido'";
            PreparedStatement busca = conecta.conectDB().prepareStatement(sql);

            //Executar a instrução SQL e pegar os resultados
            //ResultSet -> Classe do Java que armazena os resultados de uma QUERY (busca) feita em SQL
            ResultSet resposta = busca.executeQuery(sql);

            while (resposta.next()) {
                ProdutosDTO produto = new ProdutosDTO();

                produto.setId(resposta.getInt("id"));
                produto.setNome(resposta.getString("nome"));
                produto.setValor(resposta.getInt("valor"));
                produto.setStatus(resposta.getString("status"));
                listagemVendido.add(produto);
            }
            //Desconectar do banco
            conecta.desconectarDB();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar os registros do banco de dados!");            
        }
        return listagemVendido;    
    }     
}
