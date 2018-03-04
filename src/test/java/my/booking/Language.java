package my.booking;

import java.util.Arrays;

public enum Language {
    ENGLISH_UK("English (UK)", "en-gb");

    private String name;

    private String descriptor;

    Language(String name, String descriptor) {
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
