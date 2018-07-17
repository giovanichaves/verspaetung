package com.mobimeo.verspaetung.repository;

import com.google.common.collect.ImmutableList;
import com.mobimeo.verspaetung.datasource.Datasource;
import com.mobimeo.verspaetung.model.Line;
import lombok.Getter;
import org.springframework.stereotype.Repository;

@Repository
@Getter
public class LinesRepository implements com.mobimeo.verspaetung.repository.Repository<Line> {

    private final Datasource<Line> datasource;
    private ImmutableList<Line> data;

    public LinesRepository(Datasource<Line> datasource) {
        this.datasource = datasource;
        this.fetchData();
    }

    @Override
    public void fetchData() {
        this.data = ImmutableList.copyOf(datasource.fetchAll());
    }
}
