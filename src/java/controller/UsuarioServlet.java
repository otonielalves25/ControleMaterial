/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.SetorDao;
import dao.UsuarioDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Setor;
import modelo.Usuario;
import ultilidade.ConverteSenhaMD5;

/**
 *
 * @author Naty
 */
public class UsuarioServlet extends HttpServlet {

    UsuarioDao usuarioDao = new UsuarioDao();
    SetorDao setorDao = new SetorDao();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String acao = request.getParameter("acao");

        if (acao.equalsIgnoreCase("novo")) {
            
            request.setAttribute("usuario", new Usuario());
            request.setAttribute("lista", usuarioDao.buscaTudo(Usuario.class));
            request.setAttribute("setores", setorDao.buscaTudo(Setor.class));
            request.getRequestDispatcher("paginas/usuario.jsp").forward(request, response);

        } else if (acao.equalsIgnoreCase("salvar")) {

            String senhaCrip;

            // pega os parametros da tela
            int id = (request.getParameter("id").equals("")) ? 0 : Integer.parseInt(request.getParameter("id"));

            String nome = request.getParameter("nome").trim();
            String cpf = request.getParameter("cpf").trim();
            String login = request.getParameter("login").trim();
            String previlegio = request.getParameter("previlegio").trim();
            String senha = request.getParameter("senha").trim();
            boolean ativo = request.getParameter("ativo") != null;
            int idSetor = Integer.parseInt(request.getParameter("setor"));
            Setor setor = setorDao.buscaPorID(idSetor, Setor.class);

            // criando um usuario 
            Usuario usuario = new Usuario();
            usuario.setNome(nome);
            usuario.setLogin(login);
            usuario.setPrevilegio(previlegio);
            usuario.setSenha(senha);
            usuario.setAtivo(ativo);
            usuario.setSetor(setor);
            usuario.setCpf(cpf);

            // fim do usuario
            if (id == 0) {

                if (usuarioDao.consultaLogin(login)) {
                    
   
                    request.setAttribute("usuario", usuario);
                    request.setAttribute("msgErro", "Login: " + login + ", Já cadastro no banco.");
                    retornarPagina(request, response);
                    return;
                }

                if (usuarioDao.consultaPorNome(nome)) {
                    request.setAttribute("usuario", usuario);
                    request.setAttribute("msgErro", "Usuário: " + nome + ", Já cadastro no banco.");
                    retornarPagina(request, response);
                    return;
                }

                senhaCrip = ConverteSenhaMD5.ConvertendoSenha(senha);
                usuario.setSenha(senhaCrip);
                usuarioDao.salvar(usuario);
                request.setAttribute("msg", "Usuário: " + nome + ", cadastrado com sucesso.");

            } else {
                Usuario userTemp = usuarioDao.buscaPorID(id, Usuario.class);
                if (senha.equals("")) {
                    usuario.setSenha(userTemp.getSenha());
                } else {
                    senhaCrip = ConverteSenhaMD5.ConvertendoSenha(senha);
                    usuario.setSenha(senhaCrip);
                }
                usuario.setId(id);
                usuarioDao.salvar(usuario);
                request.setAttribute("msg", "Usuário: " + nome + ", alterado com sucesso.");
            }
            request.setAttribute("usuario", new Usuario());
            retornarPagina(request, response);

        } else if (acao.equalsIgnoreCase("alterar")) {
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("usuario", usuarioDao.buscaPorID(id, Usuario.class));
            retornarPagina(request, response);

        } else if (acao.equalsIgnoreCase("excluir")) {
            int id = Integer.parseInt(request.getParameter("id"));
            usuarioDao.inativaUsuario(id);
            retornarPagina(request, response);

        } else if (acao.equalsIgnoreCase("teste")) {
            
            response.setContentType("text/plain");
            response.getWriter().write("Chamou o teste");
            
            // vevifica se o login ja tem cadastro
        } else if (acao.equalsIgnoreCase("loginJaCadastrado")) {
            String cadatrado;
            String login = request.getParameter("login");
            boolean tem = usuarioDao.consultaLogin(login);
            if (tem) {
                cadatrado = "true";
            } else {
                cadatrado = "false";
            }
            response.setContentType("text/plain");
            response.getWriter().write(cadatrado);

        } else {
            retornarPagina(request, response);
        }

    }

    // Retorna a pagina    
    private void retornarPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("lista", usuarioDao.buscaTudo(Usuario.class));
        request.setAttribute("setores", setorDao.buscaTudo(Setor.class));
        request.getRequestDispatcher("paginas/usuario.jsp").forward(request, response);
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
