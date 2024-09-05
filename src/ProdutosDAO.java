
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
}
