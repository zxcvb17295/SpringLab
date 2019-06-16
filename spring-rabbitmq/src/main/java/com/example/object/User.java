package com.example.object;

import java.io.Serializable;

public class User implements Serializable {
    String name;
    Boolean verification;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getVerification() {
        return verification;
    }

    public void setVerification(Boolean verification) {
        this.verification = verification;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", verification=" + verification +
                '}';
    }
}
