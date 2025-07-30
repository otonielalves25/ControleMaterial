<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="includes/cabecalho.jsp" />

<div class="container col-3 " style="margin-top: 5%">
    <div class="card rounded-4">
        <div class="card-body p-5  ">

            <form class="form-sing" method="post" action="LoginServlet">
                <div class="form-group text-center">                           
                    <img src="img/Detran-Parana.png" alt=""  width="100%"/>
                    <h3 class="mt-3">Controle de Material</h3>
                </div>

                <c:if test="${msgErro != null}">
                    <div class="alert alert-danger col text-center" role="alert">
                        <c:out  value="${msgErro}"/>
                    </div>                          
                </c:if>

                <div class="form-group">
                    <label>Login:</label>  
                    <input class="form-control border border-dark border-opacity-50" maxlength="20" name="login" type="text"/>                       

                </div>
                <div class="form-group mt-2">
                    <label>Senha:</label>  
                    <input class="form-control border border-dark border-opacity-50" maxlength="20" name="senha" type="password" id="senha">
                    <img style="    position: absolute;
                         top: 68%;
                         right: 13%;
                         cursor: pointer;" id="olho" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAABDUlEQVQ4jd2SvW3DMBBGbwQVKlyo4BGC4FKFS4+TATKCNxAggkeoSpHSRQbwAB7AA7hQoUKFLH6E2qQQHfgHdpo0yQHX8T3exyPR/ytlQ8kOhgV7FvSx9+xglA3lM3DBgh0LPn/onbJhcQ0bv2SHlgVgQa/suFHVkCg7bm5gzB2OyvjlDFdDcoa19etZMN8Qp7oUDPEM2KFV1ZAQO2zPMBERO7Ra4JQNpRa4K4FDS0R0IdneCbQLb4/zh/c7QdH4NL40tPXrovFpjHQr6PJ6yr5hQV80PiUiIm1OKxZ0LICS8TWvpyyOf2DBQQtcXk8Zi3+JcKfNafVsjZ0WfGgJlZZQxZjdwzX+ykf6u/UF0Fwo5Apfcq8AAAAASUVORK5CYII="
                         />                 

                </div>
                <div class="form-group mt-3">   
                    <button type="submit" class="btn btn-primary w-100" name="acao" value="logar"> <i class="bi bi-arrow-right-circle"></i> Entrar</button>
                </div>
            </form>            
        
            
            

        </div>
    </div>

</div>
<script>

    var senha = $('#senha');
    var olho = $("#olho");

    olho.mousedown(function () {
        senha.attr("type", "text");
    });

    olho.mouseup(function () {
        senha.attr("type", "password");
    });
// para evitar o problema de arrastar a imagem e a senha continuar exposta, 
//citada pelo nosso amigo nos comentários
    $("#olho").mouseout(function () {
        $("#senha").attr("type", "password");
    });


</script>
<jsp:include page="includes/rodape.jsp" />

