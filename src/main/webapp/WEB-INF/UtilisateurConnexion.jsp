<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<%
    String error = (String) request.getAttribute("error");
%>
<div class="card mt-5">
    <div class="card-header">
        <h1 class="card-title text-left">Connexion</h1>
    </div>
    <div class="card-body">
        <form action="<%=request.getContextPath()%>/connexion" method="post">
            <div class=" form-group row">
                <label for="lastName" class="col-sm-2 col-form-label">Identifiant:</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" name="identifiant"
                           placeholder="Identifiant">
                </div>
                <label for="motDePasse" class="col-sm-2 col-form-label">Mot
                    de passe:</label>
                <div class="col-sm-4">
                    <input type="password" class="form-control" name="motDePasse"
                           placeholder="Mot de passe">
                </div>

                <%
                    if (error != null) {
                %>
                <span class="text-danger"><%=error%></span>
                <%
                    }
                %>
            </div>
            <div class="mt-3">
                <button type="submit" class="btn btn-primary float-end">
                    Connexion
                </button>
                <a href="${context}/inscription">
                    <button class="btn btn-info float-end">
                        Créer un compte
                    </button>
                </a>
            </div>
        </form>
    </div>
</div>
