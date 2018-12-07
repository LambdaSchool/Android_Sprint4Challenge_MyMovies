package com.lambdaschool.sprint4challenge_mymovies.apiaccess;

import android.graphics.Bitmap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public final class MovieApiDao {
    private static final String BASE_URL          = "https://api.themoviedb.org/3";
    private static final String API_KEY_PARAM    = "?api_key=b98f8f717026d85eb364fe4ac55cd214";
    private static final String QUERY_PARAMS    = "&language=en-US&query=%s&page=%d&include_adult=false";
    private static final String POPULAR_MOVIES    = BASE_URL + "/movie/popular" + API_KEY_PARAM;
    private static final String SEARCH_MOVIES    = BASE_URL + "/search/movie" + API_KEY_PARAM + QUERY_PARAMS;
    private static final String GET_MOVIE_WITH_ID = BASE_URL + "/movie/%s" + API_KEY_PARAM;

    private static final String HARD_CODED = "https://api.themoviedb.org/3/search/movie?api_key=b98f8f717026d85eb364fe4ac55cd214&language=en-US&query=batman the&page=all&include_adult=false";

    private static final String IMAGE_URL = "https://image.tmdb.org/t/p/";
    private static final String[] POSTER_SIZES = new String[] {
            "w92",
            "w154",
            "w185",
            "w342",
            "w500",
            "w780",
            "original"};

    private static final String JPG  = ".jpg";
    private static final String JSON = ".json";

    public static ArrayList<MovieOverview> searchMovies(String searchParam) {
        ArrayList<MovieOverview> data = new ArrayList<>();

        int page = 1;
        int totalPages = 1;
        do {
            try {
                String     result        = NetworkAdapter.httpRequest(String.format(SEARCH_MOVIES, searchParam, page));
                JSONObject dataObject    = new JSONObject(result);
                totalPages = dataObject.getInt("total_pages");
                JSONArray  dataJsonArray = dataObject.getJSONArray("results");

                for (int i = 0; i < dataJsonArray.length(); ++i) {
                    MovieOverview movieOverview = new MovieOverview(dataJsonArray.getJSONObject(i));
                    data.add(movieOverview);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            page++;
        } while (page <= totalPages);
        return data;
    }

    public static Bitmap getPoster(String imagePath) {
        return NetworkAdapter.getBitmapFromURL(IMAGE_URL + POSTER_SIZES[6] + imagePath);
    }

    public static Bitmap getImage(String id) {
        return NetworkAdapter.getBitmapFromURL(IMAGE_URL + id + JPG);
    }
}
