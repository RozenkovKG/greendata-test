package greendatatest.dto;

public class ClientDto {

    public Integer id;

    public String name;

    public String shortName;

    public String address;

    public Integer legalFormId;

    @Override
    public String toString() {
        return "ClientDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                ", address='" + address + '\'' +
                ", legalFormId=" + legalFormId +
                '}';
    }
}
