package br.com.biblioteca.dao;

import br.com.biblioteca.models.Autor;

public class AutorDAO implements IPersistencia<Autor>{
    @Override
    public void inserir(Autor autor) {
        
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO AUTOR (nome) VALUES (?)");
           
            stmt.setString(1, curso.getNome());

            stmt.executeUpdate();
            
            System.out.println("Curso " + curso.getNome() + " inserido com sucesso");

        } catch (SQLException ex) {
            ex.printStackTrace();

              throw new RuntimeException("Erro ao inserir informação no banco de dados");
        } finally {
            Conexao.fecharConexao(con, stmt);

        }

        
    }

    @Override
    public Autor consultar(String id) {
        
    Connection con = Conexao.getConexao();   
    PreparedStatement stmt = null;
       
       ResultSet rs = null;
       
       
       List<Curso> cursos = new ArrayList<Curso>();
       
       
       try{
           
           stmt = con.prepareStatement("select id, nome from CURSO");
           rs = stmt.executeQuery();
           
           while (rs.next()){
               Curso curso =  new Curso();
              
               curso.setId(rs.getInt("id"));
               curso.setNome(rs.getString("nome"));
              
               
               cursos.add(curso);
               
           }
           
           
       }catch (SQLException s){
           s.printStackTrace();
           
       }
       
        finally {
            Conexao.fecharConexao(con, stmt);

        }
       
      return cursos;

       
   }
        
    }

    @Override
    public void alterar(String id, Autor objeto) {
        
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE CURSO SET nome = ? where id = ? ");
           
            stmt.setString(1, curso.getNome());

            stmt.setInt(2, curso.getId());
            
            
            stmt.executeUpdate();
            
            System.out.println("Curso " + curso.getNome() + " alterado com sucesso");


        } catch (SQLException ex) {
            ex.printStackTrace();

             throw new RuntimeException("Erro ao inserir informação no banco de dados");
        } finally {
            Conexao.fecharConexao(con, stmt);

        }
    }

    @Override
    public void excluir(String id) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE from CURSO WHERE id = ?");
           
            stmt.setInt(1, curso.getId());
            
            
            stmt.executeUpdate();
            
            System.out.println("Curso " + curso.getNome() + " excluído com sucesso");


        } catch (SQLException ex) {
            ex.printStackTrace();

             throw new RuntimeException("Erro ao inserir informação no banco de dados");
        } finally {
            Conexao.fecharConexao(con, stmt);

        }

    }
}
