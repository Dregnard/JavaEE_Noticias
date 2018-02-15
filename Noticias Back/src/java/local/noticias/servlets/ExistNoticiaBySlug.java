package local.noticias.servlets;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import local.noticias.sessionbeans.NoticiaFacade;
import local.noticias.utils.Slugify;

/**
 *
 * @author DAM2
 */
public class ExistNoticiaBySlug extends HttpServlet {

    @EJB
    private NoticiaFacade noticiaFacade;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Retrieve parameters
        String titulo = req.getParameter("titulo");
        String slug = Slugify.toSlug(titulo);
        //Find if exist a "Noticia" in database with the same slug and return a message
        Map map = new HashMap();
        Gson gson = new Gson();
        if(noticiaFacade.existBySlug(slug)){
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            map.put("msg", "Ya existe una noticia con el mismo slug");
        }else{
            map.put("msg", "Slug válido");
        }
        resp.getWriter().print(gson.toJson(map));
        resp.setContentType("application/json");
    }
    
}
