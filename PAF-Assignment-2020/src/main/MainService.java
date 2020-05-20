package Main;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import helpers.ResponseConverter;
import helpers.PatientHelper;

import models.PatientModel;
import models.ResponseModel;

import java.util.List;

@Path("/patients")
public class MainService {

    PatientHelper patientService = new PatientHelper();

    /*
    Add New Patient
     */
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertPatient(@FormParam("first_name") String fName,
            @FormParam("last_name") String lName,
            @FormParam("address") String address,
            @FormParam("email") String email) {
        return new ResponseModel(patientService.insert(fName, lName, address, email));
    }

    /*
    Get Patient By ID
     */
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public javax.ws.rs.core.Response getPatientById(@PathParam("id") String id) {

        PatientModel patient = patientService.getById(id);
        if (patient != null) {
            return javax.ws.rs.core.Response.status(200).type(MediaType.APPLICATION_JSON)
                    .entity(patient).build();
        } else {
            return javax.ws.rs.core.Response.status(404).build();
        }

    }

    /*
    Get All Patients
     */
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getAllPatients() {

        List<PatientModel> patients = patientService.get();
        PatientConverter converter = new PatientConverter();
        String output = converter.getJsonArray(patients);
        return output;

    }

    /*
    Update Patient Details
     */
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel updatePatient(@PathParam("id") String id, PatientModel patient) {
        return new ResponseModel(patientService.update(id, patient));
    }

    /*
    Delete Patient
     */
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePatientDetails(@PathParam("id") String id) {
        return new ResponseModel(patientService.delete(id));
    }
}
