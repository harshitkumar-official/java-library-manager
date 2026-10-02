public record Book(int id, String title, String author) {
    @Override public String toString() { return "#" + id + " " + title + " by " + author; }
}
