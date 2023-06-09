package com.BookMyShow.Its.a.movie.booking.application.repository;

import com.BookMyShow.Its.a.movie.booking.application.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public class MovieRepository {
    @Autowired
    MongoTemplate mongoTemplate;

    public Movie save(Movie movie){
         mongoTemplate.save(movie);
         return movie;
    }
    public List<Movie> findMovie(){

      return mongoTemplate.findAll(Movie.class);
    }
    public Movie findMovieById(String movieId){

        return mongoTemplate.findById(movieId,Movie.class);

    }
    public Movie findMovieByName(String movieName){
        Query query=new Query();
        query.addCriteria(Criteria.where("movieName").is(movieName));
        Movie movie=mongoTemplate.findOne(query, Movie.class);
        return movie;
    }
    public Movie findMovieByDuration(double duration){
        Query query=new Query();
        query.addCriteria(Criteria.where("duration").gt(duration));
        Movie movie=mongoTemplate.findOne(query, Movie.class);
        return movie;
    }
    public void deleteById(String movieId){
        Query query=new Query();
        query.addCriteria(Criteria.where("movieId").is(movieId));
        mongoTemplate.remove(query, Movie.class);

    }
    public Movie findMovieByRating(int rating){
        Query query=new Query();
        query.addCriteria(Criteria.where("rating").is(rating));
        Movie movie=mongoTemplate.findOne(query,Movie.class);
        return movie;
    }
    public List<Movie>findByTheatreId(String theatreId){
        Query query=new Query();
        query.addCriteria(Criteria.where("theatreId").is(theatreId));
        return mongoTemplate.find(query, Movie.class);
    }


}
