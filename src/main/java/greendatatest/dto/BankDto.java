package greendatatest.dto;

import javax.persistence.Column;

public class BankDto {

    public int id;

    public String name;

    @Override
    public String toString() {
        return "BankDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
