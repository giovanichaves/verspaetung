package com.mobimeo.verspaetung.repository;

import com.google.common.collect.ImmutableList;
import com.mobimeo.verspaetung.datasource.Datasource;
import com.mobimeo.verspaetung.model.Stop;
import lombok.Getter;
import org.springframework.stereotype.Repository;

@Repository
@Getter
public class StopsRepository implements com.mobimeo.verspaetung.repository.Repository<Stop> {

    private ImmutableList<Stop> data;
    private final Datasource<Stop> datasource;

    public StopsRepository(Datasource<Stop> datasource) {
        this.datasource = datasource;
        this.fetchData();
    }

    @Override
    public void fetchData() {
        this.data = ImmutableList.copyOf(datasource.fetchAll());
    }
}
