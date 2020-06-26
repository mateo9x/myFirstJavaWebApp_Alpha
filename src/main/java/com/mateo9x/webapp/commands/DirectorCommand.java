package com.mateo9x.webapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DirectorCommand {
    private Long id;
    private String firstName;
    private String lastName;
    private String pseudo;
}
