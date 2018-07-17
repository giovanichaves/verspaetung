package com.mobimeo.verspaetung.repository;

import com.google.common.collect.ImmutableList;
import com.mobimeo.verspaetung.datasource.Datasource;
import com.mobimeo.verspaetung.model.Stop;
import com.mobimeo.verspaetung.model.Time;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Getter
public class TimesRepository implements com.mobimeo.verspaetung.repository.Repository<Time> {

    private ImmutableList<Time> data;
    private final Datasource<Time> datasource;

    public TimesRepository(Datasource<Time> datasource) {
        this.datasource = datasource;
        this.fetchData();
    }

    @Override
    public void fetchData() {
        this.data = ImmutableList.copyOf(datasource.fetchAll());
    }
}
