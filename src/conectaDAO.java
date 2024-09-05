
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class conectaDAO {
    
    private Connection conexao;

    public Connection conectDB() {
        return conexao;
    }
        
    public void conectarDB() throws SQLException{
        try {
            conexao = DriverManager.getConnection("jdbc:mysql://localhost/uc11", "root", "abc123");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + erro.getMessage());
        }
    }
    
    public void desconectarDB(){
        try{
            if(conexao != null && !conexao.isClosed()){
                conexao.close();
                System.out.println("Desconectado com sucesso!");
            }
        }catch (SQLException e){
            System.out.println("Erro ao desconcetar");
        }
    }    
}
