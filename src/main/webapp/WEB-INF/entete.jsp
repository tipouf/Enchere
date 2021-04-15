<c:set var="context" value="${pageContext.request.contextPath}"/>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/index">ENI Enchères</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <c:choose>
                    <c:when test="${pageAAfficher != 'UtilisateurConnexion.jsp'
				&& pageAAfficher != 'UtilisateurInscription.jsp' }">
                        <c:choose>
                            <c:when test="${sessionScope.user_id != null}">
                                <li class="nav-item">
                                    <a class="nav-link" aria-current="page" href="${context}/ajoutArticle">Enchères</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" aria-current="page" href="${context}/ajoutArticle">Vendre un
                                        articles</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" aria-current="page" href="${context}/ServletProfil">Mon
                                        profil</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" aria-current="page" href="${context}/ServletDeconnexion">Déconnexion</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                                       data-bs-toggle="dropdown" aria-expanded="false">
                                        S'incrire - Se connecter
                                    </a>
                                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                        <li><a class="dropdown-item" href="${context}/connexion">Connexion</a></li>
                                        <li><a class="dropdown-item" href="${context}/inscription">Inscription</a></li>
                                    </ul>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:when>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>
