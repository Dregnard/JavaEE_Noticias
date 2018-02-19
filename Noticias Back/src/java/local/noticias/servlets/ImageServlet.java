package local.noticias.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author xGod
 */
@WebServlet("/img/*")
public class ImageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String imgName = req.getPathInfo().substring(1);
        File fFolder = new File(System.getProperty("upload.image.path"));
        File fImage = new File(fFolder, imgName);
        resp.setHeader("Content-Type", getServletContext().getMimeType(imgName));
        resp.setHeader("Content-Length", String.valueOf(fImage.length()));
        resp.setHeader("Content-Disposition", "inline; filename=\"" + fImage.getName() + "\"");
        Files.copy(fImage.toPath(), resp.getOutputStream());
    }

}
