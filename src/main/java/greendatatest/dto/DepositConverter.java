package greendatatest.dto;

import greendatatest.entity.Bank;
import greendatatest.entity.Client;
import greendatatest.entity.Deposit;

import java.util.HashMap;
import java.util.Map;

public class DepositConverter {

    public static DepositDto toData(Deposit entity) {

        if (entity == null) return null;

        DepositDto data = new DepositDto();
        data.id = entity.getId();
        data.date = entity.getDate();
        data.percent = entity.getPercent();
        data.monthTerm = entity.getMonthTerm();
        if (entity.getBank() != null) {
            data.bankId = entity.getBank().getId();
        }
        if (entity.getClient() != null) {
            data.clientId = entity.getClient().getId();
        }

        return data;
    }

    public static Deposit toEntity(DepositDto data) {
        if (data == null) return null;

        Deposit entity = new Deposit();
        entity.setId(data.id);
        entity.setDate(new java.sql.Date(data.date.getTime()));
        entity.setPercent(data.percent);
        entity.setMonthTerm(data.monthTerm);
        if (data.bankId != null) {
            Bank bank = new Bank();
            bank.setId(data.bankId);
            entity.setBank(bank);
        }
        if (data.clientId != null) {
            Client client = new Client();
            client.setId(data.clientId);
            entity.setClient(client);
        }

        return entity;
    }

    public static DepositListDto toListData(Iterable<Deposit> entities) {
        DepositListDto data = new DepositListDto();
        Map<Integer, Bank> bankMap = new HashMap<Integer, Bank>();
        Map<Integer, Client> clientMap = new HashMap<Integer, Client>();

        for (Deposit c : entities) {
            data.deposits.add(toData(c));
            if (!bankMap.containsKey(c.getBank().getId())) {
                bankMap.put(
                        c.getBank().getId(),
                        c.getBank()
                );
            }
            if(!clientMap.containsKey(c.getClient().getId())){
                clientMap.put(
                        c.getClient().getId(),
                        c.getClient()
                );
            }
        }

        bankMap.values().forEach(d -> {
            data.banks.add(BankConverter.toData(d));
        });

        clientMap.values().forEach(d -> {
            data.clients.add(ClientConverter.toData(d));
        });

        return data;
    }
    
}
