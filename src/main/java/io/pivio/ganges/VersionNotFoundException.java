package io.pivio.ganges;

public class VersionNotFoundException extends RuntimeException {

    public VersionNotFoundException(String softwareWithVersion) {
        super("Could not find " + softwareWithVersion);
    }
}
