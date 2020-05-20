package helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import models.PatientModel;

import java.util.List;

public class PatientConverter {

    private final Gson gson;

    public PatientConverter() {
        gson = new GsonBuilder().create();
    }

    public String getJsonArray(List<PatientModel> patients) {
        JsonArray jarray = gson.toJsonTree(patients).getAsJsonArray();
        return jarray.toString();
    }

    public String getJsonObject(PatientModel patient) {
        JsonObject jsonObject = gson.toJsonTree(patient).getAsJsonObject();
        return jsonObject.toString();
    }

    public PatientModel getPatientObject(String jsonString) {
        return gson.fromJson(jsonString, PatientModel.class);
    }
}
