package service;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import model.YouTubeVideo;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author paul
 */
class VevoVideo {

    public String title;

    public VevoVideo(String title) {
        this.title = title;
    }
}

@WebServlet(urlPatterns = {"/NewServlet"})
public class NewServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            String clientID = "JavaCodeGeeks";
            String textQuery = request.getParameter("keyword");
            System.out.println("TEXT" + textQuery);
            //String textQuery = "nirvana";
            int maxResults = 10;
            boolean filter = true;
            int timeout = 2000;
            //  VideoEntry videoEntry =
            YouTubeManager ym = new YouTubeManager(clientID);
            //ArrayList<String> vidLinks = new ArrayList<String>();
            String[] vidLinks = new String[10];
            ///String[] vidLinks = new String[10];
            ArrayList<VevoVideo> vv = new ArrayList<VevoVideo>();
            List<YouTubeVideo> videos = ym.retrieveVideos(textQuery, maxResults, filter, timeout);
            int count = 0;
            for (YouTubeVideo youtubeVideo : videos) {
                System.out.println(youtubeVideo.getWebPlayerUrl());
                System.out.println("Thumbnails");
//                for (String thumbnail : youtubeVideo.getThumbnails()) {
//                    System.out.println("\t" + thumbnail);
//                }
                //request.setAttribute("videoSrc" + (++count), youtubeVideo.getEmbeddedWebPlayerUrl());
                vv.add(new VevoVideo(videos.get(count).getTitle()));
                vidLinks[count] = youtubeVideo.getEmbeddedWebPlayerUrl();
                count++;

                out.println(youtubeVideo.getEmbeddedWebPlayerUrl());
                out.println(youtubeVideo.getEmbeddedWebPlayerUrl());
                System.out.println("videoSrc" + (count));
                System.out.println(
                        videos.get(count).getTitle());
                System.out.println("**************************************************");
            }

            request.setAttribute("vidLinks", vidLinks);
            request.setAttribute("vevoVideo", vv);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(NewServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(NewServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
