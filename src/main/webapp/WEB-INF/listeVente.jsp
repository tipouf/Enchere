<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- Custom styles for this template -->
<link href="${context}/css/index.css"
      rel="stylesheet">

<div class="row text-center">
    <c:forEach items="${listeArticles}" var="article">
        <div class="col-lg-4 col-md-6 mb-4">
            <a href="${context}/enchere/${article.getNoArticle()}" class="text-decoration-none">
                <div class="card">
                    <img class="card-img-top" src="http://placeimg.com/400/400/tech" alt="img">
                    <div class="card-body">
                        <h4 class="text-center">${article.getNomArticle()}</h4>
                        <div>${article.getPrixVente()} point(s)</div>
                        <div>Fin de l'enchere: <fmt:formatDate value="${article.getDateFinEncheres()}"
                                                               pattern="dd/MM/yyyy"/>
                        </div>
                        <div>Vendeur: ${article.getProprietaire().getPseudo()}
                        </div>
                    </div>
                </div>
            </a>
        </div>
    </c:forEach>
</div>