package local.noticias.servlets;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import local.noticias.entities.Noticia;
import local.noticias.sessionbeans.NoticiaFacade;
import local.noticias.utils.Slugify;

/**
 *
 * @author DAM2
 */
@MultipartConfig
public class InsertNoticia extends HttpServlet {

    @EJB
    private NoticiaFacade noticiaFacade;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Retrieve parameters
        String titulo = req.getParameter("titulo");
        String noticia = req.getParameter("noticia");
        String autor = req.getUserPrincipal().getName();
        String slug = Slugify.toSlug(titulo);
        Part filePart = req.getPart("imagen");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        InputStream fileContent = filePart.getInputStream();
        File fRoot = new File(System.getProperty("upload.image.path"));
        File fImg = new File(fRoot, fileName);
        Files.copy(fileContent, fImg.toPath());
        //Set data into a new "Noticia" object
        Noticia n = new Noticia();
        n.setTitulo(titulo);
        n.setNoticia(noticia);
        n.setAutor(autor);
        n.setSlug(slug);
        n.setRutaImagenNoticia(fileName); // Relative path = System.getProperty("upload.image.path") + PathSeparator + fileName
        Date timeInsert = new Date();
        n.setTimeInsert(timeInsert);
        //Insert the "Noticia" into database
        noticiaFacade.create(n);
        //Return a successful message
        Map map = new HashMap();
        Gson gson = new Gson();
        map.put("msg", "Noticia guardada en base de datos");
        resp.getWriter().print(gson.toJson(map));
        resp.setContentType("application/json");
    }

}
