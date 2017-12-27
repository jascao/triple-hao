package hackathon.pojo;

/**
 * Created by jxu on 2017/12/27.
 */
public enum RequestStatus {


    PENDING("Pending"),

    DONE("Done");

    private String status;

    private RequestStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static RequestStatus fromValue(String status) {
        for (RequestStatus requestStatus : RequestStatus.values()) {
            if(requestStatus.status.equals(status)) {
                return requestStatus;
            }
        }
        throw new IllegalArgumentException(status);
    }
}
