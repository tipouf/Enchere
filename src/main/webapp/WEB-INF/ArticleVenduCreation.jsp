<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="card mt-5">
    <div class="card-header">
        <label class="card-title">
            Ajout d'un article
        </label>
    </div>
    <div class="card-body">
        <%
            if ((String) request.getAttribute("error") != null) {
        %>
        <div class="alert alert-danger" role="alert">
            <%=((String) request.getAttribute("error")).replaceAll("\n","<br/>")%>
        </div>
        <%
            }
        %>
        <form action="<%=request.getContextPath()%>/ajoutArticle" method="post">
            <div class="row">
                <div class="col-4">
                    <div class="input-group mb-3">
                        <span class="input-group-text" id="nomArticle">Nom de l'article</span>
                        <input type="text" class="form-control" name="nomArticle" placeholder="Nom de l'article...">
                    </div>
                </div>
                <div class="col-8">
                    <div class="input-group mb-3">
                        <span class="input-group-text" id="description">Description</span>
                        <textarea class="form-control"  rows="1" name="description"
                                  placeholder="Description..."></textarea>
                    </div>
                </div>
                <div class="col-4">
                    <div class="input-group mb-3">
                        <span class="input-group-text" id="dateDebutEncheres">Date de debut d'encheres</span>
                        <input type="date" class="form-control" name="dateDebutEncheres">
                    </div>
                </div>
                <div class="col-4">
                    <div class="input-group mb-3">
                        <span class="input-group-text" id="dateFinEncheres">Date de fin d'encheres</span>
                        <input type="date" value="<%=(String) request.getAttribute("dateFinEncheres")%>" class="form-control" name="dateFinEncheres">
                    </div>
                </div>
                <div class="col-4">
                    <div class="input-group mb-3">
                        <span class="input-group-text" id="prixInitial">Prix initial</span>
                        <input type="number" class="form-control" name="prixInitial"
                               placeholder="Prix initial(entier)...">
                    </div>
                </div>
                <div class="col-12">
                    <div class="input-group mb-3">
                        <label class="input-group-text" for="inputGroupSelect01">Categorie</label>
                        <select class="form-select" name="categorieId" id="inputGroupSelect01">
                            <c:forEach var="categorie" items="${listeCategories}">
                                <option value='<c:out value="${categorie.noCategorie}"/>'><c:out
                                        value="${categorie.libelle}"/></option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="col-12">
                    <div class="input-group mb-3">
                        <span class="input-group-text">Adresse de retrait</span>
                        <input type="text" name="rue" placeholder="Rue..." class="form-control">
                        <input type="number" name="codePostal" placeholder="Code postal..." class="form-control">
                        <input type="text" name="ville" placeholder="Ville..." class="form-control">
                    </div>
                </div>

                <div class="col-12 text-end">
                        <button type="submit" class="btn btn-primary mb-3 ">Creer</button>
                </div>
            </div>

        </form>
    </div>
</div>


