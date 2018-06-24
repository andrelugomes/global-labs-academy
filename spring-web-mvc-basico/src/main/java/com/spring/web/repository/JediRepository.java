package com.spring.web.repository;

import java.util.ArrayList;
import java.util.List;

import com.spring.web.model.Jedi;
import org.springframework.stereotype.Repository;

@Repository
public class JediRepository {

    private List<Jedi> jedi;

    public JediRepository() {
        jedi = new ArrayList<>();
        jedi.add(new Jedi("Luke", "Skywalker"));
        jedi.add(new Jedi("Obi-Wan", "Kenobi"));
        jedi.add(new Jedi("Qui-Gon", "Jinn"));
    }

    public List<Jedi> getAll() {
        return jedi;
    }

    public void add(final Jedi jedi) {
       this.jedi.add(jedi);
    }
}
