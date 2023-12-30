package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class MovieRepository {

   private HashMap<String,Movie> moviemap;
    private HashMap<String,Director> directormap;

    private HashMap<String, ArrayList<String>> directormoviemap;


    public MovieRepository() {
        this.directormap = new HashMap<>();
        this.moviemap = new HashMap<>();
        this.directormoviemap = new HashMap<>();

    }

    public HashMap<String, Movie> getMoviemap() {
        return moviemap;
    }

    public HashMap<String, Director> getDirectormap() {
        return directormap;
    }

    public HashMap<String, ArrayList<String>> getDirectormoviemap() {
        return directormoviemap;
    }

   public void addMovie(Movie movie)
   {
       String movieid = movie.getName();
       if(movieid !=null)
       {
           moviemap.put(movieid,movie);
       }
   }

   public void addDirector(Director director)
   {
       String id = director.getName();
       if(id!=null)
       {
           directormap.put(id,director);
       }
   }

    public void addMovieDirectorPair(String movieName, String directorName) {
        if(moviemap.containsKey(movieName) && directormap.containsKey(directorName)){
            if(directormoviemap.containsKey(directorName)){
                directormoviemap.get(directorName).add(movieName);
            }else{
                ArrayList<String> movieList = new ArrayList<String>();
                movieList.add(movieName);
                directormoviemap.put(directorName, movieList);
            }
        }
    }

   public Movie getmovie(String movieid)
   {
       Movie movie = null;
       if(moviemap.containsKey(movieid))
       {
           movie = moviemap.get(movieid);
       }
       return movie;
   }
   public Director getdirector(String id)
   {
       Director director = null;
       if(directormap.containsKey(id))
       {
           director = directormap.get(id);
       }
       return director;
   }
    public List<String> getMoviesByDirectorName(String directorName) {
        if(directormoviemap.containsKey(directorName)){
            return directormoviemap.get(directorName);
        }
        return new ArrayList<>();
    }
    public List<String> findAllMovies() {
        return new ArrayList<>(moviemap.keySet());
    }

    public void deleteDirectorByName(String directorname) {
        if(directormoviemap.containsKey(directorname)){
            for(String movieName: directormoviemap.get(directorname)){
                moviemap.remove(movieName);
            }
            directormoviemap.remove(directorname);
        }

        directormap.remove(directorname);
    }
    public void deleteAllDirectors() {
        for(String directorName: directormoviemap.keySet()){
            for(String movieName: directormoviemap.get(directorName)){
                moviemap.remove(movieName);
            }
            directormoviemap.remove(directorName);
            directormap.remove((directorName));
        }
    }

}
