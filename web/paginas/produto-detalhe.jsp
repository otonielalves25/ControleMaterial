<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../includes/cabecalho.jsp" />
<jsp:include page="../includes/menu.jsp" />
<div class="container-fluid mt-5">
    <div class="card rounded-4 p-3">
        <div class="row d-flex text-center">
            <c:forEach var="produto" items="${lista}">            
                <div class="card ms-2 col-6" style="width: 15rem;">
                    <img src="${produto.imagemB64Formatada}" class="card-img-top" alt="..." height="100%">
                    <div class="card-body">
                        <h5 class="card-title fw-bold text-success">${produto.categoria.nome}</h5>
                        <p class="card-text">${produto.nome}</p>                  
                      
                        <p class="card-text text-danger"><b>Quantidade: ${produto.quantidade}</b></p> 
                    </div>      
                    <div class="card-footer text-center">
                        <a href="ProdutoServlet?acao=alterar&id=${produto.id}" class="btn btn-primary btn-sm"><i class="bi bi-pencil"></i> Alterar</a>
                        <a href="ProdutoServlet?acao=alterar&id=${produto.id}" class="btn btn-secondary btn-sm"><i class="bi bi-pencil"></i> Sobre</a>
                    </div>    
                </div>    

            </c:forEach>
        </div>

    </div>
</div>

</script>                          

<jsp:include page="../includes/rodape.jsp" />

