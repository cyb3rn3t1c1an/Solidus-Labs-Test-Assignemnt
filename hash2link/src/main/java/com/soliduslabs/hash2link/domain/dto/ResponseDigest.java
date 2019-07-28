package com.soliduslabs.hash2link.domain.dto;

public class ResponseDigest {
    private String digest;

    public ResponseDigest(String digest) {
        this.digest = digest;
    }

    public String getDigest() {
        return digest;
    }
}
