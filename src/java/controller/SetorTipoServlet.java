/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.SetorTipoDao;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.SetorTipo;

/**
 *
 * @author Naty
 */
public class SetorTipoServlet extends HttpServlet {

    SetorTipoDao setorTipoDao = new SetorTipoDao();

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
            request.setAttribute("setorTipo", new SetorTipo());
            request.getRequestDispatcher("paginas/setor_tipo.jsp").forward(request, response);

        } else if (acao.equalsIgnoreCase("salvar")) {
            String msg, msgErro;
            String nome = request.getParameter("nome");

            int id = (request.getParameter("id").equals("")) ? 0 : Integer.parseInt(request.getParameter("id"));
            SetorTipo c = new SetorTipo();
            c.setNome(nome);

            if (id == 0) {
                // VALIDAR SE JÁ CADASTRADO
                if (setorTipoDao.buscaPorNome(nome) != null) {
                    request.setAttribute("setorTipo", new SetorTipo());
                    request.setAttribute("msgErro", nome + ", já cadastrado no banco.");
                    retornarPagina(request, response);
                    return;

                }

                setorTipoDao.salvar(c);
                request.setAttribute("msg", nome + ", cadastrado com sucesso.");
            } else {
                c.setId(id);
                setorTipoDao.salvar(c);
                request.setAttribute("msg", nome + ", alterado com sucesso.");
            }
            request.setAttribute("setorTipo", new SetorTipo());
            retornarPagina(request, response);

        } else if (acao.equalsIgnoreCase("alterar")) {
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("setorTipo", setorTipoDao.buscaPorID(id, SetorTipo.class));
            retornarPagina(request, response);

        } else if (acao.equalsIgnoreCase("excluir")) {
            int id = Integer.parseInt(request.getParameter("id"));
            try {
                setorTipoDao.excluir(id, SetorTipo.class);
            } catch (Exception e) {
                request.setAttribute("msgErro", "Tipo de localidade não pode ser excluído.");
            }

            retornarPagina(request, response);

        } else {
            retornarPagina(request, response);
        }
    }

    private void retornarPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<SetorTipo> listagem = setorTipoDao.buscaTudo(SetorTipo.class);
        if (listagem.size() > 1) {
            Collections.sort(listagem, (SetorTipo s1, SetorTipo s2) -> s1.getNome().compareTo(s2.getNome()));
        }

        request.setAttribute("lista", listagem);
        request.getRequestDispatcher("paginas/setor_tipo.jsp").forward(request, response);
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
