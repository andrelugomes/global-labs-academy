package com.spring.web.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Jedi {

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 20, message = "Name must have between 3 and 20 letters")
    private String name;
    
    @Size(max = 20, message = "Last Name must not have more than 20 letters")
    private String lastName;

    public Jedi (final String name, final String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public Jedi () {
        
    }

    public String getName () {
        return name;
    }

    public void setName (final String name) {
        this.name = name;
    }

    public String getLastName () {
        return lastName;
    }

    public void setLastName (final String lastName) {
        this.lastName = lastName;
    }

}
