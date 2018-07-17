package com.mobimeo.verspaetung.repository;

import java.util.List;

public interface Repository<T> {

    List<T> getData();

    void fetchData();
}
