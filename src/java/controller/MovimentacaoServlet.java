/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.MovimentacaoDao;
import dao.MovimentacaoItemDao;
import dao.ProdutoDao;
import dao.SetorDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Movimentacao;
import modelo.MovimentacaoItem;
import modelo.Produto;
import modelo.Setor;
import modelo.Usuario;

/**
 *
 * @author Naty
 */
public class MovimentacaoServlet extends HttpServlet {

    private final String paginaCadastro = "paginas/movimentacao.jsp";
    private final ProdutoDao produtoDao = new ProdutoDao();
    private final SetorDao setorDao = new SetorDao();
    private final MovimentacaoDao movimentacaoDao = new MovimentacaoDao();
    private Movimentacao movimentacao = new Movimentacao();
    private List<MovimentacaoItem> itens = new ArrayList<>();
    private List<MovimentacaoItem> novosItens = new ArrayList<>();
    Setor setor;
    Produto produto;

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

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

        String acction = request.getParameter("acao");

        switch (acction) {
            // LIMPA A TELA E MANDA A TELA LIMPA
            case "novo":
                movimentacao = new Movimentacao();
                itens = new ArrayList<>();
                novosItens = new ArrayList<>();
                request.setAttribute("movimentacao", new Movimentacao());
                request.setAttribute("itens", itens);
                request.getRequestDispatcher(paginaCadastro).forward(request, response);
                break;

            // SALVAR OS ITENS NO BANCO    
            case "salvar":
                ArrayList erros = new ArrayList();
                Date dataCadastro = null;
                int idMovimentacao = (request.getParameter("idMovimentacao") == null || request.getParameter("idMovimentacao").equals("")) ? 0 : Integer.parseInt(request.getParameter("idMovimentacao"));

                String dataMovimentacao = request.getParameter("dataMovimentacao");
                try {
                    dataCadastro = sdf.parse(dataMovimentacao);
                } catch (ParseException e) {
                }

                int idSetor = (request.getParameter("idSetor") == null || request.getParameter("idSetor").equals("")) ? 0 : Integer.parseInt(request.getParameter("idSetor"));
                Setor setor2 = setorDao.buscaPorID(idSetor, Setor.class);
                Usuario userLogado = retornaUsuarioLogadoSessao(request);
                String solicitante = request.getParameter("solicitante");
                String descricaoDetalhada = request.getParameter("descricaoDetalhada");
                String protocolo = request.getParameter("protocolo");
                String executor = request.getParameter("executor");
                String chamado = request.getParameter("chamado");
                String conclusao = request.getParameter("conclusao");

                movimentacao = new Movimentacao(idMovimentacao, dataCadastro, setor2, solicitante, chamado, descricaoDetalhada, conclusao, protocolo, executor, userLogado);
                // validar os campos necessários ///////////////////////////////

                if (itens.size() <= 0) {
                    erros.add(" Adicinar um item na lista. ");

                } else if (setor2 == null) {
                    erros.add(" Setor deve ser informado. ");
                }

                if (!erros.isEmpty()) {
                    request.setAttribute("msgErro", erros);
                    request.setAttribute("itens", itens);
                    request.setAttribute("quantos", itens.size());
                    request.setAttribute("movimentacao", movimentacao);
                    request.getRequestDispatcher(paginaCadastro).forward(request, response);
                    return;

                }

                // fim - validar os campos necessários /////////////////////////
                movimentacao = movimentacaoDao.salvar(movimentacao);

                Produto prod;

                // QUANDO FOR ALTERAÇÃO DEVERÁ DEVOLVER OS INTENS NO BANCO
                if (idMovimentacao >= 0) {

                    for (int i = 0; i < novosItens.size(); i++) {

                        prod = novosItens.get(i).getProduto();
                        int qtde = novosItens.get(i).getQuantidade() + prod.getQuantidade();
                        produtoDao.atualizarEstoque(prod, qtde);

                    }

                }

                //SALVANDO A LISTA NO BANCO
                for (int i = 0; i < itens.size(); i++) {

                    prod = produtoDao.buscaPorID(itens.get(i).getProduto().getId(), Produto.class);

                    int quant = itens.get(i).getQuantidade();
                    MovimentacaoItem item = new MovimentacaoItem(0, prod, quant, movimentacao);

                    // Salvando os itens no banco.                   
                    new MovimentacaoItemDao().salvar(item);
                    // atulizando no quantidade no banco
                    int quantidadeNova = prod.getQuantidade() - quant;
                    if (quantidadeNova >= 0) {
                        produtoDao.atualizarEstoque(prod, quantidadeNova);
                    }

                }

                itens = new ArrayList<>();
                novosItens = new ArrayList<>();
                request.setAttribute("msg", "Cadastrado com sucesso.");
                request.setAttribute("itens", itens);
                request.setAttribute("quantos", itens.size());
                request.setAttribute("movimentacao", new Movimentacao());
                request.getRequestDispatcher(paginaCadastro).forward(request, response);

                break;

            // BUSCA A LOCALIDADE POR LIKE E DEVOLVE UM GSON    
            case "alterar":
                int idMov = Integer.parseInt(request.getParameter("id"));
                movimentacao = movimentacaoDao.buscaPorID(idMov, Movimentacao.class);
                itens = movimentacao.getItens();

                for (MovimentacaoItem iten : itens) {
                    MovimentacaoItem m = new MovimentacaoItem(iten.getId(), iten.getProduto(), iten.getQuantidade(), null);

                    novosItens.add(m);
                }
                request.setAttribute("movimentacao", movimentacao);
                request.setAttribute("itens", itens);
                request.getRequestDispatcher(paginaCadastro).forward(request, response);

                break;
            // BUSCA A LOCALIDADE POR LIKE E DEVOLVE UM GSON    
            case "buscalike":
                String nomeSetor = request.getParameter("setor");
                List<Setor> setores = setorDao.listaSetorLike(nomeSetor);
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String stg = gson.toJson(setores);
                try (PrintWriter out = response.getWriter()) {
                    out.write(stg);
                }
                break;
            // BUSCA A material POR LIKE E DEVOLVE UM GSON    
            case "buscaMaterialLike":
                String material = request.getParameter("material");
                List<Produto> produtos = produtoDao.listaMaterialLike(material);
                Gson gson2 = new GsonBuilder().setPrettyPrinting().create();
                String stg2 = gson2.toJson(produtos);

                try (PrintWriter out = response.getWriter()) {
                    out.write(stg2);
                }
                break;

            case "local_escolhido":
                String id = request.getParameter("idSetor");
                setor = setorDao.buscaPorID(Integer.parseInt(id), Setor.class);
                movimentacao.setSetor(setor);
                request.setAttribute("movimentacao", movimentacao);
                request.setAttribute("itens", itens);
                request.getRequestDispatcher(paginaCadastro).forward(request, response);

                break;
            case "material_escolhido":
                String idM = request.getParameter("idMaterial");
                produto = produtoDao.buscaPorID(Integer.parseInt(idM), Produto.class);
                request.setAttribute("produto", produto);
                request.setAttribute("movimentacao", movimentacao);
                request.setAttribute("itens", itens);
                request.getRequestDispatcher(paginaCadastro).forward(request, response);
                break;
            case "alteraMaterial":
                String idMat2 = request.getParameter("id");
                produto = produtoDao.buscaPorID(Integer.parseInt(idMat2), Produto.class);
                request.setAttribute("produto", produto);
                request.setAttribute("movimentacao", movimentacao);
                request.setAttribute("itens", movimentacao.getItens());
                request.getRequestDispatcher(paginaCadastro).forward(request, response);
                break;
            // ADICIONA A LISTA DE MATEIRAL    
            case "addProduto":
                boolean temNaLista = false;
                MovimentacaoItem item = new MovimentacaoItem();
                String idP = request.getParameter("idMaterial");
                int quantidade = Integer.parseInt(request.getParameter("quantidade"));
                produto = produtoDao.buscaPorID(Integer.parseInt(idP), Produto.class);
                int quantidadeEstoque = produto.getQuantidade();

                item.setProduto(produto);

                for (int i = 0; i < itens.size(); i++) {

                    if (itens.get(i).getProduto().getId() == produto.getId()) {
                        if ((quantidade + itens.get(i).getQuantidade()) > quantidadeEstoque) {
                            item.setQuantidade(quantidadeEstoque);
                            request.setAttribute("msgErro", "Quantidade não disponível no estoque.");
                        } else {
                            itens.get(i).setQuantidade(quantidade + itens.get(i).getQuantidade());
                            request.setAttribute("msgErro", null);

                        }

                        temNaLista = true;
                    }
                }

                if (!temNaLista || itens.isEmpty()) {
                    if (quantidade <= quantidadeEstoque) {
                        item.setQuantidade(quantidade);
                        itens.add(item);
                    } else {
                        request.setAttribute("msgErro", "Quantidade não disponível no estoque.");
                    }

                }

                request.setAttribute("itens", itens);
                request.setAttribute("quantos", itens.size());
                request.setAttribute("movimentacao", movimentacao);
                request.getRequestDispatcher(paginaCadastro).forward(request, response);
                break;
            // ADICIONA A LISTA DE MATEIRAL      
            case "remProduto":
                int qtdTeste;
                MovimentacaoItem item2 = new MovimentacaoItem();
                produto = produtoDao.buscaPorID(Integer.parseInt(request.getParameter("idMaterial")), Produto.class);

                item2.setProduto(produto);

                for (int i = 0; i < itens.size(); i++) {
                    if (itens.get(i).getProduto().getId() == produto.getId()) {
                        qtdTeste = itens.get(i).getQuantidade() - 1;
                        if (qtdTeste > 0) {
                            itens.get(i).setQuantidade(itens.get(i).getQuantidade() - 1);
                        }

                    }
                }

                request.setAttribute("itens", itens);
                request.setAttribute("quantos", itens.size());
                request.setAttribute("movimentacao", movimentacao);
                request.getRequestDispatcher(paginaCadastro).forward(request, response);
                break;

            case "paginada":

                int limite = 15;
                int nPage = (request.getParameter("nPage") == null) ? 1 : Integer.parseInt(request.getParameter("nPage"));
                Long registros = movimentacaoDao.quantosRegistros();
                int paginas = (int) (registros / limite);
                int offset = (nPage * limite) - limite;

                if (registros % limite != 0) {
                    paginas++;
                }

                List<Movimentacao> listagem = movimentacaoDao.buscaPaginada(offset, limite);
                //Collections.sort(listagem, (Categoria s1, Categoria s2) -> s1.getNome().compareTo(s2.getNome()));
                request.setAttribute("lista", listagem);
                request.setAttribute("paginas", paginas);
                request.setAttribute("pagAtual", nPage);
                request.getRequestDispatcher("paginas/movimentacao-lista.jsp").forward(request, response);

                break;

            case "excluirLista":
                String idMat = request.getParameter("id");
                produto = produtoDao.buscaPorID(Integer.parseInt(idMat), Produto.class);
                for (int i = 0; i < itens.size(); i++) {
                    if (itens.get(i).getProduto().getId() == produto.getId()) {
                        itens.remove(i);
                    }
                }

                request.setAttribute("itens", itens);
                request.setAttribute("movimentacao", movimentacao);
                request.getRequestDispatcher(paginaCadastro).forward(request, response);
                break;

            case "excluir":

                int idMovim = Integer.parseInt(request.getParameter("id"));
                Movimentacao mov1 = movimentacaoDao.buscaPorID(idMovim, Movimentacao.class);

                for (int i = 0; i < mov1.getItens().size(); i++) {
                    produto = produtoDao.buscaPorID(mov1.getItens().get(i).getProduto().getId(), Produto.class);
                    int qtde = mov1.getItens().get(i).getQuantidade() + produto.getQuantidade();
                    produtoDao.atualizarEstoque(produto, qtde);

                }
                
                movimentacaoDao.excluir(idMovim, Movimentacao.class);

                request.setAttribute("lista", movimentacaoDao.buscaTudo(Movimentacao.class));
                response.sendRedirect("MovimentacaoServlet?acao=paginada");
                break;

            case "listar":
                request.setAttribute("lista", movimentacaoDao.buscaTudo(Movimentacao.class));
                request.getRequestDispatcher("paginas/movimentacao-lista.jsp").forward(request, response);

                break;

            // RECEBE REQUISIÇÃO DA PAGINA FILTRAGEM    
            case "Buscar":

                String txtPesquisa = request.getParameter("txtPesquisa");
                String tipoPesquisa = request.getParameter("pesquisa");
                String data = request.getParameter("txtDate");

                
                request.setAttribute("lista", movimentacaoDao.filtroAvancado(txtPesquisa, tipoPesquisa));
                request.getRequestDispatcher("paginas/movimentacao-lista.jsp").forward(request, response);

                break;

            case "filtroAvancado":

                //request.setAttribute("lista", movimentacaoDao.buscaTudo(Movimentacao.class));
                request.getRequestDispatcher("paginas/movimentacao-filtro.jsp").forward(request, response);

                break;

            default:
                request.setAttribute("lista", movimentacaoDao.buscaTudo(Movimentacao.class));
                request.getRequestDispatcher("paginas/movimentacao-lista.jsp").forward(request, response);

                break;
        }

    }

    // função que retorna um usuario logado
    private Usuario retornaUsuarioLogadoSessao(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
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
