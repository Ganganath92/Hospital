package models;

public class ResponseModel {

    private boolean status;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ResponseModel(boolean status) {
        this.status = status;
    }
}
