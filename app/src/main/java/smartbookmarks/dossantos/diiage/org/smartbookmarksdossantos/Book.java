package smartbookmarks.dossantos.diiage.org.smartbookmarksdossantos;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Quentin on 05/04/2018.
 */

public class Book implements Serializable {
    Long id;
    String title;
    String author;
    String genre;
    ArrayList<Comment> comments;
    Long commentsCount;

    public Book(){}

    public Book(Long id, String title, String author, String genre, ArrayList<Comment> comments) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.comments = comments;
    }

    public Book(Long id, String title, String author, String genre, ArrayList<Comment> comments, Long commentsCount) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.comments = comments;
        this.commentsCount = commentsCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public Long getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(Long commentsCount) {
        this.commentsCount = commentsCount;
    }
}