package greendatatest.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "client", uniqueConstraints = {@UniqueConstraint(columnNames = {"client_name", "legalform_id"})})
public class Client {
    @Id
    @Column(name = "client_id", nullable = false)
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "client_name", length = 255, nullable = false)
    private String name;

    @Column(name = "client_shortname", length = 50, nullable = false)
    private String shortName;

    @Column(name = "client_address", length = 255, nullable = false)
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "legalform_id", nullable = false)
    private LegalForm legalForm;

    public Client() {
    }

    public Client(String name, String shortName, String address, LegalForm legalForm) {
        this.name = name;
        this.shortName = shortName;
        this.address = address;
        this.legalForm = legalForm;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LegalForm getLegalForm() {
        return legalForm;
    }

    public void setLegalForm(LegalForm legalForm) {
        this.legalForm = legalForm;
    }
}
