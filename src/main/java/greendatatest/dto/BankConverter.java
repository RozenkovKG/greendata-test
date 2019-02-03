package greendatatest.dto;

import greendatatest.entity.Bank;

public class BankConverter {

    public static BankDto toData(Bank entity) {

        if (entity == null) return null;

        BankDto data = new BankDto();
        data.id = entity.getId();
        data.name = entity.getName();

        return data;
    }

    public static Bank toEntity(BankDto data) {
        if (data == null) return null;

        Bank entity = new Bank();
        entity.setId(data.id);
        entity.setName(data.name);

        return entity;
    }
}
