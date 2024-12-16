package com.erikrz.hackerrank.solutions.introduction.welcometojava;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Solution {

    private static final String BASE_URL = "https://jsonmock.hackerrank.com/api/tvseries?page=";

    private static final Gson gson = new Gson();

    private static final Comparator<TVSeries> TV_SERIES_COMPARATOR = Comparator.comparing(TVSeries::getImdb_rating).reversed().thenComparing(TVSeries::getName);

    public static void main(String[] args) {
        /* Enter your code here. Print output to STDOUT. Your class should be named Solution. */
        System.out.println("Hello, World.");
        System.out.println("Hello, Java.");

    }

    public static String bestInGenre(String genre) {
        // Write your code here
        return getAllTvShows().stream()
                .filter(tvSeries -> Objects.nonNull(tvSeries.getGenre()) && tvSeries.getGenre().contains(genre))
                .sorted(TV_SERIES_COMPARATOR)
                .map(TVSeries::getName)
                .findFirst()
                .orElse("");
    }


    private static List<TVSeries> getAllTvShows(){
        List<TVSeries> allTvShows = new ArrayList<>();
        var currentPage = 0L;
        var totalPages = 1L;
        do{
            currentPage++;
            var request = buildHttpRequest(currentPage);
            var response = execute(request);
            TvSeriesPage page = gson.fromJson(response.body(), TvSeriesPage.class);
            totalPages = page.getTotal_pages();
            allTvShows.addAll(page.getData());
        } while (currentPage <totalPages);
        return allTvShows;
    }


    private static HttpRequest buildHttpRequest(long page) {
        try {
            return HttpRequest.newBuilder()
                    .uri(new URI(BASE_URL + page))
                    .GET()
                    .build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private static HttpResponse<String> execute(HttpRequest request)  {
        try {
            return HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static class TvSeriesPage {
        private long page;
        private long per_page;
        private long total;
        private long total_pages;
        private List<TVSeries> data;

        public long getPage() {
            return page;
        }

        public void setPage(long page) {
            this.page = page;
        }

        public List<TVSeries> getData() {
            return data;
        }

        public void setData(List<TVSeries> data) {
            this.data = data;
        }

        public long getTotal_pages() {
            return total_pages;
        }

        public void setTotal_pages(long total_pages) {
            this.total_pages = total_pages;
        }

        public long getTotal() {
            return total;
        }

        public void setTotal(long total) {
            this.total = total;
        }

        public long getPer_page() {
            return per_page;
        }

        public void setPer_page(long per_page) {
            this.per_page = per_page;
        }
    }

    private static class TVSeries {
        private String name;
        private String genre;
        private double imdb_rating;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getImdb_rating() {
            return imdb_rating;
        }

        public void setImdb_rating(double imdb_rating) {
            this.imdb_rating = imdb_rating;
        }

        public String getGenre() {
            return genre;
        }

        public void setGenre(String genre) {
            this.genre = genre;
        }
    }
}