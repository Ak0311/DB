package com.example.dbinsertonly;

public class Consumer {

    String name;
    String phoneNumber;
    String accountNumber;

    public Consumer(String name,String phoneNumber,String accountNumber){

        this.name = name;
        this.phoneNumber = phoneNumber;
        this.accountNumber = accountNumber;

    }


    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
