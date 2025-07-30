/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.SetorDao;
import dao.SetorTipoDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Setor;
import modelo.SetorTipo;

/**
 *
 * @author Naty
 */
public class SetorServlet extends HttpServlet {

    SetorDao setorDao = new SetorDao();
    SetorTipoDao setorTipoDao = new SetorTipoDao();

    public SetorServlet() {

    }

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
            request.setAttribute("setor", new Setor());
            request.setAttribute("errors", "");
            request.setAttribute("tipos", setorTipoDao.buscaTudo(SetorTipo.class));
            request.getRequestDispatcher("paginas/setor.jsp").forward(request, response);

        } else if (acao.equalsIgnoreCase("salvar")) {

            ArrayList erros = new ArrayList<>();
            String nome = request.getParameter("nome");
            String tipo = request.getParameter("tipo");
            SetorTipo tpSetor = setorTipoDao.buscaPorID(Integer.parseInt(tipo), SetorTipo.class);
            int id = (request.getParameter("id").equals("")) ? 0 : Integer.parseInt(request.getParameter("id"));
            Setor setor = new Setor();
            setor.setNome(nome);
            setor.setSetorTipo(tpSetor);

            // testar validação do JPA
            if (nome.equals("")) {
                erros.add("Nome não foi informado.");
            }

            if (!erros.isEmpty()) {
                request.setAttribute("erros", erros);
                request.setAttribute("setor", new Setor());
                retornarPagina(request, response);
                return;

            }

            // fim do - testar validação do JPA
            if (id == 0) {

                Setor setorT = setorDao.buscaPorNome(nome);
                if (setorT != null) {
                    erros.add("Setor já tem cadastrao.");
                    request.setAttribute("erros", erros);
                    request.setAttribute("setor", setor);
                    retornarPagina(request, response);
                    return;
                }

                setorDao.salvar(setor);

            } else {
                setor.setId(id);
                setorDao.salvar(setor);

            }

            request.setAttribute("setor", new Setor());
            retornarPagina(request, response);

        } else if (acao.equalsIgnoreCase("alterar")) {
            
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("setor", setorDao.buscaPorID(id, Setor.class));
            request.setAttribute("tipos", setorTipoDao.buscaTudo(SetorTipo.class));
            retornarPagina(request, response);

        } else if (acao.equalsIgnoreCase("excluir")) {
            
            int id = Integer.parseInt(request.getParameter("id"));
            setorDao.excluir(id, Setor.class);
            retornarPagina(request, response);

        } else {
            
            retornarPagina(request, response);
        }
    }

    private void retornarPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        List<Setor> setores = setorDao.buscaTudo(Setor.class);
        // Se a lista for maior que 2 ordena 
        if (setores.size() > 1) {
            Collections.sort(setores, (Setor o1, Setor o2) -> o1.getNome().compareTo(o2.getNome()));
        }
        request.setAttribute("lista", setores);
        request.setAttribute("tipos", setorTipoDao.buscaTudo(SetorTipo.class));
        request.getRequestDispatcher("paginas/setor.jsp").forward(request, response);
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
