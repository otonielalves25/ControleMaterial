<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../includes/cabecalho.jsp" />
<jsp:include page="../includes/menu.jsp" />
<div class="container mt-5 col-6">

    <div class="card p-4 rounded-4">

        <!-- MOSTANDO MENSAGANS NA TELA -->
        <c:if test="${msgErro != null}">
            <div class="alert alert-danger text-center" role="alert">
                <c:out  value="${msgErro}"/>
            </div>                          
        </c:if> 

        <c:if test="${msg != null}">
            <div class="alert alert-success text-center" role="alert">
                <c:out  value="${msg}"/>
            </div>                          
        </c:if> 

        <div class="row">
            <div class="col">
                <h2><i class="bi bi-buildings-fill"></i>Cadastro de localidades/setores</h2>
            </div>
            <div class="col-3 text-end">
                <!-- Button trigger modal -->
                <button type="button" class="btn btn-primary btn-sm" data-bs-toggle="modal" data-bs-target="#staticBackdrop" onclick="limparTela();"><i class="bi bi-folder-plus" ></i> 
                    novo
                </button>          

            </div>        
        </div>
        
        <!-- Modal -->
        <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <form class="form-sing" method="post" action="SetorServlet">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="staticBackdropLabel"><i class="bi bi-buildings-fill"></i> Cadastro de Localides e Setores</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">

                            <c:if test="${msgErro != null}">
                                <div class="alert alert-danger" role="alert">
                                    <c:out  value="${msgErro}"/>
                                </div>                          
                            </c:if>                            
                            <input type="hidden" name="id" value="${setor.id}">
                            <div class="form-group">
                                <label>Localides ou Setores:</label>   
                                <input type="type" name="nome" class="form-control border border-dark border-opacity-50" value="${setor.nome}" id="idcat">
                            </div>
                            <div class="form-group">
                                <label>Tipo:</label>   
                                <select class="form-select border border-dark border-opacity-50" aria-label="Default select example" required="true" name="tipo" id="tipo">
                                    <option value="">Selecione...</option>
                                    <c:forEach var="tipo" items="${tipos}">                                                        
                                        <option value="${tipo.id}">${tipo.nome}</option>
                                    </c:forEach>                                           

                                </select>
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

        <table class="table table-sm">
            <thead class="table-dark">

                <tr>
                    <th scope="col">Código</th>
                    <th scope="col">Setor</th>
                    <th scope="col">Tipo Localidade</th>
                    <th scope="col">Acão</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${lista}" var="setor">
                    <tr>
                        <td>${setor.id}</td>
                        <td>${setor.nome}</td>
                        <td>${setor.setorTipo.nome}</td>
                        <td>  

                            <a href="SetorServlet?acao=excluir&id=${setor.id}" class="btn btn-danger rounded-circle" onclick="return validarExclusao();"><i class="bi bi-trash3"></i></a>               
                            <a href="SetorServlet?acao=alterar&id=${setor.id}" class="btn btn-success rounded-circle" data-bs-toggle="modal" data-bs-target="#model${setor.id}"><i class="bi bi-pencil"></i></a>

                            <!-- Modal -->
                            <div class="modal fade" id="model${setor.id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-lg">
                                    <form action="SetorServlet" method="post">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h1 class="modal-title fs-5" id="exampleModalLabel"><i class="bi bi-buildings-fill"></i> Cadastro de Localidade ou Setor</h1>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                <div class="container-fluid">
                                                    <div class="row">
                                                        <input type="hidden" name="id" value="${setor.id}">
                                                        <div class="form-group">
                                                            <label>Nome:</label>   
                                                            <input type="type" name="nome" required="true" class="form-control border border-dark border-opacity-50" value="${setor.nome}">
                                                        </div>   
                                                        <div class="form-group">
                                                            <label>Tipo:</label>   
                                                            <select class="form-select border border-dark border-opacity-50" aria-label="Default select example" required="true" name="tipo" id="tipo">
                                                                <option value="">Selecione...</option>
                                                                <c:forEach var="tipo" items="${tipos}">                                                        
                                                                    <option value="${tipo.id}" <c:if test="${setor.setorTipo.id == tipo.id}">selected="true"</c:if>  >${tipo.nome}</option>
                                                                </c:forEach>                                                  

                                                            </select>
                                                        </div>

                                                    </div>
                                                </div>

                                            </div>
                                            <div class="modal-footer">
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
    // Limpa a tela antes de abri
    function limparTela() {

        var hxml = new XMLHttpRequest();
        hxml.onreadystatechange = function () {

        };
        hxml.open("get", "SetorServlet?acao=novo", true);
        hxml.send();


    }




</script>                          

<jsp:include page="../includes/rodape.jsp" />

