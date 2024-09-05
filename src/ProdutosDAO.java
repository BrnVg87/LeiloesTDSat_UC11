
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;


public class ProdutosDAO {

    Connection conexao;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

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

    public ArrayList<ProdutosDTO> listarProdutos() {

        return listagem;
    }
}
