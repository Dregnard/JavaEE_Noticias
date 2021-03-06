package local.noticias.servlets;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import local.noticias.entities.Noticia;
import local.noticias.sessionbeans.NoticiaFacade;

/**
 *
 * @author xGod
 */
public class NoticiaSlug extends HttpServlet {

    @EJB
    private NoticiaFacade noticiaFacade;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String slug = req.getParameter("slug");
        if (slug == null || slug.trim().isEmpty()) {
            //Error al recibir el slug
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        Noticia n = noticiaFacade.getBySlug(slug);
        if (n == null) {
            //Error no existe la noticia con el slug recibido
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        RequestDispatcher rd = req.getRequestDispatcher("/noticia.jsp");
        req.setAttribute("noticia", n);
        rd.forward(req, resp);
    }

}
