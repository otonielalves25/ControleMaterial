<%@page import="modelo.Usuario"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<nav class="navbar navbar-expand-lg" style="background-color: #093013; font-weight: bold"> 

    <div class="container-fluid">
        <a class="navbar-brand" href="#">

        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon">
            </span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle text-white" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Movimentação
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <li><a class="dropdown-item" href="MovimentacaoServlet?acao=novo"><i class="bi bi-arrow-down-up"></i> Atendimento solicitação</a></li>                   
                        <li><a class="dropdown-item" href="MovimentacaoServlet?acao=paginada"><i class="bi bi-arrow-left-right"></i> Movimentações</a></li>                   
                        <li><a href="MovimentacaoServlet?acao=filtroAvancado" class="dropdown-item" ><i class="bi bi-search" ></i> Pesquisa avançada</a></li>                   

                    </ul>
                </li>
            </ul>


            <ul class="navbar-nav">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle text-white" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Consulta
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <li><a class="dropdown-item" href="ProdutoServlet?acao=detalhe"><i class="bi bi-database-add"></i> Material Detalhado</a></li>
                        <li><a class="dropdown-item" href="ProdutoServlet?acao=listar"><i class="bi bi-database-add"></i> Material Listagem</a></li>


                    </ul>
                </li>
            </ul>


            <ul class="navbar-nav">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle text-white" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Cadastro
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <li><a class="dropdown-item" href="ProdutoServlet?acao=listar"><i class="bi bi-database-add"></i> Material</a></li>
                        <li><a class="dropdown-item" href="CategoriaServlet?acao=paginada"><i class="bi bi-train-front"></i> Categoria de Material</a></li>
                        <li><a class="dropdown-item" href="SetorServlet?acao="><i class="bi bi-buildings-fill"></i> Setor/Localidade</a></li>
                        <li><a class="dropdown-item" href="SetorTipoServlet?acao="><i class="bi bi-bank"></i> Tipo de Localidade</a></li>
                        <li><a class="dropdown-item" href="FornecedorServlet?acao="><i class="bi bi-building-add"></i> Fornecedor</a></li>
                    </ul>
                </li>
            </ul>

            <ul class="navbar-nav ms-4">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle text-white" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Sistema
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <li><a class="dropdown-item" href="UsuarioServlet?acao="><i class="bi bi-people-fill"></i> Usuários</a></li>        

                    </ul>
                </li>
            </ul>
            <!-- opção sair do sistema -->
            <ul class="navbar-nav ms-4">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle text-white" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Sair
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <li><a class="dropdown-item" href="LoginServlet?acao=sair"><i class="bi bi-box-arrow-left"></i> Sair</a></li>        

                    </ul>
                </li>
            </ul>
        </div>
        <div class="text-white me-5">
            <% Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
                if (usuario != null)
                    out.print("Usuário: " + usuario.getNome());
            %>
        </div>

    </div>
</nav>