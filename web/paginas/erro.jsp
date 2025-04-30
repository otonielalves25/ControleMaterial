<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../includes/cabecalho.jsp" />

<div class="container mt-5 col-6">

    <div class="card p-4 rounded-4 text-center">

        <img src="img/Detran-Parana.png" alt=""  width="100%" height="150px"/>

        <h1>Erro no sistema - pagina não localizada</h1>
        <br>
        <br>
        <h2>Entre em contato com o administrador </h2>
        <br><!-- comment -->
        <br>
        <h2>Otoniel Amancio Alves - Coogi - Ramal 2341</h2>
        <br><!-- comment -->
        <br><!-- comment -->
        <p>Erro: ${erro}</p>
        <p>
            <a href="LoginServlet?acao=sair" class="btn btn-danger">Voltar ao início</a>
        </p>


    </div>

</div>        


<jsp:include page="../includes/rodape.jsp" />

