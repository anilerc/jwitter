package com.anilerc.jwitter.dto.request;

import jakarta.validation.constraints.Size;

public record RegisterRequest(@Size(min = 3)
                              String username,
                              @Size(min = 3)
                              String password) {
}
