<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../includes/cabecalho.jsp" />
<jsp:include page="../includes/menu.jsp" />
<div class="container-fluid mt-5">

    <div class="card p-4 rounded-4">

        <div class="row">
            <div class="col">
                <h2><i class="bi bi-database-add"></i> Materiais</h2>
            </div>
            <div class="col">
                <form action="ProdutoServlet">
                    <div class="row d-flex">
                        <div class="col-9">
                             <input type="text" name="pesquisa" class="form-control form-control-sm" placeholder="filtrar">
                        </div>
                        <div class="col-3">                           
                            <button type="submit" value="buscar" class="btn btn-dark ms-2 btn-sm" name="acao"><i class="bi bi-search" ></i></button>
                        </div>                       
                 
                    </div>
                </form>
            </div>
            <div class="col text-end">
                <a href="ProdutoServlet?acao=detalhe" class="btn btn-info btn-sm" ><i class="bi bi-search" ></i> Visão Detalhada</a>
                <a href="ProdutoServlet?acao=novo" class="btn btn-primary btn-sm" ><i class="bi bi-folder-plus" ></i> Novo</a>
            </div>
        </div>

        <!-- começo da table -->     

        <table class="table table-sm">
            <thead class="table-dark">

                <tr>
                    <th scope="col">Código</th>
                    <th scope="col">Foto</th>
                    <th scope="col">Produto</th>
                    <th scope="col">Categoria</th>
                    <th scope="col">Quantidade</th>
                    <th scope="col">Medida</th>
                    <th scope="col">Valor</th>
                    <th scope="col">Situação</th>
                    <th scope="col">Data Cadastro</th>
                    <th scope="col">Acão</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${lista}" var="produto">
                    <tr>
                        <td>${produto.id}</td>
                        <td><a href="ProdutoServlet?acao=detalhe">
                                <img src="${produto.imagemB64Formatada}" id="preview" height="50px" width="50px"/> 
                            </a>

                        </td>
                        <td>${produto.nome}</td>
                        <td>${produto.categoria.nome}</td>
                        <td>${produto.quantidade}</td>
                        <td>${produto.unidade}</td>
                        <td>${produto.valorFormatado}</td>  
                        <td>${produto.situacao}</td>  
                        <td>${produto.dataFormatada2}</td>                                           
                        <td>                           
                            <a href="ProdutoServlet?acao=excluir&id=${produto.id}" class="btn btn-danger rounded-circle" onclick="return validarExclusao();"><i class="bi bi-trash3"></i></a>               
                            <a href="ProdutoServlet?acao=alterar&id=${produto.id}" class="btn btn-success rounded-circle"><i class="bi bi-pencil"></i></a>
                        </td>

                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <div class="row">
            <a href="ImpressaoServlet" class="btn btn-outline-danger w-25"><i class="bi bi-printer"></i> Imprimir</a>
        </div>
        
        
        <!-- comment 
        

        <nav aria-label="Navegação de página exemplo">
            <ul class="pagination  pagination-sm justify-content-center">  
        <c:if test="${paginas >= 0}">
            <c:forEach var="i" begin="1" end="${paginas}">                    
                <li class="page-item <c:if test="${i == pagAtual}">active</c:if>"><a class="page-link" href="CategoriaServlet?acao=paginada&nPage=${i}">${i}</a></li>            
            </c:forEach>
        </c:if>
</ul>
</nav>    
        -->
    </div>
</div>
<script>
    // Validar a exclusão do banco
    function validarExclusao() {
        if (confirm("Deseja excluir o cadastro")) {
            return true;
        } else {
            return false;
        }
    }

</script>                          

<jsp:include page="../includes/rodape.jsp" />

