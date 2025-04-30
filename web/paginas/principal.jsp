<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../includes/cabecalho.jsp" />

<jsp:include page="../includes/menu.jsp" />
<div class="container mt-5">
    <div class="card">
        <div class="card-body p-4 mt-1 ">

            <form class="form-sing" method="post" action="">

                <c:if test="${msgErro != null}">
                    <div class="alert alert-danger" role="alert">
                        <c:out  value="${msgErro}"/>
                    </div>                          
                </c:if>
      
                <a href="ImpressaoServlet">teste</a>

                <div class="form-group mt-3">   
                    <button type="submit" class="btn btn-primary" > <i class="bi bi-arrow-right-circle"></i> Entrar</button>

                </div>
            </form>

        </div>
    </div>

</div>
<jsp:include page="../includes/rodape.jsp" />

