/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CategoriaDao;
import dao.FornecedorDao;
import dao.ProdutoDao;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import modelo.Categoria;
import modelo.Fornecedor;
import modelo.Produto;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Naty
 */
@MultipartConfig
public class ProdutoServlet extends HttpServlet {

    //DAOS
    ProdutoDao produtoDao = new ProdutoDao();
    FornecedorDao fornecedorDao = new FornecedorDao();
    CategoriaDao categoriaDao = new CategoriaDao();
    // VARIAVEIS 
    String paginaLista = "paginas/produto-lista.jsp";
    String paginaCadastro = "paginas/produto.jsp";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

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
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String acao = request.getParameter("acao");

        switch (acao) {
            case "novo":
                novo(request, response);
                break;
            case "salvar":
                salvar(request, response);
                break;
            case "alterar":
                alterar(request, response);
                break;
            case "excluir":
                excluir(request, response);
                break;
            case "detalhe":
                detalhe(request, response);
                break;
            case "buscar":
                buscar(request, response);
                break;
            default:
                listar(request, response);

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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ProdutoServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ProdutoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    // novo produto limpa a tela
    private void novo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("produto", new Produto());
        request.setAttribute("categorias", categoriaDao.buscaTudo(Categoria.class));
        request.setAttribute("fornecedores", fornecedorDao.buscaTudo(Fornecedor.class));
        request.getRequestDispatcher(paginaCadastro).forward(request, response);

    }

    // busca para alterar
    private void alterar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("produto", produtoDao.buscaPorID(id, Produto.class));
        request.setAttribute("categorias", categoriaDao.buscaTudo(Categoria.class));
        request.setAttribute("fornecedores", fornecedorDao.buscaTudo(Fornecedor.class));
        request.getRequestDispatcher(paginaCadastro).forward(request, response);
    }

    // excluir
    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        produtoDao.excluir(id, modelo.Produto.class);
        request.setAttribute("lista", produtoDao.buscaTudo(Produto.class));
        request.getRequestDispatcher(paginaLista).forward(request, response);

    }

    private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("lista", produtoDao.buscaTudo(Produto.class));
        request.getRequestDispatcher(paginaLista).forward(request, response);
    }

    private void salvar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException {

        response.setContentType("text/html;charset=UTF-8");

        //variáveis de scopo
        String imagemB64 = null;
        String extencao = null;

        // PEAGA AS VARIÁVEIS DA TELA
        int id = (request.getParameter("id") == null) ? 0 : Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String unidade = request.getParameter("unidade");
        String idFornecedor = request.getParameter("fornecedor");
        Fornecedor fornecedor = fornecedorDao.buscaPorID(Integer.parseInt(idFornecedor), Fornecedor.class);
        String idCategoria = request.getParameter("categoria");
        Categoria categoria = categoriaDao.buscaPorID(Integer.parseInt(idCategoria), Categoria.class);
        String peso = request.getParameter("peso");
        String valor = request.getParameter("valor").replace(".", "").replace(",", ".");
        String quantidade = request.getParameter("quantidade");
        String dataCadastro = request.getParameter("dataCadastro");
        String descricao = request.getParameter("descricao");
        String situacao = request.getParameter("situacao");
        boolean excluido = request.getParameter("excluido") != null;

        // CONVERTENTO PESO, VALOR DATA
        int pesoD = Integer.parseInt(peso);
        BigDecimal valorD = new BigDecimal(valor);
        Date dataB = sdf.parse(dataCadastro);
        int qtd = Integer.parseInt(quantidade);

        // SE TIVER IMAGEM
        Part imagem = request.getPart("imagem");

        if (!imagem.getSubmittedFileName().equalsIgnoreCase("")) {

            String path = request.getServletContext().getRealPath("img");
            File diretorio = new File(path); //acessar a foto se não exite cria a pasta.
            if (!diretorio.exists()) {
                diretorio.mkdirs();
            }

            String nomeArquivo = imagem.getSubmittedFileName();
            extencao = FilenameUtils.getExtension(nomeArquivo);
            // 5 - Crie uma randomico para gerar nome distintos, ou difina como
            Random rand = new Random();
            int numero = rand.nextInt(1000);

            // 6 - Copie o arquivo para pastas criada
            String caminhoCompleto = path + File.separator + numero + "-" + nomeArquivo;
            imagem.write(caminhoCompleto);

            // 7 - Converta em no array de byte[] foto
            File file = new File(caminhoCompleto);
            byte[] imag = Files.readAllBytes(file.toPath());

            // 8 - CONVERTE IMAGEM BASE 64
            Encoder encoder = Base64.getEncoder();
            imagemB64 = encoder.encodeToString(imag);  // está em base 64

            // 8 - não esquece de apagar a foto criada temporária
            file.delete();
        }

        if (id == 0) { // NOVO ITEM
            Produto produto = new Produto(id, nome, unidade, fornecedor, categoria, pesoD, valorD, imagemB64, extencao, descricao, qtd, dataB, excluido, situacao);
            produtoDao.salvar(produto);
            request.setAttribute("msg", "Cadastrado com Sucesso.");
            request.setAttribute("produto", new Produto());
            request.setAttribute("categorias", categoriaDao.buscaTudo(modelo.Categoria.class));
            request.setAttribute("fornecedores", fornecedorDao.buscaTudo(modelo.Fornecedor.class));
            request.getRequestDispatcher(paginaCadastro).forward(request, response);

        } else { // ALTERA ITEM
            Produto pRetorno = produtoDao.buscaPorID(id, Produto.class);
            Produto produto;
            if (imagemB64 == null || imagemB64.equals("")) {
                imagemB64 = pRetorno.getImagemB64();
                extencao = pRetorno.getExtencao();
            }
            produto = new Produto(id, nome, unidade, fornecedor, categoria, pesoD, valorD, imagemB64, extencao, descricao, qtd, dataB, excluido, situacao);

            produtoDao.salvar(produto);

            request.setAttribute("msg", "Alterado com Sucesso.");
            request.setAttribute("produto", new Produto());
            request.setAttribute("categorias", categoriaDao.buscaTudo(modelo.Categoria.class));
            request.setAttribute("fornecedores", fornecedorDao.buscaTudo(modelo.Fornecedor.class));
            request.getRequestDispatcher(paginaCadastro).forward(request, response);

        }

    }

    private void detalhe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("lista", produtoDao.buscaTudo(Produto.class));
        request.getRequestDispatcher("paginas/produto-detalhe.jsp").forward(request, response);
    }

    private void buscar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pesquisa = request.getParameter("pesquisa");
        request.setAttribute("lista", produtoDao.listaMaterialLike(pesquisa));
        request.getRequestDispatcher("paginas/produto-lista.jsp").forward(request, response);

    }

}
