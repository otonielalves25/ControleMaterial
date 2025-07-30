<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../includes/cabecalho.jsp" />
<jsp:include page="../includes/menu.jsp" />
<div class="container mt-5">

    <div class="card rounded-4 p-3">

        <c:if test="${msg != null}">
            <div class="alert alert-success text-center" role="alert">
                <c:out  value="${msg}"/>
            </div>                          
        </c:if>

        <form method="post" action="ProdutoServlet?acao=salvar" enctype="multipart/form-data" id="form">



            <div class="form-group text-center">
                <h3><i class="bi bi-database-add"></i> Cadastro de Materias</h3>
            </div>  

            <div class="row">

                <div class="col-md-4 col-4">
                    <input type="hidden" value="${produto.id}" name="id"/>

                    <img src="${produto.imagemB64Formatada}" id="preview" height="" width="100%"/> 
                    <!--                    <label class="form-control-label mt-1">Adicione uma foto:</label>
                                        <input type="file" name="imagem" accept="image/*"  id="img-input"/>-->

                </div>

                <div class="col-md-8">

                    <div class="row">
                        <div class="form-group col-12">
                            <label>Nome:</label>
                            <input type="text" name="nome" class="form-control border border-dark border-opacity-50" value="${produto.nome}" required="true">
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-2">
                            <label>Valor:</label>
                            <input type="text" name="valor" class="form-control border border-dark border-opacity-50" value="${produto.valor}" required="true">
                        </div>
                        <div class="form-group col-2">
                            <label>Peso:</label>
                            <input type="number" name="peso" min="0" class="form-control border border-dark border-opacity-50" value="${produto.peso}" required="true">
                        </div>
                        <div class="form-group col-2">
                            <label>Qtde:</label>
                            <input type="number" min="0" name="quantidade" class="form-control border border-dark border-opacity-50" value="${produto.quantidade}" required="true">
                        </div>

                        <div class="form-group col-2">
                            <label>Unidade</label>   
                            <select class="form-select border border-dark border-opacity-50" aria-label="" name="unidade" id="unidade" required="true">
                                <option value="">Selecione...</option>                       
                                <option value="Metro" <c:if test="${produto.unidade == 'Metro'}"> selected="true"</c:if>   >Grama</option>
                                <option value="Metro" <c:if test="${produto.unidade == 'Metro'}"> selected="true"</c:if> >Metro</option>
                                <option value="Quilo" <c:if test="${produto.unidade == 'Quilo'}"> selected="true"</c:if> >Quilo</option>
                                <option value="Litro" <c:if test="${produto.unidade == 'Litro'}"> selected="true"</c:if> >Litro</option>
                                <option value="Caixa" <c:if test="${produto.unidade == 'Caixa'}"> selected="true"</c:if> >Caixa</option>
                                <option value="Peça" <c:if test="${produto.unidade == 'Peça'}"> selected="true"</c:if> >Peça</option>

                                </select>
                            </div>

                            <div class="form-group col-4">
                                <label>Categoria:</label>   
                                <select class="form-select border border-dark border-opacity-50" aria-label="" name="categoria" id="categoria" required="true">
                                    <option value="">Selecione...</option>
                                <c:forEach var="cart" items="${categorias}">
                                    <option value="${cart.id}"
                                            <c:if test="${cart.id == produto.categoria.id}"> selected="true"</c:if> 
                                            >${cart.nome}</option>                               
                                </c:forEach>

                            </select>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-3">
                            <label>Data Cadastro:</label>                 
                            <input type="date" value="${produto.dataFormatada}" name="dataCadastro" id="dataCadastro"  class="form-control border border-dark border-opacity-50" required="true">

                        </div>
                        <div class="form-group col-5">
                            <label>Fornecedor:</label>   
                            <select class="form-select border border-dark border-opacity-50" aria-label="" name="fornecedor" id="fornecedor" required="true">
                                <option value="">Selecione...</option>
                                <c:forEach var="fornec" items="${fornecedores}">
                                    <option value="${fornec.id}"                                    
                                            <c:if test="${fornec.id == produto.fornecedor.id}"> selected="true"</c:if>                                    
                                            >${fornec.nome}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group col-2">
                            <label>Estado:</label>   
                            <select class="form-select border border-dark border-opacity-50" aria-label="" name="situacao" id="situacao" required="true">
                                <option value="">Selecione...</option>                       
                                <option value="Novo" <c:if test="${produto.situacao == 'Novo'}"> selected="true"</c:if>   >Novo</option>
                                <option value="Bom Estado" <c:if test="${produto.situacao == 'Bom Estado'}"> selected="true"</c:if> >Bom Estado</option>             

                                </select>
                            </div>

                            <div class="form-check col-2 mt-4">
                                <input class="form-check-input" type="checkbox" value="excluido"  id="check" <c:if test="${produto.excluido ==false}">checked="true"</c:if> >
                                <label class="form-check-label" for="check">
                                    Ativo
                                </label>
                            </div>

                        </div>


                        <div class="row">

                            <label class="form-control-label mt-1">Adicione uma foto:</label>
                            <input type="file" name="imagem" accept="image/*"  id="img-input"/>


                        </div>       


                        <div class="row">
                            <div class="form-group">
                                <div class="mb-3 mt-2">
                                    <label>Descrição:</label>
                                    <textarea class="form-control border border-dark border-opacity-50" id="area" rows="5" name="descricao">${produto.descricao}</textarea>
                            </div>
                        </div>
                    </div>


                    <div class="row">
                        <div class="col text-start">   
                            <!-- comment 
                            <label class="form-control-label">Adicione uma foto:</label><br>
                            <input type="file" name="imagem" accept="image/*"  id="img-input"/>
                             
                            -->

                            <a href="ProdutoServlet?acao=null" class="btn btn-outline-success"><i class="bi bi-list-ul"></i> Listar</a>

                        </div>
                        <div class="col text-end">                            

                            <button type="submit" value="salvar" class="btn btn-primary w-25"  name="salvar"><i class="bi bi-floppy"></i> Salvar</button>


                        </div>
                    </div>
                    </form>
                </div>
            </div>

    </div>
</div>
<script>
    // previw na tela
    document.getElementById("img-input").addEventListener("change", readImage, false);
    function readImage() {

        if (this.files && this.files[0]) {
            var file = new FileReader();
            file.onload = function (e) {
                document.getElementById("preview").src = e.target.result;
            };
            file.readAsDataURL(this.files[0]);
        }
    }

    // MASCARA NO CAMPO VALOR
    $('input[name="valor"]').mask('000.000.000.000.000,00', {reverse: true});

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
        document.getElementById("form").clear;
    }

</script>                          

<jsp:include page="../includes/rodape.jsp" />

