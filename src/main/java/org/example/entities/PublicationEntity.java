package org.example.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "publication", schema = "csd214_jpademo_f24", catalog = "")
public class PublicationEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "price")
    private Double price;
    @Basic
    @Column(name = "copies")
    private Integer copies;
    @Basic
    @Column(name = "ISBN10")
    private String isbn10;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublicationEntity that = (PublicationEntity) o;
        return id == that.id && Objects.equals(title, that.title) && Objects.equals(price, that.price) && Objects.equals(copies, that.copies) && Objects.equals(isbn10, that.isbn10);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, price, copies, isbn10);
    }
}
