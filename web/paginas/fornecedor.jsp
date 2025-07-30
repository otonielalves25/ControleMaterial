
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../includes/cabecalho.jsp" />
<jsp:include page="../includes/menu.jsp" />
<div class="container mt-5">

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
                <h2><i class="bi bi-building-add"></i> Fornecedores</h2>
            </div>
            <div class="col text-end">
                <!-- Button trigger modal -->
                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop" onclick="limparTela()" ><i class="bi bi-folder-plus" ></i> 
                    novo
                </button>          

            </div>        
        </div>

        <!-- Modal -->
        <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <form class="form-sing" method="post" action="FornecedorServlet" id="form">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="staticBackdropLabel"><i class="bi bi-building-add"></i> Cadastro de Fornecedores</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">

                            <c:if test="${msgErro != null}">
                                <div class="alert alert-danger" role="alert">
                                    <c:out  value="${msgErro}"/>
                                </div>                          
                            </c:if>                            
                            <input type="hidden" name="id" value="${fornecedor.id}" id="id"/>
                            <div class="row">
                                <div class="form-group col-6">
                                    <label>Fornecedor:</label>   
                                    <input type="text" name="nome" required="true" class="form-control border border-dark border-opacity-50" value="${fornecedor.nome}" id="nome">
                                </div>
                                <div class="form-group col-3">
                                    <label>Telefone:</label>   
                                    <input type="text" name="telefone_empresa" required="true" class="form-control border border-dark border-opacity-50" value="${fornecedor.telefone_empresa}" placeholder="(  )    -" id="telefone_empresa">
                                </div>
                                <div class="form-group col-3">
                                    <label>CNPJ:</label>   
                                    <input type="text" name="cnpj" class="form-control border border-dark border-opacity-50" value="${fornecedor.cnpj}" placeholder="00.000.000/0000-00" id="cnpj">
                                </div>
                            </div>

                            <div class="row">
                                <div class="form-group col-8">
                                    <label>Contato:</label>   
                                    <input type="text" name="contato" class="form-control border border-dark border-opacity-50" value="${fornecedor.contato}" id="contato">
                                </div>
                                <div class="form-group col-4">
                                    <label>Telefone Contato:</label>   
                                    <input type="text" name="telefone_contato" class="form-control border border-dark border-opacity-50" value="${fornecedor.telefone_contato}" placeholder="(  )    -" id="telefone_contato">
                                </div>
                            </div>

                            <div class="row">
                                <div class="form-group col-md-3">
                                    <label>CEP:</label>   
                                    <input type="text" onblur="buscaCep()" name="cep" class="form-control border border-dark border-opacity-50" value="${fornecedor.cep}" placeholder="00.000-000" id="cep" >
                                </div>

                                <div class="form-group col-7">
                                    <label>Rua:</label>   
                                    <input type="text" name="rua"  class="form-control border border-dark border-opacity-50" value="${fornecedor.rua}" id="rua">
                                </div>

                                <div class="form-group col-2">
                                    <label>Numero:</label>   
                                    <input type="text" name="numero" class="form-control border border-dark border-opacity-50" value="${fornecedor.numero}" id="numero">
                                </div>


                            </div>
                            <div class="row">

                                <div class="form-group col-4">
                                    <label>Bairro:</label>   
                                    <input type="v" name="bairro" required="true" class="form-control border border-dark border-opacity-50" value="${fornecedor.bairro}" id="bairro">
                                </div>


                                <div class="form-group col-4">
                                    <label>Cidade:</label>   
                                    <input type="text" name="cidade" required="true" class="form-control border border-dark border-opacity-50" value="${fornecedor.cidade}" id="cidade">
                                </div>
                                <div class="form-group col-4">
                                    <label>Estado:</label>   
                                    <input type="text" name="estado" required="true" class="form-control border border-dark border-opacity-50" value="${fornecedor.estado}" id="estado">
                                </div>


                            </div>
                        </div>
                        <div class="form-group">
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
                                <button type="submit" class="btn btn-primary" name="acao" value="salvar" > <i class="bi bi-arrow-right-circle"></i> Salvar</button>
                            </div>
                        </div>

                </form>

            </div>
        </div>
    </div>                        
    <!-- começo da table -->     

    <table class="table table-sm">
        <thead class="table-dark">

            <tr>
                <th scope="col">Código</th>
                <th scope="col">Fornecedor</th>
                <th scope="col">Telefone Empresa</th>
                <th scope="col">Contato</th>
                <th scope="col">Telefone</th>
                <th scope="col">Cidade</th>
                <th scope="col">Acão</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${lista}" var="forneced">
                <tr>
                    <td>${forneced.id}</td>
                    <td>${forneced.nome}</td>
                    <td>${forneced.telefone_empresa}</td>
                    <td>${forneced.contato}</td>
                    <td>${forneced.telefone_contato}</td>
                    <td>${forneced.cidade}</td>
                    <td>  
                        <c:set var = "string2" value = "${fn:replace(forneced.nome, ' ', '')}" />
                        <a href="FornecedorServlet?acao=excluir&id=${forneced.id}" class="btn btn-danger rounded-circle" onclick="return validarExclusao();"><i class="bi bi-trash3"></i></a>               
                        <a href="FornecedorServlet?acao=alterar&id=${forneced.id}" onclick="buscarAlterar(${forneced.id})" class="btn btn-success rounded-circle" data-bs-toggle="modal" data-bs-target="#staticBackdrop"><i class="bi bi-pencil"></i></a>



                </tr>
            </c:forEach>
        </tbody>
    </table>

    <nav aria-label="Navegação de página exemplo">
        <ul class="pagination  pagination-sm justify-content-center">  
            <c:if test="${paginas >= 1}">
                <c:forEach var="i" begin="1" end="${paginas}">                    
                    <li class="page-item <c:if test="${i == pagAtual}">active</c:if>"><a class="page-link" href="CategoriaServlet?acao=paginada&nPage=${i}">${i}</a></li>            
                    </c:forEach>
                </c:if>
        </ul>
    </nav>                     
</div>
</div>
<script>
    // Mascara nos campos

    $('input[name="cep"]').mask('00.000-000');
    $('input[name="telefone_empresa"]').mask('(00)0000-0000');
    $('input[name="telefone_contato"]').mask('(00)0000-0000');
    $('input[name="cnpj"]').mask('00.000.000/0000-00');

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
        const formulario = document.getElementById("form");
        formulario.reset();
    }
    // CARREGA NA TELA PARA ALTERAR
    function buscarAlterar(id) {
        var hxml = new XMLHttpRequest();
        hxml.onreadystatechange = function () {
            if (hxml.readyState == 4 && hxml.status == 200) {
                var fornecedor = JSON.parse(hxml.responseText);                
                document.getElementById("id").value = fornecedor.id;
                document.getElementById("nome").value = fornecedor.nome;
                document.getElementById("cnpj").value = fornecedor.cnpj;
                document.getElementById("telefone_empresa").value = fornecedor.telefone_empresa;
                document.getElementById("contato").value = fornecedor.contato;
                document.getElementById("telefone_contato").value = fornecedor.telefone_contato;
                document.getElementById("rua").value = fornecedor.rua;
                document.getElementById("numero").value = fornecedor.numero;
                document.getElementById("bairro").value = fornecedor.bairro;
                document.getElementById("cep").value = fornecedor.cep;
                document.getElementById("cidade").value = fornecedor.cidade;
                document.getElementById("estado").value = fornecedor.estado;
            }

        };
        hxml.open("get", "FornecedorServlet?acao=alterar&id=" + id, true);
        hxml.send();
    }

    // função que busca o cep via ajax
    function buscaCep() {
        var cep2 = document.getElementById("cep").value;
        var cep = cep2.replace(".", "").replace("-", "");
        var xCep = new XMLHttpRequest();
        xCep.onreadystatechange = function () {
            if (xCep.readyState === 4 && xCep.status === 200) {
                
                var endereco = JSON.parse(xCep.responseText);                
                 document.getElementById("rua").value = endereco["logradouro"];   
                 document.getElementById("bairro").value = endereco["bairro"];   
                 document.getElementById("cidade").value = endereco["localidade"];   
                 document.getElementById("estado").value = endereco["estado"];   

            }

        };
        xCep.open("get", "http://viacep.com.br/ws/" + cep + "/json/", true);
        xCep.send();
    }



</script>                          

<jsp:include page="../includes/rodape.jsp" />

