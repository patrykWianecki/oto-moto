package com.app.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.app.model.Counties;
import com.app.model.County;
import com.app.model.Localities;
import com.app.model.Locality;
import com.app.model.Voivodeship;
import com.app.model.Voivodeships;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

@Service
public class ImporterService {

  private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

  private MongoDatabase mongoDatabase;

  @Autowired
  public ImporterService(
      @Value("${host-value}") String host,
      @Value("${database-name-value}") String databaseName,
      @Value("${port-value}") int port
  ) {
    final MongoClient mongoClient = new MongoClient(host, port);
    this.mongoDatabase = mongoClient.getDatabase(databaseName);
  }

  public List<Voivodeship> createVoivodeships(String voivodeshipsJSON) {
    Optional<Voivodeships> optionalVoivodeships =
        Optional.ofNullable(gson.fromJson(voivodeshipsJSON, Voivodeships.class));

    if (optionalVoivodeships.isPresent()) {
      List<Voivodeship> voivodeships = optionalVoivodeships.get().getVoivodeships();

      if (CollectionUtils.isNotEmpty(voivodeships)) {
        List<Document> documents = voivodeships
            .parallelStream()
            .map(ImporterService::createVoivodeshipDocument)
            .collect(Collectors.toList());

        mongoDatabase.getCollection("voivodeships").insertMany(documents);
        return voivodeships;
      }
    }

    return Collections.emptyList();
  }

  public List<County> createCounties(String countiesJSON) {
    Optional<Counties> optionalCounties =
        Optional.ofNullable(gson.fromJson(countiesJSON, Counties.class));

    if (optionalCounties.isPresent()) {
      List<County> counties = optionalCounties.get().getCounties();

      if (CollectionUtils.isNotEmpty(counties)) {
        List<Document> documents = counties
            .parallelStream()
            .map(ImporterService::createCountyDocument)
            .collect(Collectors.toList());

        mongoDatabase.getCollection("counties").insertMany(documents);
        return counties;
      }
    }

    return Collections.emptyList();
  }

  public List<Locality> createLocalities(String localitiesJSON) {
    Optional<Localities> optionalLocalities =
        Optional.ofNullable(gson.fromJson(localitiesJSON, Localities.class));

    if (optionalLocalities.isPresent()) {
      List<Locality> localities = optionalLocalities.get().getLocalities();

      if (CollectionUtils.isNotEmpty(localities)) {
        List<Document> documents = localities
            .parallelStream()
            .map(ImporterService::createLocalityDocument)
            .collect(Collectors.toList());

        mongoDatabase.getCollection("localities").insertMany(documents);
        return localities;
      }
    }

    return Collections.emptyList();
  }

  private static Document createVoivodeshipDocument(Voivodeship voivodeship) {
    Document document = new Document();
    document.put("_id", voivodeship.getId());
    document.put("name", voivodeship.getName());
    return document;
  }

  private static Document createCountyDocument(County county) {
    Document document = new Document();
    document.put("_id", county.getId());
    document.put("name", county.getName());
    document.put("voivodeshipId", county.getVoivodeshipId());
    return document;
  }

  private static Document createLocalityDocument(Locality locality) {
    Document document = new Document();
    document.put("_id", locality.getId());
    document.put("name", locality.getName());
    document.put("countyId", locality.getCountyId());
    document.put("distance", locality.getDistance());
    document.put("longitute", locality.getLongitude());
    document.put("latitude", locality.getLatitude());
    return document;
  }
}
