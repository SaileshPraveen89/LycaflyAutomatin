package my.booking.config;

import java.util.Arrays;

public enum Currency {
    EURO("Euro", "EUR");

    private String name;

    private String descriptor;

    Currency(String name, String descriptor) {
        this.name = name;
        this.descriptor = descriptor;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public static String getDescriptorByName(String name) {
        return Arrays.stream(values())
                .filter(v -> v.name.equals(name))
                .findFirst().orElseThrow(NullPointerException::new).getDescriptor();
    }

}
