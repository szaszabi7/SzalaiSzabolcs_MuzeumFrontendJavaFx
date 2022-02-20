package com.example.muzeumfrontendjavafx;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class Api{
    private static final String BASE_URL = "http://localhost:8000";
    public static final String PAINTING_API_URL = BASE_URL + "/api/paintings";
    public static final String STATUE_API_URL = BASE_URL + "/api/statues";

    public static List<Painting> getPaintings() throws IOException {
        Response response = RequestHandler.get(PAINTING_API_URL);
        String json = response.getContent();
        Gson jsonConvert = new Gson();
        if (response.getResponseCode() >= 400){
            String message = jsonConvert.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        Type type = new TypeToken<List<Painting>>(){}.getType();
        return jsonConvert.fromJson(json,type);
    }

    public static boolean deletePainting(int id) throws IOException {
        Response response = RequestHandler.delete(PAINTING_API_URL + "/" + id);

        Gson jsonConvert = new Gson();
        String json = response.getContent();
        if (response.getResponseCode() >= 400){
            String message = jsonConvert.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        return response.getResponseCode() == 204;
    }

    public static List<Statue> getStatues() throws IOException {
        Response response = RequestHandler.get(STATUE_API_URL);
        String json = response.getContent();
        Gson jsonConvert = new Gson();
        if (response.getResponseCode() >= 400){
            String message = jsonConvert.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        Type type = new TypeToken<List<Statue>>(){}.getType();
        return jsonConvert.fromJson(json,type);
    }

}
