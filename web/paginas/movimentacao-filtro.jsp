<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../includes/cabecalho.jsp" />
<jsp:include page="../includes/menu.jsp" />
<div class="container-fluid mt-3 col-11">
    <div class="card p-4 rounded-4">

        <h1 class="mb-5">Pequisa avançada</h1>        

        <form action="MovimentacaoServlet" method="get">

            <div class="d-flex">

                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="pesquisa" id="exampleRadios1" value="chamado" checked>
                    <label class="form-check-label" for="exampleRadios1">
                        Chamado
                    </label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="pesquisa" id="exampleRadios2" value="solicitante">
                    <label class="form-check-label" for="exampleRadios2">
                        Solicitande
                    </label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="pesquisa" id="exampleRadios3" value="setor">
                    <label class="form-check-label" for="exampleRadios3">
                        Setor
                    </label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="pesquisa" id="exampleRadios4" value="data" id="datad" onchange="mostraCampoData();">
                    <label class="form-check-label" for="exampleRadios4">
                        Data
                    </label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="pesquisa" id="exampleRadios5" value="descricao">
                    <label class="form-check-label" for="exampleRadios5">
                        Descrição
                    </label>
                </div>
                <div class="form-check form-check-inline me-3">
                    <input class="form-check-input" type="radio" name="pesquisa" id="exampleRadios6" value="executor">
                    <label class="form-check-label" for="exampleRadios6">
                        Excetor
                    </label>
                </div>

                <div class="col-md me-2">
                    <input class="form-control form-control-sm" type="text" aria-label="form-control-sm" name="txtPesquisa" id="txtPesquisa">
                </div>
                <div class="col-md-3 me-2">
                    <input class="form-control form-control-sm" type="date" aria-label="form-control-sm" name="txtDate" id="txtDate">
                </div>
                <div class="col-md-2 me-2">                 
                    <button  type="submit" name="acao" value="Buscar" class="btn btn-sm btn-primary"><i class="bi bi-search"></i> Buscar</button>
                </div>


            </div>
        </form>

    </div>
</div>

<script>

    function  mostraCampoData() {
        var seg = document.querySelector('input[name=data]:checked').value;
        alert(seg);

    }

</script>                          

<jsp:include page="../includes/rodape.jsp" />

