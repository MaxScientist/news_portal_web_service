package com.example.resource;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Scanner;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class StepDefinition {
    private static final String CREATE_PATH = "/news/create";
    private static final String APPLICATION_JSON = "application/json";


    private final InputStream jsonInputStream = this.getClass().getClassLoader().getResourceAsStream("article.json");
    private final String jsonString = new Scanner(Objects.requireNonNull(jsonInputStream), "UTF-8").useDelimiter("\\Z").next();

    private final WireMockServer wireMockServer = new WireMockServer(options().dynamicPort());
    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    @When("News is uploaded")
    public void userUploadArticle() throws IOException {
        wireMockServer.start();

        configureFor("localhost", wireMockServer.port());

        stubFor(post(urlEqualTo(CREATE_PATH))
                .withHeader("content-type", equalTo(APPLICATION_JSON))
                .withRequestBody(containing("title"))
                .withRequestBody(containing("contentOfNews"))
                .withRequestBody(containing("shortDescription"))
                .willReturn(aResponse().withStatus(200)));

        HttpPost request = new HttpPost("http://localhost:" + wireMockServer.port() + "/" + "news/" + "create");
        StringEntity entity = new StringEntity(jsonString);
        request.addHeader("content-type", APPLICATION_JSON);
        request.setEntity(entity);
        HttpResponse response = httpClient.execute(request);

        assertEquals(200, response.getStatusLine().getStatusCode());
        verify(postRequestedFor(urlEqualTo(CREATE_PATH))
                .withHeader("content-type", equalTo(APPLICATION_JSON)));

        wireMockServer.stop();
    }

    @When("News retrieval from id {long}")
    public void userWantInformationOnArticleById(long id) throws IOException {
        wireMockServer.start();

        configureFor("localhost", wireMockServer.port());
        stubFor(get(urlEqualTo("/news/1")).withHeader("accept", equalTo(APPLICATION_JSON))
                .willReturn(aResponse().withBody(jsonString)));

        HttpGet request = new HttpGet("http://localhost:" + wireMockServer.port() + "/" + "news/" + id);
        request.addHeader("accept", APPLICATION_JSON);
        HttpResponse httpResponse = httpClient.execute(request);
        String response = convertResponseToString(httpResponse);

        assertThat(response, containsString(jsonString));
//        assertThat(response, containsString("\"content\":\"fergme\""));
//        assertThat(response, containsString("\"onfeofre\":\"erpgkepr\""));

        wireMockServer.stop();
    }

    @When("News is deleted by id {long}")
    public void newsDeletionById(long id) throws IOException {
        wireMockServer.start();

        configureFor("localhost", wireMockServer.port());
//        stubFor(get(urlEqualTo("/news/1")).withHeader("accept", equalTo(APPLICATION_JSON))
//                .willReturn(aResponse().withBody(jsonString)));

        HttpDelete request = new HttpDelete("http://localhost:" + wireMockServer.port() + "/" + "news/" + id);
        request.addHeader("accept", APPLICATION_JSON);
        HttpResponse httpResponse = httpClient.execute(request);
//        String response = convertResponseToString(httpResponse);

//        assertThat(response, );
//        assertThat(response, containsString("\"content\":\"fergme\""));
//        assertThat(response, containsString("\"onfeofre\":\"erpgkepr\""));

        wireMockServer.stop();
    }

    @When("All news retrieval")
    public void allNewsRetrieval() throws IOException {
        wireMockServer.start();

        configureFor("localhost", wireMockServer.port());
        stubFor(get(urlEqualTo("/news/allNews")).withHeader("accept", equalTo(APPLICATION_JSON))
                .willReturn(aResponse().withBody(jsonString)));

        HttpGet request = new HttpGet("http://localhost:" + wireMockServer.port() + "/" + "news/allNews");
        request.addHeader("accept", APPLICATION_JSON);
        HttpResponse httpResponse = httpClient.execute(request);
        String response = convertResponseToString(httpResponse);

        assertThat(response, containsString(jsonString));
//        assertThat(response, containsString("\"content\":\"fergme\""));
//        assertThat(response, containsString("\"onfeofre\":\"erpgkepr\""));

        wireMockServer.stop();
    }

    @Then("server should handle & return status OK")
    public void theServerShouldReturnASuccessStatus() {
    }

    @Then("requested data returned")
    public void requestedDataIReturned() {
    }

    private String convertResponseToString(HttpResponse httpClient) throws IOException {
        InputStream response = httpClient.getEntity().getContent();
        try (Scanner scanner = new Scanner(response, "UTF-8")) {
            return scanner.useDelimiter("//Z").next();
        }
    }
}
