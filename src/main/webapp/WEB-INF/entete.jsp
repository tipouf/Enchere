<c:set var="context" value="${pageContext.request.contextPath}" />
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
        <a class="navbar-brandhref="${pageContext.request.contextPath}/index">Enchere ENI</a>
        <c:choose>
            <c:when test="${pageAAfficher != 'UtilisateurConnexion.jsp'
				&& pageAAfficher != 'UtilisateurInscription.jsp' }">
                <c:choose>
                    <c:when test="${sessionScope.user_id != null}">
                        <a class="ml-auto align-right"
                           href="${context}/connexion">Enchères </a>
                        <a class="ml-auto align-right"
                           href="${context}/vendre">Vendre un
                            articles </a>
                        <a class="ml-auto align-right"
                           href="${context}/ServletProfil">Mon profil
                        </a>
                        <a class="ml-auto align-right"
                           href="${context}/ServletDeconnexion">Déconnexion
                        </a>
                    </c:when>
                    <c:otherwise>
                        <a class="ml-auto align-right"
                           href="${context}/connexion">S'incrire
                            - Se connecter </a>
                    </c:otherwise>
                </c:choose>
            </c:when>
        </c:choose>
    </div>
</nav>
