package com.ecomhack.riddle.sphere.models;

public class Challenge {
    private String id;
    private LocalizedStrings name;

    public String getId() {
        return id;
    }

    public LocalizedStrings getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Challenge{" +
                "id='" + id + '\'' +
                ", name=" + name +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Challenge challenge = (Challenge) o;

        if (id != null ? !id.equals(challenge.id) : challenge.id != null) return false;
        return !(name != null ? !name.equals(challenge.name) : challenge.name != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
