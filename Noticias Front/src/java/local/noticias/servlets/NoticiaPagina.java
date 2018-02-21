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
public class NoticiaPagina extends HttpServlet {

    @EJB
    private NoticiaFacade noticiaFacade;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int p = 1;
        String pagina = null;
        if (req.getParameter("p") != null) {
            pagina = req.getParameter("p");
            try {
                p = Integer.parseInt(pagina);
            } catch (NumberFormatException nfe) {
            }
        }
        if (p <= 0) {
            p = 1;
        }
        int count = noticiaFacade.count();
        int pages = count / 3;
        if (count % 3 != 0) {
            pages++;
        }
        List<Noticia> noticias = noticiaFacade.getNoticiasByPage(p);
        RequestDispatcher rd = req.getRequestDispatcher("/pagina-noticia.jsp");
        req.setAttribute("noticias", noticias);
        req.setAttribute("paginas", pages);
        req.setAttribute("actual", p);
        rd.forward(req, resp);
    }

}
