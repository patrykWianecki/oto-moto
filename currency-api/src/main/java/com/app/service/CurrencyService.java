package com.app.service;

import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.app.model.Currency;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class CurrencyService {

  @Value("${exchange-api-url}")
  private String EXCHANGE_API_URL;

  public Currency findCurrentExchange() throws URISyntaxException, InterruptedException {
    CountDownLatch countDownLatch = new CountDownLatch(1);
    final List<Currency> currency = new ArrayList<>();

    createResponse().thenAccept(response -> {
          Gson gson = new GsonBuilder().setPrettyPrinting().create();
          currency.add(gson.fromJson(response.body(), Currency.class));
          countDownLatch.countDown();
        }
    );
    countDownLatch.await();

    return currency
        .stream()
        .findFirst()
        .orElseThrow(() -> new NullPointerException("Failed to get actual currency"));
  }

  private HttpRequest createGetRequest() throws URISyntaxException {
    return HttpRequest.newBuilder()
        .uri(new URI(EXCHANGE_API_URL))
        .version(Version.HTTP_2)
        .timeout(Duration.ofSeconds(10))
        .GET()
        .build();
  }

  private CompletableFuture<HttpResponse<String>> createResponse() throws URISyntaxException {
    return HttpClient.newBuilder()
        .proxy(ProxySelector.getDefault())
        .build()
        .sendAsync(createGetRequest(), HttpResponse.BodyHandlers.ofString());
  }
}
