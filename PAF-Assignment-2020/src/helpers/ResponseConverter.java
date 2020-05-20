package helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import models.ResponseModel;

import java.util.List;

public class ResponseConverter {

    private final Gson gson;

    public ResponseConverter() {
        gson = new GsonBuilder().create();
    }

    public String getJsonArray(List<ResponseModel> patients) {
        JsonArray jarray = gson.toJsonTree(patients).getAsJsonArray();
        return jarray.toString();
    }

    public String getJsonObject(ResponseModel patient) {
        JsonObject jsonObject = gson.toJsonTree(patient).getAsJsonObject();
        return jsonObject.toString();
    }

    public ResponseModel getPatientObject(String jsonString) {
        return gson.fromJson(jsonString, ResponseModel.class);
    }

}
