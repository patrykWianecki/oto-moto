package com.app.service;

import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

import org.springframework.stereotype.Service;

import com.app.model.Currency;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class CurrencyService {

  private final static String PATH = "https://api.exchangeratesapi.io/latest?base=PLN";

  public Currency findCurrentExchange() throws URISyntaxException, InterruptedException {
    CountDownLatch countDownLatch = new CountDownLatch(1);
    final List<Currency> currency = new java.util.ArrayList<>();

    createResponse().thenAccept(response -> {
          Gson gson = new GsonBuilder().setPrettyPrinting().create();
          currency.add(gson.fromJson(response.body(), Currency.class));
          countDownLatch.countDown();
        }
    );
    countDownLatch.await();

    return currency.get(0);
  }

  private static HttpRequest createGetRequest() throws URISyntaxException {
    return HttpRequest.newBuilder()
        .uri(new URI(PATH))
        .version(Version.HTTP_2)
        .timeout(Duration.ofSeconds(10))
        .GET()
        .build();
  }

  private static CompletableFuture<HttpResponse<String>> createResponse() throws URISyntaxException {
    return HttpClient.newBuilder()
        .proxy(ProxySelector.getDefault())
        .build()
        .sendAsync(createGetRequest(), HttpResponse.BodyHandlers.ofString());
  }
}
