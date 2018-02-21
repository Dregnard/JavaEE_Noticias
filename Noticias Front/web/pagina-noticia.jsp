<%@page import="local.noticias.entities.Noticia"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Noticias</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/noticias.css" type="text/css">

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    </head>
    <body>
        <div id="content-mobile">
            <!---------------------------------------------------- Jumbotron -->
            <!-- Header of the page                                          -->
            <!----------------------------------------------------------------->
            <div class="jumbotron">
                <div class="container text-center">
                    <h1>Página de noticias</h1>
                </div>
            </div>

            <!----------------------------------------------- Grid Skelleton -->
            <!-- Body content of the page. List of news and publicity        -->
            <!-- Mobile & Tablets (Auto)                                     -->
            <!-- Desktop and higher screen resolution (8/4)                  -->
            <!----------------------------------------------------------------->
            <div class="container-fluid">
                <div class="row">
                    <div class="col-sm-8"> <!-- List of news -->
                        <div class="container-fluid">

                            <%
                                List<Noticia> noticias = (List<Noticia>) request.getAttribute("noticias");
                                for (Noticia n : noticias) {%> 
                            <div class="thumbnail">
                                <img src="<%=n.getRutaImagenNoticia()%>" alt="..." >
                                <div class="caption">
                                    <a href="<%="noticia?slug="+n.getSlug()%>"><h3><%=n.getTitulo()%></h3></a>
                                    <p><%=n.getNoticia()%></p>
                                </div>
                            </div><%
                                }
                            %>

                            <!--
                            <div class="thumbnail">
                                <img src="..." alt="...">
                                <div class="caption">
                                    <a href="..."><h3>Título de la noticia</h3></a>
                                    <p>Descripción de la noticia</p>
                                </div>
                            </div>
                            -->

                            <div class="text-center">
                                <nav aria-label="...">
                                    <ul class="pagination">
                                        <%
                                            int pagina = (Integer) request.getAttribute("paginas");
                                            int actual = (Integer) request.getAttribute("actual");
                                            if (actual > 1) {
                                        %>
                                        <li><a href="<%="/Noticias_Front/pagina?p=" + (actual - 1)%>" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
                                            <%
                                            } else {
                                            %>
                                        <li><a href="#" class="disabled" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
                                            <%
                                                }
                                            %>

                                        <%
                                            for (int i = 1; i <= pagina; i++) {
                                                if (actual == i) {
                                        %>
                                        <li class="active"><a href="#"><%=i%><span class="sr-only">(current)</span></a></li>
                                            <%
                                            } else {
                                            %>
                                        <li><a href="<%="/Noticias_Front/pagina?p=" + i%>"><%=i%><span class="sr-only">(current)</span></a></li>
                                            <%
                                                    }
                                                }
                                                if (actual < pagina) {
                                            %>
                                        <li><a href="<%="/Noticias_Front/pagina?p=" + (actual + 1)%>" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
                                            <%
                                            } else {
                                            %>
                                        <li><a href="#" class="disabled" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
                                            <%
                                                }
                                            %>
                                    </ul>
                                </nav>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-4"> <!-- Publicity. Only desktop -->
                        <div id="pDesktop">
                            <p>Hola, esto es publicidad de escritorio</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!------------------------------------------- Publicity. Only mobile -->
        <!-- This is a fixed div 80px height at bottom of screen             -->
        <!--------------------------------------------------------------------->
        <div id="pMobile"> 
            <p>Hola, esto es publicidad de móvil</p>
        </div>
    </body>
</html>
