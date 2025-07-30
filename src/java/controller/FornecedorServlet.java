/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import dao.FornecedorDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Fornecedor;

/**
 *
 * @author Naty
 */
public class FornecedorServlet extends HttpServlet {

    FornecedorDao fornecedorDao = new FornecedorDao();
    String paginaCadastra = "paginas/fornecedor.jsp";

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
        switch (acao) {
            case "salvar":
                salvar(request, response);
                break;
            case "novo":
                novo(request, response);
                break;
            case "alterar":
                alterar(request, response);
                break;
            case "excluir":
                excluir(request, response);
                break;
            default:
                lista(request, response);
        }

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

    private void salvar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = (request.getParameter("id") == null) ? 0 : Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String cnpj = request.getParameter("cnpj");
        String telefone_empresa = request.getParameter("telefone_empresa");
        String contato = request.getParameter("contato");
        String telefone_contato = request.getParameter("telefone_contato");
        String contato2 = request.getParameter("telefone_contato2");
        String rua = request.getParameter("rua");
        String numero = request.getParameter("numero");
        String cidade = request.getParameter("cidade");
        String bairro = request.getParameter("bairro");
        String cep = request.getParameter("cep");
        String estado = request.getParameter("estado");

        Fornecedor fornecedor = new Fornecedor(id, cep, nome, cnpj, telefone_empresa, contato, telefone_contato, true, contato2, telefone_contato, rua, numero, cidade, bairro, estado);

        // VALIDAR OS CAMPOS ANTES DE SALVAR
        if (fornecedorDao.buscaPorNome(nome) != null && id == 0) {
            request.setAttribute("msgErro", "Fornecedor: " + nome + ", já cadastrado no banco.");
            request.setAttribute("fornecedor", fornecedor);
            request.setAttribute("lista", fornecedorDao.buscaTudo(Fornecedor.class));
            request.getRequestDispatcher(paginaCadastra).forward(request, response);
            return;

        }
        
        // SE TU
        fornecedorDao.salvar(fornecedor);
        if(id == 0){
            request.setAttribute("msg", "Fornecedor: " + nome + ", cadastrado com sucesso.");
        }else{
            request.setAttribute("msg", "Fornecedor: " + nome + ", alterado com sucesso.");
        }
        
        request.setAttribute("fornecedor", new Fornecedor());
        request.setAttribute("lista", fornecedorDao.buscaTudo(Fornecedor.class));
        request.getRequestDispatcher(paginaCadastra).forward(request, response);

    }

    // retorna para alterar
    private void alterar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        Fornecedor fornecedor = fornecedorDao.buscaPorID(id, Fornecedor.class);
        Gson gson = new Gson();
        String json = gson.toJson(fornecedor);

        try (PrintWriter out = response.getWriter()) {
            out.write(json);
        }

    }

    // funçao busca novo e limpa a tela
    private void novo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Fornecedor> listagem = fornecedorDao.buscaTudo(Fornecedor.class);
        request.setAttribute("fornecedor", new Fornecedor());
        request.setAttribute("lista", listagem);
        request.getRequestDispatcher(paginaCadastra).forward(request, response);
    }

    // função lista tudo
    private void lista(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Fornecedor> listagem = fornecedorDao.buscaTudo(Fornecedor.class);
        request.setAttribute("fornecedor", new Fornecedor());
        request.setAttribute("lista", listagem);
        request.getRequestDispatcher(paginaCadastra).forward(request, response);
    }

    // função lista tudo
    private void excluir(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        fornecedorDao.excluir(id, Fornecedor.class);
        request.setAttribute("fornecedor", new Fornecedor());
        request.setAttribute("lista", fornecedorDao.buscaTudo(Fornecedor.class));
        request.getRequestDispatcher(paginaCadastra).forward(request, response);
    }
}
