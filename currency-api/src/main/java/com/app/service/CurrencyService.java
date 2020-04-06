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
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.app.model.Currency;
import com.app.model.Rate;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import static org.apache.commons.lang3.StringUtils.*;

/**
 * This is service class which captures actual value of most popular curriencies,
 * map them to {@link com.app.model.Currency} class and prepares request.
 *
 * @author Patryk Wianecki
 */
@Service
public class CurrencyService {

  private static final long TIMEOUT_IN_SECONDS = 10L;

  @Value("${exchange-api-url}")
  private String EXCHANGE_API_URL;

  /**
   * Returns Currency object, which contains informations about base, date and rates.
   * <p>
   * Request param is one of currencies, e.g. PLN, USD, EUR.
   * If request param is not passed, returns by default information about EUR (Euro) currency.
   *
   * @return the {@link com.app.model.Currency} which contains information about
   * date of exchanged rate and current rates.
   * @throws URISyntaxException   if URI violates RFC&nbsp;2396
   * @throws InterruptedException if the current thread is interrupted while waiting
   */
  public Currency findCurrentExchange(String base) throws URISyntaxException, InterruptedException {
    CountDownLatch countDownLatch = new CountDownLatch(1);
    final List<Currency> currencies = new ArrayList<>();

    createResponse(base).thenAccept(response -> {
          Gson gson = new GsonBuilder().setPrettyPrinting().create();
          currencies.add(gson.fromJson(response.body(), Currency.class));
          countDownLatch.countDown();
        }
    );
    countDownLatch.await();

    if (currencies.isEmpty()) {
      throw new NullPointerException("Failed to get actual currency");
    }

    Currency currency = currencies.get(0);
    validateRequest(currency);

    return currency;
  }

  private CompletableFuture<HttpResponse<String>> createResponse(
      String base) throws URISyntaxException {
    return HttpClient.newBuilder()
        .proxy(ProxySelector.getDefault())
        .build()
        .sendAsync(createGetRequest(base), HttpResponse.BodyHandlers.ofString());
  }

  private HttpRequest createGetRequest(String base) throws URISyntaxException {
    String url = isEmpty(base) ? EXCHANGE_API_URL : createURLWithParam(base);
    return HttpRequest.newBuilder()
        .uri(new URI(url))
        .version(Version.HTTP_2)
        .timeout(Duration.ofSeconds(TIMEOUT_IN_SECONDS))
        .GET()
        .build();
  }

  private String createURLWithParam(String base) {
    return EXCHANGE_API_URL + "?base=" + base;
  }

  private static void validateRequest(Currency currency) {
    Optional<String> base = Optional.ofNullable(currency.getBase());
    Optional<String> date = Optional.ofNullable(currency.getDate());
    Optional<Rate> rates = Optional.ofNullable(currency.getRates());

    if (base.isEmpty() && date.isEmpty() && rates.isEmpty()) {
      throw new IllegalArgumentException("");
    }
  }
}
