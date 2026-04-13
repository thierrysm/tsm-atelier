package com.tsm.atelier.factory;

import com.tsm.atelier.domain.collection.Collection;
import com.tsm.atelier.domain.collection.CollectionStatus;

public class CollectionTestFactory {

  public static CollectionBuilder aCollection() {
    return new CollectionBuilder();
  }

  public static class CollectionBuilder {
    private Long id = 1L;
    private String name = "Verão 2026";
    private CollectionStatus status = CollectionStatus.ACTIVE;

    public CollectionBuilder withId(Long id) {
      this.id = id;
      return this;
    }

    public CollectionBuilder withName(String name) {
      this.name = name;
      return this;
    }

    public Collection build() {
      Collection collection = new Collection();
      collection.setId(id);
      collection.setName(name);
      collection.setStatus(status);
      return collection;
    }
  }
}
