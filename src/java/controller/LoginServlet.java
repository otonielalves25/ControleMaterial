/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UsuarioDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Usuario;
import ultilidade.ConverteSenhaMD5;

/**
 *
 * @author Naty
 */
public class LoginServlet extends HttpServlet {

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

    }

    // Cria o administrador no primeiro acesso    
    private Usuario createAdministrador(Usuario usuario) {
        UsuarioDao dao = new UsuarioDao();
        Usuario admin = dao.autenticarUsuario(usuario);
        if (admin == null) {
            usuario.setNome("Administrador");
            usuario.setCpf("000.000.000-00");
            usuario.setPrevilegio("admin");
            usuario.setAtivo(true);
            dao.salvar(usuario);
        }
        return usuario;
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
        String acao = request.getParameter("acao");
        if (acao.equalsIgnoreCase("sair")) {
            HttpSession session = request.getSession();
            session.invalidate();
            request.getRequestDispatcher("index.jsp").forward(request, response);

        }

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

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        // Pega a ação da tela
        String acao = request.getParameter("acao");

        if (acao.equalsIgnoreCase("logar")) {

            // PEGA OS PARAMETROS DA TELA
            String login = request.getParameter("login").trim();
            String senha = request.getParameter("senha").trim();

            // CRIA O USUARIO
            Usuario usuario = new Usuario();
            usuario.setLogin(login);
            usuario.setSenha(ConverteSenhaMD5.ConvertendoSenha(senha));

            // VERIFICA SE É login admin e senha admin
            if ((login.equals("admin") && senha.equals("admin"))) {
                usuario = createAdministrador(usuario);
            }

            // RETORNA O USUARIO LOGADO      
            Usuario usuarioRetornado = new UsuarioDao().autenticarUsuario(usuario);

            if (usuarioRetornado != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute("usuarioLogado", usuarioRetornado);
                request.setAttribute("usuarioRetornado", usuarioRetornado);
                request.getRequestDispatcher("paginas/principal.jsp").forward(request, response);
            } else {
                request.setAttribute("msgErro", "Usuário ou Senha inválidos.");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }

        }

    }
}
