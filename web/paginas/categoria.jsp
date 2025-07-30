<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<jsp:include page="../includes/cabecalho.jsp" />
<jsp:include page="../includes/menu.jsp" />
<div class="container mt-5 col-6">

    <div class="card p-4 rounded-4">

        <!-- MOSTANDO MENSAGANS NA TELA -->
        <c:if test="${msgErro != null}">
            <div class="alert alert-danger text-center" role="alert">
                <c:out  value="${msgErro}"/>
                <c:remove var="msgErro" scope="session" />
            </div>                          
        </c:if> 

        <c:if test="${msg != null}">
            <div class="alert alert-success text-center" role="alert">
                <c:out  value="${msg}"/>
                <c:remove var="msg" scope="session" />
            </div>                          
        </c:if> 


        <div class="row">
            <div class="col">
                <h2><i class="bi bi-train-front"></i> Categorias</h2>
            </div>
            <div class="col text-end">
                <!-- Button trigger modal -->
                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop" onclick="limparTela();"><i class="bi bi-folder-plus" ></i> 
                    novo
                </button>          

            </div>        
        </div>

        <!-- Modal -->
        <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <form class="form-sing" method="get" action="CategoriaServlet">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="staticBackdropLabel"><i class="bi bi-train-front"></i> Cadastro de Categoria</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">

                            <input type="hidden" name="id" value="${categoria.id}">
                            <div class="form-group">
                                <label>Categoria:</label>   
                                <input type="type" name="categoria" maxlength="150" class="form-control border border-dark border-opacity-50" value="${categoria.nome}" id="idcat">
                            </div>
                        </div>             
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
                            <button type="submit" class="btn btn-primary" name="acao" value="salvar" > <i class="bi bi-arrow-right-circle"></i> Salvar</button>
                        </div>
                    </div>

                </form>
            </div>
        </div>

        <!-- começo da table -->  

        <table class="table table-sm" id="tbl">
            <thead class="table-dark">

                <tr>
                    <th scope="col">Código</th>
                    <th scope="col">Categoria</th>
                    <th scope="col" class="d-flex justify-content-center">Acão</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${lista}" var="categoria">
                    <tr>
                        <td>${categoria.id}</td>
                        <td>${categoria.nome}</td>
                        <td class="d-flex justify-content-center">  
                            <c:set var = "string2" value = "${fn:replace(categoria.nome, ' ', '')}" />
                            <a href="CategoriaServlet?acao=excluir&id=${categoria.id}" class="btn btn-danger rounded-circle me-1" onclick="return validarExclusao();"><i class="bi bi-trash3"></i></a>               
                            <a href="CategoriaServlet?acao=alterar&id=${categoria.id}" class="btn btn-success rounded-circle" data-bs-toggle="modal" data-bs-target="#${string2}"><i class="bi bi-pencil"></i></a>

                            <!-- Modal -->
                            <div class="modal fade" id="${string2}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <form class="form-sing" method="get" action="CategoriaServlet">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h1 class="modal-title fs-5" id="exampleModalLabel">Cadastro de Categoria</h1>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                <div class="container-fluid">
                                                    <div class="row">
                                                        <input type="hidden" name="id" value="${categoria.id}">
                                                        <div class="form-group">
                                                            <label>Categoria:</label>   
                                                            <input type="type" name="categoria" required="true" class="form-control border border-dark border-opacity-50" value="${categoria.nome}">
                                                        </div>                                               

                                                    </div>
                                                </div>

                                            </div>
                                            <div class="modal-footer ">
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
                                                <button type="submit" class="btn btn-primary" name="acao" value="salvar">Salvar</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>

                        </td>

                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <nav aria-label="Navegação de página exemplo">
            <c:if test="${paginas > 1}">
                <ul class="pagination  pagination-sm justify-content-center">

                    <c:forEach var="i" begin="1" end="${paginas}">                    
                        <li class="page-item <c:if test="${i == pagAtual}">active</c:if>"><a class="page-link" href="CategoriaServlet?acao=paginada&nPage=${i}">${i}</a></li>            
                        </c:forEach>

                </ul>
            </c:if>
        </nav>                     
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
    ;
// Limpa a tela antes de abri
    function limparTela()
    {
        document.getElementById("idcat").value = '';
    }


</script>                          

<jsp:include page="../includes/rodape.jsp" />

