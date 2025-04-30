<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../includes/cabecalho.jsp" />
<jsp:include page="../includes/menu.jsp" />
<div class="container mt-5">

    <div class="card p-4 rounded-4">

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
                <h2><i class="bi bi-people-fill"></i>  Usuários cadastrados</h2>
            </div>
            <div class="col text-end">
                <!-- Button trigger modal -->
                <button id="bota" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop"><i class="bi bi-folder-plus" onclick="limparTela();"></i> 
                    novo
                </button>          

            </div>        
        </div>

        <!-- Modal -->
        <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog">
                <form class="form-sing" method="get" action="UsuarioServlet" id="form1">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="staticBackdropLabel"><i class="bi bi-people-fill"></i> Cadastro de Usuário</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">


                            <input type="hidden" name="id" value="${usuario.id}" id="id">
                            <div class="row">
                                <div class="form-group">
                                    <label>Nome:</label>   
                                    <input type="type" name="nome" required="true" class="form-control border border-dark border-opacity-50" value="${usuario.nome}" id="nome">
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-4">
                                    <label>CPF:</label>   
                                    <input type="type" name="cpf" required="true" class="form-control border border-dark border-opacity-50" value="${usuario.cpf}" id="cpf" placeholder="000.000.000-00">
                                </div>
                                <div class="form-group col-4">
                                    <label>Login:</label>   
                                    <input type="type" name="login" required="true" class="form-control border border-dark border-opacity-50" value="${usuario.login}" id="login" onblur="validarLogin(this)">
                                </div>
                                <div class="form-group col-4">
                                    <label>Senha:</label>   
                                    <input type="password" name="senha" required="true" class="form-control border border-dark border-opacity-50" value="${usuario.senha}" id="senha">
                                </div>
                            </div>

                            <label>Previlégio:</label>   
                            <select class="form-select" aria-label="Default select example" required="true" name="previlegio" id="previlegio">>
                                <option value="">Selecione...</option>
                                <option value="usuario">Usuário</option>
                                <option value="admin">Administrador</option>                              
                            </select>

                            <label>Setor</label>    
                            <select class="form-select" aria-label="Default select example" name="setor" id="setor" required="true">
                                <option value="">Selecione...</option>
                                <c:forEach var="set" items="${setores}">
                                    <option value="${set.id}">${set.nome}</option>
                                </c:forEach>
                            </select>
                            <div class="form-check mt-3">
                                <input class="form-check-input" type="checkbox" value="" id="ativo" <c:if test="${usuario.ativo==true}">checked</c:if> name="ativo">
                                    <label class="form-check-label" for="ativo">
                                        Ativo
                                    </label>
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
                        <th scope="col">Nome</th>
                        <th scope="col">CPF</th>
                        <th scope="col">Login</th>
                        <th scope="col">Setor</th>
                        <th scope="col">Previlégio</th>
                        <th scope="col">Ativo</th>
                        <th scope="col">Acão</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${lista}" var="usuario">
                    <tr>
                        <td>${usuario.id}</td>
                        <td>${usuario.nome}</td>
                        <td>${usuario.cpf}</td>
                        <td>${usuario.login}</td>
                        <td>${usuario.setor.nome}</td>
                        <td><c:if test="${usuario.previlegio=='admin'}">Administrador</c:if><c:if test="${usuario.previlegio=='usuario'}">Usuário</c:if></td>
                        <td><c:if test="${usuario.ativo}">Ativo</c:if><c:if test="${!usuario.ativo}">Inativo</c:if></td>
                            <td>  
                            <c:set var = "string2" value = "${fn:replace(usuario.nome, ' ', '-')}" />  
                            <c:if test="${usuario.login!='admin'}">
                                <a href="UsuarioServlet?acao=excluir&id=${usuario.id}" class="btn btn-danger rounded-circle" onclick="return validarExclusao();"><i class="bi bi-trash3"></i></a>               
                                <a href="UsuarioServlet?acao=alterar&id=${usuario.id}" class="btn btn-success rounded-circle" data-bs-toggle="modal" data-bs-target="#${string2}"><i class="bi bi-pencil"></i></a>
                                </c:if>
                            <!-- Modal -->
                            <div class="modal fade" id="${string2}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <form class="form-sing" action="UsuarioServlet" method="get">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h1 class="modal-title fs-5" id="exampleModalLabel">Cadastro de Usuario</h1>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">

                                                <input type="hidden" name="id" value="${usuario.id}">
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label>Nome:</label>   
                                                        <input type="type" name="nome" required="true" class="form-control border border-dark border-opacity-50" value="${usuario.nome}">
                                                    </div>

                                                </div>
                                                <div class="row">
                                                    <div class="form-group col-4">
                                                        <label>CPF:</label>   
                                                        <input type="type" name="cpf" required="true" class="form-control border border-dark border-opacity-50" value="${usuario.cpf}" id="cpf" placeholder="000.000.000-00">
                                                    </div>
                                                    <div class="form-group col-4">
                                                        <label>Login:</label>   
                                                        <input type="type" name="login" required="true" class="form-control border border-dark border-opacity-50" value="${usuario.login}" id="login" onblur="validarLogin(this)">
                                                    </div>
                                                    <div class="form-group col-4">
                                                        <label>Senha:</label>   
                                                        <input type="password" name="senha"  class="form-control border border-dark border-opacity-50" value="" id="senha">
                                                    </div>
                                                </div>
                                                <label>Previlégio:</label>   
                                                <select class="form-select" aria-label="Default select example" required="true" name="previlegio">
                                                    <option value="">Selecione...</option>
                                                    <option value="usuario" <c:if test="${usuario.previlegio=='usuario'}"> selected </c:if> >Usuário</option>
                                                    <option value="admin" <c:if test="${usuario.previlegio=='admin'}"> selected </c:if> >Administrador</option>                              
                                                    </select>

                                                    <label>Setor</label>   
                                                    <select class="form-select" aria-label="Default select example" name="setor">
                                                        <option value="">Selecione...</option>
                                                    <c:forEach var="set" items="${setores}">
                                                        <option value="${set.id}" <c:if test="${usuario.setor.id == set.id}">selected="true"</c:if> >${set.nome}</option>
                                                    </c:forEach>
                                                </select>

                                                <div class="form-check mt-3">
                                                    <input class="form-check-input" type="checkbox" value="${usuario.ativo}" id="flexCheckChecked"  name="ativo" <c:if test="${usuario.ativo}">checked</c:if>>
                                                        <label class="form-check-label" for="flexCheckChecked">
                                                            Ativo
                                                        </label>
                                                    </div>
                                                </div>

                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
                                                <c:if test="${usuario.login !='admin'}">  
                                                    <button type="submit" class="btn btn-primary" name="acao" value="salvar">Salvar</button>
                                                </c:if>
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
    // MASCARA CPF
    $('input[name="cpf"]').mask('000.000.000-00');

    // VER SE LOGIN JÁ CADASTRADO
    function validarLogin(login) {

        var hxml = new XMLHttpRequest();
        hxml.onreadystatechange = function () {
            if (hxml.readyState == 4 && hxml.status == 200) {
                var resp = hxml.responseText;
                if (resp == "true") {
                    login.value = '';
                    alert("Login já cadastrado no banco.");
                    login.focus;
                }
            }
            ;
        };
        hxml.open("get", "UsuarioServlet?acao=loginJaCadastrado&login=" + login.value, true);
        hxml.send();
    }
    ;
    // mostar mensagem antes da exclusão
    function validarExclusao() {
        if (confirm("Deseja excluir o cadastro")) {
            return true;
        } else {
            return false;
        }
    }
    ;
    // Validando o formulario antes de salvar
    function validarFormulario() {

        var hxml = new XMLHttpRequest();
        hxml.onreadystatechange = function () {
            if (hxml.readyState == 4 && hxml.status == 200) {
                alert(hxml.responseText);
            }
            ;
        };
        hxml.open("get", "UsuarioServlet?acao=teste", true);
        hxml.send();
    }
    ;
    // limpar o modal
    function limparTela() {

        document.getElementById("id").value = '';
        document.getElementById("nome").value = '';
        document.getElementById("login").value = '';
        document.getElementById("senha").value = '';
        document.getElementById("previlegio").value = '';
        document.getElementById("setor").value = '';
        document.getElementById("ativo").checked = true;

    }
    ;

</script>                          

<jsp:include page="../includes/rodape.jsp" />

