package greendatatest.entity;

import javax.persistence.*;
import java.sql.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "deposit")
public class Deposit {

    @Id
    @Column(name = "deposit_id", nullable = false)
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id", nullable = false)
    private Bank bank;

    @Column(name = "deposit_datebeg", nullable = false)
    private Date date;

    @Column(name = "deposit_percent", nullable = false)
    private Double percent;

    @Column(name = "deposit_monthterm", nullable = true)
    private Integer monthTerm;

    public Deposit() {
    }

    public Deposit(Client client, Bank bank, Date date, Double percent, Integer monthTerm) {
        this.client = client;
        this.bank = bank;
        this.date = date;
        this.percent = percent;
        this.monthTerm = monthTerm;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public Integer getMonthTerm() {
        return monthTerm;
    }

    public void setMonthTerm(Integer monthTerm) {
        this.monthTerm = monthTerm;
    }
}
