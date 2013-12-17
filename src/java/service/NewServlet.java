package service;

import de.umass.lastfm.Album;
import model.YouTubeVideo;
import de.umass.lastfm.Artist;
import de.umass.lastfm.Library;
import de.umass.lastfm.Track;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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
@WebServlet(urlPatterns = {"/NewServlet"})
public class NewServlet extends HttpServlet {

    private static final DateFormat RELEASE_DATE_FORMAT = new SimpleDateFormat("d MMM yyyy, HH:mm", Locale.ENGLISH);
    private static final ThreadLocal<SimpleDateFormat> df = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("d MMM yyyy, HH:mm", Locale.US);
        }
    };

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String year = "";
        try {

            String key = "e1806023723dbbd4469e55b819646b6c";
            String clientID = "JavaCodeGeeks";
            String textQuery = request.getParameter("keyword").toString();
            System.out.println("TEXT" + textQuery);
            //String textQuery = "nirvana";
            Artist artist = Artist.getInfo(textQuery.trim(), key);
            //Artist artist = Artist.getInfo("Nirvana", key);
            String artistId = artist.getMbid();
            String artistName = Artist.getInfo(artistId, key).getName();
            //String artistName = Artist.getInfo(artistId, key).getName();
            String artistDesc = Artist.getInfo(artistId, key).getWikiText();
            //String lastfmDesc = Artist.getInfo(lastfmArtistId, new Locale("PL"),"nlight19", key).getWikiText();

            if (artistDesc == null || artistDesc.isEmpty()) {
                artistDesc = "Sorry, no description.<br />";
            }

            Band band = new Band(artistName, artistDesc);
            Collection<Album> lfmAlbums = Artist.getTopAlbums(artistName, key);
            ArrayList<LastFmAlbum> albums = new ArrayList<LastFmAlbum>();
            ArrayList<VevoVideo> tracks = new ArrayList<VevoVideo>();
            Map<LastFmAlbum, ArrayList> albumsTab = new HashMap<LastFmAlbum, ArrayList>();
            //ArrayList[] albumsTab = new ArrayList[lfmAlbums.size()];
            System.out.println("album size " + lfmAlbums.size());
            Object[] alb = lfmAlbums.toArray();
            for (int i = 0; i < lfmAlbums.size(); i++) {
                System.out.println(i + " album name " + ((Album) alb[i]).getName());

                String albumName = ((Album) alb[i]).getName();
                String albumYear = "undefined";
                if (Album.getInfo(artistName, albumName, key).getReleaseDate() != null) {
                    Date relDate = Album.getInfo(artistName, albumName, key).getReleaseDate();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(relDate);
                    int albumyear = cal.get(Calendar.YEAR);
                    //System.out.println("year" + year);
                    Date releaseDate = Album.getInfo(artistName, albumName, key).getReleaseDate();
                    albumYear = String.valueOf(albumyear);
                    //albumYear = releaseDate.toString();
                }
                if (!albumName.equals("undefined")) {
                    albums.add(new LastFmAlbum(albumName, "opis plyty", albumYear));
                }
                //Collection<Album> lfmTracks = Artist.getTopAlbums(artistName, key).;
                //Object[] lmfTracks = lfmTracks.toArray();
                Collection<Track> lfmTracks = Album.getInfo(artistName, albumName, key).getTracks();
                Object[] lmfTracksTab = lfmTracks.toArray();
                System.out.println(i + " track size: " + lfmTracks.size());
                for (int j = 0; j < lfmTracks.size(); j++) {
                    String trackName = ((Track) lmfTracksTab[j]).getName();
                    tracks.add(new VevoVideo(j + "." + trackName, "", ""));
                    System.out.println(j + " track name " + trackName);
                }
                albumsTab.put(new LastFmAlbum(albumName, "", albumYear), tracks);
                tracks.clear();
                //albums.add(new LastFmAlbum(albumName, "opis plyty", albumYear));

            }


//            int maxResults = 10;
//            boolean filter = true;
//            int timeout = 2000;
//            YouTubeManager ym = new YouTubeManager(clientID);
//            String[] vidLinks = new String[10];
//            ArrayList<VevoVideo> vv = new ArrayList<VevoVideo>();
//            List<YouTubeVideo> videos = ym.retrieveVideos(textQuery, maxResults, filter, timeout);
//            int count = 0;
//            for (YouTubeVideo youtubeVideo : videos) {
//                String ytTitle = (String) videos.get(count).getTitle().toString();
//                String lastDescription = Artist.getInfo("Nirvana", key).getWikiText();
//                if (lastDescription == null || lastDescription.isEmpty()) {
//                    lastDescription = "Brak opisu<br />";
//                }
//                String url = youtubeVideo.getEmbeddedWebPlayerUrl();
//                vv.add(new VevoVideo(ytTitle, lastDescription, url));
//                vidLinks[count] = youtubeVideo.getEmbeddedWebPlayerUrl();
//
//                count++;
//            }

            //request.setAttribute("vidLinks", vidLinks);
            //request.setAttribute("vidLinks", vidLinks);
            //request.setAttribute("vevoVideo", vv);
            request.setAttribute("band", band);
            request.setAttribute("albums", albums);
            request.setAttribute("tracks", tracks);
            request.setAttribute("albumsTab", albumsTab);
            //request.setAttribute("lastDescription", vv);
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
//                out.println(youtubeVideo.getEmbeddedWebPlayerUrl());
//                out.println(youtubeVideo.getEmbeddedWebPlayerUrl());
//                System.out.println("videoSrc" + (count));
//                System.out.println(
//                        videos.get(count).getTitle());
//                System.out.println("**************************************************");
//            Collection<Album> chart = Artist.getTopAlbums("Nirvana", key);
//            for (Album album : chart) {
//                System.out.println(album.getArtist() + " \""
//                        + album.getName() + "\" (" + album.getReleaseDate().toString() + ")");
//            }

