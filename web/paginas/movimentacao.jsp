<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../includes/cabecalho.jsp" />
<jsp:include page="../includes/menu.jsp" />
<div class="container-fluid mt-3 col-11">
    <div class="card p-4 rounded-4">

        <c:if test="${msg != null}">
            <div class="alert alert-success text-center" role="alert">
                <c:out  value="${msg}"/>
            </div>                          
        </c:if>
        <c:if test="${msgErro != null}">
            <div class="alert alert-danger text-center" role="alert">
                <c:out  value="${msgErro}"/>
            </div>                          
        </c:if>

        <div class="row"> 
            <div class="row mb-1">
                <div class="col">
                    <h2><i class="bi bi-arrow-left-right"></i> Movimentação de Material</h2>
                </div>
            </div>    

            <div class="col-7 p-4">
                <table class="table table-striped table-sm">
                    <thead class="table-dark">

                        <tr>
                            <th>Código</th>
                            <th>Imagem</th>
                            <th>Material</th>
                            <th>Categoria</th>
                            <th>Quantidade</th>                            
                            <th>Medida</th>                       
                            <th>Acão</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${itens}" var="item" varStatus="inicio">
                            <tr>
                                <td>${inicio.index + 1}</td>                         
                                <td><img src="${item.produto.imagemB64Formatada}" alt="alt" width="40px" height="40px"/></td>
                                <td>${item.produto.nome}</td>
                                <td>${item.produto.categoria.nome}</td>
                                <td>

                                    <span class="fw-bold">

                                        <a href="MovimentacaoServlet?acao=remProduto&idMaterial=${item.produto.id}" class="btn btn-outline-danger rounded-circle btn-sm me-3">
                                            <i class="bi bi-dash-lg"></i>
                                        </a>

                                        <span> ${item.quantidade} </span>

                                        <a href="MovimentacaoServlet?acao=addProduto&idMaterial=${item.produto.id}&quantidade=1" class="btn btn-outline-success rounded-circle btn-sm ms-3">
                                            <i class="bi bi-plus-lg"></i>                                            
                                        </a>  

                                    </span>

                                </td>                            
                                <td>${item.produto.unidade}</td>                                                                                                  
                                <td class="text-center">                           
                                    <a href="MovimentacaoServlet?acao=excluirLista&id=${item.produto.id}" class="btn btn-sm btn-danger rounded-circle" onclick="return validarExclusao();"><i class="bi bi-x-lg"></i></a>               


                                </td>

                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <div class="form-group row text-end">
                    <div class="col">
                        <button type="button" class="btn btn-secondary rounded-circle" name="acao" data-bs-toggle="modal" data-bs-target="#modalproduto" onclick="buscarMaterial();"><i class="bi bi-zoom-in"></i></button>
                    </div>

                </div>
                <div class="form-group col-4 mt-4">
                    <c:if test="${produto.nome != null}">                         
                        <button type="submit" value="addProduto" name="acao" class="btn btn-success">Adionar a Lista</button>

                    </c:if>
                </div>  

            </div>
            <div class="col-5 p-4 shadow-lg">
                <form action="MovimentacaoServlet" method="post">                
                    <!-- linha da localidade -->
                    <div class="row">
                        <input type="hidden" name="idSetor" value="${movimentacao.setor.id}">
                        <input type="hidden" name="idMovimentacao" value="${movimentacao.id}">
                        <div class="form-group col-7">                            
                            <label>Nome do setor</label>
                            <input type="text" form-group class="form-control form-control-sm border border-dark border-opacity-50" name="setor" value="${movimentacao.setor.nome}" readonly="true" required="true">
                        </div>
                        <div class="form-group col-4">
                            <label>Tipo do setor</label>
                            <input type="text" name="tipoSetor" class="form-control  form-control-sm border border-dark border-opacity-50" value="${movimentacao.setor.setorTipo.nome}" readonly="true" required="true">
                        </div>
                        <div class="form-group col-1">
                            <br>
                            <button type="button" name="acao" class="btn btn-primary btn-sm" data-bs-toggle="modal" data-bs-target="#modallocalidade" onclick="buscarLocalidade();" ><i class="bi bi-search"></i></button>
                        </div>
                    </div> 
                        
                    <div class="row">
                        <div class="form-group col-3">
                            <label>Data:</label>
                            <input type="date" form-group class="form-control form-control-sm border border-dark border-opacity-50" name="dataMovimentacao" value="${movimentacao.dataFormatada}">
                        </div>
                        <div class="form-group col-2">
                            <label>Chamado:</label>
                            <input type="text" form-group class="form-control form-control-sm border border-dark border-opacity-50" name="chamado" value="${movimentacao.chamado}">
                        </div>
                        <div class="form-group col-7">                          
                            <label>Solicitante</label>
                            <input type="text" form-group class="form-control form-control-sm border border-dark border-opacity-50" name="solicitante" value="${movimentacao.solicitante}">
                        </div>                    
                    </div>

                    <div class="form-group col-12 mt-2">
                        <label>Executor:</label>
                        <input type="text" class="form-control form-control-sm border border-dark border-opacity-50" name="executor" value="${movimentacao.executor}">
                    </div>

                    <div class="form-group d-flex row">
                        <div class="mb-2">
                            <label for="textArea" class="form-label">Descrição da solicitação:</label>
                            <textarea class="form-control form-control-sm  border border-dark border-opacity-50" id="textArea" name="descricaoDetalhada" rows="6">${movimentacao.descricaoDetalhada}</textarea>
                        </div>
                    </div>
                    <div class="form-group d-flex row">
                        <div class="mb-2">
                            <label for="textArea" class="form-label">Conclusão:</label>
                            <textarea class="form-control form-control-sm border border-dark border-opacity-50" id="textArea" name="conclusao" rows="6">${movimentacao.conclusao}</textarea>
                        </div>
                    </div>
                    <div class="form-group d-flex">
                        <div class="form-group col text-start">  
                            <a href="MovimentacaoServlet?acao=paginada" class="btn btn-sm btn-warning"><i class="bi bi-list-ul"></i> Listar</a>                         
                            <a href="MovimentacaoServlet?acao=novo" class="btn btn-sm btn-danger"><i class="bi bi-arrow-counterclockwise"></i> Limpar </a>   

                        </div>
                        <div class="form-group  col text-end">  

                            <button type="submit" value="salvar" name="acao" class="btn btn-sm btn-success"><i class="bi bi-floppy"></i> Gravar</button>
                        </div>
                    </div>


                </form>
            </div>
        </div>
    </div>
</div>
<!-- Modal Busca Localidade -->                   
<!-- Modal -->
<div class="modal fade" id="modallocalidade" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg" style="height: 500px; overflow: scroll;">>

        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="staticBackdropLabel">Busca de Localidade</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label>Pesquisar:</label>  
                    <div class="d-flex">
                        <input type="text" onkeyup="buscarLocalidade();" class="form-control me-2 border border-dark border-opacity-50" 
                               name="setor" required="true" id="pesquisalocalidade" maxlength="100">

                    </div>              

                </div>
                <div>
                    <table  class="table table-striped">
                        <thead>
                            <tr>                           
                                <th>Localidade</th>
                                <th>Tipo</th>
                                <th>Ação</th>
                            </tr>
                        </thead>      
                        <tbody id="tbodyId" style="height:80px; overflow:auto;">
                        </tbody>
                    </table>

                </div>
            </div>            

        </div>

    </div>
</div>
<!-- Modal Busca PRODUTO -->                   
<!-- Modal -->
<div class="modal fade bd-example-modal-lg" id="modalproduto" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg" style="height: 500px; overflow: scroll;">

        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="staticBackdropLabel">Busca de Material</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label>Pesquisa Material:</label>  
                    <div class="d-flex">
                        <input type="text" onkeyup="buscarMaterial();" class="form-control me-2 border border-dark border-opacity-50" name="produto" required="true" id="pesquisaMaterial" maxlength="100">

                    </div>              

                </div>
                <div>
                    <table  class="table table-striped">
                        <thead>
                            <tr>                           
                                <th>Material</th>
                                <th>Tipo</th>
                                <th>Estoque</th>
                                <th>Ação</th>
                            </tr>
                        </thead>
                        <tbody id="tbodyId2" style="height:80px; overflow:auto;">
                        </tbody>
                    </table>

                </div>
            </div>            

        </div>

    </div>
</div>

<script>

    // BUSCA LOCALIDADE 
    function buscarLocalidade() {
        
        var setor = document.getElementById("pesquisalocalidade").value;
        var hxml = new XMLHttpRequest();
        hxml.onreadystatechange = function () {
            if (hxml.readyState == 4 && hxml.status == 200) {
                var gsonSetor = JSON.parse(hxml.responseText);
                var tbodyId = document.getElementById("tbodyId");
                // faz o loop na tabela
                tbodyId.innerHTML = ""; // lipa a tabela
                for (var i = 0; i < gsonSetor.length; i++) {
                    var tr = tbodyId.insertRow();

                    var col_nome = tr.insertCell();
                    var col_tipo = tr.insertCell();
                    var col_acao = tr.insertCell();

                    col_nome.innerText = gsonSetor[i].nome;
                    col_tipo.innerText = gsonSetor[i]["setorTipo"].nome;
                    col_acao.innerHTML = '<a href="MovimentacaoServlet?acao=local_escolhido&idSetor=' + gsonSetor[i].id + '" class="btn btn-success btn-sm"><i class="bi bi-check"></i></a>';
                }
            }
            ;
        };
        hxml.open("get", "MovimentacaoServlet?acao=buscalike&setor=" + setor, true);
        hxml.send();
    }

    // BUSCA LOCALIDADE 
    function buscarMaterial() {
        
        var material = document.getElementById("pesquisaMaterial").value;
        var hxml2 = new XMLHttpRequest();

        hxml2.onreadystatechange = function () {
            if (hxml2.readyState == 4 && hxml2.status == 200) {
                var gsonMaterial = JSON.parse(hxml2.responseText);
                var tbodyId2 = document.getElementById("tbodyId2");

                // faz o loop na tabela
                tbodyId2.innerHTML = ""; // lipa a tabela
                for (var i = 0; i < gsonMaterial.length; i++) {
                    var tr = tbodyId2.insertRow();

                    var col_nome = tr.insertCell();
                    var col_tipo = tr.insertCell();
                    var col_estoque = tr.insertCell();
                    var col_acao = tr.insertCell();


                    col_nome.innerText = gsonMaterial[i].nome;
                    col_tipo.innerText = gsonMaterial[i]["categoria"].nome;
                    col_estoque.innerText = gsonMaterial[i].quantidade;
                    col_acao.innerHTML = '<a href="MovimentacaoServlet?acao=addProduto&idMaterial=' + gsonMaterial[i].id + '&quantidade=1" class="btn btn-success btn-sm"><i class="bi bi-check"></i> Select</a>';
                }
            }
            ;
        };
        hxml2.open("get", "MovimentacaoServlet?acao=buscaMaterialLike&material=" + material, true);
        hxml2.send();
    }

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

