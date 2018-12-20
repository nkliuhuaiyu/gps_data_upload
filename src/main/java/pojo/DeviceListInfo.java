package pojo;

public class DeviceListInfo{
    private Integer total_count;
    private Integer per_page;
    private Integer page;
    private DeviceInfo[] devices;

    public Integer getTotal_count() {
        return total_count;
    }

    public void setTotal_count(Integer total_count) {
        this.total_count = total_count;
    }

    public Integer getPer_page() {
        return per_page;
    }

    public void setPer_page(Integer per_page) {
        this.per_page = per_page;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public DeviceInfo[] getDevices() {
        return devices;
    }

    public void setDevices(DeviceInfo[] devices) {
        this.devices = devices;
    }
}
