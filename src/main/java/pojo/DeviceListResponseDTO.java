package pojo;

public class DeviceListResponseDTO {
    private Integer errno;
    private String error;
    private DeviceListInfo data;

    public Integer getErrno() {
        return errno;
    }

    public void setErrno(Integer errno) {
        this.errno = errno;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public DeviceListInfo getData() {
        return data;
    }

    public void setData(DeviceListInfo data) {
        this.data = data;
    }
}







