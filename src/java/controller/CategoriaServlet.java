/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CategoriaDao;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Categoria;

/**
 *
 * @author Naty
 */
public class CategoriaServlet extends HttpServlet {

    CategoriaDao categoriaDao = new CategoriaDao();

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

        // NOVO - LIMPA A TELA
        if (acao.equalsIgnoreCase("novo")) {
            request.setAttribute("categoria", new Categoria());
            request.setAttribute("lista", categoriaDao.buscaTudo(Categoria.class));
            request.getRequestDispatcher("paginas/categoria.jsp").forward(request, response);

            // SALVAR    
        } else if (acao.equalsIgnoreCase("salvar")) {
            String msg = null, msgErro = null;
            String nome = request.getParameter("categoria");
            int id = (request.getParameter("id").equals("")) ? 0 : Integer.parseInt(request.getParameter("id"));
            Categoria c = new Categoria();
            c.setNome(nome);

            if (id == 0) {
                // Verifica se já está cadastrada
                if (categoriaDao.buscaPorNome(nome) == null) {
                    categoriaDao.salvar(c);
                    msg = nome + ", cadastrado com sucesso.";

                } else {
                    request.setAttribute("categoria", new Categoria());
                    request.setAttribute("msgErro", "Categoria " + nome + " já está cadastrada.");
                    request.setAttribute("lista", categoriaDao.buscaTudo(Categoria.class));
                    request.getRequestDispatcher("paginas/categoria.jsp").forward(request, response);
                    return;
                }

            } else {
                c.setId(id);
                categoriaDao.salvar(c);
                msg = nome + ", alterada com sucesso.";

            }
            request.setAttribute("categoria", new Categoria());
            request.getSession(false).setAttribute("msg", msg);
            response.sendRedirect("CategoriaServlet?acao=paginada");

            // BUSCA PARA ALTERAR    
        } else if (acao.equalsIgnoreCase("alterar")) {
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("categoria", categoriaDao.buscaPorID(id, Categoria.class));
            retornarPagina(request, response);

            // EXCLUIR    
        } else if (acao.equalsIgnoreCase("excluir")) {
            int id = Integer.parseInt(request.getParameter("id"));
            try {
                categoriaDao.excluir(id, Categoria.class);
                request.getSession(false).setAttribute("msg", "Categoria excluída.");
            } catch (Exception e) {
                request.getSession(false).setAttribute("msgErro", "Categoria não pode ser excluída, erro: violação de chave.");
            }

            retornarPagina(request, response);

        } else if (acao.equalsIgnoreCase("paginada")) {

            int limite = 10;
            int nPage = (request.getParameter("nPage") == null) ? 1 : Integer.parseInt(request.getParameter("nPage"));
            Long registros = categoriaDao.quantosRegistros();
            int paginas = (int) (registros / limite);
            int offset = (nPage * limite) - limite;

            if (registros % limite != 0) {
                paginas++;
            }

            List<Categoria> listagem = categoriaDao.buscaPaginada(offset, limite);
            if (listagem.size() > 1) {
                Collections.sort(listagem, (Categoria s1, Categoria s2) -> s1.getNome().compareTo(s2.getNome()));
            }
            request.setAttribute("lista", listagem);
            request.setAttribute("paginas", paginas);
            request.setAttribute("pagAtual", nPage);
            request.getRequestDispatcher("paginas/categoria.jsp").forward(request, response);

        } else {
            retornarPagina(request, response);
        }

    }

    // RETORNA PARA PAGINA    
    private void retornarPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Categoria> listagem = (List<Categoria>) categoriaDao.buscaPorQuery("from Categoria");
        if (listagem.size() > 1) {
            Collections.sort(listagem, (Categoria s1, Categoria s2) -> s1.getNome().compareTo(s2.getNome()));
        }
        request.setAttribute("lista", listagem);
        request.getRequestDispatcher("paginas/categoria.jsp").forward(request, response);
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
