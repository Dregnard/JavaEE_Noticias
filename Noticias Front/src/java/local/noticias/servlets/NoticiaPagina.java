package local.noticias.servlets;

import java.io.IOException;
import java.util.List;
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
@WebServlet("/pagina/*")
public class NoticiaPagina extends HttpServlet {

    @EJB
    private NoticiaFacade noticiaFacade;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int p = 1;
        String pagina = null;
        if (req.getPathInfo() != null) {
            pagina = req.getPathInfo().substring(1);
            try {
                p = Integer.parseInt(pagina);
            } catch (NumberFormatException nfe) {
            }
        }
        if (p <= 0) {
            p = 1;
        }
        List<Noticia> noticias = noticiaFacade.getNoticiasByPage(p);
        RequestDispatcher rd = req.getRequestDispatcher("/pagina-noticia.jsp");
        req.setAttribute("noticias", noticias);
        rd.forward(req, resp);
    }

}
