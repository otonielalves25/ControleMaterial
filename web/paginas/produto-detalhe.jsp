<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../includes/cabecalho.jsp" />
<jsp:include page="../includes/menu.jsp" />
<div class="container-fluid mt-5">
    <div class="card rounded-4 p-3">
        <div class="row d-flex text-center">
            <c:forEach var="produto" items="${lista}">            
                <div class="card ms-2 col-4" style="width: 18rem;">
                    <img src="${produto.imagemB64Formatada}" class="card-img-top" alt="..." height="200px">
                    <div class="card-body">
                        <h5 class="card-title fw-bold">${produto.categoria.nome}</h5>
                        <p class="card-text fw-bold">${produto.nome}</p>                    
                        <p class="card-text">Quantidade: ${produto.quantidade}</p> 
                         <p class="card-text">${produto.situacao}</p>   

                    </div>      
                    <div class="card-footer text-center">
                        <a href="ProdutoServlet?acao=alterar&id=${produto.id}" class="btn btn-primary btn-sm"><i class="bi bi-pencil"></i> Alterar</a>
                    </div>    
                </div>    

            </c:forEach>
        </div>

    </div>
</div>

</script>                          

<jsp:include page="../includes/rodape.jsp" />

