package examples;

import java.util.function.Consumer;

public class ConsumerAndThenExample {
    public static void main(String[] args) {
        Consumer<Member2> consumerA = member2 -> System.out.println("consumerA: " + member2.getName());
        Consumer<Member2> consumerB = member2 -> System.out.println("consumerB: " + member2.getId());

        Consumer<Member2> consumerAB = consumerA.andThen(consumerB);
        consumerAB.accept(new Member2("홍길동","hong",null));
    }

}

class Member2 {
    private String name;
    private String id;
    private Address address;

    public Member2(String name, String id, Address address) {
        this.name = name;
        this.id = id;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public Address getAddress() {
        return address;
    }
}

class Address {
    private String country;
    private String city;

    public Address(String country, String city) {
        this.country = country;
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }
}
