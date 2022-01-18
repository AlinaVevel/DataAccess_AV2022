import java.time.LocalDate;

class ArtWork {
    protected String title;
    protected Author author;
    LocalDate startDate;

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public ArtWork(String title, Author author, LocalDate startDate) {
        setStartDate(startDate);
        setTitle(title);
        setAuthor(author);

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }


}
