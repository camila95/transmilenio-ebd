package utils;

import com.google.gson.Gson;

import spark.ResponseTransformer;

public class JsonTransformer implements ResponseTransformer {

    public String render(Object data) throws Exception {
        return new Gson().toJson(data);
    }
}
