package greendatatest.dto;

import java.util.Date;

public class DepositDto {

    public Integer id;

    public Integer clientId;

    public Integer bankId;

    public Date date;

    public Double percent;

    public Integer monthTerm;

    @Override
    public String toString() {
        return "DepositDto{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", bankId=" + bankId +
                ", date=" + date +
                ", percent=" + percent +
                ", monthTerm=" + monthTerm +
                '}';
    }
}
