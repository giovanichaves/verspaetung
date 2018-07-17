package com.mobimeo.verspaetung.repository;

import com.google.common.collect.ImmutableList;
import com.mobimeo.verspaetung.datasource.Datasource;
import com.mobimeo.verspaetung.model.Delay;
import lombok.Getter;
import org.springframework.stereotype.Repository;

@Repository
@Getter
public class DelaysRepository implements com.mobimeo.verspaetung.repository.Repository<Delay> {

    private final Datasource<Delay> datasource;
    private ImmutableList<Delay> data;

    public DelaysRepository(Datasource<Delay> datasource) {
        this.datasource = datasource;
        this.fetchData();
    }

    @Override
    public void fetchData() {
        this.data = ImmutableList.copyOf(datasource.fetchAll());
    }
}
