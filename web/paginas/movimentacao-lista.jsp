<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../includes/cabecalho.jsp" />
<jsp:include page="../includes/menu.jsp" />
<div class="container-fluid mt-3 col-11">
    <div class="card p-4 rounded-4">
        <div class="row">
            <div class="col">
                <h2><i class="bi bi-arrow-left-right"></i> Movimentações</h2>
            </div>
            <div class="col text-end">
                <a href="MovimentacaoServlet?acao=filtroAvancado" class="btn btn-warning btn-sm" ><i class="bi bi-search" ></i> Pesquisa avançada</a>
                <a href="MovimentacaoServlet?acao=novo" class="btn btn-primary btn-sm" ><i class="bi bi-plus-circle"></i> Novo</a>
            </div>
        </div>

        <!-- começo da table -->     
 
        <table class="table table-sm">
            <thead class="table-dark">

                <tr>
                    <th scope="col">Código</th>
                    <th scope="col">Data</th>                    
                    <th scope="col">Setor Destino</th>
                    <th scope="col">Tipo</th>
                    <th scope="col">Solicitante</th>
                    <th scope="col">Chamado</th>
                    <th scope="col">Executores</th>
                    <th scope="col">Acão</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${lista}" var="movi">
                    <tr>
                        <td>${movi.id}</td>
                        <td>${movi.dataFormatada2}</td>
                         <td>${movi.setor.nome}</td>
                        <td>${movi.setor.setorTipo.nome}</td>                       
                        <td>${movi.solicitante}</td>
                        <td>${movi.chamado}</td>
                        <td>${movi.executor}</td>       

                        <td>                           
                            <a href="MovimentacaoServlet?acao=excluir&id=${movi.id}" class="btn btn-danger btn-sm rounded-circle" onclick="return validarExclusao();"><i class="bi bi-trash3"></i></a>               
                            <a href="MovimentacaoServlet?acao=alterar&id=${movi.id}" class="btn btn-success btn-sm rounded-circle"><i class="bi bi-pencil"></i></a>
                        </td>

                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <!-- comment -->

        <nav aria-label="Navegação de página exemplo">
            <ul class="pagination  pagination-sm justify-content-center">  
                <c:if test="${paginas >= 0}">
                    <c:forEach var="i" begin="1" end="${paginas}">                    
                        <li class="page-item <c:if test="${i == pagAtual}">active</c:if>"><a class="page-link" href="MovimentacaoServlet?acao=paginada&nPage=${i}">${i}</a></li>            
                        </c:forEach>
                    </c:if>
            </ul>
        </nav>  


    </div>
</div>

<script>

    // Validar a exclusão do banco
    function validarExclusao() {
        if (confirm("Deseja excluir da lista ?")) {
            return true;
        } else {
            return false;
        }
    }
    ;

</script>                          

<jsp:include page="../includes/rodape.jsp" />

